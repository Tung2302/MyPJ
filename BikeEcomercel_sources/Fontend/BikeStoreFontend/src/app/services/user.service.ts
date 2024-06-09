import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import {Observable} from "rxjs"
import { User } from '../models/user';
import { ResponseObject } from '../models/responseObject';
import { Register } from '../models/register';
import { AuthRequest } from '../models/authRequest';
// import { AuthResponse } from '../models/authresponse';


@Injectable({ providedIn: 'root'})
export class UserService {
   
    constructor(private httpClient: HttpClient) { }
    token: any = localStorage.getItem('token');
    reqHeader = new HttpHeaders({ 
        "Access-Control-Allow-Headers": "*", 
        "Access-Control-Allow-Methods": 'OPTIONS,POST,GET', 
        'Content-Type': 'application/json',
        'Authorization': 'Bearer' + this.token
    });
    public getAllUsers(): Observable<User[]>{
        return this.httpClient.get<User[]>('http://localhost:8080/api/v1/Users',{headers: this.reqHeader});
    }

    public addUser(user:User): Observable<User>{
        return this.httpClient.post<User>(`http://localhost:8080/api/v1/Users/save`,user,{headers: this.reqHeader});
    }
    public updateUser(user:User,userId:number): Observable<User>{
        return this.httpClient.put<User>(`http://localhost:8080/api/v1/Users/${userId}`,user,{headers: this.reqHeader});
    }
    public deleteUser(userId:number): Observable<void>{
        return this.httpClient.delete<void>(`http://localhost:8080/api/v1/Users/${userId}`,{headers: this.reqHeader});
    }
    public loginUser(loginDTO: AuthRequest): Observable<ResponseObject> {
        return this.httpClient.post<ResponseObject>('http://localhost:8080/api/v1/auth/login', loginDTO);
      }
    public registerUser(registerDTO: Register): Observable<ResponseObject> {
        return this.httpClient.post<ResponseObject>('http://localhost:8080/api/v1/auth/register', registerDTO);
      }
    public getUserByToken(): Observable<ResponseObject>{
        console.log(localStorage.getItem('token'));
        return this.httpClient.get<ResponseObject>(`http://localhost:8080/api/v1/Users/get-user-by-token`,{headers: this.reqHeader});

    }  
    }
    