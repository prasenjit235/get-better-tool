import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ContentComponent } from './content/content.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ParametersComponent } from './parameters/parameters.component';

const routes: Routes = [
  {
      path: '',
      redirectTo: '/content',
      pathMatch: 'full'
  },
  {
      path: 'login',
      component: LoginComponent,
  },
  {
      path: 'forgotPassword',
      component: ForgotPasswordComponent,
  },
  {
      path: 'content',
      component: ContentComponent,
      children: [
          { path: 'dashboard', component: DashboardComponent },
          { path: 'parameters', component: ParametersComponent },
          { path: '**', redirectTo: '/content/dashboard', pathMatch: 'full' }
      ]
  },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
