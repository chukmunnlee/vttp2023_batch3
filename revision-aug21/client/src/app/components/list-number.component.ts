import { Component, Input, OnChanges, OnInit, SimpleChanges, inject } from '@angular/core';
import { NumberService } from '../number.service';

@Component({
  selector: 'app-list-number',
  templateUrl: './list-number.component.html',
  styleUrls: ['./list-number.component.css']
})
export class ListNumberComponent implements OnInit, OnChanges {

  @Input()
  value: number = 0

  @Input()
  name!: string

  numSvc = inject(NumberService)

  numList: number[] = []
  sum = 0

  ngOnChanges(changes: SimpleChanges): void {
    console.info('>>> changes: ', changes)
    const newValue = changes['value'].currentValue
    this.numList.push(newValue)
    this.sum = this.numList.reduce((acc, val) => acc + val)
  }

  ngOnInit(): void {
    //this.numList.push(this.value)
    //this.sum = this.numList.reduce((acc, val) => acc + val)
    this.numSvc.numEvent.subscribe(
      value => {
        this.numList.push(value)
        this.sum = this.numList.reduce((acc, val) => acc + val)
      }
    )
  }

}
