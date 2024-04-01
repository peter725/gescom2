import { Component, OnInit } from '@angular/core';
import { HiddenFormData } from '@base/shared/components/form';
import { AuthManagerService } from '@base/shared/security';


@Component({
  selector: 'ja-logout-page',
  templateUrl: './logout-page.component.html',
  styles: [`:host {
    height: 100vh;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .login__status__indicator {
    height: 7rem;
    width: 7rem;
    font-size: 7rem;
  }`],
})
export class LogoutPageComponent implements OnInit {
  formData: HiddenFormData | undefined;
  logoutComplete = false;

  constructor(private authManager: AuthManagerService) {
  }

  ngOnInit() {
    // Utilizamos los timeouts para visualizar correctamente la vista
    // no son necesarios para el funcionamiento
    setTimeout(() => this.logout(), 1000);
  }

  private async logout() {
    try {
      // Volver a habilitar el FormData en cuanto funcione el timeout
      // this.formData = await this.authManager.requestSignOut();
      await this.authManager.requestSignOut();
    } finally {
      this.logoutComplete = true;
      setTimeout(async () => {
        const redirectPage = !this.formData ? 'login' : undefined;
        await this.authManager.attemptSignOut(redirectPage);
      }, 1500);
    }
  }

}
