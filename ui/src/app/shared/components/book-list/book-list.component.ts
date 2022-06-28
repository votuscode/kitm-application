import {Component, Input} from '@angular/core';
import {AsyncData} from '~/app/shared/util/async-data';
import {changeDetection} from '~/change-detection.strategy';

export interface BookVm {
  name: string;
  description: string;
  image: string;
  context: string;
  link: string;
  ordered: boolean;
}

const includes = (search: string) => (value: string) => value.toLowerCase().includes(search.toLowerCase());

@Component({
  selector: 'app-book-list',
  styles: [`
    .card {
      height: 19rem;
    }

    .card-body {
      overflow: hidden;
    }

    .card-title,
    .card-text {
      font-size: x-small;
    }

    .book-ordered {
      filter: opacity(0.5);
    }
  `],
  template: `
    <div class="row">
      <h2 *ngIf="loading">Loading...</h2>
      <p *ngIf="empty">List is empty.</p>
      <div class="col-lg-2 col-md-3 col-sm-4 col-xs-6" *ngFor="let vm of vms | appFilter:predicate(search)">
        <div class="card mt-3" [ngClass]="vm.ordered ? 'book-ordered' : ''">
          <a [routerLink]="vm.link">
            <img class="card-img-top" [src]="vm.image" alt="Book">
          </a>
          <div class="card-body">
            <h6 class="card-title">{{ vm.name }}</h6>
            <p class="card-text">{{ vm.description }}</p>
          </div>
        </div>
      </div>
    </div>
  `,
  changeDetection,
})
export class BookListComponent {

  @Input() vms: AsyncData<BookVm[]>;

  @Input() search: AsyncData<string>;

  readonly predicate = (search: AsyncData<string>) => ({context}: BookVm) => {
    return search ? [context].some(includes(search)) : true;
  };

  get loading() {
    return this.vms === null;
  }

  get empty() {
    return Array.isArray(this.vms) && this.vms.length === 0;
  }
}
