import {HttpClient, HttpParams} from "@angular/common/http";
import {inject, Injectable} from "@angular/core";
import {firstValueFrom, map, Observable} from "rxjs";
import { Deck, DeckOfCards } from "./models";

const BASE = 'https://www.deckofcardsapi.com/api'

@Injectable()
export class DeckService {

  private http = inject(HttpClient)

  createDeck(count = 1): Promise<Deck> {
    const params = new HttpParams().set('deck_count', count)
    return firstValueFrom(
      this.http.get<Deck>(`${BASE}/deck/new/shuffle`, { params })
    )
  }

  getDeck(deckId: string): Promise<Deck> {
    return firstValueFrom(
      this.http.get<Deck>(`${BASE}/deck/${deckId}`)
    )
  }

  draw(deckId: string, count = 1): Observable<DeckOfCards> {
    const params = new HttpParams().set('count', count)
    return this.http.get<DeckOfCards>(`${BASE}/deck/${deckId}/draw`, { params })
  }
}
