import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/core/domain/Usuario';
import { UsuarioService } from '../usuario.service';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { Router } from '@angular/router';
import { ToastService } from 'src/app/core/toast.service';
import { FormGroup, FormBuilder, Validators, FormControl, AbstractControl } from '@angular/forms';

declare var $: any;

@Component({
  selector: 'app-usuario-cadastro',
  templateUrl: './usuario-cadastro.component.html',
  styleUrls: ['./usuario-cadastro.component.css']
})
export class UsuarioCadastroComponent implements OnInit {

  formulario: any;

  constructor(
    private usuarioService: UsuarioService,
    private errorHandlerService: ErrorHandlerService,
    private toastServie: ToastService,
    private router: Router,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.configurarFormulario();
  }

  configurarFormulario() {
    this.formulario = this.formBuilder.group({
      nome: [null, Validators.required],
      email: [null, Validators.email],
      senha: [null, Validators.minLength(5)],
      confirmacaoSenha: [null, Validators.required],
      ativo: ['true'],
      tipo: ['LEITOR']
    }, {
      validators: this.confirmacaoDeSenha
    });
  }

  confirmacaoDeSenha(abstractControl: AbstractControl): { confirmacaoSenha: boolean } {
    const senha = abstractControl.get('senha').value;
    const confirmacaoSenha = abstractControl.get('confirmacaoSenha').value;

    if (senha !== confirmacaoSenha && confirmacaoSenha != null) {
      $('#confirmacaoSenha').removeClass('valid').addClass('invalid');
      return {confirmacaoSenha: true};
    }

    if (confirmacaoSenha !== null){
      $('#confirmacaoSenha').removeClass('invalid').addClass('valid');
    }
  }

  adicionarUsuario() {
    this.usuarioService.adicionar(this.usuario).then(() => {
      this.toastServie.toast('Usuário cadastrado com sucesso');
      this.router.navigateByUrl('/');
    }).catch(error => this.errorHandlerService.handle(error));
  }

  get usuario(): Usuario {
    const usuario = Object.assign({}, this.formulario.value);
    delete usuario.confirmacaoSenha;
    return usuario;
  }

}
