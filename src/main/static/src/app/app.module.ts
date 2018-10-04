import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { NgxAdminLteModule } from 'ngx-admin-lte';
import { DynamicFormModule } from './dynamic-form/dynamic-form.module';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './login/login.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { SideMenuComponent } from './side-menu/side-menu.component';
import { ContentComponent } from './content/content.component';
import { AppraisalComponent } from './appraisal/appraisal.component';
import { ParametersComponent } from './parameters/parameters.component';

import { AjaxService } from './services/ajax.service';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ForgotPasswordComponent,
    HeaderComponent,
    FooterComponent,
    SideMenuComponent,
    ContentComponent,
    ParametersComponent,
    AppraisalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    DynamicFormModule,
    HttpModule,
    HttpClientModule,
    NgxAdminLteModule
  ],
  providers: [AjaxService],
  bootstrap: [AppComponent]
})
export class AppModule { }
