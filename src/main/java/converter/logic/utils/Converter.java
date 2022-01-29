package converter.logic.utils;

public class Converter {

    public double convert(double currencyRateFROM, 
                          double currencyRateTO, double amount) {        
        return ((currencyRateFROM / currencyRateTO) * amount);
    }
    
}
