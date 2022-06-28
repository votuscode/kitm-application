import {Component} from '@angular/core';
import {AuthorService} from '@api/api/author.service';
import {BookService} from '@api/api/book.service';
import {CategoryService} from '@api/api/category.service';
import {BehaviorSubject, forkJoin} from 'rxjs';
import {map} from 'rxjs/operators';
import {BookVm} from '~/app/shared/components/book-list/book-list.component';
import {asMap} from '~/app/shared/util/as-map';
import {changeDetection} from '~/change-detection.strategy';
import {RestaurantService} from "@api/api/restaurant.service";
import {MenuService} from '@api/api/menu.service';

@Component({
  template: `
    <app-layout>
      <div widget>
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"
               (input)="search(searchInput.value)" #searchInput>
      </div>
      <app-book-list [vms]="vm$ | async" [search]="search$ | async"></app-book-list>
    </app-layout>
  `,
  changeDetection,
})
export class HomeView {

  readonly search$ = new BehaviorSubject<string>('');

  readonly vm$ = forkJoin([
    this.bookService.getBooks(),
    this.authorService.getAuthors(),
    this.categoryService.getCategories(),
    this.restaurantService.getRestaurants(),
    this.menuService.getMenus()
  ]).pipe(
    map(([books, authors, categories, restaurants, menus]) => {
      const authorMap = asMap(authors);
      const categoryMap = asMap(categories);

      return restaurants.map((restaurant): BookVm => {
        return {
          name: restaurant.name,
          description: restaurant.description || 'No description',
          image: restaurant.image || '',
          context: [restaurant.name, restaurant.description].join(''),
          link: `/books/${restaurant.id}`,
          ordered: false,
        };
      });
    }),
  );

  constructor(
    readonly bookService: BookService,
    readonly authorService: AuthorService,
    readonly categoryService: CategoryService,
    readonly restaurantService: RestaurantService,
    readonly menuService: MenuService
  ) {
  }

  search = (value: string) => {
    this.search$.next(value);
  };
}
