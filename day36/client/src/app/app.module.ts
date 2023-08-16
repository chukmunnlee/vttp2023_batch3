import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { RegistrationComponent } from './compnents/registration.component';
import { RegistrationService } from './services/registration.service';

@NgModule({
  declarations: [
    AppComponent, RegistrationComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [ RegistrationService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
