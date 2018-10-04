import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup, FormArray, FormControl, ValidatorFn } from '@angular/forms';
import { AjaxService } from './../services/ajax.service';

@Component({
  selector: 'app-appraisal',
  templateUrl: './appraisal.component.html',
  styleUrls: ['./appraisal.component.css']
})
export class AppraisalComponent implements OnInit {

  years = [
    { label : "2018 - 19", value : 2018 }
  ];
  months = [
    { label : "Jan" , value : 1 },
    { label : "Feb" , value : 2 },
    { label : "Mar" , value : 3 },
    { label : "Apr" , value : 4 },
    { label : "May" , value : 5 },
    { label : "Jun" , value : 6 },
    { label : "Jul" , value : 7 },
    { label : "Aug" , value : 8 },
    { label : "Sep" , value : 9 },
    { label : "Oct" , value : 10 },
    { label : "Nov" , value : 11 },
    { label : "Dec" , value : 12 },
  ];
  employees = [
    { id : 1058 , name : "Lokesh Matturti" },
    { id : 1002 , name : "Ranjith Ayinala" },
    { id : 1081 , name : "Prasenjit Das" },
    { id : 1089 , name : "Mallikarjun" },
  ];
  searchForm: any;
  appraisalForm: any;
  showNew: Boolean = false;
  searchResults: Boolean = false;
  title: string = 'Create Appraisal';
  categories: any = [];
  parameters: any;
  collapseArray: any = [];

  constructor(
    private ajaxService: AjaxService,
    private formBuilder:FormBuilder
  ) {
    this.searchForm = this.formBuilder.group({
      'employee': ['', [Validators.required]],
      'year': ['', [Validators.required]],
      'month': ['', [Validators.required]]
    });

    this.appraisalForm = this.formBuilder.group({
      'ownership': ['', [Validators.required]],
      'punctuality': ['', [Validators.required]],
      'collaboration': ['', [Validators.required]],
      'communication': ['', [Validators.required]],
      'delivery': ['', [Validators.required]],
      'quality': ['', [Validators.required]],
      'mentoring': ['', [Validators.required]],
      'design': ['', [Validators.required]],
      'tasks': ['', [Validators.required]],
      'performance': ['', [Validators.required]],
      'task_closure': ['', [Validators.required]],
      'ownership_commnets' : new FormControl(''),
      'punctuality_commnets' : new FormControl(''),
      'collaboration_commnets' : new FormControl(''),
      'communication_commnets' : new FormControl(''),
      'delivery_commnets' : new FormControl(''),
      'quality_commnets' : new FormControl(''),
      'mentoring_commnets' : new FormControl(''),
      'design_commnets' : new FormControl(''),
      'tasks_commnets' : new FormControl(''),
      'performance_commnets' : new FormControl(''),
      'task_closure_commnets' : new FormControl(''),
      'feedback': new FormControl('')
    });
  }

  ngOnInit() {
    //localStorage.setItem("token","eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMDA0IiwiZXhwIjoxNTM5NTI1NDMyfQ.At_Pp6pARiQyJkpxf3PhA5zw6UJCM6FRsOm2sOu0LTyz1rTEtyAcGLbYppe5pflR1330-MTczPsKnvlfAjexaQ");
  }

  getCategories():void {
    this.ajaxService.getCategories()
        .then(data => {
            this.categories = data;
        });
  }

  getParameters(i):void {
    let catId = this.categories[i].id;
    this.parameters = this.categories.filter(category => category.id === catId);
  }

  onNew():void {
    this.showNew = true;
  }

  onCancel():void {
    this.showNew = false;
  }

  serachAppraisal():void {
    if(this.searchForm.valid){
      let empId = this.searchForm.controls["employee"].value;
      console.log(empId)
      let queryData = {
        'financial-year' : this.searchForm.controls["year"].value,
        'month' : this.searchForm.controls["month"].value
      };
      this.ajaxService.serachAppraisals(empId,queryData)
          .then(data => {
              console.log(data);
          });
    }
  }

  addAppraisal():void {

  }

}
