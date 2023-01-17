import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  ngOnInit(): void {
    if (this.userAuth.isLoggedIn()) {
      this.router.navigate(['/home'])
    }
  }

  buttonDisabled: boolean = false;
  errorEnable: boolean = true;
  error: string = "Invalid credentails.";

  constructor(
    private userService: UserService,
    private userAuth: UserAuthService,
    private router: Router,
  ) { }

  login(loginForm: NgForm) {
    console.log(loginForm.value);
    console.log(this.userAuth.isLoggedIn());
    this.buttonDisabled = true;
    this.userService.login(loginForm.value).subscribe(
      (response: any) => {
        this.errorEnable = true;
        console.log(response);
        this.userAuth.setRoles(response.user.role[0].roleName);
        console.log(response.user.role[0].roleName)
        console.log(response.jwtToken)
        this.userAuth.setToken(response.jwtToken);
        this.userAuth.setUserId(response.user.email);
        this.userAuth.setOrg(response.user.organization);
        this.userAuth.setUserName(response.user.name);

        if (response.user.role[0].roleName === 'Admin')
          this.router.navigate(['/admin']);
        else
          this.router.navigate(['/home']);
      },
      (error => {
        console.log(error.status);
        this.errorEnable = false;
        this.buttonDisabled = false;
      })
    )
  }
}
