import { Component, OnInit, inject } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {

  bookId!: string
  bookTitle!: string

  title = inject(Title)
  activatedRoute = inject(ActivatedRoute)

  ngOnInit(): void {

    this.bookId = this.activatedRoute.snapshot.params['bkId']
    this.bookTitle = this.activatedRoute.snapshot.queryParams['title']

    this.title.setTitle(`Details of ${this.bookTitle}`)

  }

}
