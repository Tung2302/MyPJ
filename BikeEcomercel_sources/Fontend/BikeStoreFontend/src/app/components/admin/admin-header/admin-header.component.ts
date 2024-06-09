import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-header',
  templateUrl: './admin-header.component.html',
  styleUrls: ['./admin-header.component.css']
})
export class AdminHeaderComponent implements OnInit{
  sessionUser:string | null | undefined;

  ngOnInit(): void {
    this.sessionUser = localStorage.getItem("userName");
  }
  
  public clearSession():void {
    localStorage.clear();
}
  

}
