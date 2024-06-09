import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataSharingService {
  searchKey: string = '';

  setSearchKey(key: any) {
    this.searchKey = key;
  }
  getSearchKey(): string {
    return this.searchKey;
  }
}