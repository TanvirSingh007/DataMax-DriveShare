import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';
import { catchError, Observable, takeUntil, throwError } from 'rxjs';


@Component({
  selector: 'app-access-requests',
  templateUrl: './access-requests.component.html',
  styleUrls: ['./access-requests.component.css']
})
export class AccessRequestsComponent {

  ngOnInit(): void {
    this.getRequests()

  }

  constructor(private httpClient: HttpClient, private userAuth: UserAuthService, private userService: UserService) { }

  isDataPresent = true;
  requests = []

  getRequests() {
    this.httpClient.get(this.userService.API_PATH + "/requests/" + this.userAuth.getUserId(), { headers: this.userService.loggedInHeader }).subscribe(
      (data: any) => {
        this.requests = data
        if (this.requests.length !== 0)
          this.isDataPresent = true
        // console.log(data, this.userAuth.getOrg())
      }
    ), catchError(error => {
      return throwError(error);
    }
    )
  }

  acceptRequests(requestId: string) {
    this.httpClient.post(this.userService.API_PATH + "/acceptRequest/" + requestId, { headers: this.userService.loggedInHeader }, { responseType: 'text' }).subscribe(
      (data: any) => {
        console.log(data)
        this.requests = this.requests.filter(function (obj: any) { return obj['requestID'] !== requestId })
      }
    ), catchError(error => {
      return throwError(error);
    }
    )
  }

  denyRequests(requestId: string) {
    this.httpClient.post(this.userService.API_PATH + "/denyRequest/" + requestId, { headers: this.userService.loggedInHeader }, { responseType: 'text' }).subscribe(
      (data: any) => {
        console.log(data)
        this.requests = this.requests.filter(function (obj: any) { return obj['requestID'] !== requestId })
      }
    ), catchError(error => {
      return throwError(error);
    }
    )
  }

}
