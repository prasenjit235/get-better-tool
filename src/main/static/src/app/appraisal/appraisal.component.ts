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
    { id : 1081 , name : "Prasen" },
    { id : 1089 , name : "Mallik" },
  ];
  searchForm: any;
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
  }

  ngOnInit() {
    this.getCategories();
  }

  getCategories():void {
    this.ajaxService.getCategories()
        .then(data => {
            this.categories = data;
            for(let cat in this.categories){
              console.log(cat)
            }
            this.getParameters(0);
        });
  }

  getParameters(i):void {
    let catId = this.categories[i].id;
    this.parameters = this.categories.filter(category => category.id === catId);
    console.log(this.parameters)
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

}
