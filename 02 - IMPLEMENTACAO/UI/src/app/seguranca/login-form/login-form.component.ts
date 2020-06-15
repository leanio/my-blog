import { Component, OnInit } from '@angular/core';
import { OauthService } from '../oauth.service';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  constructor(
    public auth: OauthService,
    private errorHandler: ErrorHandlerService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  login(usuario: string, senha: string) {
    this.auth.login(usuario, senha).then( () => {
      this.router.navigate(['/']);
    }).catch(erro => this.errorHandler.handle(erro));
  }

}
