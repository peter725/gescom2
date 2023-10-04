import { ThemePalette } from '@angular/material/core';

export enum ConfirmActionType {
  CANCEL,
  CONFIRM,
  REJECT,
}

/**
 * Data structure used to define an action for the prompt.
 */
export interface ConfirmationPromptBtn {
  /**
   * Text shown as the button value
   */
  text: string;
  /**
   * Button type. Default is cancel.
   */
  type?: ConfirmActionType;
  // role 'cancel' | 'confirm' | 'destructive'
  /**
   * Optional icon to use alongside the text.
   */
  icon?: string;
  /**
   * Optional button color.
   */
  color?: ThemePalette;
  /**
   * Callback fn executed when the prompt is resolved.
   */
  handler?: () => void | Promise<void>;
}


/**
 * Data structure used to require a confirmation from the user.
 */
export interface ConfirmationPromptSrc {
  /**
   * Message displayed to the user.
   */
  prompt: string,
  /**
   * Optional params that may be required to display the prompt.
   */
  promptParams?: Record<string, any>;
  /**
   * Optional message to be displayed along the prompt message.
   */
  message?: string;
  /**
   * Optional params that may be required by the message.
   */
  messageParams?: Record<string, any>;
  /**
   * Optional actions to be displayed. If present this will
   * replace default values.
   */
  buttons?: ConfirmationPromptBtn[];
}

export interface ConfirmationPrompt {
  prompt: string;
  buttons: ConfirmationPromptBtn[];
  message?: string;
}

