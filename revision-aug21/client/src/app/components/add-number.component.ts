import { Component, OnInit, Output, inject } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Subject } from 'rxjs';
import { NumberService } from '../number.service';

@Component({
  selector: 'app-add-number',
  templateUrl: './add-number.component.html',
  styleUrls: ['./add-number.component.css']
})
export class AddNumberComponent implements OnInit {

  @Output()
  onNumber = new Subject<number>()

  numForm!: FormGroup

  fb = inject(FormBuilder)
  numSvc = inject(NumberService)

  ngOnInit(): void {
    this.numForm = this.createForm()
  }

  processForm() {
    const value = this.numForm.value['num']
    console.info('>>> value: ', value)
    this.onNumber.next(value)
    this.numForm.reset()
    this.numSvc.numEvent.next(value)
  }

  clearForm() {
    this.numForm.reset()
  }

  private createForm() {
    return this.fb.group({
      num: this.fb.control<number>(0)
    })
  }

}
