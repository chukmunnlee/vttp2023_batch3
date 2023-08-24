import { inject } from "@angular/core";
import { CanActivateFn, CanDeactivateFn, Router } from "@angular/router";
import { UserService } from "./user.service";
import { FormComponent } from "./components/form.component";

export const checkUser: CanActivateFn = (routeSnapshot, state) => {
  const userSvc = inject(UserService)
  const router = inject(Router)
  const userId = routeSnapshot.params['userId']
  return userSvc.findUserById(userId)
    .then(result => {
      if (result)
        return true
      return router.createUrlTree(['/notfound'])
      //return false
    })

}

export const checkIsDirtyForm: CanDeactivateFn<FormComponent> = (comp, currRt, currSt, nextSt) => {
  console.info('>> in check form')
  if (comp.form.dirty)
    return confirm('You have not saved the form.\nAre you sure you wish to leave?')
  return true
}
