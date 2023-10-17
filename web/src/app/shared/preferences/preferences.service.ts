import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { DEFAULT_PREFERENCES, PREFERENCES_STORAGE_KEY } from './constants';
import { UserPreferences } from './model';


@Injectable({ providedIn: 'root' })
export class PreferencesService {
  private readonly preferences = new BehaviorSubject<UserPreferences>(DEFAULT_PREFERENCES);

  constructor() {
    this.loadPrevious();
  }

  get current() {
    return { ...this.preferences.value };
  }

  get preferences$() {
    return this.preferences.asObservable();
  }

  update(preferences: Partial<UserPreferences>) {
    this.setPreferences(preferences);
  }

  private loadPrevious() {
    const preferencesStr = localStorage.getItem(PREFERENCES_STORAGE_KEY);
    if (!preferencesStr) {
      return;
    }

    try {
      const pref = JSON.parse(preferencesStr) as UserPreferences;
      this.setPreferences(pref);
    } catch (e) {
      this.setPreferences(DEFAULT_PREFERENCES);
    }
  }

  private setPreferences(preferences: Partial<UserPreferences>) {
    const update = { ...DEFAULT_PREFERENCES, ...preferences } as UserPreferences;
    this.preferences.next(update);
    localStorage.setItem(PREFERENCES_STORAGE_KEY, JSON.stringify(update));
  }
}
