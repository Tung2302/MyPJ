import { HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ResponseObject } from 'src/app/models/responseObject';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-registeration',
  templateUrl: './registeration.component.html',
  styleUrls: ['./registeration.component.css']
})
export class RegisterationComponent implements OnInit{
  userRegister:User|undefined;
  errorMessage:string|undefined;
  constructor(private userService: UserService) {}
  ngOnInit(): void {
  }

  public registerUser(registerForm: NgForm): void {
    this.userService.registerUser(registerForm.value).subscribe(
      (response: ResponseObject) => {
        this.errorMessage = response.message;
        this.userRegister = response.data;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}

