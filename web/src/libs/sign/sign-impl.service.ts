import {Injectable} from '@angular/core';

import {SignOptions} from './models';


@Injectable({providedIn: 'root'})
export class SignImplService {

  numSerieCertHex = '';

  private static loadAutoFirma() {
    AutoScript.cargarAppAfirma();
    AutoScript.setStickySignatory(true);
  }

  public sign(options: SignOptions): Promise<string> {
    SignImplService.loadAutoFirma();
    //var params="headless=true";
    let params = '';
    if (this.numSerieCertHex != '')
      params = "filter=ssl:" + this.numSerieCertHex;
    else
      params = "filters=signingCert";
    return new Promise((resolve, reject) => {
      if (options.isFile) {
        AutoScript.sign(options.base64, "SHA256withRSA", "PAdES", params, resolve, (type: any, message: string) => {
          reject(this.signError(type, message))
        });
      } else {
        AutoScript.sign(options.base64, "SHA256withRSA", "CAdES", params, resolve, (type: any, message: string) => {
          reject(this.signError(type, message))
        });
      }
    })

  }

  signError(type: any, message: string): string {
    let mensaje: string;
    //Mostramos un mensaje con el resultado de la operaci&oacute;n
    if (message != null && message.indexOf("cancelado") != -1) {
      mensaje = 'text.sign.error.cancel';
    } else if (message != null && message.indexOf("Operacion de seleccion de certificado cancelada") != -1) {
      mensaje = 'text.sign.error.select';
    } else if (message != null && message.indexOf("conectar") != -1 && message.indexOf("AutoFirma") != -1) {
      mensaje = 'text.sign.error.connect';
    } else {
      mensaje = 'text.sign.error.general';
    }
    return mensaje || message;
  }


}
