import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup, FormArray, FormControl, ValidatorFn } from '@angular/forms';
import { AjaxService } from './../services/ajax.service';

@Component({
  selector: 'app-parameters',
  templateUrl: './parameters.component.html',
  styleUrls: ['./parameters.component.css']
})
export class ParametersComponent implements OnInit {

  parametersForm: any;
  categories: any = [];
  parameters: any;
  showNew: Boolean = false;
  submitType: string = 'Save';
  title: string = 'Create Parameters';
  config = [
    {
      type: 'input',
      label: 'Full name',
      name: 'name',
      placeholder: 'Enter your name',
    },
    {
      type: 'select',
      label: 'Favourite food',
      name: 'food',
      options: ['Pizza', 'Hot Dogs', 'Knakworstje', 'Coffee'],
      placeholder: 'Select an option',
    },
    {
      label: 'Submit',
      name: 'submit',
      type: 'button',
    },
  ];

  constructor(
    private ajaxService: AjaxService,
    private formBuilder:FormBuilder
  ) {
    this.parametersForm = this.formBuilder.group({
      'categoryId': ['', [Validators.required]],
      'parameterName': ['', [Validators.required]],
      'se_weightage': ['', [Validators.required]],
      'sse_weightage': ['', [Validators.required]],
      'ta_weightage': ['', [Validators.required]]
    });
  }

  ngOnInit() {
    this.getCategories();
  }

  onNew():void {
    this.showNew = true;
    this.submitType = 'Save';
  }

  onCancel():void {
    this.showNew = false;
  }

  getCategories():void {
    this.ajaxService.getCategories()
        .then(data => {
            this.categories = data;
            this.getParameters();
        });
  }

  getParameters():void {
    let data = this.categories;
    let paramsArray = [];
    for(let i = 0;i < data.length; i++){
      if(data[i]['parameters'] && data[i]['parameters'].length > 0){

        for(let j = 0; j < data[i]['parameters'].length; j++){
          if(data[i]['parameters'][j]['weightages'] && data[i]['parameters'][j]['weightages'].length > 0){
            let jsonData = {
              categoryId: data[i]['id'],
              categoryName: data[i]['name'],
              parameterId: data[i]['parameters'][j]['parameterId'],
              parameterName: data[i]['parameters'][j]['parameterName'],
              seWeightage: '',
              sseWeightage: '',
              taWeightage: ''
            };
            for(let k = 0; k < data[i]['parameters'][j]['weightages'].length; k++){
              let score = data[i]['parameters'][j]['weightages'][k]['weightage'];

              if(data[i]['parameters'][j]['weightages'][k]['designationId'] === 1){
                jsonData['seWeightage'] = score;
              }else if(data[i]['parameters'][j]['weightages'][k]['designationId'] === 2){
                jsonData['sseWeightage'] = score;
              }else{
                jsonData['taWeightage'] = score;
              }
            }
            paramsArray.push(jsonData);
          }
        }
      }
    }
    this.parameters = paramsArray;
  }

  addParameters():void {
    if(this.parametersForm.valid){
        let catId = this.parametersForm.controls["categoryId"].value;

        let formData = {
          "parameterName": this.parametersForm.controls["parameterName"].value,
          "categoryId" : catId,
          "status" : 1,
          "weightages" : [
                  {
                      "weightage" : this.parametersForm.controls["se_weightage"].value,
                      "designationId" : 1,
                      "status" : 1
                  },
                  {
                      "weightage" : this.parametersForm.controls["sse_weightage"].value,
                      "designationId" : 2,
                      "status" : 1
                  },
                  {
                      "weightage" : this.parametersForm.controls["ta_weightage"].value,
                      "designationId" : 3,
                      "status" : 1
                  },
                  {
                      "weightage" : this.parametersForm.controls["ta_weightage"].value,
                      "designationId" : 4,
                      "status" : 1
                  },
                  {
                      "weightage" : this.parametersForm.controls["ta_weightage"].value,
                      "designationId" : 5,
                      "status" : 1
                  },
                  {
                      "weightage" : this.parametersForm.controls["ta_weightage"].value,
                      "designationId" : 6,
                      "status" : 1
                  },
                  {
                      "weightage" : this.parametersForm.controls["ta_weightage"].value,
                      "designationId" : 7,
                      "status" : 1
                  },
                  {
                      "weightage" : this.parametersForm.controls["ta_weightage"].value,
                      "designationId" : 8,
                      "status" : 1
                  }
                ]
        };

        this.ajaxService.addParameter(formData,catId)
            .then(data => {
              this.getCategories();
            });
    }
  }

  updateParam(data):void {
    this.showNew = true;
    this.submitType = 'Update';
    this.parametersForm.controls['categoryId'].setValue(data.categoryId);
    this.parametersForm.controls['parameterName'].setValue(data.parameterName);
    this.parametersForm.controls['se_weightage'].setValue(data.seWeightage);
    this.parametersForm.controls['sse_weightage'].setValue(data.sseWeightage);
    this.parametersForm.controls['ta_weightage'].setValue(data.taWeightage);

    if(this.parametersForm.valid){
        let catId = this.parametersForm.controls["categoryId"].value;

        let formData = {
          "parameterName": this.parametersForm.controls["parameterName"].value,
          "categoryId" : catId,
          "status" : 1,
          "weightages" : [
                  {
                      "weightage" : this.parametersForm.controls["se_weightage"].value,
                      "designationId" : 1,
                      "status" : 1
                  },
                  {
                      "weightage" : this.parametersForm.controls["sse_weightage"].value,
                      "designationId" : 2,
                      "status" : 1
                  },
                  {
                      "weightage" : this.parametersForm.controls["ta_weightage"].value,
                      "designationId" : 3,
                      "status" : 1
                  },
                  {
                      "weightage" : this.parametersForm.controls["ta_weightage"].value,
                      "designationId" : 4,
                      "status" : 1
                  },
                  {
                      "weightage" : this.parametersForm.controls["ta_weightage"].value,
                      "designationId" : 5,
                      "status" : 1
                  },
                  {
                      "weightage" : this.parametersForm.controls["ta_weightage"].value,
                      "designationId" : 6,
                      "status" : 1
                  },
                  {
                      "weightage" : this.parametersForm.controls["ta_weightage"].value,
                      "designationId" : 7,
                      "status" : 1
                  },
                  {
                      "weightage" : this.parametersForm.controls["ta_weightage"].value,
                      "designationId" : 8,
                      "status" : 1
                  }
                ]
        };

        this.ajaxService.updateParameter(formData,catId,data.parameterId)
            .then(data => {
              this.showNew = false;
              this.getCategories();
            });
      }
  }

  deleteParam(data):void {
    this.ajaxService.deleteParameter(data.categoryId,data.parameterId)
        .then(data => {
          this.getCategories();
        });
  }

}
