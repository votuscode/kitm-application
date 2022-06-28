import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationGuard} from '~/app/core/security/authentication.guard';
// feature
import {RestaurantView, HomeView} from './components';

const routes: Routes = [
  {
    path: '', children: [
      {path: '', component: HomeView, data: {title: 'Restaurants'}},
      {path: 'restaurant/:menuId', component: RestaurantView, data: {title: 'Check menu'}},
    ],
    canActivate: [AuthenticationGuard],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
})
export class UserRoutingModule {
}
