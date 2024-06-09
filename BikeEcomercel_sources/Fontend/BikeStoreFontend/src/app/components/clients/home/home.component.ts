import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ResponseObject } from 'src/app/models/responseObject';
import { ProductService } from 'src/app/services/product.service';
import { HeaderComponent } from '../header/header.component';
import { CartService } from 'src/app/services/cart.service';
import { Router } from '@angular/router';
import { HttpResponse } from '@angular/common/http';



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  @Input('bsCarousel') carouselConfig: any = {
    interval: 5000, // Thời gian hiển thị giữa các slide (đơn vị: mili giây)
    keyboard: true, // Cho phép sử dụng bàn phím để điều khiển carousel
    pauseOnHover: true, // Tạm dừng carousel khi rê chuột vào
    wrap: true, // Vòng lặp vô hạn của carousel (khi đến slide cuối cùng, sẽ quay lại slide đầu tiên)
    showNavigationArrows: true, // Hiển thị các mũi tên điều hướng (previous/next)
    showNavigationIndicators: true,
  };
  products: Product[] = [];
  responseObj: ResponseObject[] = [];
  message: String | undefined;
  arrayI = [1, 2, 3];
  searchKey: string | undefined;



  constructor(private productService: ProductService, private cartService: CartService, private router: Router) { }

  ngOnInit(): void {
    this.getAllProducts();
    

  }

  public getAllProducts(): void {
    this.productService.getAllProducts().subscribe(
      (result:ResponseObject) => {
        if (result && result.data) {
        this.products = result.data;
        console.log(this.products);
        }
        console.log(result);

      },
      (error: any) => {
        console.error(error);
      }
    );
  }

  public getSearchProducts(): void {
    this.productService.searchProduct(this.searchKey).subscribe(
      (result:ResponseObject) => {
        if (result && result.data) {
        this.products = result.data;
        console.log(this.products);
        }
        console.log(result);

      },
      (error: any) => {
        console.error(error);
      }
    );
  }

  public addToCart(productId: any): void {
    this.cartService.addToCart(productId).subscribe(
      (result: ResponseObject) => {
        alert(result.message);
        // window.location.reload();
        
      },
      (error: any) => {
        console.error(error);
      }
    );



  }
  public removeAll(): void {
    this.cartService.clear().subscribe(
      (result: ResponseObject) => {
        console.log(result.message);
      },
    );
  }
}


