import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UsuarioCadastroComponent } from './usuario-cadastro/usuario-cadastro.component';

const routes: Routes = [
  {path: 'cadastro', component: UsuarioCadastroComponent},
  {path: ':codigo', component: UsuarioCadastroComponent}
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
export class UsuariosRoutingModule { }
