// // This file can be replaced during build by using the `fileReplacements` array.
// // `ng build` replaces `environment.ts` with `environment.prod.ts`.
// // The list of file replacements can be found in `angular.json`.

// import {EnvironmentData} from './models';


// export const environment: EnvironmentData = {
//   name: 'local',
//   production: false,
//   baseHref: '',
//   srv: {
//     app: {
//       host: 'http://localhost:8080/gescom-rest-app',
//       api: '/api/v1',
//     },
//     auth: {
//       host: 'http://localhost:8080/gescom-auth-app',
//       api: '/api/v1',
//       username:'GESCOM',
//       password:'GESCOM'
//     },
//   },
// };

// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

import {EnvironmentData} from './models';


export const environment: EnvironmentData = {
  name: 'local',
  production: false,
  baseHref: '',
  srv: {
    app: {
      host: 'http://localhost:8080/gescom-rest-app',
      //host: 'http://micapps01des.consumo.gob.es:8080/gescom-rest-app',
      //host: 'http://micapps01pre.consumo.gob.es:8080/gescom-rest-app',
      api: '/api/v1',
    },
    auth: {
      host: 'http://localhost:8080/gescom-auth-app',
      //host: 'http://micapps01des.consumo.gob.es:8080/gescom-auth-app',
      //host: 'http://micapps01pre.consumo.gob.es:8080/gescom-auth-app',
      api: '/api/v1',
      username:'GESCOM',
      password:'GESCOM'
    },
  },
};
