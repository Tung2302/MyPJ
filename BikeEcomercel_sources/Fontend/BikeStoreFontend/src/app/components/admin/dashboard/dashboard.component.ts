import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/models/category';
import { Product } from 'src/app/models/product';
import { ResponseObject } from 'src/app/models/responseObject';
import { User } from 'src/app/models/user';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  usersCount: number | undefined;
  countCategories:number | undefined;
  countProducts:number | undefined;
  role:String|null = sessionStorage.getItem("role");
  
  constructor(private userService: UserService,private categoryService:CategoryService,private productService:ProductService) { }
  ngOnInit(): void {
    this.countAll()

  }

  public countAll(): void {
    this.userService.getAllUsers().subscribe(
      (result: User[]) => {
        this.usersCount = result.length;
      }
    );
    this.categoryService.getAllCategories().subscribe(
      (result: Category[]) => {
          this.countCategories = result.length;
        },
    )
    this.productService.getAllProducts().subscribe(
      (result: ResponseObject) => {
          this.countProducts = result.data.length;
          console.log(this.usersCount);

        },
    )
  }

}



