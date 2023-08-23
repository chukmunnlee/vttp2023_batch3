import {HttpClient} from "@angular/common/http";
import {ElementRef, inject, Injectable} from "@angular/core";
import {firstValueFrom} from "rxjs";

@Injectable()
export class UploadService {

  http = inject(HttpClient)

  upload(description: string, elemRef: ElementRef): Promise<any> {

    console.info('>>>> files: ', elemRef.nativeElement.files)

    // To create multipart
    const data = new FormData()
    data.set("description", description)
    data.set("myfile", elemRef.nativeElement.files[0])

    return firstValueFrom(
      this.http.post<any>('/upload', data)
    )
  }
}
