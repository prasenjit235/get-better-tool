import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup, FormArray, FormControl, ValidatorFn } from '@angular/forms';
import { AjaxService } from './../services/ajax.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  title: string = 'Get Better Tool';
  loginForm: any;

  constructor(
    private ajaxService: AjaxService,
    private formBuilder:FormBuilder
  ) {
    this.loginForm = this.formBuilder.group({
      'empId': ['', [Validators.required]],
      'pwd': ['', [Validators.required]]
    });
  }

  ngOnInit() {
  }

  login():void {
    if(this.loginForm.valid){
      let formData = {
        'username' : this.loginForm.controls["empId"].value,
        'password' : this.loginForm.controls["pwd"].value
      };

      this.ajaxService.userLogin(formData)
          .then(data => {
              console.log(data);
          });
    }
  }

}
