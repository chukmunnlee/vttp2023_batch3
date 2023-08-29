export interface Deck {
  success: boolean
  deck_id: string
  shuffled: boolean
  remaining: number
}

export interface Card {
  code: string
  image: string
  images: {
    svg: string
    png: string
  }
  value: string
  suit: string
}

export interface DeckOfCards {
  success: boolean
  deck_id: string
  cards: Card[]
  remaining: number
}
