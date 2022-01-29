package converter.logic;

import java.time.LocalDate;

import converter.Controller;
import converter.logic.utils.Converter;
import converter.logic.utils.Currency;
import converter.logic.utils.HTTPDriver;
import converter.logic.utils.RetrievedData;
import converter.logic.utils.RetrievedDataParser;
import converter.logic.utils.RetrievedData.ResponseType;

public class MainEngine {

    private Controller controller;
    HTTPDriver httpDriver;
    RetrievedDataParser retrievedResponseParser;
    Converter converter;
    RetrievedData retrievedDataFROM;
    RetrievedData retrievedDataTO;

    public MainEngine(Controller controller) {
        this.controller = controller;
        this.httpDriver = new HTTPDriver();
        this.retrievedResponseParser = new RetrievedDataParser();
        this.converter = new Converter();
    }

    public void processConvertActionInLogic(Currency currencyCodeFROM, Currency currencyCodeTO, 
                                            double amount, LocalDate inquiredDate) {
        retrieveData(currencyCodeFROM, currencyCodeTO, inquiredDate);
        adjustRetrievedDataPLN();
        dispatchFurtherFlow(amount);
    }

    private void retrieveData(Currency currencyCodeFROM, Currency currencyCodeTO,
                              LocalDate inquiredDate) {
        this.retrievedDataFROM = httpDriver.retrieveData(currencyCodeFROM, inquiredDate);
        this.retrievedDataTO = httpDriver.retrieveData(currencyCodeTO, inquiredDate);        
    }

    /* Because of the specific of the data on exchange rates provided by 
       National Bank of Poland the exchangeRateDate for PLN is always the same as inquired
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
        double result = getConvertedValue(amount);
        LocalDate responseDate = retrievedDataFROM.getExchangeRateDate();
        Currency currencyCodeFROM = retrievedDataFROM.getCurrencyCode();
        Currency currencyCodeTO = retrievedDataTO.getCurrencyCode();
        this.controller.showUsualResultInGUI(amount, result, responseDate, currencyCodeFROM, currencyCodeTO);
    }

    private void showBackwardResultInGUI(double amount) {
        double result = getConvertedValue(amount);
        LocalDate responseDate = retrievedDataFROM.getExchangeRateDate();
        Currency currencyCodeFROM = retrievedDataFROM.getCurrencyCode();
        Currency currencyCodeTO = retrievedDataTO.getCurrencyCode();
        this.controller.showBackwardResultInGUI(amount, result, responseDate, currencyCodeFROM, currencyCodeTO);
    }

    private void showFailedResultInGUI() {
        this.controller.showFailedResultInGUI();
    }

    private double getConvertedValue(double amount) {
        double currencyRateFROM = retrievedResponseParser.getCurrencyRate(retrievedDataFROM.getResponseBody());
        double currencyRateTO = retrievedResponseParser.getCurrencyRate(retrievedDataTO.getResponseBody());
        return converter.convert(currencyRateFROM, currencyRateTO, amount);
    }
    
}
