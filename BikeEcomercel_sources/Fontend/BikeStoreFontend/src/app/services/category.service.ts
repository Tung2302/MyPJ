import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable, map } from "rxjs"
import { Category } from '../models/category';
import { ResponseObject } from '../models/responseObject';
@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) { }
  token: any = localStorage.getItem('token');
    reqHeader = new HttpHeaders({ 
        "Access-Control-Allow-Headers": "*", 
        "Access-Control-Allow-Methods": 'OPTIONS,POST,GET', 
        'Content-Type': 'application/json',
        'Authorization': 'Bearer' + this.token
    });
  public getAllCategories(): Observable<any> {
    return this.http.get<ResponseObject>(`http://localhost:8080/api/v1/Categories`,{headers: this.reqHeader}).pipe(
      map(response => response.data)
  );
  }
  public addCategory(category: Category): Observable<Category> {
    return this.http.post<Category>(`http://localhost:8080/api/v1/Categories/create`, category,{headers: this.reqHeader});
  }
  public updateCategory(category: Category, categoryId: number): Observable<any> {
    return this.http.put<Category>(`http://localhost:8080/api/v1/Categories/update/${categoryId}`, category,{headers: this.reqHeader});
  }
  public deleteCategory(categoryId: number): Observable<void> {
    return this.http.delete<void>(`http://localhost:8080/api/v1/Categories/${categoryId}`,{headers: this.reqHeader});
  }
  public findCategoryById(categoryId:number): Observable<any> {
    return this.http.get<ResponseObject>(`http://localhost:8080/api/v1/Categories/${categoryId}`,{headers: this.reqHeader})
  }
}