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
  editableData: any;

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
              seWeightageId: '',
              seWeightage: '',
              sseWeightageId: '',
              sseWeightage: '',
              taWeightageId: '',
              taWeightage: ''
            };
            for(let k = 0; k < data[i]['parameters'][j]['weightages'].length; k++){
              let score = data[i]['parameters'][j]['weightages'][k]['weightage'];
              let weightage = data[i]['parameters'][j]['weightages'][k]['weightageId'];

              if(data[i]['parameters'][j]['weightages'][k]['designationId'] === 1){
                jsonData['seWeightage'] = score;
                jsonData['seWeightageId'] = weightage;
              }else if(data[i]['parameters'][j]['weightages'][k]['designationId'] === 2){
                jsonData['sseWeightage'] = score;
                jsonData['sseWeightageId'] = weightage;
              }else{
                jsonData['taWeightage'] = score;
                jsonData['taWeightageId'] = weightage;
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
          "weightages" : this.getWeigtagesData()
        };

        if(this.submitType === 'Update' && this.editableData){
          let catId = this.editableData.categoryId;
          let paramId = this.editableData.parameterId;
          formData['parameterId'] = paramId;
          this.ajaxService.updateParameter(formData,catId,paramId)
              .then(data => {
                this.editableData = null;
                this.showNew = false;
                this.getCategories();
              });
        }else{
          this.ajaxService.addParameter(formData,catId)
              .then(data => {
                this.showNew = false;
                this.getCategories();
              });
        }
    }
  }

  updateParam(data):void {
    this.editableData = data;
    this.showNew = true;
    this.submitType = 'Update';
    this.title = 'Update Parameters';
    this.parametersForm.controls['categoryId'].setValue(data.categoryId);
    this.parametersForm.controls['parameterName'].setValue(data.parameterName);
    this.parametersForm.controls['se_weightage'].setValue(data.seWeightage);
    this.parametersForm.controls['sse_weightage'].setValue(data.sseWeightage);
    this.parametersForm.controls['ta_weightage'].setValue(data.taWeightage);
  }

  deleteParam(data):void {
    this.ajaxService.deleteParameter(data.categoryId,data.parameterId)
        .then(data => {
          if(data === "OK"){
            this.getCategories();
          }
        });
  }

  getWeigtagesData() {
    let data = [];
    for(let i = 1; i < 9; i++){
      let obj = {};
      switch(i){
        case 1:
          obj['weightage'] = this.parametersForm.controls["se_weightage"].value;
          obj['designationId'] = i;
          obj['status'] = 1;
          if(this.editableData){
            obj['weightageId'] = this.editableData['seWeightageId'];
          }
          break;
        case 2:
          obj['weightage'] = this.parametersForm.controls["sse_weightage"].value;
          obj['designationId'] = i;
          obj['status'] = 1;
          if(this.editableData){
            obj['weightageId'] = this.editableData['sseWeightageId'];
          }
          break;
        default:
          obj['weightage'] = this.parametersForm.controls["ta_weightage"].value;
          obj['designationId'] = i;
          obj['status'] = 1;
          if(this.editableData){
            obj['weightageId'] = this.editableData['taWeightageId'];
          }
          break;
      }
      data.push(obj);
    }

    return data;
  }
}
