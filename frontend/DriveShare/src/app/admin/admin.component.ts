import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { catchError, Observable, takeUntil, throwError } from 'rxjs';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {

  ngOnInit(): void {
    this.getGuidelines()
    this.getUsers()
    this.getOrgList()
    this.getRoles()
  }

  constructor(private httpClient: HttpClient, private userAuth: UserAuthService, private userService: UserService) { }

  guidelines: string = '';
  users = []
  organizations = []
  roles = []

  setRole(role: string, userID: string) {

  }

  getRoles() {
    this.httpClient.get(this.userService.API_PATH + "/roles", { headers: this.userService.loggedInHeader }).subscribe(
      (data: any) => {
        this.roles = data
      }
    ), catchError(error => {
      return throwError(error);
    }
    )
  }

  getUsers() {
    this.httpClient.get(this.userService.API_PATH + "/userList", { headers: this.userService.loggedInHeader }).subscribe(
      (data: any) => {
        this.users = data
      }
    ), catchError(error => {
      return throwError(error);
    }
    )
  }

  getOrgList() {
    this.httpClient.get(this.userService.API_PATH + "/getOrgList", { headers: this.userService.loggedInHeader }).subscribe(
      (data: any) => {
        this.organizations = data
      }
    ), catchError(error => {
      return throwError(error);
    }
    )
  }

  getGuidelines() {
    this.httpClient.get(this.userService.API_PATH + "/guidelines", { headers: this.userService.loggedInHeader, responseType: "text" }).subscribe(
      (data: any) => {
        this.guidelines = data
      }
    ), catchError(error => {
      return throwError(error);
    }
    )
  }

  setGuidelines() {
    const formData = { "guidelines": this.guidelines }
    this.httpClient.post(this.userService.API_PATH + "/updateGuidelines", JSON.stringify(formData), { headers: this.userService.loggedInHeader, responseType: "text" }).subscribe(
      (data: any) => {
      }
    ), catchError(error => {
      return throwError(error);
    }
    )
  }

}
