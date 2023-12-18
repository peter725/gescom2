export type GeneratedCode = {
  code: string;
  description: string;
  // Ampliar si hace falta
  // steps: [{
  //   step: 'PROCESS',
  //   action: 'Preserving with preserving additives',
  // }]
};

export type SearchForm = {
  search: string | null;
  restrictHierarchy: boolean;
};

export type CatDialogData = {
  catalogueId: number;
  hierarchyId: number;
  title?: string;
  descriptions?: string[];
  previousCode?: GeneratedCode;
}

export type CatDialogResult = {
  applied: boolean;
  cancelled: boolean;
  code?: GeneratedCode | undefined;
}
