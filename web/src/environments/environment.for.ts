import { EnvironmentData } from './models';


export const environment: EnvironmentData = {
  name: 'for',
  production: true,
  baseHref: '/gescom',
  srv: {
    app: {
      host: 'https://gescom-for-jee-r01a-ip-vs-1.msc.es/rest',
      api: '/api/v1',
    },
    auth: {
      host: 'https://gescom-for-jee-r01a-ip-vs-1.msc.es/rest',
      api: '/api/v1',
      username: 'GESCOM',
      password: 'GESCOM'
    },
  },
};
