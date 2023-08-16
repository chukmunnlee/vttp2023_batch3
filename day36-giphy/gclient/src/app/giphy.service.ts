import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

const url = 'http://localhost:8080/api/search'

@Injectable()
export class GiphyService {

  constructor(private http: HttpClient) { }

  search(q: string): Observable<string[]> {
    const params = new HttpParams().set('q', q)
    return this.http.get<string[]>(url, { params })
  }

}

