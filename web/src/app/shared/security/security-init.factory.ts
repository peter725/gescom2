import { AuthManagerService } from './auth-manager.service';

/**
 * Builds a factory that initializes an authentication status check and completes any
 * remaining processes.
 */
export const SecurityInitFactory = (manager: AuthManagerService) => () => manager.initAuthentication();
