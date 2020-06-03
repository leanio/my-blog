import { TipoUsuario } from './tipo-usuario';

export class Usuario {
  nome: string;
  email: string;
  senha: string;
  ativo = true;
  tipo = TipoUsuario.LEITOR;
}
