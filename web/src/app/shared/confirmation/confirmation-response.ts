import { filter, Observable, Subject } from 'rxjs';
import { CANCEL_PROMPT_ACTION } from './constants';
import { ConfirmActionType, ConfirmationPromptBtn } from './models';

export class ConfirmationResp {
  private readonly resolution = new Subject<ConfirmationPromptBtn>();

  constructor() {
  }

  resolve(action: ConfirmationPromptBtn | undefined) {
    if (!action) {
      action = { ...CANCEL_PROMPT_ACTION };
    }

    this.resolution.next(action);
    this.resolution.complete();
    if (action.handler) action.handler();
  }

  resolved(): Observable<ConfirmationPromptBtn> {
    return this.resolution.asObservable();
  }

  isConfirmed() {
    return this.resolved().pipe(filter(({ type }) => type === ConfirmActionType.CONFIRM));
  }

  isCancelled() {
    return this.resolved().pipe(filter(({ type }) => type === ConfirmActionType.CANCEL));
  }

  isRejected() {
    return this.resolved().pipe(filter(({ type }) => type === ConfirmActionType.REJECT));
  }
}
