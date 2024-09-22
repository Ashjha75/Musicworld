import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';

import { ProductComponent } from './simplecomponents/product/product.component';
import { CategoryComponent } from './simplecomponents/category/category.component';
import { LoginComponent } from './simplecomponents/login/login.component';
import { RegisterComponent } from './simplecomponents/register/register.component';
import { OrderComponent } from './simplecomponents/order/order.component';

import { HomeComponent } from './home/home.component';

import { AllproductsComponent } from './composedcomponents/allproducts/allproducts.component';
import { AllcategoriesComponent } from './composedcomponents/allcategories/allcategories.component';
import { SearchComponent } from './composedcomponents/search/search.component';
import { BestComponent } from './composedcomponents/best/best.component';
import { ProducerComponent } from './composedcomponents/producer/producer.component';
import { AllcartsComponent } from './composedcomponents/allcarts/allcarts.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    CategoryComponent,
    HomeComponent,
    LoginComponent,
    AllproductsComponent,
    AllcategoriesComponent,
    RegisterComponent,
    SearchComponent,
    BestComponent,
    ProducerComponent,
    OrderComponent,
    AllcartsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [AllproductsComponent,
              AllcategoriesComponent,
              BestComponent,
              ProducerComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
