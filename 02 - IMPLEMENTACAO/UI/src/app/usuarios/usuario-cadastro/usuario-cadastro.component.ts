import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../usuario.service';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastService } from 'src/app/core/toast.service';
import { FormBuilder, Validators, AbstractControl } from '@angular/forms';

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
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.configurarFormulario();

    if (this.codigoUsuario()) {
      this.carregarUsuario();
    }

  }

  codigoUsuario(): number {
    return this.activatedRoute.snapshot.params.codigo;
  }

  salvarUsuario(): void {
    if (this.isEditando()) {
      this.atualizarUsuario();
    } else {
      this.adicionarUsuario();
    }
  }

  adicionarUsuario(): void {
    this.usuarioService.adicionar(this.formulario.value).then(() => {
      this.toastServie.toast('Usuário cadastrado com sucesso');
      this.router.navigateByUrl('/');
    }).catch(error => this.errorHandlerService.handle(error));
  }

  atualizarUsuario(): void {
    this.usuarioService.atualizar(this.codigoUsuario(), this.formulario.value).then(() => {
      this.toastServie.toast('Usuário atualizado com sucesso');
      this.router.navigateByUrl('/');
    }).catch(error => this.errorHandlerService.handle(error));
  }

  carregarUsuario(): void {
    this.usuarioService.buscarPeloCodigo(this.codigoUsuario()).then(usuario => {
      $('#nomeLabel').addClass('active');
      $('#emailLabel').addClass('active');
      this.configurarFormulario();
      delete usuario.codigo;
      delete usuario.grupo;
      delete usuario.ativo;
      this.formulario.setValue(usuario);
    }).catch(error => this.errorHandlerService.handle(error));
  }

  isEditando(): boolean {
    return this.codigoUsuario() !== undefined;
  }

  configurarFormulario(): void {
    if (this.isEditando()) {
      this.formulario = this.formBuilder.group({
        nome: [null, Validators.required],
        email: [null, Validators.email]
      });
    } else {
      this.formulario = this.formBuilder.group({
        nome: [null, Validators.required],
        email: [null, Validators.email],
        senha: [null, Validators.minLength(5)],
        confirmacaoSenha: [null, Validators.required]
      }, {
        validators: this.confirmacaoDaSenha
      });
    }
  }

  confirmacaoDaSenha(abstractControl: AbstractControl): { confirmacaoSenha: boolean } {
    const senha = abstractControl.get('senha').value;
    const confirmacaoSenha = abstractControl.get('confirmacaoSenha').value;

    if (senha !== confirmacaoSenha && confirmacaoSenha != null) {
      $('#confirmacaoSenha').removeClass('valid').addClass('invalid');
      return { confirmacaoSenha: true };
    }

    if (confirmacaoSenha !== null) {
      $('#confirmacaoSenha').removeClass('invalid').addClass('valid');
    }
  }

  formularioParaUsuario(): any {
    const usuario = Object.assign({}, this.formulario.value);
    delete usuario.confirmacaoSenha;
    return usuario;
  }

}
