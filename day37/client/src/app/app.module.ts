import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router'

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home.component';
import { CatComponent } from './components/cat.component';
import { DogComponent } from './components/dog.component';
import { CustomerComponent } from './components/customer.component';
import { ReactiveFormsModule } from '@angular/forms';
import { BooksComponent } from './components/books.component';

const appRoutes: Routes = [
  { path: '', component: HomeComponent, title: 'My Home' },
  { path: 'cat', component: CatComponent, title: 'Cat' },
  { path: 'dog', component: DogComponent, title: 'Dog' },
  { path: 'customer/:cId', component: CustomerComponent },
  { path: 'mycat', redirectTo: '/dog', pathMatch: 'full'},

  // default in switch, has to last
  { path: '**', redirectTo: '/', pathMatch: 'prefix' }
]

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent, CatComponent, DogComponent, CustomerComponent, BooksComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
