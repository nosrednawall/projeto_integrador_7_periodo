import { Component } from '@angular/core';

/**
 * Generated class for the PlotComponent component.
 *
 * See https://angular.io/api/core/Component for more info on Angular
 * Components.
 */
@Component({
  selector: 'plot',
  templateUrl: 'plot.html'
})
export class PlotComponent {

  text: string;

  constructor() {
    console.log('Hello PlotComponent Component');
    this.text = 'Hello World';
  }

}
