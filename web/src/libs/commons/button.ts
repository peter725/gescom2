import { BaseComponentStates } from './status';


export type BtnStatus = BaseComponentStates | 'SUCCESS';

export interface BtnDef {
  text: string;
  textParams: Record<string, any> | undefined;

  altText: string;
  altTextParams: Record<string, any> | undefined;

  icon: string | undefined;
  compact: boolean;

  state: BtnStatus | undefined;

  handler: () => void;
}

export interface LinkBtnDef extends BtnDef {
  link: string | string[];
  linkParams: Record<string, any> | undefined;
}

export type BtnSrc = Partial<BtnDef>;
export type LinkBtnSrc = Partial<LinkBtnDef>;

export const btnBuilder = (src: BtnSrc): BtnDef => {
  const text = src.text || '';
  const handler = () => {
  };
  return {
    text,
    textParams: src.textParams,
    altText: src.altText || text,
    altTextParams: src.altTextParams,
    icon: src.icon,
    compact: src.compact || false,
    state: src.state,
    handler: src.handler || handler,
  };
};

export const linkBtnBuilder = (src: LinkBtnSrc): LinkBtnDef => {
  const btn: Partial<LinkBtnDef> = btnBuilder(src);
  btn.link = src.link || '';
  btn.linkParams = src.linkParams;
  return btn as LinkBtnDef;
};
