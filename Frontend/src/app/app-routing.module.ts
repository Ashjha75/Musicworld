import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AllproductsComponent } from './composedcomponents/allproducts/allproducts.component';
import { AllcategoriesComponent } from './composedcomponents/allcategories/allcategories.component';
import { SearchComponent } from './composedcomponents/search/search.component';
import { BestComponent } from './composedcomponents/best/best.component';
import { ProducerComponent } from './composedcomponents/producer/producer.component';

import { LoginComponent } from './simplecomponents/login/login.component';
import { RegisterComponent } from './simplecomponents/register/register.component';
import { OrderComponent } from './simplecomponents/order/order.component';
import { ErrorComponent } from './simplecomponents/error/error.component';
import { AllcartsComponent } from './composedcomponents/allcarts/allcarts.component';

const routes: Routes = [
  {path: '', component: BestComponent},
  {path: 'products', component: AllproductsComponent},
  {path: 'categories', component: AllcategoriesComponent},
  {path: 'cart', component: AllcartsComponent},
  {path: 'order', component: AllproductsComponent},
  {path: 'shipment', component: AllproductsComponent},
  {path: 'category/:id', component: AllproductsComponent},
  {path: 'order', component: OrderComponent},
  {path: 'search', component: SearchComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'producer/:mail', component: ProducerComponent},
  {path: '**', component: ErrorComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
