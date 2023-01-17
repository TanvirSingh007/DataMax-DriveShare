import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MdbModalContainerComponent, MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { catchError, map, Observable, throwError } from 'rxjs';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';


@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.scss']
})
export class UploadComponent {

  API_PATH = "http://localhost:9090";

  ngOnInit(): void {
    this.getUserFolder()
  }
  folders = []
  public gfg = true;
  saveBtnDisabled: boolean = false;
  uploadBtnDisabled: boolean = false;
  folderName: string = "";
  successDisable: Boolean = true;
  message: string = "File Upload SuccessFull!"

  createNewFolder() {
    if (this.folderName === "") {
      return;
    }
    this.saveBtnDisabled = true;
    this.httpClient.post(this.userService.API_PATH + "/createFolder/" + this.userAuth.getUserId() + "/" + this.folderName, { headers: this.userService.loggedInHeader }, { responseType: 'text' }).subscribe(
      (data: any) => {
        // console.log(data)
        this.getUserFolder()
        this.gfg = true
        this.folderName = ''
      }
    )
    this.saveBtnDisabled = false
    console.log("reached")

  }

  getUserFolder() {
    this.httpClient.get(this.userService.API_PATH + "/folders/" + this.userAuth.getUserId(), { headers: this.userService.loggedInHeader }).subscribe(
      (data: any) => {
        this.folders = data;
        // console.log(data)
      }
    ), catchError(error => {
      return throwError(error);
    }
    )
  }

  requestHeader = new HttpHeaders(
    { 'Authorization': `Bearer ${this.userAuth.getToken}` }
  )

  constructor(private httpClient: HttpClient, private userAuth: UserAuthService, private userService: UserService) { }

  fileToUpload: File | null = null;

  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
  }


  postFile(fileToUpload: File, ngform: NgForm): Observable<boolean> {
    const formData: FormData = new FormData();
    formData.append('file', fileToUpload, fileToUpload.name);
    formData.append('name', ngform.value.name);
    formData.append('description', ngform.value.description);
    formData.append('location', ngform.value.location);
    formData.append('userId', this.userAuth.getUserId());
    formData.append('share', ngform.value.share);
    return this.httpClient
      .post(this.API_PATH + "/upload", formData, { headers: this.requestHeader })
      .pipe(map(() => { return true; }));
  }

  uploadFile(uploadForm: NgForm) {

    this.uploadBtnDisabled = true;

    this.postFile(this.fileToUpload!, uploadForm).subscribe(data => {
      console.log(data)
      if (data === true) {
        this.successDisable = false
      }
    }, error => {
      console.log(error);
      this.uploadBtnDisabled = false;
    });

    this.uploadBtnDisabled = true;


    // this.httpClient.post(this.API_PATH + "/upload", formData, {headers: this.requestHeader}).subscribe(
    //   (data: any) => console.log(data)
    // )
  }

}
