import { NgModule } from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import {UploadService} from './upload.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [ UploadService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
