import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthService } from './user-auth.service';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class ApiMethodsService {

  API_PATH = "http://localhost:9090";

  requestHeader = new HttpHeaders(
    {"No-Auth": "True"}
  )

  constructor(private httpClient: HttpClient, private userAuthService: UserAuthService) { }

}
