import { HttpClient } from '@angular/common/http';
import { ACL, ACLCondition, ResourceAccessKey } from '@libs/security';
import { firstValueFrom } from 'rxjs';


type ACLConfiguration = Record<ResourceAccessKey, ACLCondition | string[]>;

export const AclFactory = async (http: HttpClient): Promise<ACL> => {
  try {
    const conf = await firstValueFrom(http.get<ACLConfiguration>('assets/configs/acl.config.json'));
    const acl: ACL = new Map<ResourceAccessKey, ACLCondition>();
    Object.entries(conf).forEach(([key, value]) => {
      let condition: ACLCondition;
      if (Array.isArray(value)) {
        condition = {
          check: 'ALL',
          roles: value,
        };
      } else {
        condition = value;
      }
      acl.set(key, condition);
    });
    return acl;
  } catch (e) {
    console.error(`ACL configuration has encountered an error. ACL will work in restricted mode.`);
    console.error(e);
    return new Map<ResourceAccessKey, ACLCondition>();
  }
};
