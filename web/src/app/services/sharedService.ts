import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SharedService {
  private valor = new BehaviorSubject<boolean>(false);
  private convenio = new BehaviorSubject<boolean>(false);
  convenio$ = this.convenio.asObservable();
  valor$ = this.valor.asObservable();

  actualizarValor(newValue: boolean){
      this.valor.next(newValue);
  }
}
