import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListUserComponent } from './components/admin/list-user/list-user.component';
import { FormsModule } from '@angular/forms';
import { UserService } from './services/user.service';
import { HomeComponent } from './components/clients/home/home.component';
import { HeaderComponent } from './components/clients/header/header.component';
import { LoginComponent } from './components/clients/login/login.component';
import { DashboardComponent } from './components/admin/dashboard/dashboard.component';
import { CategoryComponent } from './components/admin/categories/categories.component';
import { RegisterationComponent } from './components/clients/registeration/registeration.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CarouselModule } from 'ngx-bootstrap/carousel';
import { DetailComponent } from './components/clients/detail/detail.component';
import { ProductComponent } from './components/admin/product/product.component';
import { AdminHeaderComponent } from './components/admin/admin-header/admin-header.component';
import { FooterComponent } from './components/clients/footer/footer.component';
import { CartComponent } from './components/clients/cart/cart.component';



@NgModule({
  declarations: [
    AppComponent,
    ListUserComponent,
    HomeComponent,
    HeaderComponent,
    LoginComponent,
    DashboardComponent,
    CategoryComponent,
    RegisterationComponent,
    DetailComponent,
    ProductComponent,
    AdminHeaderComponent,
    FooterComponent,
    CartComponent,
  
 
    
  ],
  imports: [
    FormsModule,
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    CarouselModule,

  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }



