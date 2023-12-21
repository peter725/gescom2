// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
import { EnvironmentData } from './models';


export const environment: EnvironmentData = {
  name: 'local',
  production: false,
  baseHref: '',
  srv: {
    app: {
      //host: 'http://micapps01des.consumo.gob.es:8080/gescom-rest-app',
      host: 'http://localhost:8080/rest',
      api: '/api',
    },
    auth: {
      //host: 'http://localhost:8080/rest',
      host: 'http://localhost:9090/jwtrest',
      api: '/api/v1',
    },
  },
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
