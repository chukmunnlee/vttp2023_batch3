import { Component, OnInit, inject } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { Order } from '../models';

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent implements OnInit {

  orderForm!: FormGroup
  lineItemsArray!: FormArray

  private fb = inject(FormBuilder)

  ngOnInit(): void {
    this.orderForm = this.createOrder()
  }

  submit() {
    const order: Order = this.orderForm.value

    console.info('>>> order: ', order)
  }

  addLineItem() {
    this.lineItemsArray.push(
      this.fb.group({
        name: this.fb.control<string>(''),
        quantity: this.fb.control<number>(1),
        unitPrice: this.fb.control<number>(.5),
      })
    )
  }

  removeLineItem(idx: number) {
    this.lineItemsArray.removeAt(idx)
  }

  private createOrder() {
    this.lineItemsArray = this.fb.array([])
    return this.fb.group({
      name: this.fb.control<string>(''),
      email: this.fb.control<string>(''),
      express: this.fb.control<boolean>(false),
      lineItems: this.lineItemsArray
    })
  }

}
