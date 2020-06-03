import { TipoUsuario } from './tipo-usuario';

export class UsuarioNewInput {
  email: string;
  senha: string;
  nome: string;
}

export class UsuarioUpdateInput {
  email: string;
  nome: string;
}

export class UsuarioOutput {
  codigo: number;
  email: string;
  nome: string;
  ativo: boolean;
  grupo: any;
}
