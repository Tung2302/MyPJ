import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs"
import { ResponseObject } from '../models/responseObject';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }
  token: any = localStorage.getItem('token');
    reqHeader = new HttpHeaders({ 
        'Authorization': 'Bearer' + this.token
    });
  public getAllProducts(): Observable<any> {
    return this.http.get<ResponseObject>(`http://localhost:8080/api/v1/Products/auth`)
  }

  public createProduct(formData:FormData): Observable<ResponseObject> {
    return this.http.post<ResponseObject>('http://localhost:8080/api/v1/Products/create', formData,{headers: this.reqHeader});
  }
  public updateProduct(formData:FormData, productId: number): Observable<ResponseObject> {

    return this.http.put<ResponseObject>(`http://localhost:8080/api/v1/Products/update/${productId}`, formData, {headers: this.reqHeader});
  }
  public deleteProduct(id: number): Observable<ResponseObject> {

    return this.http.delete<ResponseObject>(`http://localhost:8080/api/v1/Products/${id}`,{headers: this.reqHeader});
  }
  public searchProduct(searchKey: any): Observable<any> {
    return this.http.get<ResponseObject>(`http://localhost:8080/api/v1/Products/auth/search-product?searchKey=${searchKey}`)
  }

  public findProductById(id: number | undefined): Observable<ResponseObject> {

    return this.http.get<ResponseObject>(`http://localhost:8080/api/v1/Products/${id}`,{headers: this.reqHeader});
  }

}

