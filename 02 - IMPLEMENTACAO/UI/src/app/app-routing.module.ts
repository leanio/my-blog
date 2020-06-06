import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { PostagemFiltroComponent } from './postagens/postagem-filtro/postagem-filtro.component';

const routes: Routes = [
  {path: 'usuarios', loadChildren: () => import('./usuarios/usuarios.module').then(m => m.UsuariosModule)},
  {path: 'postagens', loadChildren: () => import('./postagens/postagens.module').then(m => m.PostagensModule)},
  {path: '', component: PostagemFiltroComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
