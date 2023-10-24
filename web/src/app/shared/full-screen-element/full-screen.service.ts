import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

const SLIM_SCROLLBAR = 'slim__scrollbar';

@Injectable({ providedIn: 'root' })
export class FullScreenService {

  private readonly currentStatus$ = new BehaviorSubject({ enabled: false });
  private readonly isSupported = !!document.documentElement.requestFullscreen;

  private activeElm: Element | null = null;

  constructor() {
    this.configureEventListeners();
  }

  get status$() {
    return this.currentStatus$.asObservable();
  }

  show(selector: string) {
    this.activeElm = document.querySelector(selector);
    this.enableFullScreen();
  }

  close() {
    this.exitFullScreen();
    this.activeElm = null;
  }

  private updateFullScreenStatus() {
    // document active element may not be the same as the selected one,
    // but it does mean that something is full screen
    const activeElement = document.fullscreenElement;
    const isEnabled = document.fullscreenEnabled;

    const enabled = !!activeElement && isEnabled;
    this.currentStatus$.next({ enabled });
  }

  private async enableFullScreen() {
    if (this.isSupported && this.activeElm) {
      this.activeElm.classList.add(SLIM_SCROLLBAR);
      await this.activeElm.requestFullscreen();
    }
  }

  private async exitFullScreen() {
    try {
      await document.exitFullscreen();
      if (this.activeElm) {
        this.activeElm.classList.remove(SLIM_SCROLLBAR);
        this.activeElm = null;
      }
    } catch (e) {
      // Ignored
    }
  }

  private configureEventListeners() {
    document.addEventListener('fullscreenchange', () => this.updateFullScreenStatus());
  }
}
