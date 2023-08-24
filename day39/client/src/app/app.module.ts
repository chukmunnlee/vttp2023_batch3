import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { FormComponent } from './components/form.component';
import { StorageService } from './storage.service';
import { UserService } from './user.service';
import { NotFoundComponent } from './compoonents/not-found.component';

import { checkIsDirtyForm, checkUser } from './guards'

const appRoutes: Routes = [
  { path: '', component: MainComponent, title: 'Main' },
  { path: 'form', component: FormComponent
      , canDeactivate: [ checkIsDirtyForm ] },
  { path: 'form/:userId', component: FormComponent
      , canActivate: [ checkUser ], canDeactivate: [ checkIsDirtyForm ] },
  { path: 'notfound', component: NotFoundComponent },
]

@NgModule({
  declarations: [
    AppComponent,
    MainComponent, FormComponent, NotFoundComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule,
    RouterModule.forRoot(appRoutes, { useHash: true })
  ],
  providers: [ StorageService, UserService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
