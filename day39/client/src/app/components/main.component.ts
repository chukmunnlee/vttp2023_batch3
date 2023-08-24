import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UserSummary } from '../models';
import { UserService } from '../user.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  private fb = inject(FormBuilder)
  private router = inject(Router)
  private userSvc = inject(UserService)

  form!: FormGroup
  users: UserSummary[] = []


  ngOnInit(): void {
    this.form = this.fb.group({
      userId: this.fb.control('')
    })
    this.userSvc.loadUsers()
      .then(result => this.users = result)
  }

  process() {
    this.router.navigate(['/form', this.form.value['userId']])
  }
}
