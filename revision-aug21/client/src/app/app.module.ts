import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AddNumberComponent } from './components/add-number.component';
import { ListNumberComponent } from './components/list-number.component';
import { NumberImageComponent } from './components/number-image.component';
import { NumberService } from './number.service';

const appRoutes: Routes = [
  //{ path: '', component: NumberImageComponent },
  { path: 'num/:num', component: NumberImageComponent }
]

@NgModule({
  declarations: [
    AppComponent,
    AddNumberComponent,
    ListNumberComponent,
    NumberImageComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [ NumberService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
