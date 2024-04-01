import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

interface CampaignMinimal {
  id: number | null;
  nameCampaign: string | null;
}

@Injectable({
  providedIn: 'root'
})
export class DataSharingService {
  private campaignSource = new BehaviorSubject<CampaignMinimal>({ id: null, nameCampaign: null });
  currentCampaign = this.campaignSource.asObservable();

  constructor() { }

  changeCampaign(campaign: CampaignMinimal) {
    this.campaignSource.next(campaign);
  }
}
