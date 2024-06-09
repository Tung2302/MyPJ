import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cart } from 'src/app/models/cart';
import { Product } from 'src/app/models/product';
import { ResponseObject } from 'src/app/models/responseObject';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit{
  itemQuantity:number=0;
  cartList:Cart[]=[];
  product_id:number | undefined;
  total:number | undefined
  
  constructor(private cartService: CartService){}
  ngOnInit(): void {
    this.getAllCart();
    
  }
  
  public getAllCart():void{
    this.cartService.getAllCart().subscribe(
      (result: Cart[]) => {
        this.cartList = result;
        console.log(this.cartList);
        this.getTotal();
      },
      (error: any) => {
        console.error(error);
      }
    );
  }

  public clearAllCart():void{
    this.cartService.clear().subscribe(
      (result: ResponseObject) => {
        alert("All Cart Item was removed")
        this.getAllCart();
      },
      (error: any) => {
        console.error(error);
      }
    );
  }

  public removeCart(productId:any):void{
    this.cartService.remove(productId).subscribe(
      (result: ResponseObject) => {
        alert("All Cart Item was removed")
        this.getAllCart();
      },
      (error: any) => {
        console.error(error);
      }
    );
  }
  increaseQuantity(quantity:any,productId:any) {
    quantity +=1;
    this.cartService.update(productId,quantity).subscribe(
      (result: Cart) => {
        alert("Your cart updated")
        this.getAllCart();
      },
      (error: any) => {
        console.error(error);
      }
    );

  }

  decreaseQuantity(quantity:any,productId:any) {
    if (quantity > 0) {
      quantity -=1;
      this.cartService.update(productId,quantity).subscribe(
        (result: Cart) => {
          alert("Your cart updated")
          this.getAllCart();
        },
        (error: any) => {
          console.error(error);
        }
      );
    }
  }

  public getTotal():void {
    this.cartService.getTotal().subscribe(
      (result: number) => {
        this.total=result;
        console.log(this.total)
      },
      (error: any) => {
        console.error(error);
      }
    );
  }
  

}
