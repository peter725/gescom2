import { EnvironmentData } from './models';

export const environment: EnvironmentData = {
  name: '',
  production: true,
  baseHref: '/gescom',
  srv: {
    app: {
      host: 'https://gescom-web-jee-r01a-id-vs-1.msc.es/rest',
      api: '/api/v1',
    },
    auth: {
      host: 'https://gescom-web-jee-r01a-id-vs-1.msc.es/rest',
      api: '/api/v1',
      username: '',
      password: ''
    },
  },
};
