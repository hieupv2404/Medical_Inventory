import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service'

import { Product } from '../_models'

@Injectable({ providedIn: 'root' })

export class ProductService {

    constructor(private http: HttpClient,
        private authenticationService: AuthenticationService) { }

    public register(product): Observable<any> {
        return this.http.post(`http://localhost:8080/product`, product);
    }
    public update(product): Observable<any> {
        return this.http.put(`http://localhost:8080/product/${product.id}`, product);
    }
    public delete(id: number) {
        console.log(id)
        return this.http.delete(`http://localhost:8080/product/${id}`);
    }
    public getAll() {
        debugger
        return this.http.get<Product[]>(`http://localhost:8080/product`, {

            headers: {
                AuthToken: `${this.authenticationService.currentUserValue.token}`

            }
        });
    }

}
