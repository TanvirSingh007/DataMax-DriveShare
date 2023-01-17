import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { AccessRequestsComponent } from './access-requests/access-requests.component';
import { AdminComponent } from './admin/admin.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LogsComponent } from './logs/logs.component';
import { MyFilesComponent } from './my-files/my-files.component';
import { RegisterComponent } from './register/register.component';
import { RequestFilesComponent } from './request-files/request-files.component';
import { SharedFilesComponent } from './shared-files/shared-files.component';
import { UploadComponent } from './upload/upload.component';
import { AuthGuard } from './_auth/auth.guard';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},  
  {path: 'register', component: RegisterComponent},
  {path: 'admin', component: AdminComponent, canActivate:[AuthGuard], data:{roles:['Admin']}},
  {path: 'about', component: AboutComponent,canActivate:[AuthGuard]},
  {path: 'upload', component: UploadComponent,canActivate:[AuthGuard]},
  {path: 'home', component: HomeComponent, canActivate:[AuthGuard]},
  {path: 'myFiles', component: MyFilesComponent, canActivate:[AuthGuard]},
  {path: 'sharedFiles', component: SharedFilesComponent, canActivate:[AuthGuard]},
  {path: 'requestFiles', component: RequestFilesComponent, canActivate:[AuthGuard]},
  {path: 'accessRequests', component: AccessRequestsComponent, canActivate:[AuthGuard]},
  {path: 'logs', component: LogsComponent, canActivate:[AuthGuard]},
  {path: '**', redirectTo: 'register', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
