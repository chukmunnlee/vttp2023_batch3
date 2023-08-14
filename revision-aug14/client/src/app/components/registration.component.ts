import { Component, Input, OnInit, Output, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Registration } from '../models';
import { RegistrationService } from '../registration.service';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  @Input()
  regInfo!: Registration

  @Output()
  newRegistration = new Subject<Registration>()

  regForm!: FormGroup

  // @Autowired
  private fb = inject(FormBuilder)
  private regSvc = inject(RegistrationService)

  ngOnInit(): void {
    this.regForm = this.createRegistrationForm()
    // If data is passed in, set the values to the form
    if (!!this.regInfo)
      this.populateForm(this.regInfo)
  }

  populateForm(data: Registration) {
    this.regForm.get('name')?.setValue(this.regInfo.name)
    this.regForm.get('email')?.setValue(this.regInfo.email)
  }

  performReg() {
    const form: Registration = this.regForm.value
    console.info('form: ', form)
    this.newRegistration.next(form)
    /*
    this.regSvc.register(form)
      .then(resp => {
        console.info('>>> resp: ', resp)
        alert(resp.message)
        this.regForm.reset()
      })
      .catch(err => {
        console.info('>>> err: ', err)
        alert(err)
      })
    console.info('>>>> after register')
    */

  }

  private createRegistrationForm() {
    return this.fb.group({
      name: this.fb.control<string>('', [ Validators.required ]),
      email: this.fb.control<string>('', [ Validators.required, Validators.email ]),
    })
  }
}

