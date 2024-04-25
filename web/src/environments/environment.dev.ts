import { EnvironmentData } from './models';


export const environment: EnvironmentData = {
  name: 'dev',
  production: true,
  baseHref: '/gescom',
  srv: {
    app: {
      //host: 'http://micapps01des.consumo.gob.es:8080/gescom-rest-app',
      host: 'http://micapps01pre.consumo.gob.es:8080/gescom-rest-app',
      api: '/api/v1',
    },
    auth: {
      //host: 'http://micapps01des.consumo.gob.es:8080/gescom-auth-app',
      host: 'http://micapps01pre.consumo.gob.es:8080/gescom-auth-app',
      api: '/api/v1',
      username: 'GESCOM',
      password: 'GESCOM'
    },
  },
};
