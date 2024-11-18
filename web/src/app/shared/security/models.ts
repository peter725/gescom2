export type AuthProcessState = 'INIT' | 'PROCESS' | 'ERROR' | 'DONE';

export enum AuthProcessResultParam {
  AUTH_ACTION = 'auth_action',
  STATUS_PARAM = 'auth_status',
  AUTH_CODE = 'auth_code',
  STATUS_MESSAGE = 'auth_message',
}

export enum AuthProcessAction {
  SIGN_IN = "sign_in",
  SIGN_OUT = "sign_out",
  REGISTER = "register"
}

export enum AuthProcessResultValues {
  OK = 'OK',
  KO = 'KO',
}