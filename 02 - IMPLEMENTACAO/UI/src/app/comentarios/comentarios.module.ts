import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComentarioCadastroComponent } from './comentario-cadastro/comentario-cadastro.component';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [ComentarioCadastroComponent],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [
    ComentarioCadastroComponent
  ]
})
export class ComentariosModule { }
