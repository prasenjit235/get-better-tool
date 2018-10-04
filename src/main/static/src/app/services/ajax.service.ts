import { Injectable } from '@angular/core';
import { Http, Headers, URLSearchParams, RequestOptions, ResponseContentType } from '@angular/http';
import { environment } from './../../environments/environment';

import * as _ from 'underscore';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AjaxService {

  constructor(
    private http:Http
  ) { }

  doPost(url,params): Promise<String>{
    return new Promise((resolve,reject)=>{
      let self =this;

      let headers = new Headers();
      headers.set('Content-Type','application/json');
      headers.set('Authorization','Bearer ' + this.getUserAccessToken());

      this.http.post(environment.host+url,params,{headers:headers})
      .toPromise().then(
          data => {
            if(data['status'] === 201) {
              let response = JSON.parse(data["_body"]);
              resolve (response);
            }else{
              reject(data);
            }
          }
        ).catch(e =>{
          console.log(e);
        });
      });
  }

  doGet(url,params): Promise<String>{
      return new Promise((resolve,reject)=>{
        let self =this;
        let headers = new Headers();
        headers.set('Content-Type','application/json');
        headers.set('Authorization','Bearer ' + this.getUserAccessToken());

        this.http.get(environment.host+url,{search:params,headers:headers})
        .toPromise().then(
            data => {
              if(data['status'] === 200) {
                let response = JSON.parse(data["_body"]);
                resolve (response);
              }else{
                reject(data);
              }
            }
          ).catch(e =>{
            console.log("error",e);
          });
      });
  }

  doPut(url,params): Promise<String>{
    return new Promise((resolve,reject)=>{
      let self =this;
      let loginMessage:string = "";
      let headers = new Headers();
      headers.set('Content-Type','application/json');
      headers.set('Authorization','Bearer ' + this.getUserAccessToken());

      this.http.put(environment.host+url,params,{headers:headers})
      .toPromise().then(
          data => {
            if(data['status'] === 200) {
              let response = JSON.parse(data["_body"]);
              resolve (response);
            }else{
              reject(data);
            }
          }
        ).catch(e =>{
          console.log(e);
        });
      });
  }

  doDelete(url): Promise<String>{
    return new Promise((resolve,reject)=>{
      let self =this;
      let loginMessage:string = "";
      let headers = new Headers();
      headers.set('Content-Type','application/json');
      headers.set('Authorization','Bearer ' + this.getUserAccessToken());

      this.http.delete(environment.host+url,{headers:headers})
      .toPromise().then(
          data => {
            if(data['status'] === 200) {
              resolve (data['statusText']);
            }else{
              reject(data);
            }
          }
        ).catch(e =>{
          console.log(e);
        });
      });
  }

  doLogin(url,params): Promise<String>{
    return new Promise((resolve,reject)=>{
      let self =this;

      let headers = new Headers();
      headers.set('Content-Type','application/json');

      this.http.post(environment.host+url,params,{headers:headers})
      .toPromise().then(
          data => {
            if(data['status'] === 200) {
              let headers = data['headers']['_headers'][""[[Entries]]""][4].value;
              localStorage.setItem("token",headers);
              let response = JSON.parse(data["_body"]);
              resolve (response);
            }else{
              reject(data);
            }
          }
        ).catch(e =>{
          console.log(e);
        });
      });
  }

  userLogin(data): Promise<String>{
    return this.doLogin('login',data);
  }

  getCategories(): Promise<String>{
    return this.doGet('backoffice/appraisal/categories',"");
  }

  addParameter(data,catId): Promise<String>{
    return this.doPost('backoffice/appraisal/categories/'+catId+'/parameters',data);
  }

  updateParameter(data,catId,paramId): Promise<String>{
    return this.doPut('backoffice/appraisal/categories/'+catId+'/parameters/'+paramId,data);
  }

  deleteParameter(catId,paramId): Promise<String>{
    return this.doDelete('backoffice/appraisal/categories/'+catId+'/parameters/'+paramId);
  }

  serachAppraisals(empId,data): Promise<String>{
    let queryData = this.getSearchParams(data);
    return this.doGet('appraisals/employees/'+empId,queryData);
  }

  getUserAccessToken():string{
    let userToken = localStorage.getItem("token");
    if(userToken){
      return userToken;
    }
    return "";
  }

  getEmployeeId():string{
    let empId = localStorage.getItem("id");
    if(empId){
      return empId;
    }
    return "";
  }

  getSearchParams(searchData){
    let searchParams = new URLSearchParams();
    _.each(searchData, function(value,key) {
        searchParams.set(key, value);
    });
    return searchParams;
  }

  getFormData(credentials){
    let formData = new URLSearchParams();
    _.each(credentials, function(value,key) {
        formData.append(key, value);
    });
    return formData;
  }
}
