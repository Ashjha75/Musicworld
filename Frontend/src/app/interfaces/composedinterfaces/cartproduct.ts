import { Cart } from "../cart";
import { Product } from "../product";

export interface CartProduct {
    cart: Cart;
    product: Product;
}