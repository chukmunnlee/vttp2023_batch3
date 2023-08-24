import { Injectable } from "@angular/core";
import { User } from "./models";
import { ulid } from 'ulid'

@Injectable()
export class StorageService {

  lastKey = ''

  save(user: User) {
    const id = ulid()
    sessionStorage.setItem(id, JSON.stringify(user))
    this.lastKey = id
    return id
  }

  load(id: string): User | null {
    const data = sessionStorage.getItem(id)
    if (!data)
      return null
    return JSON.parse(data) as User
  }

  remove(id: string) {
    sessionStorage.removeItem(id)
  }

}
