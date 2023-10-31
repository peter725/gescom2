import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { TranslateService } from '@ngx-translate/core';
import { PreferencesService } from '@base/shared/preferences';
import { UserPreferences } from '@base/shared/preferences/model';
import { GAuthSubject } from '@base/shared/security';
import { CrudImplService, RequestConfig, SortBuilder, SortDirection } from '@libs/crud-api';
import { ModelStates } from '@libs/sdk/common';
import { Language } from '@libs/sdk/language';
import { Module } from '@libs/sdk/module';
import { SampleSeason } from '@libs/sdk/sample-season';
import { VSampleTemplate } from '@libs/sdk/sample-template';
import { ScopeView } from '@libs/sdk/scope';
import { User } from '@libs/sdk/user';
import { AuthContextService } from '@libs/security';
import { BehaviorSubject, distinctUntilChanged, filter, firstValueFrom, forkJoin, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ContextChangeLoaderComponent } from './context-change-loader/context-change-loader.component';
import { ContextChangeConfig } from './models';


/**
 * Sample context management. Controls options regarding active scopes, modules, etc.
 */
@Injectable({ providedIn: 'root' })
export class AppContextService {

    private readonly activeLanguage = new BehaviorSubject<Language | undefined>(undefined);
    private readonly activeModule = new BehaviorSubject<Module | undefined>(undefined);
    private readonly activeScope = new BehaviorSubject<ScopeView | undefined>(undefined);
    private readonly activeSeason = new BehaviorSubject<SampleSeason | undefined>(undefined);
    private readonly activeTemplate = new BehaviorSubject<VSampleTemplate | undefined>(undefined);

    // Almacena todos los datos disponibles a utilizar, filtrados
    // Existen casos donde es necesario disponer de un listado completo de valores
    // y mostrar solamente algunos de estos.
    private readonly availableLanguages = new BehaviorSubject<Language[]>([]);
    private readonly availableModules = new BehaviorSubject<Module[]>([]);
    private readonly availableScopes = new BehaviorSubject<ScopeView[]>([]);
    private readonly availableSeasons = new BehaviorSubject<SampleSeason[]>([]);
    private readonly availableTemplates = new BehaviorSubject<VSampleTemplate[]>([]);

    // Almacena todos los datos disponibles a utilizar sin filtrar
    private readonly allLoadedSeasons = new BehaviorSubject<SampleSeason[]>([]);

    private loadingAnimationRef: MatDialogRef<ContextChangeLoaderComponent> | undefined;
    private loadingAnimationTimeout: ReturnType<typeof setTimeout> | undefined;

    constructor(
        private authContext: AuthContextService<GAuthSubject>,
        private crudService: CrudImplService,
        private translateService: TranslateService,
        private preferences: PreferencesService,
        private dialog: MatDialog,
    ) {
        this.registerAuthEvents();
    }

    async initializeData() {
        await this.loadData();
        this.activateValues();
    }

    useLanguage(value: Language | undefined) {
        this.activeLanguage.next(value);
        if (value) this.translateService.use(value.isoCode);

        this.updateContextPreferences();
    }

    useModule(value: Module | undefined) {
        this.showLoadingAnimation({
            messageText: 'text.other.loadingModuleConfiguration',
            messageParams: { module: value?.name || '-' },
        });
        this.activeModule.next(value);
        this.filterAvailableSeasons();
        this.activateSeason();
        this.loadTemplates();
        this.updateContextPreferences();
    }

    useScope(value: ScopeView | undefined) {
        this.activeScope.next(value);
        this.loadTemplates();
        this.updateContextPreferences();
    }

    useSeason(value: SampleSeason | undefined) {
        this.activeSeason.next(value);
        this.updateContextPreferences();
    }

    useTemplate(value: VSampleTemplate | undefined) {
        this.activeTemplate.next(value);
    }

    async loadTemplates() {
        const templates = await firstValueFrom(this.templateLoader());
        this.availableTemplates.next(templates);
    }

    get languages$() {
        return this.availableLanguages.asObservable();
    }

    get modules$() {
        return this.availableModules.asObservable();
    }

    get seasons$() {
        return this.availableSeasons.asObservable();
    }

    get scopes$() {
        return this.availableScopes.asObservable();
    }

    get templates$() {
        return this.availableTemplates.asObservable();
    }

    get language$() {
        return this.activeLanguage.asObservable().pipe(filter(Boolean), distinctUntilChanged());
    }

    get module$() {
        return this.activeModule.asObservable().pipe(filter(Boolean), distinctUntilChanged());
    }

    get season$() {
        return this.activeSeason.asObservable().pipe(filter(Boolean), distinctUntilChanged());
    }

    get scope$() {
        return this.activeScope.asObservable().pipe(filter(Boolean), distinctUntilChanged());
    }

    get template$() {
        return this.activeTemplate.asObservable();
    }

    get language() {
        return this.activeLanguage.value;
    }

    get module() {
        return this.activeModule.value;
    }

    get season() {
        return this.activeSeason.value;
    }

    get scope() {
        return this.activeScope.value;
    }

    get template() {
        return this.activeTemplate.value;
    }

    private registerAuthEvents() {
        this.authContext.get().subscribe({
            next: s => s.isAuthenticated() ? this.initializeData() : this.clearValues(),
            error: () => this.clearValues(),
        });
    }

    private async loadData() {
        const [languages, modules, scopes] = await firstValueFrom(forkJoin([
            this.languagesLoader(),
            this.modulesLoader(),
            this.scopesLoader(),
        ]));

        const seasons = await firstValueFrom(this.seasonsLoader(modules.map(v => v.id)));

        this.availableLanguages.next(languages);
        this.availableModules.next(modules);
        this.availableScopes.next(scopes);
        this.allLoadedSeasons.next(seasons);
    }

