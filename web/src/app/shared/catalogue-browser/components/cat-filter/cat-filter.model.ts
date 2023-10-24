import { TulsaCatalogue, TulsaCatalogueHierarchy } from '@tulsa/libs/sdk/catalogue';

export interface CatFilterModel {
  catalogue: TulsaCatalogue | null;
  hierarchy: TulsaCatalogueHierarchy | null;
}
