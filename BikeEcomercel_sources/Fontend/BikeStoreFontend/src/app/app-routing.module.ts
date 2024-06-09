import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListUserComponent } from './components/admin/list-user/list-user.component';
import { HomeComponent } from './components/clients/home/home.component';
import { LoginComponent } from './components/clients/login/login.component';
import { DashboardComponent } from './components/admin/dashboard/dashboard.component';
import { CategoryComponent } from './components/admin/categories/categories.component';
import { RegisterationComponent } from './components/clients/registeration/registeration.component';
import { DetailComponent } from './components/clients/detail/detail.component';
import { ProductComponent } from './components/admin/product/product.component';
import { CartComponent } from './components/clients/cart/cart.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: '/home', 
    pathMatch: 'full'
  },

  {
    path: 'users',
    component: ListUserComponent
  },

  {
    path: 'home',
    component: HomeComponent
  },
  {
    path:'detail',
    component: DetailComponent
  },
  {
    path:'cart',
    component: CartComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'registeration',
    component: RegisterationComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent
  },
  {
    path: 'categories',
    component: CategoryComponent
  },
  {
    path: 'products',
    component: ProductComponent
  }
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
