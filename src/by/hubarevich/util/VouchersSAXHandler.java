package by.hubarevich.util;


import by.hubarevich.entity.CommonVoucher;
import by.hubarevich.entity.InternationalVoucher;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import by.hubarevich.entity.NationalVoucher;
import by.hubarevich.servlet.XMLParserServlet;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Anton on 13.01.2016.
 * class handles the XML-content ahd returns elements
 */
public class VouchersSAXHandler extends DefaultHandler {

    private final String INTERNATIONAL = "international-voucher";
    private final String NATIONAL = "national-voucher";
    private Set<CommonVoucher> vouchers;
    private CommonVoucher currentCommonVoucher = null;
    private VouchersEnum currentEnum = null;
    private EnumSet<VouchersEnum> withText;


    public VouchersSAXHandler() {
        vouchers = new HashSet<>();
        withText = EnumSet.range(VouchersEnum.VOUCHER_TYPE, VouchersEnum.COST);
    }

    public Set<CommonVoucher> getInternationalVouchers() {
        return vouchers;
    }

    /**
     * looks for the start element in XML-file
     * @param uri
     * @param localName
     * @param qName
     * @param attrs
     */

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        switch (localName) {
            case INTERNATIONAL:
                currentCommonVoucher = new InternationalVoucher();
                ((InternationalVoucher) currentCommonVoucher).setCountry(attrs.getValue(1));
                break;
            case NATIONAL:
                currentCommonVoucher = new NationalVoucher();
                ((NationalVoucher) currentCommonVoucher).setProfessionalUnion(Boolean.parseBoolean(attrs.getValue(1)));
                break;

            default:
                VouchersEnum temp = VouchersEnum.valueOf(localName.toUpperCase().replace("-", "_"));
                if (withText.contains(temp)) {
                    currentEnum = temp;
                }
                break;

        }
    }

    /**
     * looks for the end element in XML-file
     * @param uri
     * @param localName
     * @param qName
     */
    public void endElement(String uri, String localName, String qName) {
        if (VouchersEnum.INTERNATIONAL_VOUCHER.getValue().equals(localName) |
                VouchersEnum.NATIONAL_VOUCHER.getValue().equals(localName)) {
            vouchers.add(currentCommonVoucher);
        }
    }

    /**
     * parsing the text content of XML-elements
     * @param ch
     * @param start
     * @param length
     */

    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case VOUCHER_TYPE:
                    currentCommonVoucher.setVoucherType(s);
                    break;
                case CITY:
                    currentCommonVoucher.setCity(s);
                    break;
                case HOTEL:
                    currentCommonVoucher.setHotel(s);
                    break;
                case COST:
                    currentCommonVoucher.setCost(Float.parseFloat(s));
                    break;
                case TRANSFER:
                    currentCommonVoucher.setTransfer(s);
                    break;
                case NUMBER_DAYS_NIGHTS:
                    currentCommonVoucher.setNumberDaysNights(s);
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());

            }
        }
        currentEnum = null;
    }
}
