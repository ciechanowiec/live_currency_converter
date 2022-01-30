package converter;

public enum Currency {
    AUD("Australian dollar"),
    BGN("Bulgarian lev"),
    BRL("Brazilian real"),
    CAD("Canadian dollar"),
    CHF("Swiss franc"),
    CLP("Chilean peso"),
    CNY("Chinese yuan"),
    CZK("Czech koruna"),
    DKK("Danish krone"),
    EUR("Euro"),
    GBP("Pound sterling"),
    HKD("Hong Kong dollar"),
    HRK("Croatian kuna"),
    HUF("Hungarian forint"),
    IDR("Indonesian rupiah"),
    ILS("Israeli new shekel"),
    INR("Indian rupee"),
    ISK("Icelandic króna"),
    JPY("Japanese yen"),
    KRW("South Korean won"),
    MXN("Mexican peso"),
    MYR("Malaysian ringgit"),
    NOK("Norwegian krone"),
    NZD("New Zealand dollar"),
    PHP("Philippine peso"),
    PLN("Polish złoty"),
    RON("Romanian leu"),
    RUB("Russian ruble"),
    SEK("Swedish krona"),
    SGD("Singapore dollar"),
    THB("Thai baht"),
    TRY("Turkish lira"),
    UAH("Ukrainian hryvnia"),
    USD("United States dollar"),
    XDR("Special drawing rights"),
    ZAR("South African rand");

    private String description;

    private Currency(String description) {
        this.description = description;
    }
    
    public static Currency[] getAllCurrencies() {
        return Currency.class.getEnumConstants();
    }

    public static String[] getAllFullRepresentations() {
        Currency[] allEnums = Currency.getAllCurrencies();
        String[] allFullRepresentations = new String[allEnums.length];
        for (int i = 0; i < allEnums.length; i++) {
            allFullRepresentations[i] = allEnums[i].getFullRepresentation();
        }
        return allFullRepresentations;
    }

    public static Currency getCurrencyByFullRepresentation(String fullRepresentation) {
        Currency[] allCurrencies = Currency.getAllCurrencies();
        for (int i = 0; i < allCurrencies.length; i++) {
            if (allCurrencies[i].getFullRepresentation().equals(fullRepresentation)) {
                return allCurrencies[i];
            }
        }
        throw new IllegalArgumentException("The currency with searched full representation doesn't exist.");
    }
    
    private String getDescription() {        
        return this.description;
    }

    public String getFullRepresentation() {
        return String.format("%s - %s", this.toString(), this.getDescription());
    }

}