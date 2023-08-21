import { Component, OnInit, inject } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
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
        name: this.fb.control<string>('', [ Validators.required ]),
        quantity: this.fb.control<number>(1, [ Validators.required, Validators.min(1) ]),
        unitPrice: this.fb.control<number>(.5, [ Validators.required, Validators.min(.05) ]),
      })
    )
  }

  removeLineItem(idx: number) {
    this.lineItemsArray.removeAt(idx)
  }

  invalid(): boolean {
    //console.info('>>> ', this.orderForm.invalid || this.lineItemsArray.controls.length <= 0)
    return this.orderForm.invalid || this.lineItemsArray.controls.length <= 0
  }

  private createOrder() {
    this.lineItemsArray = this.fb.array([])
    return this.fb.group({
      name: this.fb.control<string>('', [ Validators.required, Validators.minLength(3) ]),
      email: this.fb.control<string>('', [ Validators.required, Validators.email ]),
      express: this.fb.control<boolean>(false),
      lineItems: this.lineItemsArray
    })
  }

}
