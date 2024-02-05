
/**
 * Alias for an application resource key.
 */
export type ResourceAccessKey = string;

export type AuthorizationConfig = {
  redirect: {
    unauthenticated: string;
    unauthorized: string;
  };
};
