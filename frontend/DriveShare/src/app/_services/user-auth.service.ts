import { Injectable } from '@angular/core';
import { isEmpty } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() { }

  public setRoles(role: string) {
    localStorage.setItem("roles", role);
  }

  public getRoles(): any {
    return localStorage.getItem("roles");
  }

  public setToken(jwtToken: string) {
    localStorage.setItem("jwtToken", jwtToken);
  }

  public setOrg(org: string) {
    localStorage.setItem("org", org)
  }

  public getOrg() {
    return localStorage.getItem("org");
  }

  public getToken(): any {
    return localStorage.getItem("jwtToken");
  }

  public setUserId(email: string) {
    localStorage.setItem("email", email)
  }

  public getUserId(): any {
    return localStorage.getItem("email")
  }

  public setUserName(name: string) {
    localStorage.setItem("name", name)
  }

  public getUserName(): any {
    return localStorage.getItem("name")
  }

  public clearStorage() {
    localStorage.clear();
  }

  public isLoggedIn(): Boolean {
    return localStorage.getItem("roles") !== null && localStorage.getItem("jwtToken") !== null;
  }

}
