import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import { GiphyService } from './giphy.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit {

  @ViewChild('q')
  qCtrl!: ElementRef

  urls: string[] = []

  constructor(private giphySvc: GiphyService) { }

  ngAfterViewInit(): void {
    console.info('>>> qCtrl: ', this.qCtrl.nativeElement)
  }

  search() {
    const q = this.qCtrl.nativeElement.value
    if (!q)
      return
    console.info('>>> q: ', q)

    this.giphySvc.search(q)
      .subscribe(result => {
        this.urls = result
      })

    this.qCtrl.nativeElement.value = ""
  }
}
