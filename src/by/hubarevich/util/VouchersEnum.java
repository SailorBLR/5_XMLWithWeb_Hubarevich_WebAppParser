package by.hubarevich.util;

/**
 * Created by Anton on 13.01.2016.
 */
public enum VouchersEnum {
    TOURIST_VOUCHERS("tourist-vouchers"),
    INTERNATIONAL_VOUCHER("international-voucher"),
    NATIONAL_VOUCHER("national-voucher"),
    PROFESSIONAL_UNION("professional-union"),
    COUNTRY("country"),
    VOUCHER_ID("id"),
    VOUCHER_TYPE("voucher-type"),
    CITY("city"),
    NUMBER_DAYS_NIGHTS("number-days-nights"),
    TRANSFER("transfer"),
    HOTEL("hotel"),
    COST("cost");
    private String value;

    private VouchersEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
