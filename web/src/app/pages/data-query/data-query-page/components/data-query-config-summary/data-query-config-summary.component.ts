import { Component, OnInit } from "@angular/core";
import { DataQueryService } from "../../data-query.service";


@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: "tsw-data-query-config-summary",
  templateUrl: "./data-query-config-summary.component.html",
  styleUrls: ["./data-query-config-summary.component.scss"],
})
export class DataQueryConfigSummaryComponent implements OnInit {
  constructor(
    private service: DataQueryService,
  ) {}

  ngOnInit(): void {}
}
