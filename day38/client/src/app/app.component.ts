import { Component, ElementRef, inject, OnInit, ViewChild } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {UploadService} from './upload.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  fb = inject(FormBuilder)
  uploadSvc = inject(UploadService)

  @ViewChild('toUpload')
  toUpload!: ElementRef

  uploadForm!: FormGroup

  ngOnInit(): void {
    this.uploadForm = this.fb.group({
      description: this.fb.control<string>('')
    })
  }

  processForm() {
    const value = this.uploadForm.value
    this.uploadSvc.upload(value['description'], this.toUpload)
      .then(resp => {
        console.info('>>>> resp: ', resp)
      })
      .catch(error => {
        console.error('error: ', error)
      })
  }
}
