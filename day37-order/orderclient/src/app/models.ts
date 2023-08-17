export interface LineItem {
  name: string,
  quantity: number,
  unitPrice: number
}

export interface Order {
  name: string,
  email: string,
  express: boolean,
  lineItems: LineItem[]
}
