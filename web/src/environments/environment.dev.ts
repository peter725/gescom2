import { EnvironmentData } from './models';


export const environment: EnvironmentData = {
  name: 'dev',
  production: true,
  baseHref: '/gescom',
  srv: {
    app: {
      //host: 'https://servicios-des.consumo.gob.es/gescom-rest-app',
      host: 'https://servicios-pre.consumo.gob.es/gescom-rest-app',
      api: '/api/v1',
    },
    auth: {
      //host: 'https://servicios-des.consumo.gob.es/gescom-auth-app',
      host: 'https://servicios-pre.consumo.gob.es/gescom-auth-app',
      api: '/api/v1',
      username: 'GESCOM',
      password: 'GESCOM'
    },
  },
};
