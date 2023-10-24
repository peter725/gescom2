import { Clipboard } from '@angular/cdk/clipboard';
import { SelectionModel } from '@angular/cdk/collections';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CrudImplService } from '@libs/crud-api';
import {
  Catalogue,
  CatalogueBaseTerm,
  CatalogueFacet,
  CatalogueHierarchy
} from '@libs/sdk/catalogue';
import { BaseModel } from '@libs/sdk/common';
import { BehaviorSubject, Subject } from 'rxjs';
import { GeneratedCode } from './models';



const FACET_CODE_SEPARATOR = '$';

const EMPTY_CODE: GeneratedCode = {
  code: '',
  description: ''
};

@Injectable({ providedIn: 'root' })
export class CatalogueBrowserService {

  private readonly _generatedCode = new BehaviorSubject<GeneratedCode>(EMPTY_CODE);

  /**
   * Data required to search for terms
   */
  private readonly _activeCatalogue = new BehaviorSubject<Catalogue | null>(null);
  private readonly _activeHierarchy = new BehaviorSubject<CatalogueHierarchy | null>(null);

  /**
   * Data required to generate codes
   */
  private readonly _activeBaseTerm = new BehaviorSubject<CatalogueBaseTerm | undefined>(undefined);
  /**
   * Active selected facets codes.
   */
  private readonly _activeFacets = new SelectionModel<string>(true, []);
  /**
   * Selected facets full data
   */
  private readonly _selectedFacets = new Map<string, CatalogueFacet>();

  private readonly _reset = new Subject<void>();

  private readonly canGenerateCode = new BehaviorSubject<boolean>(false);

  constructor(
    private crudService: CrudImplService<BaseModel>,
    private router: Router,
    private route: ActivatedRoute,
    private clipboard: Clipboard,
  ) {
  }

  useCatalogue(catalogue: Catalogue) {
    this._activeCatalogue.next(catalogue);
    // this.router.navigate([], {
    //   relativeTo: this.route,
    //   queryParamsHandling: 'merge',
    //   queryParams: { catalogue: catalogue.id },
    // });
    this.resetBaseTerm();
    this.updateGeneratedCode();
    this.updateCodeGenCondition();
  }

  useHierarchy(hierarchy?: CatalogueHierarchy) {
    this._activeHierarchy.next(hierarchy ? hierarchy : null);
    // this.router.navigate([], {
    //   relativeTo: this.route,
    //   queryParamsHandling: 'merge',
    //   queryParams: { hierarchy: hierarchy.id },
    // });
    this.updateCodeGenCondition();
  }

  createCode(baseTerm: CatalogueBaseTerm) {
    this._activeBaseTerm.next(baseTerm);
    this._activeFacets.clear();
    this._selectedFacets.clear();
    // this.router.navigate([], {
    //   relativeTo: this.route,
    //   queryParamsHandling: 'merge',
    //   queryParams: { baseTerm: baseTerm.id },
    // });
    this.updateGeneratedCode();
  }

  toggleFacet(facet: CatalogueFacet) {
    this._activeFacets.toggle(facet.facetCode);

    if (this._selectedFacets.has(facet.facetCode)) {
      this._selectedFacets.delete(facet.facetCode);
    } else {
      this._selectedFacets.set(facet.facetCode, facet);
    }
    this.updateGeneratedCode();
  }

  clearCode() {
    this._activeCatalogue.next(null);
    this._activeHierarchy.next(null);

    this.resetBaseTerm();
    this.updateCodeGenCondition();
    this.updateGeneratedCode();
    this._reset.next();
    // this.router.navigate([], {
    //   relativeTo: this.route,
    //   queryParams: {},
    // });
  }

  copyValue(type: 'code' | 'desc' | 'both') {
    const value = this._generatedCode.value;
    switch (type) {
      case 'code':
        this.clipboard.copy(value.code);
        break;
      case 'desc':
        this.clipboard.copy(value.description);
        break;
    }
  }

  get generatedCode$() {
    return this._generatedCode.asObservable();
  }

  get canGenerateCode$() {
    return this.canGenerateCode.asObservable();
  }

  get catalogue$() {
    return this._activeCatalogue.asObservable();
  }

  get catalogue() {
    return this._activeCatalogue.value;
  }

  get hierarchy$() {
    return this._activeHierarchy.asObservable();
  }

  get hierarchy() {
    return this._activeHierarchy.value;
  }

  get facets() {
    return this._activeFacets;
  }

  get reset$() {
    return this._reset.asObservable();
  }

  private registerParams() {
    this.route.queryParamMap.subscribe(params => {
      const catalogueId = params.get('catalogue');
      const hierarchyId = params.get('hierarchy');
      const baseTermId = params.get('baseTerm');

      if (catalogueId && (+catalogueId) !== this.catalogue?.id) {
        const id = +catalogueId;
        this.crudService.findById<Catalogue>(id, { resourceName: 'catalogues', pathParams: { id } }).subscribe(
          result => this._activeCatalogue.next(result),
        );
      }

      // if (hierarchyId && (+hierarchyId) !== this.hierarchy?.id) {
      //   const id = +hierarchyId;
      //   this.crudService.findById<TulsaCatalogueHierarchy>(id, {
      //     resourceName: 'catHierarchy',
      //     pathParams: { id }
      //   }).subscribe(
      //     result => this._activeHierarchy.next(result),
      //   );
      // }

      if (baseTermId && (+baseTermId) !== this._activeBaseTerm.value?.id) {
        const id = +baseTermId;
        this.crudService.findById<CatalogueBaseTerm>(id, {
          resourceName: 'catBaseTerm',
          pathParams: { id }
        }).subscribe(
          result => this._activeBaseTerm.next(result),
        );
      }
    });
  }

  private resetBaseTerm() {
    this._activeBaseTerm.next(undefined);
    this._activeFacets.clear();
    this._selectedFacets.clear();
  }

  private updateGeneratedCode() {
    const base = this._activeBaseTerm.value;
    if (!base) {
      this._generatedCode.next(EMPTY_CODE);
      return;
    }
    const facets = this._activeFacets.selected || [];

    const uniqueList = Array.from(new Set(facets));
    const value = uniqueList.reduce((acc, value, index) => {
      const codePrefix = index > 0 ? FACET_CODE_SEPARATOR : ``;
      acc.code.push(codePrefix + value);

      const facet = this._selectedFacets.get(value);
      if (facet?.attributeName) {
        const label = facet.attributeName.toUpperCase();
        const value = facet.termDisplayName;


        acc.description.push(label + ' = ' + value);
      }

      return acc;
    }, { code: [], description: [] } as { code: string[], description: string[] });

    const codes = value.code.length > 0 ? `#${ value.code.join('') }` : '';
    const code = base.termCode + codes;
    const description = [base.termDisplayName,...value.description].join(', ');

    this._generatedCode.next({
      code,
      description,
    });
  }

  private updateCodeGenCondition() {
    const next = !!this._activeCatalogue.value && !!this._activeHierarchy.value;
    if (next !== this.canGenerateCode.value) {
      this.canGenerateCode.next(next);
    }
  }

}