    private languagesLoader() {
        const config: RequestConfig = {
            resourceName: 'languages',
            queryParams: {
                state: [ModelStates.ON],
            },
            pageReq: {
                unpaged: true,
                sort: [SortBuilder.from('id', SortDirection.ASC)],
            }
        };
        return this.crudService.findAll<Language>(config).pipe(
            map(page => page.content),
        );
    }

    private modulesLoader() {
        const id = this.authContext.instant().getDetails().userId;
        const config: RequestConfig = {
            resourceName: 'users',
            pathParams: { id },
        };
        return this.crudService.findById<User>(id, config).pipe(
            map(value => value.modules),
            map(list => list.filter(v => v.state === ModelStates.ON).sort((a, b) => {
                if (a.id === b.id) return 0;
                return a.id < b.id ? -1 : 1;
            })),
        );
    }

    private scopesLoader() {
        const userId = this.authContext.instant().getDetails().userId;
        const config: RequestConfig = {
            resourceName: 'scopes',
            queryParams: {
                state: [ModelStates.ON],
                userId,
            },
            pageReq: {
                unpaged: true,
                sort: [SortBuilder.from('id', SortDirection.ASC)],
            }
        };
        return this.crudService.findAll<ScopeView>(config).pipe(
            map(page => page.content)
        );
    }

    private seasonsLoader(modules: number[]) {
        const config: RequestConfig = {
            resourceName: 'sampleSeason',
            queryParams: {
                state: [ModelStates.ON],
                moduleId: modules,
                // endDateGt: new Date().toISOString(),
            },
            pageReq: {
                unpaged: true,
                sort: [SortBuilder.from('id', SortDirection.ASC)],
            }
        };
        return this.crudService.findAll<SampleSeason>(config).pipe(
            map(page => page.content)
        );
    }

    private templateLoader() {
        const moduleId = this.activeModule.value?.id;
        const scope = this.activeScope.value?.scopeCode;
        if (!moduleId || !scope) {
            return of([]);
        }

        const config: RequestConfig = {
            resourceName: 'sampleTemplate',
            queryParams: {
                sharedAccess: true,
                scope,
                moduleId,
            },
            pageReq: {
                unpaged: true,
                sort: [SortBuilder.from('ownerId', SortDirection.ASC)],
            }
        };
        return this.crudService.findAll<VSampleTemplate>(config).pipe(
            map(page => page.content),
            map(list => {
                const userId = this.authContext.instant().getDetails().userId;
                return list.sort((a, b) => {
                    if (a.ownerId === userId && b.ownerId === userId) {
                        return 0;
                    } else if (a.ownerId === userId) {
                        return -1;
                    } else {
                        return 1;
                    }
                });
            }),
        );
    }

    private activateValues() {
        const { moduleId, scopeCode, langCode } = this.preferences.current;

        const activeLang = langCode || this.translateService.currentLang || this.translateService.getDefaultLang();
        const nextLang = this.availableLanguages.value.find(v => v.isoCode === activeLang) || this.availableLanguages.value[0];
        this.activeLanguage.next(nextLang);

        const [firstModule] = this.availableModules.value;
        const nextModule = moduleId
            ? (this.availableModules.value.find(v => v.id === moduleId) || firstModule)
            : firstModule;
        this.useModule(nextModule);

        const [firstScope] = this.availableScopes.value;
        const nextScope = scopeCode
            ? (this.availableScopes.value.find(v => v.scopeCode === scopeCode) || firstScope)
            : firstScope;
        this.useScope(nextScope);
    }

    private activateSeason() {
        const { seasonId } = this.preferences.current;

        const seasons = this.availableSeasons.value;
        const [firstSeason] = seasons;
        const nextSeason = seasonId
            ? (seasons.find(v => v.id === seasonId) || firstSeason)
            : firstSeason;
        this.useSeason(nextSeason);
    }

    private filterAvailableSeasons() {
        const module = this.activeModule.value;
        const loaded = [...this.allLoadedSeasons.value];
        const next = module
            ? loaded.filter(v => v.module?.id === module.id)
            : loaded;
        this.availableSeasons.next(next);
    }

    private updateContextPreferences() {
        const next: Partial<UserPreferences> = {};

        if (this.language) {
            next.langCode = this.language.isoCode;
        }

        if (this.module) {
            next.moduleId = this.module.id;
        }

        if (this.scope) {
            next.scopeCode = this.scope.scopeCode;
        }

        if (this.season) {
            next.seasonId = this.season.id;
        }

        this.preferences.update(next);
    }

    private clearValues() {
        this.availableModules.next([]);
        this.availableScopes.next([]);
        this.availableSeasons.next([]);

        this.useModule(undefined);
        this.useScope(undefined);
        this.useSeason(undefined);

        this.preferences.update({
            moduleId: undefined,
            seasonId: undefined,
            scopeCode: undefined,
        });
    }

    private showLoadingAnimation(data: ContextChangeConfig) {
        if (!this.authContext.instant().isAuthenticated() || this.loadingAnimationRef) return;

        this.loadingAnimationRef = this.dialog.open(ContextChangeLoaderComponent, {
            maxWidth: '95vw',
            disableClose: true,
            data,
        });

        if (this.loadingAnimationTimeout) {
            clearTimeout(this.loadingAnimationTimeout);
            this.loadingAnimationTimeout = undefined;
        }

        const autoCloseAfterMs = data.autoCloseInMs || 1_500;
        this.loadingAnimationTimeout = setTimeout(() => {
            this.loadingAnimationRef?.close();
            this.loadingAnimationRef = undefined;
        }, autoCloseAfterMs);
    }
}
