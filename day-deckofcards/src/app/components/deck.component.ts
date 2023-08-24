import { Component, OnInit, inject } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Observable, tap} from 'rxjs';

import {DeckService} from '../deck.service';
import { DeckOfCards} from '../models';


@Component({
  selector: 'app-deck',
  templateUrl: './deck.component.html',
  styleUrls: ['./deck.component.css']
})
export class DeckComponent implements OnInit {

  private router = inject(Router)
  private deckSvc = inject(DeckService)
  private activatedRoute = inject(ActivatedRoute)

  deckId = ''
  remaining = 0
  numCards: number[] = []
  toDraw = 1
  hand$!: Observable<DeckOfCards>

  ngOnInit(): void {
    this.deckId = this.activatedRoute.snapshot.params['deckId']
    this.deckSvc.getDeck(this.deckId)
      .then(result => {
        this.deckId = result.deck_id
        this.remaining = result.remaining
        this.numCards = Array(this.remaining)
          .fill(0).map((_, i) => i + 1)
      })
  }

  onChange(elem: any) {
    this.toDraw = elem.target.value
  }

  drawCard() {
    if (this.remaining <= 0) {
      alert('No more cards')
      return
    }
    this.hand$ = this.deckSvc.draw(this.deckId, this.toDraw)
        .pipe(
          tap(deck => this.remaining = deck.remaining)
        )
  }

}
