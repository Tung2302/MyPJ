import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthResponse } from 'src/app/models/authResponse';
import { ResponseObject } from 'src/app/models/responseObject';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
 
  userLogined:User | undefined;
  errorMessage:String | undefined;
  userAuthResponse:AuthResponse |undefined;

  constructor(private userService: UserService,private router: Router) {}
  ngOnInit(): void {
    this.clearSession();
  }

  public loginUser(loginForm: NgForm): void {
    this.userService.loginUser(loginForm.value).subscribe(
      (response: ResponseObject) => {
        console.log(response.data.token);
        if (response.status == "Successed" && response.data.token != null) {
          localStorage.setItem('token', response.data.token);
          this.checkAndFetchUserInfo();
          if(localStorage.getItem('userRole')=='ROLE_USER'){
            this.router.navigate(['/home']);
          }
          else if(localStorage.getItem('userRole')=='ROLE_ADMIN'|| localStorage.getItem('userRole')=='ROLE_MANAGER')
          this.router.navigate(['/dashboard']);

        }
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  
  private checkAndFetchUserInfo(): void {
    this.userService.getUserByToken().subscribe(
      (response: ResponseObject) => {
          localStorage.setItem('userName',response.data.first_name);
          localStorage.setItem('userRole',response.data.roles[0].name);
          console.log('User information retrieved:', localStorage.getItem('userRole'));
        
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public clearSession(): void{
    localStorage.clear();
  }

  
}
