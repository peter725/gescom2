import { Version } from '@angular/core';
import { environment } from '@env';

const getVersion = () => {
  let version = '0.0.4';
  if (environment.name) {
    version += '-' + environment.name;
  }
  return version;
};

export type AgencyInfo = {
  fullName: string;
  shortName?: string;
  webUrl: string;
  iconColor: string;
  iconNegative: string;
  iconPositive: string;
};

export const appName = 'Gescom';

export const version = new Version(getVersion());

export const isDevEnvironment = !environment.production;

export const government: AgencyInfo = {
  fullName: 'Gobierno de España',
  webUrl: 'https://www.lamoncloa.gob.es',
  iconColor: './assets/images/logos/logo-gob-esp.png',
  iconPositive: '',
  iconNegative: '',
};

export const ministry: AgencyInfo = {
  fullName: 'Ministerio de Consumo',
  webUrl: 'https://www.consumo.gob.es/',
  iconColor: './assets/images/logos/MDSCA.png',
  iconPositive: '',
  iconNegative: '',
};

export const prtr: AgencyInfo = {
  fullName: 'Plan de Recuperación, Transformación y Resiliencia',
  webUrl: '',
  iconColor: './assets/images/logos/prtr_color.png',
  iconPositive: '',
  iconNegative: '',
};

export const ue: AgencyInfo = {
  fullName: 'Unión Europea',
  webUrl: '',
  iconColor: './assets/images/logos/ue_nextgeneration.jpg',
  iconPositive: '',
  iconNegative: '',
};

export const dgc = {
  name: 'Dirección General de Consumo',
  shortName: 'DGC',
  web: 'https://www.consumo.gob.es/',
  icon: './assets/images/logos/MDSCA.Gob.Web-72px.jpg',
};

export const appOrigin = window.location.origin + environment.baseHref;

const {auth, app} = environment.srv;
export const gescoAppAPI = {
  srvPath: app.host,
  apiPath: app.host + app.api,
};

export const gescoAuthAPI = {
  srvPath: auth.host,
  apiPath: auth.host + auth.api,
  authorization: "Basic " + btoa(auth.username + ':' + auth.password)
};

