package by.hubarevich.builder;

/**
 * Created by Anton on 16.01.2016.
 * Class uses StAX parser and creates the Set of Objects extends CommonVoucher
 */
import by.hubarevich.entity.CommonVoucher;
import by.hubarevich.entity.InternationalVoucher;
import by.hubarevich.entity.NationalVoucher;
import by.hubarevich.util.VouchersEnum;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
public class VoucherStAXBuilder extends AbstractVoucherBuilder{

    final static Logger LOGGER = LogManager.getLogger(VoucherSAXBuilder.class.getName());
    private HashSet<CommonVoucher> vouchers = new HashSet<>();
    private XMLInputFactory inputFactory;

    public VoucherStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public VoucherStAXBuilder(Set<CommonVoucher> vouchers) {
        super(vouchers);
    }

    public Set<CommonVoucher> getVouchers() {
        return vouchers;
    }

    /**
     * creates the Set of CommonVoucher
     * @param fileName
     */

    public void buildSetVouchers(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name
                    == VouchersEnum.INTERNATIONAL_VOUCHER.getValue()) {
                        CommonVoucher commonVoucher = new InternationalVoucher();
                        ((InternationalVoucher) commonVoucher).setCountry(
                                reader.getAttributeValue(null,VouchersEnum.COUNTRY.getValue()));
                        commonVoucher = buildVoucher(reader,commonVoucher);
                        vouchers.add(commonVoucher);
                    }
                    if (name
                            == VouchersEnum.NATIONAL_VOUCHER.getValue()) {
                        CommonVoucher commonVoucher = new NationalVoucher();
                        ((NationalVoucher) commonVoucher).setProfessionalUnion(Boolean.valueOf(
                                reader.getAttributeValue(null,VouchersEnum.PROFESSIONAL_UNION.getValue())));
                        commonVoucher = buildVoucher(reader,commonVoucher);
                        vouchers.add(commonVoucher);
                    }
                }
            }
        } catch (XMLStreamException e) {

            LOGGER.error(e.getStackTrace());

        } catch (FileNotFoundException e) {

            LOGGER.error(e.getStackTrace());

        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {

                LOGGER.error(e.getStackTrace());

            }
        }
    }

    /**
     * creates the CommonVoucher Object
     * @param reader
     * @param commonVoucher
     * @return
     */
    private CommonVoucher buildVoucher(XMLStreamReader reader, CommonVoucher commonVoucher) {

        String name;

        try {
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        switch (VouchersEnum.valueOf(name.toUpperCase().replace("-", "_"))) {
                            case CITY:
                                commonVoucher.setCity(getXMLText(reader));
                                break;
                            case COST:
                                commonVoucher.setCost(Float.parseFloat(getXMLText(reader)));
                                break;
                            case TRANSFER:
                                commonVoucher.setTransfer(getXMLText(reader));
                                break;
                            case NUMBER_DAYS_NIGHTS:
                                commonVoucher.setNumberDaysNights(getXMLText(reader));
                                break;
                            case HOTEL:
                                commonVoucher.setHotel(getXMLText(reader));
                                break;
                            case VOUCHER_TYPE:
                                commonVoucher.setVoucherType(getXMLText(reader));
                                break;
                        }
                        break;
                   case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        if ((VouchersEnum.valueOf(name.toUpperCase().replace("-", "_")) == VouchersEnum.COST)) {

                            return commonVoucher;
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            LOGGER.error(e.getStackTrace());
        }
        return commonVoucher;
    }

    /**
     * gets the content of XML
     * @param reader
     * @return
     */
    private String getXMLText(XMLStreamReader reader){
        String text = null;
        try {
            if (reader.hasNext()) {
                reader.next();
                text = reader.getText();
            }
        } catch (XMLStreamException e) {
            LOGGER.error(e.getStackTrace());
        }
        return text;
    }
}
