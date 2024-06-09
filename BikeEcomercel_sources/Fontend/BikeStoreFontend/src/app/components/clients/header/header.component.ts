import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{
  count:number | undefined
  constructor(private productService:ProductService ,private cartService:CartService, private router: Router){}
  sessionUser:string | null | undefined;
  ngOnInit(): void {
    if(localStorage.getItem("userName")!=null){
      this.sessionUser=localStorage.getItem("userName")

    }
    else{
      this.sessionUser="User";
    }
    this.getCount();
  }

  public getCount():void {
    this.cartService.getCount().subscribe(
    (result: any) => {
      this.count = result;
      localStorage.setItem('cartCount',result)
    },
    (error: any) => {
      console.error(error);
    }
  );
  }
  public logout():void{
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
