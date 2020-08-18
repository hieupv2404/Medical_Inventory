import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../_services/category.service';
import { AuthenticationService } from '../_services/authentication.service';
import { Category } from '../_models';
import { User, Role } from '../_models';
@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
    categorys: Category[];
    constructor(private categoryService: CategoryService, private authenticationService: AuthenticationService) {
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
            type: 'Description',
            value: 'description'
        }
    ];
    ngOnInit() {
        this.getAllCategory();

    }
    getAllCategory(): void {
        this.categoryService.getAllCategory().subscribe((res: any) => {
            debugger
            console.log(res);
            if (res) {
                this.categorys = res;
            }
            
          
            console.log(res);
        });
    };

}
