package converter;
import java.time.LocalDate;

import converter.logic.MainEngine;
import converter.logic.utils.Currency;

public class Controller {

    private MainEngine mainEngine;

    public void run() {
        this.mainEngine = new MainEngine(this);

        Currency currencyCodeFROM = Currency.PLN;
        Currency currencyCodeTO = Currency.USD;        
        double amount = 1;        
        LocalDate inquiredDate = LocalDate.parse("2022-01-28");

        processConvertActionInLogic(currencyCodeFROM, currencyCodeTO, amount, inquiredDate);
    }    

    public void processConvertActionInLogic(Currency currencyCodeFROM, Currency currencyCodeTO, 
                                            double amount, LocalDate inquiredDate) {
        this.mainEngine.processConvertActionInLogic(currencyCodeFROM, currencyCodeTO,
                                                    amount, inquiredDate);
    }

    public void showUsualResultInGUI(double amount, double result, LocalDate responseDate, 
                                     Currency currencyCodeFROM, Currency currencyCodeTO) {
        System.out.println("USUAL RESPONSE:");
        System.out.printf("%.2f of %s = %.2f of %s\n", amount, currencyCodeFROM.toString(),
                                                     result, currencyCodeTO.toString());
        System.out.printf("date: %s", responseDate.toString());
    }

    public void showBackwardResultInGUI(double amount, double result, LocalDate responseDate, 
                                        Currency currencyCodeFROM, Currency currencyCodeTO) {
        System.out.println("BACKWARD RESPONSE:");
        System.out.printf("%.2f of %s = %.2f of %s\n", amount, currencyCodeFROM.toString(),
                                                        result, currencyCodeTO.toString());
        System.out.printf("date: %s", responseDate.toString());
    }

    public void showFailedResultInGUI() {
        System.out.println("ERROR: No such data exists.");
    }
    
}
