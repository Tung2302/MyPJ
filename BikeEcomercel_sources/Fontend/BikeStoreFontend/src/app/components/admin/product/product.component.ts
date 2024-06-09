import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, NgForm } from '@angular/forms';
import { Category } from 'src/app/models/category';
import { Product } from 'src/app/models/product';
import { ResponseObject } from 'src/app/models/responseObject';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  constructor(private productService: ProductService, private categoryService: CategoryService) { }

  categories: Category[] = [];
  products: Product[] = [];
  responeObj: ResponseObject = {};
  file: File | undefined;
  editFile: File | undefined;
  editProduct:Product | null | undefined;
  deleteProduct:Product| null | undefined;
  ngOnInit(): void {
    this.getAllCategories();
    this.getAllProducts();
  }

  public getAllCategories(): void {
    this.categoryService.getAllCategories().subscribe(
      (result: Category[]) => {
        this.categories = result;
        console.log(this.categories);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public getAllProducts(): void {
    this.productService.getAllProducts().subscribe(
      (result: ResponseObject) => {
        this.products = result.data;
        console.log(this.products);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }
  public onAddProduct(addProductForm: NgForm): void {
    console.log(addProductForm.value);

    const formData: FormData = new FormData();
    formData.append('name', addProductForm.value.name);
    formData.append('cost', addProductForm.value.cost);
    formData.append('size', addProductForm.value.size);
    formData.append('quantity', addProductForm.value.quantity);
    formData.append('description', addProductForm.value.description);
    formData.append('color', addProductForm.value.color);
    formData.append('categoryId', addProductForm.value.categoryId);
    if (this.file) {
      formData.append('file', this.file);
    }
    this.productService.createProduct(formData).subscribe(
      (response: ResponseObject) => {
        console.log(response);
        alert(response.message);
        this.getAllProducts();
        addProductForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onFileSelected(event: any): void {
    console.log(event);
    if (event.target.files && event.target.files.length > 0) {
      this.file = event.target.files[0];
      console.log(this.file);
    }
  }

  public onUpdateProduct(editProductForm: NgForm): void {
    
    const editformData: FormData = new FormData();
    editformData.append('name', editProductForm.value.name);
    editformData.append('cost', editProductForm.value.cost);
    editformData.append('size', editProductForm.value.size);
    editformData.append('quantity', editProductForm.value.quantity);
    editformData.append('description', editProductForm.value.description);
    editformData.append('color', editProductForm.value.color);
    editformData.append('categoryId', editProductForm.value.categoryId);
    if (this.editFile) {
        editformData.append('file', this.editFile, this.editFile.name);
    }
    console.log(editformData.get('name'));
    console.log(editformData.get('cost'));
    console.log(editformData.get('size'));
    console.log(editformData.get('quantity'));
    console.log(editformData.get('description'));
    console.log(editformData.get('color'));
    console.log(editformData.get('categoryId'));
    console.log(editformData.get('file'));
   
  

    // Call the productService to update the product
    this.productService.updateProduct(editformData, editProductForm.value.id).subscribe(
        (response: ResponseObject) => {
            console.log(response);
            alert(response.message);
            this.onCloseModal(); // Close the modal after successful update
            this.getAllProducts(); // Refresh the product list
            editProductForm.reset(); // Reset the form after successful update
        },
        (error: HttpErrorResponse) => {
            if (error.status === 403) {
                alert("You don't have permission to perform this action.");
            } else {
                alert("An error occurred while updating the product. Please try again later.");
            }
        }
    );
}



public onEditFileSelected(event: any): void {
    // Handle file selection for editing
    if (event.target.files && event.target.files.length > 0) {
      this.editFile = event.target.files[0];
      console.log(this.editFile);
    }
}

  onDeleteProduct(productId:any){
    this.productService.deleteProduct(productId).subscribe(
    (response: ResponseObject) => {
      console.log(response);
      alert(response.message);
      this.onCloseModal();
      this.getAllProducts();
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  );
  }
  onOpenModal(product: Product | null, mode: string) {
    const modalAdd = document.getElementById('addProductModal');
    const modalEdit = document.getElementById('editProductModal');
    const modalDelete = document.getElementById('deleteProductModal');
    if (modalAdd && mode == "add") {
      modalAdd.classList.add('show');
      modalAdd.style.display = 'block';
    }
    if (modalEdit && mode=="edit") {
      this.editProduct =product;
      modalEdit.classList.add('show');
      modalEdit.style.display = 'block';
    }
    if (modalDelete && mode=="delete") {
      this.deleteProduct = product;
      modalDelete.classList.add('show');
      modalDelete.style.display = 'block';
    }   
  }
  onCloseModal() {
    const modalAdd = document.getElementById('addProductModal');
    const modalDelete = document.getElementById('deleteProductModal');
    const modalEdit = document.getElementById('editProductModal');
    if (modalDelete) {
      modalDelete.classList.remove('show');
      modalDelete.style.display = 'none';
    }
    if (modalAdd) {
      modalAdd.classList.remove('show');
      modalAdd.style.display = 'none';
    }
    if (modalEdit) {
      modalEdit.classList.remove('show');
      modalEdit.style.display = 'none';
    }
  }

}
