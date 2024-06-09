import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ResponseObject } from 'src/app/models/responseObject';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit{
  
  buyProduct:Product|undefined;
  listProducts:Product[]=[];
  product_id:number | undefined;

  constructor(private route: ActivatedRoute, private router: Router,private productService: ProductService) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.product_id = params['product_id'];
      console.log(this.product_id);
    });
    this.findProductById();
  }

  findProductById():void{
    this.productService.findProductById(this.product_id)
    .subscribe(
      (result: ResponseObject) => {
        this.buyProduct = result.data;
        console.log(this.buyProduct);
      },
      (error: any) => {
        console.error(error);
      }
    );

  }

}
