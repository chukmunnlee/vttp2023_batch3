import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { GiphyService } from './giphy.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule, HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [ GiphyService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
