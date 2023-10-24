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
  token: string;
  exp: number;
}

export interface AuthUserDetails {
  petitionId: string;
  relayId: string;

  userId: number;
  firstName: string;
  firstSurname: string;
  secondSurname?: string;

  profile: string;
  roles: string[];
  scopes: string[];
}
