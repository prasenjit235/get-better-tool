import { Injectable } from '@angular/core';
import { Http, Headers, URLSearchParams, RequestOptions, ResponseContentType } from '@angular/http';
import { environment } from './../../environments/environment';

import * as _ from 'underscore';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AjaxService {

  routesArray = [];
  constructor(
    private http:Http
  ) {
    this.routesArray = ['addchildcare','addchildcareaddress','getsubscriptions','addsubscription','addchildcareadmin','authenticate'];
  }

  doPost(url,params): Promise<String>{
    return new Promise((resolve,reject)=>{
      let self =this;

      let headers = new Headers();
      headers.set('Content-Type','application/json');

      this.http.post(environment.apiUrl+url,params,{headers:headers})
      .toPromise().then(
          data => {
            let response = JSON.parse(data["_body"]);
            resolve(response);
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

        this.http.get(environment.apiUrl+url,{search:params,headers:headers})
        .toPromise().then(
            data => {
              let response = JSON.parse(data["_body"]);
              resolve (response);
            }
          ).catch(e =>{
            console.log(e);
          });
      });
  }

  doPut(url,params): Promise<String>{
    return new Promise((resolve,reject)=>{
      let self =this;
      let loginMessage:string = "";
      let headers = new Headers();
      headers.set('Content-Type','application/json');

      this.http.put(environment.apiUrl+url,params,{headers:headers})
      .toPromise().then(
          data => {
            let response = JSON.parse(data["_body"]);
            resolve(response);
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

      this.http.delete(environment.apiUrl+url,{headers:headers})
      .toPromise().then(
          data => {
            console.log(data);
          }
        ).catch(e =>{
          console.log(e);
        });
      });
  }

  getCategories(): Promise<String>{
    return this.doGet('categories',"");
  }

  addParameter(data,catId): Promise<String>{
    return this.doPost('categories/'+catId+'/parameters',data);
  }

  updateParameter(data,catId,paramId): Promise<String>{
    return this.doPut('categories/'+catId+'/parameters/'+paramId,data);
  }

  deleteParameter(catId,paramId): Promise<String>{
    return this.doDelete('categories/'+catId+'/parameters/'+paramId);
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
