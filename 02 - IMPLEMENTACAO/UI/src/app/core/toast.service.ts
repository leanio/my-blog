import { Injectable } from '@angular/core';

declare var M: any;

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  constructor() { }

  toast(mensagem: string) {
    M.toast({html: mensagem});
  }
}
