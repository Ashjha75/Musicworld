import { Cart } from "../cart";

export interface OrderResponse {
    order: string;
    cartList: Cart[];
}
