import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router'
import { HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { BooksComponent } from './components/books.component';
import { BookDetailComponent } from './components/book-detail.component';

const appRoutes: Routes = [
  { path: '', component: BooksComponent, title: 'List of Books'},
  { path: 'book/:bkId', component: BookDetailComponent },
  { path: '**', redirectTo: '/', pathMatch: 'prefix'}
]

@NgModule({
  declarations: [
    AppComponent,
    BooksComponent,
    BookDetailComponent
  ],
  imports: [
    BrowserModule, HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
