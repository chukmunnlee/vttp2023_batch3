import { Injectable } from "@angular/core";
import { ulid } from 'ulid'

import Dexie from 'dexie'
import { User, UserSummary } from "./models";

@Injectable()
export class UserService extends Dexie {

  userCol: Dexie.Table<User, string>

  constructor() {
    // the database name
    super('userdb')
    // Construct the collection
    this.version(1).stores({
      user: 'userId'
    })

    this.userCol = this.table('user')
  }

  save(data: User) {
    data.userId = ulid()
    return this.userCol.add(data)
  }

  findUserById(userId: string) {
    return this.userCol.get(userId)
  }

  loadUsers(): Promise<UserSummary[]> {
    return this.userCol.toArray()
      .then(users => users.map(u => {
        return {
          userId: u.userId,
          name: u.name
        } as UserSummary
      }))
  }

}
