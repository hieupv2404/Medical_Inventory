import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service'

import { Category } from '../_models'

@Injectable({ providedIn: 'root' })

export class CategoryService {
    
    constructor(private http: HttpClient,
        private authenticationService: AuthenticationService) { }

    public registerCategory(category): Observable<any> {
        return this.http.post(`http://localhost:8080/category/add`, category);
    }
    public update(category): Observable<any> {
        return this.http.put(`http://localhost:8080/category/edit/${category.id}`, category);
    }
    public deleteCategory(id: number) {
        console.log(id)
        return this.http.get(`http://localhost:8080/category/delete/${id}`);
    }
    public getAllCategory() {
        debugger
        return this.http.get<Category[]>(`http://localhost:8080/category`, {

            headers: {
                AuthToken: `${this.authenticationService.currentUserValue.token}`

            }});
    }
   
}
