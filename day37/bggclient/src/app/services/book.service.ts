import { HttpClient } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";
import { BookSummary } from "../models";
import { Observable, map } from "rxjs";

//const url = 'http://localhost:8080/api/books'
const url = '/api/books'

// @Injectable -> @Service
@Injectable({ providedIn: 'root' })
export class BookService {

  http = inject(HttpClient)

  getBooks(): Observable<BookSummary[]> {
    return this.http.get<BookSummary[]>(url)
        .pipe(
          map(books => {
            return books.map(bk => {
              return {
                bookId: bk.bookId,
                title: bk.title.toUpperCase()
              } as BookSummary
            })
          })
        )
  }

}

