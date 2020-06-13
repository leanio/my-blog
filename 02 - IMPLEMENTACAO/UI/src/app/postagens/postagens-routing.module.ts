import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PostagemCadastroComponent } from './postagem-cadastro/postagem-cadastro.component';
import { PostagemFiltroComponent } from './postagem-filtro/postagem-filtro.component';
import { PostagemComponent } from './postagem/postagem.component';

const routes: Routes = [
  {path: 'cadastro', component: PostagemCadastroComponent},
  {path: ':codigo', component: PostagemCadastroComponent},
  {path: 'visualizar/:codigo', component: PostagemComponent},
  {path: '', component: PostagemFiltroComponent}
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class PostagensRoutingModule { }
