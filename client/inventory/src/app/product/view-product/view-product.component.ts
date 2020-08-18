import { Component, OnInit,Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ProductService } from '../../_services/product.service';
import { Product } from '../../_models/product'

@Component({
  selector: 'app-view-product',
  templateUrl: './view-product.component.html',
  styleUrls: ['./view-product.component.css']
})
export class ViewProductComponent implements OnInit {

    @Input() product: Product;
    public url;

    constructor(private activeModal: NgbActiveModal, private productService: ProductService) {

    }

    ngOnInit() {
        this.product.create_date = this.formatdate(this.product.create_date);
        this.product.update_date = this.formatdate(this.product.update_date);
    }
    close() {
        this.activeModal.close("ok i fine");
    }
    formatdate(a) {
        var year = a.slice(6, 9);
        var month = a.slice(3, 4);
        var day = a.slice(0, 1);
        debugger
        return [year, month, day].join('-');

    }

}
