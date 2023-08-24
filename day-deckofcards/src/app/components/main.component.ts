import { Component, inject, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {DeckService} from '../deck.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  form!: FormGroup
  deckSize = [ 1, 2, 3, 4 ]

  private router = inject(Router)
  private fb = inject(FormBuilder)
  private deckSvc = inject(DeckService)

  ngOnInit(): void {
    this.form = this.fb.group({
      deckCount: this.fb.control<number>(1, [ Validators.required, Validators.min(1) ])
    })
  }

  createDeck() {
    this.deckSvc.createDeck(this.form.value['deckCount'])
      .then(deck => {
        console.info('>>> deck: ', deck)
        return this.router.navigate([ '/deck', deck.deck_id ])
      })
      .catch(error => {
        alert(`Error: ${JSON.stringify(error)}`)
      })

  }

}
