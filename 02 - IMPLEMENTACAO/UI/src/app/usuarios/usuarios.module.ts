import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { UsuariosRoutingModule } from './usuarios-routing.module';
import { UsuarioCadastroComponent } from './usuario-cadastro/usuario-cadastro.component';


@NgModule({
  declarations: [UsuarioCadastroComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,

    UsuariosRoutingModule
  ]
})
export class UsuariosModule { }
