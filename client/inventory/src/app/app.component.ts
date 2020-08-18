import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User, Role } from './_models';
import { AuthenticationService} from './_services';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
    currentUser: User;
    title = 'inventory';
    isShown: boolean = false;
    constructor(
        private router: Router,
        private authenticationService: AuthenticationService,
    ) {
        this.authenticationService.currentUser.subscribe(x => {
            this.currentUser = x;
           
        });}
    get isAdmin() {
        return this.currentUser && this.currentUser.role === Role.ADMIN;
    }

    get isUser() {
        return this.currentUser && this.currentUser.role === Role.STAFF;
    }
}
