import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthService } from '../_services/user-auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  isCollapsed = true;
  isAdminFn(){
    if(this.userAuth.getRoles() === 'Admin'){
      return true;
    }
    return false;
  }
  isAdmin = this.isAdminFn();

  constructor( private userAuth:UserAuthService,
    public router: Router){}

  public isLoggedIn(){
    return this.userAuth.isLoggedIn();
  }

  public logout(){
    this.userAuth.clearStorage();
    this.router.navigate(['/login']);
  }

}
