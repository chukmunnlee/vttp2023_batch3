import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { Routes, RouterModule } from '@angular/router'

import { AppComponent } from './app.component';
import { UploadComponent } from './components/upload.component';
import { PlayComponent } from './components/play.component';
import {UploadService} from './upload.service';

const appRoutes: Routes = [
  { path: '', component: UploadComponent },
  { path: 'play/:id', component: PlayComponent }
]

@NgModule({
  declarations: [
    AppComponent,
    UploadComponent, PlayComponent
  ],
  imports: [
    BrowserModule, HttpClientModule,
    RouterModule.forRoot(appRoutes, { useHash: true })
  ],
  providers: [ UploadService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
