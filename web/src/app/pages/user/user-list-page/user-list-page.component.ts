import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {UserService} from "@base/pages/user/user.service";
import {Router} from "@angular/router";
import {MatTableDataSource} from "@angular/material/table";
import {Paginator} from "@base/config/paginator";
import {UserCriterial} from "@base/pages/user/userCriterial";


@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'tsw-user-list-page',
  templateUrl: './user-list-page.component.html',
  styleUrls: ['./user-list-page.component.scss'],
})

export class UserListPageComponent implements AfterViewInit {

  displayedColumns: string[] = ['id', 'name', 'firstSurname', 'secondSurname', 'nif', 'email', 'password', 'accion'];
  userElement: UserElement[] = [];
  pagination = new Paginator();
  dataSource: any;
  userCriterial!: UserCriterial;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
      public userService: UserService,
      private router: Router
  ) {
  }

  ngAfterViewInit(): void {
    this.pagination.index = 0;
    this.pagination.size = 10;
    this.pagination.sort = "id";
    this.pagination.sortDirection = "ASC";

    this.userCriterial = new UserCriterial();

    this.userService.all(this.userCriterial).subscribe((res: any) => {

      console.log(res.content);

      res.content.forEach((item: { id: any; name: any; firstSurname: any; secondSurname: any; nif: any; email: any; password: any; state: any; }) => {

        let userItem: UserElement = {
          id: item.id,
          name: item.name,
          firstSurname: item.firstSurname,
          secondSurname: item.secondSurname,
          nif: item.nif,
          email: item.email,
          password: item.password,
          state: item.state
        };
        this.userElement.push(userItem)
      });

      this.dataSource = new MatTableDataSource<UserElement>(this.userElement);
      this.dataSource.paginator = this.paginator;

    });
  }
}
    export interface UserElement {
      id: number;
      name: string;
      firstSurname: string;
      secondSurname: string;
      nif: string;
      email: string;
      password: string;
      state: number;
    }


