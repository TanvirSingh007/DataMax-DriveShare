import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-logs',
  templateUrl: './logs.component.html',
  styleUrls: ['./logs.component.css']
})
export class LogsComponent {
  constructor(
    private userService: UserService,
    private httpClient: HttpClient,
    private userAuth: UserAuthService
  ) { }

  data = []
  isDataPresent: Boolean = false;
  ngOnInit(): void {
    this.getLogs();
  }

  getLogs() {

    this.httpClient.get(this.userService.API_PATH + "/getLogs/" + this.userAuth.getUserId(), { headers: this.userService.loggedInHeader }).subscribe(
      (data: any) => {
        this.data = data;
        // console.log(this.data)
        if (this.data.length !== 0)
          this.isDataPresent = true
      }
    ), catchError(error => {
      return throwError(error);
    }
    )

  }
}
