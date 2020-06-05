import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { PostagemCadastroComponent } from './postagem-cadastro/postagem-cadastro.component';
import { PostagensRoutingModule } from './postagens-routing.module';

@NgModule({
  declarations: [PostagemCadastroComponent],
  imports: [
    CommonModule,
    FormsModule,

    PostagensRoutingModule
  ]
})
export class PostagensModule { }
