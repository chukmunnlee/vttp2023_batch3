import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private fb = inject(FormBuilder)
  private router = inject(Router)

  customers = [
    { custId: 1, view: 'simple' },
    { custId: 2, view: 'detailed' },
    { custId: 3, view: 'detailed' },
  ]

  form!: FormGroup

  ngOnInit(): void {
    this.form = this.createForm()
  }

  perform() {
    const custId = this.form.value['custId']
    const view = this.form.value['view']
    console.info(`>>> custId: ${custId}, view: ${view}`)
    this.router.navigate(
      ['/customer', custId],
      { queryParams: { view, limit: 10, offset: 0 }}
    )
  }

  private createForm(): FormGroup {
    return this.fb.group({
      custId: this.fb.control<string>('', [ Validators.required ]),
      view: this.fb.control<string>('simple')
    })
  }

}
