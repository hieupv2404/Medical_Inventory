import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgxPaginationModule } from 'ngx-pagination';
import { OrderModule } from 'ngx-order-pipe';
import { AppRoutingModule } from './app.routes';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './login/login.component';
import { HomeadminComponent } from './homeadmin/homeadmin.component';
import { HomeuserComponent } from './homeuser/homeuser.component';
import { CategoryComponent } from './category/category.component';
import { FilterPipe } from './filter/filter.pipe';
import { ProductComponent } from './product/product.component';
import { UpdateProductComponent } from './product/update-product/update-product.component';
import { ViewProductComponent } from './product/view-product/view-product.component';
import { CreateProductComponent } from './product/create-product/create-product.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeadminComponent,
    HomeuserComponent,
        CategoryComponent,
        FilterPipe,
        ProductComponent,
        UpdateProductComponent,
        ViewProductComponent,
        CreateProductComponent
  ],
    imports: [
        NgbModule,
    BrowserModule,
    AppRoutingModule,
      HttpClientModule,
      ReactiveFormsModule,
        FormsModule,
        NgxPaginationModule,
        OrderModule,
     
   

  ],
    providers: [],
    entryComponents: [
      
        UpdateProductComponent,
        CreateProductComponent,
        ViewProductComponent,
      
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
