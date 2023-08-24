import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../models';
import { StorageService } from '../storage.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  private fb = inject(FormBuilder)
  private router = inject(Router)
  private storageSvc = inject(StorageService)
  private userSvc = inject(UserService)
  private activtedRoute = inject(ActivatedRoute)

  form!: FormGroup

  ngOnInit(): void {
    const userId = this.activtedRoute.snapshot.params['userId']
    this.form = this.createForm()

    if (!userId)
      return

    this.userSvc.findUserById(userId)
      .then(user => {
        this.form = this.createForm(user)
      })

    //let user: User | null = this.storageSvc.load(userId)
    //this.form = this.createForm(user)
  }

  createForm(data: User | null = null) {
      return this.fb.group({
        name: this.fb.control<string>(data? data.name: ''
            , [ Validators.required ]),
        email: this.fb.control<string>(data? data.email: ''
            , [ Validators.required, Validators.email ]),
      })
  }

  process() {
    const value: User = this.form.value
    console.info('value: ', value)
    //this.storageSvc.save(value)
    this.userSvc.save(value)
      .then(id => {
        console.info('>>> id: ', id)
        this.form.reset()
        this.router.navigate(['/'])
      })
  }

}
