import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Category } from 'src/app/models/category';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoryComponent implements OnInit {
  categories: Category[] = [];
  editCategory: Category | null = null;
  deleteCategory: Category | null = null;

  constructor(private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.getAllCategories();
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

  public onAddCategory(addCategoryForm: NgForm): void {
    console.log(addCategoryForm.value);
    this.categoryService.addCategory(addCategoryForm.value).subscribe(
      (response: Category) => {
        console.log(response);
        alert('Category added successfully');
        this.getAllCategories();
        addCategoryForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addCategoryForm.reset();
      }
    );
  }

  public onUpdateCategory(editCategoryForm: NgForm): void {
    this.categoryService.updateCategory(editCategoryForm.value, editCategoryForm.value.categoryId).subscribe(
      (response: Category) => {
        console.log(response);
        alert('Category updated successfully');
        this.onCloseModal();
        this.getAllCategories();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteCategory(categoryId: number): void {
    this.categoryService.deleteCategory(categoryId).subscribe(
      (response: void) => {
        console.log(response);
        this.onCloseModal();
        this.getAllCategories();
      },
      (error: HttpErrorResponse) => {
        alert("Exist Product have this category");
      }
    );
  }

  onOpenModal(category: Category | null, mode: string): void {
    const modalAdd = document.getElementById('addCategoryModal');
    const modalEdit = document.getElementById('updateCategoryModal');
    const modalDelete = document.getElementById('deleteCategoryModal');

    if (modalAdd && mode === 'add') {
      modalAdd.classList.add('show');
      modalAdd.style.display = 'block';
    }
    if (modalEdit && mode === 'edit') {
      this.editCategory = category;
      modalEdit.classList.add('show');
      modalEdit.style.display = 'block';
    }
    if (modalDelete && mode === 'delete') {
      this.deleteCategory = category;
      console.log(this.deleteCategory?.categoryId);
      modalDelete.classList.add('show');
      modalDelete.style.display = 'block';
    }
  }

  onCloseModal(): void {
    const modalAdd = document.getElementById('addCategoryModal');
    const modalDelete = document.getElementById('deleteCategoryModal');
    const modalEdit = document.getElementById('updateCategoryModal');

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