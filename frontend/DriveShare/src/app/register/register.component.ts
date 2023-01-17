import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';
import { catchError, Observable, takeUntil, throwError } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  ngOnInit(): void {
    this.getOrgList()
    if (this.userAuth.isLoggedIn()) {
      this.router.navigate(['/home'])
    }
  }

  organizations = []

  constructor(
    private httpClient: HttpClient,
    private userService: UserService,
    private userAuth: UserAuthService,
    private router: Router,
  ) { }

  successDisable: Boolean = true;
  message: string = "Registration Successfull!"

  register(registerForm: NgForm) {
    console.log(registerForm.value);
    this.userService.register(registerForm.value).subscribe(
      (response: any) => {
        console.log(response);
        this.successDisable = false;
      },
      (error => {
        console.log(error);
      })
    )
  }

  getOrgList() {
    this.httpClient.get(this.userService.API_PATH + "/getOrgList", { headers: this.userService.requestHeader }).subscribe(
      (data: any) => {
        this.organizations = data
        console.log(this.organizations)
      }
    ), catchError(error => {
      return throwError(error);
    }
    )
  }
}
