export type HiddenFormData = {
  action: string;
  method: 'GET' | 'POST';
  params: { key: string, value: string }[];
};
