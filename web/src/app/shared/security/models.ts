export type AuthProcessState = 'INIT' | 'PROCESS' | 'ERROR' | 'DONE';

export enum AuthProcessResultParam {
  STATUS_PARAM = 'login_status',
  STATUS_CODE = 'login_status_code',
  STATUS_MESSAGE = 'login_message',
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
