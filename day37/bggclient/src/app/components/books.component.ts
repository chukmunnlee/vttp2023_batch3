import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { BookSummary } from '../models';
import { BookService } from '../services/book.service';
import { Observable, Subscription } from 'rxjs';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit, OnDestroy {

  // @Autowired
  booksSvc = inject(BookService)

  books: BookSummary[] = []

  bkSub$!: Subscription
  bkObs$!: Observable<BookSummary[]>

  ngOnInit(): void {
    this.bkObs$ = this.booksSvc.getBooks()
    // this.bkSub$ = this.booksSvc.getBooks()
    //   .subscribe(
    //     books => {
    //       this.books = books
    //     }
    //   )
  }

  ngOnDestroy(): void {
      console.info('component destroyed')
      // this.bkSub$.unsubscribe()
  }

}
