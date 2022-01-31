package converter.controller;
import java.time.LocalDate;

import converter.gui.MainFrame;
import converter.logic.MainEngine;

public class Controller {

    private MainEngine mainEngine;
    private MainFrame mainFrame;

    public void run() {
        this.mainEngine = new MainEngine(this);
        this.mainFrame = new MainFrame(this);                
    }    

    public void processConvertActionInLogic(Currency currencyCodeFROM, Currency currencyCodeTO, 
                                            double amount, LocalDate inquiredDate) {
        this.mainEngine.processConvertActionInLogic(currencyCodeFROM, currencyCodeTO,
                                                    amount, inquiredDate);
    }

    public void showUsualResultInGUI(Currency currencyCodeFROM, Currency currencyCodeTO,
                                     double amount, LocalDate responseDate, double result) {
        this.mainFrame.showUsualResultInGUI(currencyCodeFROM, currencyCodeTO,
                                            amount, responseDate, result);
    }

    public void showBackwardResultInGUI(Currency currencyCodeFROM, Currency currencyCodeTO,
                                        double amount, LocalDate responseDate, double result) {
        this.mainFrame.showBackwardResultInGUI(currencyCodeFROM, currencyCodeTO,
                                               amount, responseDate, result);
    }

    public void showFailedResultInGUI() {
        this.mainFrame.showFailedResultInGUI();
    }
    
}
