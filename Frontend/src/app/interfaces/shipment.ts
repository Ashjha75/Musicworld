export interface Shipment {
  id: number;
  shipDate: Date;
  arriveDate: Date;
  shipAddress: string;
  total: number;
  orderId: number;
}
