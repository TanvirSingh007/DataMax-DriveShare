import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';
import { catchError, Observable, takeUntil, throwError } from 'rxjs';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent {

  ngOnInit(): void {
    this.getGuidelines()

  }

  constructor(private httpClient: HttpClient, private userAuth: UserAuthService, private userService: UserService) { }


  guidelines: string = '';

  getGuidelines() {
    this.httpClient.get(this.userService.API_PATH + "/guidelines", { headers: this.userService.loggedInHeader, responseType: "text" }).subscribe(
      (data: any) => {
        this.guidelines = data
        console.log(data)
      }
    ), catchError(error => {
      return throwError(error);
    }
    )
  }

}
