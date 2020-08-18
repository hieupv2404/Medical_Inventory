import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeuserComponent } from './homeuser';
import { HomeadminComponent } from './homeadmin';
import { CategoryComponent } from './category';
import { LoginComponent } from './login';
import { ProductComponent } from './product';
import { Role } from './_models';
import { AuthGuard } from './_guards';
const routes: Routes = [
    {
        path: '',
        component: LoginComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'home',
        component: HomeuserComponent,
        canActivate: [AuthGuard],
        data: { roles: [Role.STAFF] }
    },
    {
        path: 'home',
        component: HomeadminComponent,
        canActivate: [AuthGuard],
        data: { roles: [Role.ADMIN] }
    },
    {
      path: 'login',
      component: LoginComponent
    },
    {
        path: 'category',
        component: CategoryComponent,
        canActivate: [AuthGuard],
        data: { roles: [Role.ADMIN] }
    },
    {
        path: 'product',
        component: ProductComponent,
        canActivate: [AuthGuard],
        data: { roles: [Role.ADMIN] }
    },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
