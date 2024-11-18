import { BaseModel, SimpleModel, TranslatedModel } from './common';


export interface Catalogue extends SimpleModel {
  acceptNonStandardCodes: number;
  code: string;
  dcfType: string;
  deprecated: boolean;
  generateMissingCodes: number;
  groups: string;
  IdOrigen: number;
  idType: number;
  label: string;
  lastUpdate?: Date;
  name: string;
  scopeNote: string;
  status: string;
  termCodeLength: number;
  termCodeMask: string;
  termMinCode: string;
  validFrom: Date;
  validTo?: Date;
  version: string;
}

export interface CatalogueHierarchy extends SimpleModel {
  name: string;
}

export interface CatalogueTerm extends SimpleModel {
  code: string;
  deprecated: boolean;
  catalogueId: number;
  lastUpdate?: Date;
  status: string;
  validFrom: Date;
  validTo?: Date;
  version: string;
}

export interface CatalogueAttribute extends SimpleModel, TranslatedModel {
  catCode: string;
  code: string;
  deprecated: boolean
  catalogueId: number;
  inheritance: string;
  label: string;
  lastUpdate?: Date;
  maxLength: number;
  name: string;
  ordering: number;
  precision: number;
  reportable: string;
  scale: number;
  scopeNote: string;
  searchable: boolean;
  singleOrRepeatable: string;
  status: string;
  termCodeAlias: boolean;
  type: string;
  uniqueness: boolean;
  validFrom: Date;
  validTo: Date;
  version: string;
  visible: boolean;
}

/**
 * Data structure representing a CatalogueTerm that is considered
 * to be a starting point for the code generation.
 */
export interface CatalogueBaseTerm extends SimpleModel {
  catalogueId: number;
  idHierarchy: number;
  termId: number;
  termCode: string;
  termExtendedName: string;
  termScopeNote: string;
  termShortName: string;
  termDisplayName: string;
}

/**
 * Data structure of the combination of a term and its corresponding
 * attribute that can be applied to a BaseTerm.
 */
export interface CatalogueFacet extends BaseModel {
  idCatalogue: number;
  idHierarchy: number;
  termId: number;
  termCode: string;
  termExtendedName: string;
  termDisplayName: string;
  attributeId: number;
  attributeCode: string;
  attributeName: string;
  facetCode: string;
}

export const facetComparisonFn = (a: CatalogueFacet, b: CatalogueFacet) => {
  return a.termId === b.termId
    // && a.termCode === b.termCode
    && a.attributeId === b.attributeId
    // && a.attributeCode === b.attributeCode
    ;
};
