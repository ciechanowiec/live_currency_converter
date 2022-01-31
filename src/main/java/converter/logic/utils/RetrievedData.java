package converter.logic.utils;

import java.time.LocalDate;

import converter.controller.Currency;

public class RetrievedData {

    public enum ResponseType {
        USUAL, BACKWARD, FAILED
    }

    private Currency currencyCode;
    private String responseBody;
    private ResponseType responseType;
    private LocalDate exchangeRateDate;

    public RetrievedData(Currency currencyCode, String responseBody, 
                         ResponseType responseType, LocalDate exchangeRateDate) {
        this.currencyCode = currencyCode;
        this.responseBody = responseBody;
        this.responseType = responseType;
        this.exchangeRateDate = exchangeRateDate;
    }

    public Currency getCurrencyCode() {
        return this.currencyCode;
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public void setExchangeRateDate(LocalDate exchangeRateDate) {
        this.exchangeRateDate = exchangeRateDate;
    }
    
    public ResponseType getResponseType() {
        return this.responseType;
    }

    public LocalDate getExchangeRateDate() {
        return this.exchangeRateDate;
    }
    
}
