import { Component, inject, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UploadService} from '../upload.service';

@Component({
  selector: 'app-play',
  templateUrl: './play.component.html',
  styleUrls: ['./play.component.css']
})
export class PlayComponent implements OnInit {

  song = ""
  songId = ""
  songUrl = ""

  activatedRoute = inject(ActivatedRoute)
  uploadSvc = inject(UploadService)

  ngOnInit(): void {

    this.song = this.activatedRoute.snapshot.queryParams['name']
    this.songId = this.activatedRoute.snapshot.params['id']
    this.uploadSvc.getUrl(this.songId)
      .then(result => {
        // { url: the_url }
        this.songUrl = result['url']
        console.info('>>> ', this.songUrl)
      })

  }

}
