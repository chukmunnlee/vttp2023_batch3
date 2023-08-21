import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { NumberService } from '../number.service';

@Component({
  selector: 'app-number-image',
  templateUrl: './number-image.component.html',
  styleUrls: ['./number-image.component.css']
})
export class NumberImageComponent implements OnInit, OnDestroy {

  activateRoute = inject(ActivatedRoute)

  num = 0;
  sub$!: Subscription

  numSvc = inject(NumberService)

  ngOnInit(): void {
    console.info('>> inside ngOnInit')
    this.num = this.activateRoute.snapshot.params['num']
    this.sub$ = this.activateRoute.params.subscribe(
      params => { this.num = params['num'] }
    )
    // this.sub$ = this.numSvc.numEvent.subscribe(
    //   value => {
    //     this.num = value
    //   }
    // )
  }

  ngOnDestroy(): void {
      this.sub$.unsubscribe()
  }

}
