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

  handle(errorResponse: any): void {
    let mensagem = 'Erro ao processar serviço remoto. Tente novamente';

    if (typeof errorResponse === 'string') {
      mensagem = errorResponse;
    }

    if (errorResponse instanceof HttpErrorResponse && errorResponse.status >= 400 && errorResponse.status < 500) {
      const conteudo = errorResponse.error;

      if (!Array.isArray(conteudo)) {
        this.toastService.toast(conteudo.mensagemUsuario);

        return;
      }

      for (const erro of conteudo) {
        this.toastService.toast(erro.mensagemUsuario);
      }

      return;
    }

    this.toastService.toast(mensagem);
  }

}
