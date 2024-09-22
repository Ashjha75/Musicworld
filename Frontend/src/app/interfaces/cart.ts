export interface Cart {
  id: number
  productId: number;
  quantity: number;
  mail: string;
  date: Date;
  bought: boolean;
  orderId: number;
  overallPrice: number;
}
