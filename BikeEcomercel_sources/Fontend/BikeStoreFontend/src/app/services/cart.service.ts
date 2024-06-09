import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable, concat, map } from "rxjs"
import { Category } from '../models/category';
import { ResponseObject } from '../models/responseObject';


@Injectable({
    providedIn: 'root'
})
export class CartService {
    
    constructor(private http: HttpClient) { }
    token: any = localStorage.getItem('token');
    reqHeader = new HttpHeaders({ 
        "Access-Control-Allow-Headers": "*", 
        "Access-Control-Allow-Methods": 'OPTIONS,POST,GET', 
        'Content-Type': 'application/json',
        'Authorization': 'Bearer' + this.token
    });
    public getCount(): Observable<any> {
        return this.http.get<any>('http://localhost:8080/api/v1/shopping-cart/count', {headers: this.reqHeader}).pipe(
            map(response => response.data)
        );
        
       

    }
    tokenParse(arg0: string, tokenParse: any) {
        throw new Error('Method not implemented.');
    }


    public getAllCart(): Observable<any> {
        return this.http.get<ResponseObject>(`http://localhost:8080/api/v1/shopping-cart`,{headers: this.reqHeader}).pipe(
            map(response => response.data)
        );
    }

    public addToCart(productId: any): Observable<ResponseObject> {
        const headers = new HttpHeaders();
        headers.set('Authorization', 'Bearer ' + this.token);
        headers.set('Content-Type','application/json')
        
        return this.http.get<ResponseObject>(`http://localhost:8080/api/v1/shopping-cart/add/${productId}`,{headers: this.reqHeader})
    }

    public getTotal(): Observable<any> {
        return this.http.get<ResponseObject>('http://localhost:8080/api/v1/shopping-cart/total',{headers: this.reqHeader}).pipe(
            map(response => response.data));
    }

    public remove(id: number): Observable<any> {
        return this.http.delete<ResponseObject>(`http://localhost:8080/api/v1/shopping-cart/remove/${id}`,{headers: this.reqHeader});
    }

    public clear(): Observable<any> {
        return this.http.delete<ResponseObject>('http://localhost:8080/api/v1/shopping-cart/clear',{headers: this.reqHeader});
    }

    public update(id: number, quantity: number): Observable<any> {
        return this.http.post<ResponseObject>(`http://localhost:8080/api/v1/shopping-cart/update/${id}`, quantity,{headers: this.reqHeader}).pipe(
            map(response => response.data));
    }

}