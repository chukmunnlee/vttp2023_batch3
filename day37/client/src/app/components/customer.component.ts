import { Component, OnInit, inject } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  private activatedRoute = inject(ActivatedRoute)
  private title = inject(Title)

  custId = ""
  view = ''

  ngOnInit(): void {
    this.custId = this.activatedRoute.snapshot.params['cId']
    this.view = this.activatedRoute.snapshot.queryParams['view']

    this.title.setTitle('Customer ' + this.custId)
  }
}
