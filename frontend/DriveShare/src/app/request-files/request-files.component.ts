import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';
import { catchError, Observable, takeUntil, throwError } from 'rxjs';
import { saveAs } from 'file-saver';


@Component({
  selector: 'app-request-files',
  templateUrl: './request-files.component.html',
  styleUrls: ['./request-files.component.css']
})
export class RequestFilesComponent {

  users = []
  files = []
  isDataPresent = true
  isAdminFn() {
    if (this.userAuth.getRoles() === 'Admin') {
      return true;
    }
    return false;
  }
  isAdmin = this.isAdminFn();

  ngOnInit(): void {
    // this.getUserFiles()
    this.getUserList(this.userAuth.getUserId())
  }

  constructor(private httpClient: HttpClient, private userAuth: UserAuthService, private userService: UserService) { }

  httpHeader = new HttpHeaders(
    {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.userAuth.getToken()}`
    }
  )

  sendFileRequest(fileID: string, userID: string, fileName: string) {

    const formData = { "fileID": fileID, "senderID": this.userAuth.getUserName(), "receiverID": userID, "fileName": fileName }
    this.httpClient.post(this.userService.API_PATH + "/requestFile", JSON.stringify(formData), { headers: this.userService.loggedInHeader, responseType: 'text' }).subscribe(
      (data: any) => {
        console.log(data)
      }
    ), catchError(error => {
      return throwError(error);
    }
    )
  }

  getUserList(userId: string) {
    this.httpClient.get(this.userService.API_PATH + "/userList/" + this.userAuth.getOrg(), { headers: this.userService.loggedInHeader }).subscribe(
      (data: any) => {
        this.users = data
        this.users = this.users.filter(function (obj: any) { return obj['email'] !== userId })
        console.log(this.users)
      }
    ), catchError(error => {
      return throwError(error);
    }
    )
  }

  getUserFiles(userId: string) {
    this.httpClient.get(this.userService.API_PATH + "/userFiles/" + userId, { headers: this.userService.loggedInHeader }).subscribe(
      (data: any) => {
        this.files = data
        if (this.files.length === 0)
          this.isDataPresent = false
        else
          this.isDataPresent = true
        this.files = this.files.filter(function (obj: any) { return obj['share'] === "no" })
        console.log(this.files)
        // console.log(data, this.userAuth.getOrg())
      }
    ), catchError(error => {
      return throwError(error);
    }
    )
  }

  changeShare(share: string, fileId: string, i: number) {
    return this.httpClient.post(this.userService.API_PATH + "/updateFileShare/" + fileId + "/" + share, { headers: this.userService.loggedInHeader }, { responseType: 'text' }).subscribe(
      (data: any) => {
        console.log(data)
      }
    ), catchError(error => {
      return throwError(error);
    }
    )
  }


  download(file: string | undefined): Observable<Blob> {
    return this.httpClient.get(this.userService.API_PATH + "/download/" + file + "/" + this.userAuth.getUserId(), {
      responseType: 'blob'
    });
  }

  deleteFile(fileId: string) {
    return this.httpClient.post(this.userService.API_PATH + "/deleteFile/" + fileId, { headers: this.userService.loggedInHeader }, { responseType: 'text' }).subscribe(
      (data: any) => {
        this.files = this.files.filter(function (obj: any) { return obj['fileID'] !== fileId })
        console.log(data)
      }
    ), catchError(error => {
      return throwError(error);
    }
    )
  }

  downloadFile(fileId: string, name: string): void {
    this.download(fileId)
      .subscribe(blob => saveAs(blob, name));
  }
}
