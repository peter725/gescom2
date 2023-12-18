/**
 * Values definition.
 * ALL: All roles must be present in order to allow access
 * ANY: At least one role must be present to allow access
 */
export type ACLCheckMode = 'ALL' | 'ANY';

export type ACLCondition = {
  check: ACLCheckMode;
  roles: string[];
};

/**
 * Alias for an application resource key.
 */
export type ResourceAccessKey = string;

/**
 * A map of application resources and their access conditions.
 */
export type ACL = Map<ResourceAccessKey, ACLCondition>;

export type AuthorizationConfig = {
  redirect: {
    unauthenticated: string;
    unauthorized: string;
  };
};
