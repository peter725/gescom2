import { GEModule } from '@libs/sdk/module';

export interface UserSignInRequest {
  docNum?: string; // Opcional
  processReturnUrl: string;
}

export interface UserSignInPetition {
  petitionId: string;
  relayId: string;
  tempToken: string;
  authData: ClaveRequestFormData;
}

export interface ClaveRequestFormData {
  authUrl: string;
  relayId: string;
  authParams: Record<string, string>;
}

export interface AuthDataResponse {
  access_token: string;
  expires_in: number;
  refresh_token:string;
  token_type:string;
}

export interface AuthUserDetails {
  userId: number;
  docNum: string;
  firstName: string;
  firstSurname: string;
  secondSurname?: string;

  profile: string;
  modules: GEModule[];
}
