package converter.logic.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RetrievedDataParser {    
    
    public double getCurrencyRate(String retrievedData) {
        double currencyRate = 0;
        Object jsonObjectRaw = null;
        try {
            jsonObjectRaw = new JSONParser().parse(retrievedData);
        } catch (ParseException e) {            
            e.printStackTrace();
        }
        
        JSONObject jsonObject = (JSONObject) jsonObjectRaw;    
        JSONArray rates = (JSONArray) jsonObject.get("rates");
        Iterator iteratorOverRates = rates.iterator();

        while (iteratorOverRates.hasNext()) {
            Iterator<Entry> iteratorOverRatesEntries = ((Map) iteratorOverRates.next()).entrySet().iterator();
            while (iteratorOverRatesEntries.hasNext()) {
                Entry pair = iteratorOverRatesEntries.next();
                String dataKey = (String) pair.getKey();                
                if (dataKey.equals("mid")) {
                    currencyRate = (double) pair.getValue();                    
                }
            }
        }
        return currencyRate;
    }


}
