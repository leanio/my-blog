import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { PostagemCadastroComponent } from './postagem-cadastro/postagem-cadastro.component';
import { PostagensRoutingModule } from './postagens-routing.module';
import { PostagemFiltroComponent } from './postagem-filtro/postagem-filtro.component';
import { PostagemComponent } from './postagem/postagem.component';
import { ComentariosModule } from '../comentarios/comentarios.module';

@NgModule({
  declarations: [PostagemCadastroComponent, PostagemFiltroComponent, PostagemComponent],
  imports: [
    CommonModule,
    FormsModule,

    ComentariosModule,
    PostagensRoutingModule
  ]
})
export class PostagensModule { }
