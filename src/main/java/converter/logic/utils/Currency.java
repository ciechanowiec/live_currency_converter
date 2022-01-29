package converter.logic.utils;


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

    //TODO: needed?
    public static Currency[] getAllEnums() {
        return Currency.class.getEnumConstants();
    }

    //TODO: needed?
    public String getDescription() {
        return this.description;
    }

}