import { Component, Input, OnInit } from '@angular/core';
import { Protocol } from '@libs/sdk/protocol';

@Component({
  selector: 'stw-protocol-list',
  templateUrl: './protocol-list.component.html',
  standalone: true,
  styleUrls: ['./protocol-list.component.scss']

})
export class ProtocolListComponent {

  @Input() protocols: Protocol[] = [];

  constructor() { }

  ngOnInit() {
  }
}