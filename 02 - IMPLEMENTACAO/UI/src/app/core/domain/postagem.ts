import { UsuarioCodigo } from './Usuario';

export class PostagemCodigo {
  codigo: number;
}

export class PostagemInput {
  titulo: string;
  corpo: string;
  usuario = new UsuarioCodigo();
}

export class PostagemOutput {
  codigo: number;
  titulo: string;
  corpo: string;
  dataPublicacao: Date;
  autor: string;
}
