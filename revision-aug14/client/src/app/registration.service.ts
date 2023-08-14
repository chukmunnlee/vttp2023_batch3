import { HttpClient } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";
import { Registration, RegistrationResponse } from "./models";
import { firstValueFrom } from "rxjs";

const URL = 'http://localhost:8080/api/register'
//const URL = 'https://important-key-production.up.railway.app/api/register'
//const URL = '/api/register'

@Injectable()
export class RegistrationService {

  http = inject(HttpClient)

  register(form: Registration): Promise<RegistrationResponse> {

    return firstValueFrom(
      this.http.post<RegistrationResponse>(URL, form)
    )

  }

}
