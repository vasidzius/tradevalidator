import {Component} from '@angular/core';
import {ValidatorService} from "./validator.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: [ './app.component.css' ]
})
export class AppComponent {

  constructor(
    private validatorService: ValidatorService
  ) {
  }

  textValue = 'put json array of trades here ' +
    '\n\n example:' +
    '\n\n' +
    '[\n' +
    '  {\n' +
    '    "customer": "PLUTO1",\n' +
    '    "ccyPair": "EURUSD",\n' +
    '    "type": "Spot",\n' +
    '    "direction": "BUY",\n' +
    '    "tradeDate": "2016-08-11",\n' +
    '    "amount1": 1000000.00,\n' +
    '    "amount2": 1120000.00,\n' +
    '    "rate": 1.12,\n' +
    '    "valueDate": "2016-08-15",\n' +
    '    "legalEntity": "CS Zurich",\n' +
    '    "trader": "JohannBaumfiddler"\n' +
    '  },{\n' +
    '    "customer": "PLUTO3",\n' +
    '    "ccyPair": "EURUSD",\n' +
    '    "type": "VanillaOption",\n' +
    '    "style": "AMERICAN",\n' +
    '    "direction": "SELL",\n' +
    '    "strategy": "CALL",\n' +
    '    "tradeDate": "2016-08-11",\n' +
    '    "amount1": 1000000.00,\n' +
    '    "amount2": 1120000.00,\n' +
    '    "rate": 1.12,\n' +
    '    "deliveryDate": "2016-08-22",\n' +
    '    "expiryDate": "2016-08-19",\n' +
    '    "exerciseStartDate": "2016-08-10",\n' +
    '    "payCcy": "USD",\n' +
    '    "premium": 0.20,\n' +
    '    "premiumCcy": "USD",\n' +
    '    "premiumType": "%USD",\n' +
    '    "premiumDate": "2016-08-12",\n' +
    '    "legalEntity": "CS Zurich",\n' +
    '    "trader": "Johann Baumfiddler"\n' +
    '  }\n' +
    ']';

  validationResults: string = 'Validation results will appear here';

  validateTrades( value: string ): void {
    this.validatorService.validateTrades(value)
      .subscribe(
        () => {
          this.validationResults = 'Trades are valid';
        },
        ( err: HttpErrorResponse ) => {
          if (err.status == 400) {
            this.validationResults = ( err.error );
          }
        }
      )
  }

  shutdown(): void {
    this.validatorService.shutdownServer()
      .subscribe( () => {});
  }
}
