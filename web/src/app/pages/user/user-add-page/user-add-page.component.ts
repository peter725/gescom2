import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from "@base/pages/user/user.service";
import {Router} from "@angular/router";


@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'tsw-user-add-page',
  templateUrl: './user-add-page.component.html',
  styleUrls: ['./user-add-page.component.scss'],

})
export class UserAddPageComponent implements OnInit {

  form! : FormGroup;

  constructor(
      public userService : UserService,
      private router : Router
  ) { }

  ngOnInit(): void {

    this.form = new FormGroup({
      name: new FormControl('', [Validators.required]),
      firstSurname: new FormControl('', [Validators.required]),
      secondSurname: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required]),
      nif: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
    });
  }

  submit(){
    console.log(this.form.value);
    this.userService.add(this.form.value).subscribe((res:any) => {
      console.log('Post created successfully!');
      this.router.navigateByUrl('post/index');
    })
  }


}
