import { AfterViewChecked, AfterViewInit, Component, ElementRef, OnInit, ViewChild, inject } from '@angular/core';
import { RegistrationComponent } from './components/registration.component';
import { FormControl } from '@angular/forms';
import { Registration } from './models';
import { RegistrationService } from './registration.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit, AfterViewChecked {

  data: Registration = {
    name: 'betty',
    email: 'betty@gmail.com'
  }

  //@ViewChild('reg')
  @ViewChild(RegistrationComponent)
  regComp!: RegistrationComponent

  @ViewChild('mainTitle')
  mainTitle!: ElementRef

  regSvc = inject(RegistrationService)

  myTitle = 'hello, world'

  proxySubmit() {
    console.info('>>>> in proxySubmit')
    const form: Registration = this.regComp.regForm.value
    console.info('>>> form: ', form)
    form.name = form.name.toLocaleUpperCase()
    form.email = form.email.toLocaleUpperCase()
    this.regSvc.register(form)
      .then(result => {
        console.info('>>>> ', result)
      })
  }

  processReg(form: Registration) {
    console.info('>>> registration: ', form)
  }

  ngOnInit(): void {
    console.info('onInit: ', this.regComp)
    console.info('onInit: data ', this.data)
  }

  ngAfterViewInit(): void {
    console.info('onAfterViewInit: ', this.regComp)
    console.info('onAfterViewInit: h1: ', this.mainTitle.nativeElement)
    //this.mainTitle.nativeElement.innerText = 'hello, world'
    this.regComp.regForm.get('name')?.setValue(this.data.name)
    this.regComp.regForm.get('email')?.setValue(this.data.email)
    this.regComp.newRegistration.subscribe(
      form => {
        console.info('>>>>> manual registration: ', form)
        this.regSvc.register(form)
          .then(result => {
            console.info('>>>> ', result)
          })
      }
    )
  }

  ngAfterViewChecked(): void {
  }
}
