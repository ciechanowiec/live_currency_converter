/** 
 * # Live Currency Converter
 * 
 * ## Table of Contents
 * 1. Basic Info
 * 2. Features
 * 3. Conversion Mechanism
 * 4. Architecture
 * 5. National Bank of Poland API
 * 6. License
 * 
 * ## Basic Info
 * This program is a desktop live currency converter. It supports 36 currencies and retrieves 
 * data on the exchange rates from National Bank of Poland in real time. The program provides
 * currency conversion for any day since 1 January 2002 till now.
 * 
 * | Parameter                         | Data                                        |
 * | :-------------------------------: | :-----------------------------------------: |
 * | Program name:                     | Live Currency Converter                     |
 * | Date of creation:                 | January 2022                                |
 * | Technologies used:                | Java SE, HTTP, JSON, LGoodDatePicker, Maven |
 * | Time spent to create the program: | ~2 days                                     |
 * | Author:                           | Herman Ciechanowiec, herman@ciechanowiec.eu |
 * | License:                          | MIT No Attribution License                  |
 * 
 * ## Features
 * The program has the following features:
 * - graphical user interface
 * - online retrieving of exchange rates from National Bank of Poland
 * - 36 supported currencies
 * - historical conversion for any day after 1 January 2002
 * - handling incorrect user input
 * - in-built calendar
 * 
 * ## Conversion Mechanism
 * 1. The program is based on exchange rates published by National Bank of Poland in 'Average foreign exchange 
 *    rates - table A', currently handled according to the Resolution No. 51/2002 of the Management Board of 
 *    National Polish Bank dated 23 September 2002.
 * 
 * 2. National Bank of Poland (the 'NBP') provides only the exchange rates from foreign currencies (i.e. from 
 *    currencies other than Polish Złoty - PLN) to the domestic currency (i.e. to Polish Złoty - PLN), such as 
 *    the exchange rate of USD → PLN. NBP doesn't provide direct exchange rates between foreign currencies 
 *    (e.g. the exchange rate of USD → AUD).
 * 
 * 3. Since NBP doesn't provide direct exchange rates between foreign currencies the result of the conversion 
 *    is inferred from the PLN value of each of the compared currencies according to the following algorithm:
 *     - get the PLN exchange value of one unit of foreign currency from 
 *       which the conversion is processed (the ‘Exchange Value FROM’),
 *     - get the PLN exchange value of one unit of foreign currency to 
 *       which the conversion is processed (the ‘Exchange Value TO’),
 *     - divide the Exchange Value FROM by the Exchange Value TO,
 *     - multiply the result of the above division by the total amount of money to 
 *       be exchanged - the product of this multiplication is the final result.
 *     
 * 4. NBP doesn’t provide exchange rates for every day after 1 January 2002 and for each of the 36 currencies 
 *    supported by Live Currency Converter. For instance, as of 1 January 2002 NBP provided exchange rates for 
 *    only 27 currencies. Therefore, in cases when a user tries to make a conversion which cannot be handled 
 *    basing on the exchange rates provided by NBP, the program uses the following logic:
 *     - if NBP hasn’t published the exchange rate for the inquired day for the inquired currencies, the program 
 *       sequentially tries to retrieve the exchange rate for the inquired currencies from exchange rates published 
 *       the previous day, but not further than 5 days backwards from the originally inquired day,
 *     - if the above inquiry is unsuccessful, the program will show a message about the unsuccessful inquiry.
 * 
 * ## Architecture
 * The program is divided into three parts: front-end (graphical user interface), back-end (logic of the program)
 * and the controller, which handles a relationship between the front-end and the back-end. The connection with 
 * NBP’s server is established at the back-end level.
 * 
 * ## National Bank of Poland API
 * 1. Official description of NBP’s API used by the program is published here: http://api.nbp.pl/en.html
 * 
 * 2. The program retrieves data from NBP by HTTP in JSON format.
 * 
 * ## License
 * The program is subject to MIT No Attribution License
 * 
 * Copyright © 2022 Herman Ciechanowiec
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so.
 * 
 * The Software is provided "as is", without warranty of any kind, express or implied,
 * including but not limited to the warranties of merchantability, fitness for a
 * particular purpose and noninfringement. In no event shall the authors or copyright
 * holders be liable for any claim, damages or other liability, whether in an action
 * of contract, tort or otherwise, arising from, out of or in connection with the
 * Software or the use or other dealings in the Software.
 * */

package converter;

import converter.controller.Controller;

public class AppRunner {    

    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.run();

    }
}
