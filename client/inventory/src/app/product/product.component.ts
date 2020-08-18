import { Component, OnInit } from '@angular/core';
import { ProductService } from '../_services/product.service';
import { AuthenticationService } from '../_services/authentication.service';
import { Product } from '../_models';
import { User, Role } from '../_models';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ViewProductComponent } from './view-product/view-product.component'
@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

    products: Product[];
    constructor(private productService: ProductService, private authenticationService: AuthenticationService,
        private modal: NgbModal) {
        this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    }
    currentUser: User;
    sortType = 'name';
    type = 'name'
    sortReverse = false;
    public types: any = [
        {
            type: 'ID',
            value: 'id'
        },
        {
            type: 'Name',
            value: 'name'
        },
        {
            type: 'Create Date',
            value: 'create_date'
        }, {
            type: 'Update Date',
            value: 'update_date'
        }
    ];
    ngOnInit() {
        this.getAll();

    }
    getAll(): void {
        this.productService.getAll().subscribe((res: any) => {
            debugger
            console.log(res);
            if (res) {
                this.products = res;
            }


            console.log(res);
        });
    };
    openPopup1(product) {
        const modalRef = this.modal.open(ViewProductComponent, { size: 'lg' })
        modalRef.componentInstance.product = product;

        modalRef.result.then((result) => {
          
        }).catch((error) => {
            console.log(error);
            debugger
        });
    }

}
