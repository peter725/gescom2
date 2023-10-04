import { ConfirmActionType, ConfirmationPromptBtn } from './models';


export const GENERIC_CONFIRMATION_PROMPT = 'text.prompt.completeAction';

export const CANCEL_PROMPT_ACTION: ConfirmationPromptBtn = {
  text: 'generic.actions.cancel',
  type: ConfirmActionType.CANCEL,
};

export const CONFIRM_PROMPT_ACTION: ConfirmationPromptBtn = {
  text: 'generic.actions.yes',
  type: ConfirmActionType.CONFIRM,
};

export const REJECT_PROMPT_ACTION: ConfirmationPromptBtn = {
  text: 'generic.actions.no',
  type: ConfirmActionType.REJECT,
};

export const DEFAULT_PROMPT_ACTIONS: ConfirmationPromptBtn[] = [
  { ...REJECT_PROMPT_ACTION },
  { ...CONFIRM_PROMPT_ACTION },
];
