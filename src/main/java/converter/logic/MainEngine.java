package converter.logic;

import java.time.LocalDate;

import converter.Controller;
import converter.Currency;
import converter.logic.utils.Converter;
import converter.logic.utils.HTTPDriver;
import converter.logic.utils.RetrievedData;
import converter.logic.utils.RetrievedDataParser;
import converter.logic.utils.RetrievedData.ResponseType;

public class MainEngine {

    private Controller controller;
    HTTPDriver httpDriver;
    RetrievedDataParser retrievedDataParser;
    Converter converter;
    RetrievedData retrievedDataFROM;
    RetrievedData retrievedDataTO;

    public MainEngine(Controller controller) {
        this.controller = controller;
        this.httpDriver = new HTTPDriver();
        this.retrievedDataParser = new RetrievedDataParser();
        this.converter = new Converter();
    }

    public void processConvertActionInLogic(Currency currencyCodeFROM, Currency currencyCodeTO, 
                                            double amount, LocalDate inquiredDate) {        
        //TODO: handle the same currency codes
        retrieveData(currencyCodeFROM, currencyCodeTO, inquiredDate);
        adjustRetrievedDataPLN();
        dispatchFurtherFlow(amount);
    }

    private void retrieveData(Currency currencyCodeFROM, Currency currencyCodeTO,
                              LocalDate inquiredDate) {
        this.retrievedDataFROM = httpDriver.retrieveData(currencyCodeFROM, inquiredDate);
        this.retrievedDataTO = httpDriver.retrieveData(currencyCodeTO, inquiredDate);        
    }

    /* Because of the specific of the data on exchange rates provided by National
       Bank of Poland the exchangeRateDate for PLN is always the same as the inquiredDate
       and the responseType is always 'USUAL'. To avoid inconsistency with retrieved data
       for the opposite currency the mentioned parameters for PLN are modified here to be
       the same as the parameters of the opposite currency. */
    private void adjustRetrievedDataPLN() {
        if (this.retrievedDataFROM.getCurrencyCode() == Currency.PLN) {
            this.retrievedDataFROM.setExchangeRateDate(this.retrievedDataTO.getExchangeRateDate());
            this.retrievedDataFROM.setResponseType(this.retrievedDataTO.getResponseType());
        }
        if (this.retrievedDataTO.getCurrencyCode() == Currency.PLN) {
            this.retrievedDataTO.setExchangeRateDate(this.retrievedDataFROM.getExchangeRateDate());
            this.retrievedDataTO.setResponseType(this.retrievedDataFROM.getResponseType());
        }
    }

    private void dispatchFurtherFlow(double amount) {
        if (retrievedDataFROM.getResponseType() == ResponseType.USUAL 
            && retrievedDataTO.getResponseType() == ResponseType.USUAL
            && retrievedDataFROM.getExchangeRateDate().equals(retrievedDataTO.getExchangeRateDate())) {                
            showUsualResultInGUI(amount);
        } else if (retrievedDataFROM.getResponseType() == ResponseType.BACKWARD 
                   && retrievedDataTO.getResponseType() == ResponseType.BACKWARD
                   && retrievedDataFROM.getExchangeRateDate().equals(retrievedDataTO.getExchangeRateDate())){                
            showBackwardResultInGUI(amount);
        } else {
            showFailedResultInGUI();
        }
    }

    private void showUsualResultInGUI(double amount) {        
        Currency currencyCodeFROM = retrievedDataFROM.getCurrencyCode();
        Currency currencyCodeTO = retrievedDataTO.getCurrencyCode();
        LocalDate responseDate = retrievedDataFROM.getExchangeRateDate();
        double result = getConvertedValue(amount);
        this.controller.showUsualResultInGUI(currencyCodeFROM, currencyCodeTO, amount,
                                             responseDate, result);
    }

    private void showBackwardResultInGUI(double amount) {
        Currency currencyCodeFROM = retrievedDataFROM.getCurrencyCode();
        Currency currencyCodeTO = retrievedDataTO.getCurrencyCode();
        LocalDate responseDate = retrievedDataFROM.getExchangeRateDate();
        double result = getConvertedValue(amount);
        this.controller.showBackwardResultInGUI(currencyCodeFROM, currencyCodeTO, amount,
                                                responseDate, result);
    }

    private void showFailedResultInGUI() {
        this.controller.showFailedResultInGUI();
    }

    private double getConvertedValue(double amount) {
        double currencyRateFROM = retrievedDataParser.getCurrencyRate(retrievedDataFROM);
        double currencyRateTO = retrievedDataParser.getCurrencyRate(retrievedDataTO);
        return converter.convert(currencyRateFROM, currencyRateTO, amount);
    }
    
}
