import { Injectable, Inject } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { ToastService } from './toast.service';

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService {

  constructor(
    private toastService: ToastService
  ) { }

  handle(errorResponse: any) {
    let msg = 'Erro ao processar serviço remoto. Tente novamente';

    if (typeof errorResponse === 'string') {
      msg = errorResponse;
    }

    if (errorResponse instanceof HttpErrorResponse && errorResponse.status >= 400 && errorResponse.status < 500) {
      try {

        for (const erro of errorResponse.error) {
          this.toastService.toast(erro.mensagemUsuario);
        }

        return;
      } catch (e) { }
    }

    this.toastService.toast(msg);
  }

}
