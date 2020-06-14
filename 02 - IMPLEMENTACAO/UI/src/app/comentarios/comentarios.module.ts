import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComentarioCadastroComponent } from './comentario-cadastro/comentario-cadastro.component';
import { FormsModule } from '@angular/forms';
import { ComentarioFiltroComponent } from './comentario-filtro/comentario-filtro.component';



@NgModule({
  declarations: [ComentarioCadastroComponent, ComentarioFiltroComponent],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [
    ComentarioCadastroComponent,
    ComentarioFiltroComponent
  ]
})
export class ComentariosModule { }
