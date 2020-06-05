import { TipoUsuario } from './tipo-usuario';

export class UsuarioCodigo {
  codigo: number;
}

export class UsuarioInput {
  email: string;
  nome: string;
}

export class UsuarioComSenhaInput extends UsuarioInput {
  senha: string;
}

export class UsuarioOutput {
  codigo: number;
  email: string;
  nome: string;
  ativo: boolean;
  grupo: any;
}
