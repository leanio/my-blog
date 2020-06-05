import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PostagemCadastroComponent } from './postagem-cadastro/postagem-cadastro.component';

const routes: Routes = [
  {path: 'cadastro', component: PostagemCadastroComponent},
  {path: ':codigo', component: PostagemCadastroComponent}
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
