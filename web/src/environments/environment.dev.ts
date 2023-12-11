import { EnvironmentData } from './models';


export const environment: EnvironmentData = {
  name: 'dev',
  production: true,
  baseHref: '/gescom',
  srv: {
    app: {
      host: 'http://micapps01des.consumo.gob.es:8080/gescom-rest-app',
      api: '/api/v1',
    },
    auth: {
      host: 'https://gescom-web-jee-r01a-id-vs-1.msc.es/jwtrest',
      api: '/api/v1',
    },
  },
};
