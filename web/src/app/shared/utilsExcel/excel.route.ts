import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class EstadisticaResolve {
    constructor(
    ) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    
        return route.params['tipo'] ? route.params['tipo'] : null;
    }
}
