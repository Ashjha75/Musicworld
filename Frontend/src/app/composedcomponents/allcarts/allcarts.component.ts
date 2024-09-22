import { Component, OnInit } from '@angular/core';
import { Cart } from 'src/app/interfaces/cart';
import { CartService } from 'src/app/services/cart.service';
import { DataService } from 'src/app/services/data.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CartProduct } from 'src/app/interfaces/composedinterfaces/cartproduct';
import { ProductService } from 'src/app/services/product.service';
import { Product } from 'src/app/interfaces/product';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-allcarts',
  templateUrl: './allcarts.component.html',
  styleUrls: ['./allcarts.component.css']
})
export class AllcartsComponent implements OnInit {

  public cartproducts: CartProduct[] = [];

  constructor(private cartService: CartService,
    private productService: ProductService,
    private categoryService: CategoryService,
    private dataService: DataService) { }

  ngOnInit(): void {
    let cartproducts = [];
    this.cartService.getCart(
      this.dataService.getAuth()
    ).subscribe(
      (response: Cart[]) => {
        this.getCartProduct(response)
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      })
  }

  private getCartProduct(response: Cart[]) {
    response.forEach(cart => {
      let cartproduct: CartProduct = <CartProduct>{}
      cartproduct.cart = cart
      this.productService.getById(cart.productId).subscribe(
        (response: Product) => {
          cartproduct.product = response
          this.categoryService.getById(cartproduct.product.category).subscribe(category => cartproduct.product.categoryName = category.name)
        }
      )
      this.cartproducts.push(cartproduct)
    })
  }

}
