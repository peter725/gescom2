import { EnvironmentData } from './models';


export const environment: EnvironmentData = {
  name: 'pre',
  production: true,
  baseHref: '/gescom',
  srv: {
    app: {
      host: 'https://servicios-pre.consumo.gob.es/gescom-rest-app',
      api: '/api/v1',
    },
    auth: {
      host: 'https://servicios-pre.consumo.gob.es/gescom-auth-app',
      api: '/api/v1',
      username: 'GESCOM',
      password: 'GESCOM'
    },
  },
};
