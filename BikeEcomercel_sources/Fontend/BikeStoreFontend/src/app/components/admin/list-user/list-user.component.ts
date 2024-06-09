import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {
  users: User[] = [];
  deleteUser: User | null = null;

  editUser: User | null = null;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
      this.getAllUser();
  }

  public getAllUser(): void {
    this.userService.getAllUsers().subscribe(
      (result: User[]) => {
        this.users = result;
        console.log(result);
      }
      
    );
  }
  public onAddUser(addForm: NgForm): void {
    this.userService.addUser(addForm.value).subscribe(
      (response: User) => {
        console.log(response);
        alert("Add succecssfully");
        this.getAllUser();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateUser(editForm: NgForm): void {
    this.userService.updateUser(editForm.value,editForm.value.id).subscribe(
      (response: User) => {
        console.log(response);
        alert("Update succecssfully")
        this.onCloseModal();
        this.getAllUser();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
 


  public onDeleteUser(userId: number): void {
    this.userService.deleteUser(userId).subscribe(
      (response: void) => {
        console.log(response);
        this.onCloseModal();
        this.getAllUser();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onOpenModal(user: User|null,mode: string) {
    const modalAdd = document.getElementById('addUserModal');
    const modalEdit = document.getElementById('updateUserModal');
    const modalDelete = document.getElementById('deleteUserModal');
    if (modalAdd && mode=="add") {
      modalAdd.classList.add('show');
      modalAdd.style.display = 'block';
    }
    if (modalEdit && mode=="edit") {
      this.editUser =user
      modalEdit.classList.add('show');
      modalEdit.style.display = 'block';
    }
    if (modalDelete && mode=="delete") {
      this.deleteUser = user;
      modalDelete.classList.add('show');
      modalDelete.style.display = 'block';
    }
    
  }
  public onCloseModal() {
    const modalAdd = document.getElementById('addUserModal');
    const modalDelete = document.getElementById('deleteUserModal');
    const modalEdit = document.getElementById('updateUserModal');
    if (modalDelete) {
      modalDelete.classList.remove('show');
      modalDelete.style.display = 'none';
    }
    if(modalAdd) {
      modalAdd.classList.remove('show');
      modalAdd.style.display = 'none';
    }
    if(modalEdit) {
      modalEdit.classList.remove('show');
      modalEdit.style.display = 'none';
    }
  }
  }
  

