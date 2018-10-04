import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ContentComponent } from './content/content.component';
import { AppraisalComponent } from './appraisal/appraisal.component';
import { ParametersComponent } from './parameters/parameters.component';

const routes: Routes = [
  {
      path: '',
      redirectTo: '/index',
      pathMatch: 'full'
  },
  {
      path: 'index',
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
          { path: 'appraisal', component: AppraisalComponent },
          { path: 'parameters', component: ParametersComponent },
          { path: '**', redirectTo: '/content/appraisal', pathMatch: 'full' }
      ]
  },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
