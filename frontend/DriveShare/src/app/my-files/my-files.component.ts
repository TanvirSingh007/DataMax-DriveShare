import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';
import { catchError, Observable, takeUntil, throwError } from 'rxjs';
import { saveAs } from 'file-saver';



@Component({
  selector: 'app-my-files',
  templateUrl: './my-files.component.html',
  styleUrls: ['./my-files.component.css']
})
export class MyFilesComponent {

  ngOnInit(): void {
    this.getFiles()
    this.getFolders()
  }

  files = []
  folders: any[] = []

  isDataPresent: Boolean = false
  isAdminFn() {
    if (this.userAuth.getRoles() === 'Admin') {
      return true;
    }
    return false;
  }
  isAdmin = this.isAdminFn();
  constructor(private httpClient: HttpClient, private userAuth: UserAuthService, private userService: UserService) { }

  httpHeader = new HttpHeaders(
    {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.userAuth.getToken()}`
    }
  )

  getFiles() {
    this.httpClient.get(this.userService.API_PATH + "/userFiles/" + this.userAuth.getUserId(), { headers: this.userService.loggedInHeader }).subscribe(
      (data: any) => {
        this.files = data
        if (this.files.length !== 0)
          this.isDataPresent = true
        // console.log(data, this.userAuth.getOrg())
      }
    ), catchError(error => {
      return throwError(error);
    }
    )
  }

  getOnlyFolderData(folderName: string) {
    return this.files.filter(function (obj: any) { return obj['location'] === folderName })
  }

  isFolderNotEmpty(folderName: string) {
    return this.getOnlyFolderData(folderName).length !== 0
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

  getFolders() {
    this.httpClient.get(this.userService.API_PATH + "/folders/" + this.userAuth.getUserId(), { headers: this.userService.loggedInHeader }).subscribe(
      (data: any) => {
        this.folders = data;
        this.folders.push({ userID: this.userAuth.getUserId(), name: "root" })
        console.log(this.folders)
        // console.log(data)
      }
    ), catchError(error => {
      return throwError(error);
    }
    )
  }

}
