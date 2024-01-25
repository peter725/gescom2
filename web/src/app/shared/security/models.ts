export type AuthProcessState = 'INIT' | 'PROCESS' | 'ERROR' | 'DONE';

export enum AuthProcessResultParam {
  AUTH_ACTION = 'auth_action',
  STATUS_PARAM = 'auth_status',
  AUTH_CODE = 'auth_code',
  STATUS_MESSAGE = 'auth_message'
}

export enum AuthProcessResultValues {
  OK = 'OK',
  KO = 'KO',
}

export type TempAuthData = {
  petitionId: string;
  relayId: string;
  tempToken: string;
};
