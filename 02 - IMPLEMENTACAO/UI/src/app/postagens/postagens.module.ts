import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { PostagemCadastroComponent } from './postagem-cadastro/postagem-cadastro.component';
import { PostagensRoutingModule } from './postagens-routing.module';
import { PostagemFiltroComponent } from './postagem-filtro/postagem-filtro.component';

@NgModule({
  declarations: [PostagemCadastroComponent, PostagemFiltroComponent],
  imports: [
    CommonModule,
    FormsModule,

    PostagensRoutingModule
  ]
})
export class PostagensModule { }
