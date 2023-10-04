import { BehaviorSubject, take } from 'rxjs';
import { map } from 'rxjs/operators';

/**
 * Accepted component control.
 *
 * IDLE    - Component is expecting user interaction.
 * LOAD    - Component is loading data and configurations
 * PROCESS - A process is ongoing and user must wait for it to complete.
 *           They must wait for its completion in order to be able to
 *           interact with data. Some cases should allow to cancel the
 *           process.
 *           Should display an animation or other kind of interactive elm.
 * ERROR   - Component has encountered an error. Acts like idle, but
 *           should display some message or notification.
 */
export type BaseComponentStates = 'IDLE' | 'PROCESS' | 'LOAD' | 'ERROR';

/**
 * Provides a way to control the current main component status.
 */
export class ComponentStatus<T = BaseComponentStates> {
  private readonly state$: BehaviorSubject<T>;

  constructor(initValue: T) {
    this.state$ = new BehaviorSubject<T>(initValue);
  }

  is$(...value: T[]) {
    return this.status$.pipe(
      take(1),
      map(v => value.includes(v))
    );
  }

  isNot$(...value: T[]) {
    return this.status$.pipe(
      take(1),
      map(v => !value.includes(v))
    );
  }

  is(...value: T[]) {
    return value.includes(this.status);
  }

  isNot(...value: T[]) {
    return !value.includes(this.status);
  }

  get status$() {
    return this.state$.asObservable();
  }

  get status() {
    return this.state$.value;
  }

  set status(status: T) {
    if (this.status !== status) this.state$.next(status);
  }
}
