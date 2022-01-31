package converter.logic.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import converter.controller.Currency;
import converter.logic.utils.RetrievedData.ResponseType;

public class HTTPDriver {

    private HttpClient client;
    private HttpRequest request;
    private HttpResponse<String> response;

    public HTTPDriver() {
        initClient();
    }

    private void initClient() {
        this.client = HttpClient.newBuilder()
                                .followRedirects(HttpClient.Redirect.ALWAYS)
                                .build();
    }    

    /* Arguments: 1. Currency code according to ISO 4217 standard (e.g. 'USD').
                  2. Date according to ISO 8601 standard (e.g. '2021-12-03').
         Returns: Raw data in JSON format (to see an example of returned data go to:
                  https://api.nbp.pl/api/exchangerates/rates/a/USD/2022-01-28/?format=json) */         
    public RetrievedData retrieveData(Currency currencyCode, LocalDate inquiredDate) {                
        if (currencyCode == Currency.PLN) {
            return getResponseForPLN(currencyCode, inquiredDate);
        } else {
            LocalDate responseDate = inquiredDate.minusDays(0); // Easy-way for cloning this object.
            return getResponseGeneral(currencyCode, inquiredDate, responseDate, 0);
        }
    }

    /* National Bank of Poland doesn't provide exchange rates for Polish złoty - PLN. 
       Therefore, the fictional response is used to produce retrieved data. */    
    private RetrievedData getResponseForPLN(Currency currencyCode, LocalDate inquiredDate) {
        String responseBodyForPLN = null;
        try {
            responseBodyForPLN = Files.readString(Paths.get("src/main/resources/PLN_response.txt"));
        } catch (IOException e) {            
            e.printStackTrace();
        }
        return new RetrievedData(currencyCode, responseBodyForPLN, ResponseType.USUAL, inquiredDate);
    }
    
    private RetrievedData getResponseGeneral(Currency currencyCode, LocalDate inquiredDate,
                                            LocalDate responseDate, int counter) {
        counter++;
        try {
            buildRequest(currencyCode, responseDate);
            this.response = this.client.send(this.request, BodyHandlers.ofString());
        } catch (URISyntaxException | IOException | InterruptedException e) {        
            e.printStackTrace();
        }
        if (counter > 5) {
            String responseBody = this.response.body();
            ResponseType responseType = ResponseType.FAILED;
            LocalDate exchangeRateDate = inquiredDate;
            return new RetrievedData(currencyCode, responseBody, responseType, exchangeRateDate);
        } else if ((this.response.statusCode() == 404 || this.response.statusCode() == 400)) {            
            responseDate = responseDate.minusDays(1);
            return getResponseGeneral(currencyCode, inquiredDate, responseDate, counter);
        } else {
            String responseBody = this.response.body();            
            ResponseType responseType = inquiredDate.equals(responseDate) ? ResponseType.USUAL : ResponseType.BACKWARD;
            LocalDate exchangeRateDate = responseDate;
            return new RetrievedData(currencyCode, responseBody, responseType, exchangeRateDate);
        }
    }

    private void buildRequest(Currency currencyCode, LocalDate requestedDate) throws URISyntaxException {
        this.request = HttpRequest.newBuilder()
                                  .uri(createURI(currencyCode, requestedDate))
                                  .GET()
                                  .build();
    }

    private URI createURI(Currency currencyCode, LocalDate requestedDate) throws URISyntaxException {
        String uriModel = String.format("https://api.nbp.pl/api/exchangerates/rates/a/%s/%s/?format=json",
                                         currencyCode.toString(), requestedDate.toString());
        return new URI(uriModel);                                         
    }

}
