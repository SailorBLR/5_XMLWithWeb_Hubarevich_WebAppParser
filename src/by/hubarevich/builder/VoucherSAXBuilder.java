package by.hubarevich.builder;

/**
 * Created by Anton on 13.01.2016.
 * Class operates with SAX-parsed data and creates the set of Objects, listed in XML
 */

import by.hubarevich.entity.CommonVoucher;
import by.hubarevich.util.VouchersSAXHandler;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class VoucherSAXBuilder extends AbstractVoucherBuilder {

    final static Logger LOGGER = LogManager.getLogger(VoucherSAXBuilder.class.getName());
    private Set<CommonVoucher> vouchers;
    private VouchersSAXHandler vouchersSAXHandler;
    private XMLReader reader;

    public VoucherSAXBuilder() {
        vouchersSAXHandler = new VouchersSAXHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(vouchersSAXHandler);
        } catch (SAXException e) {
            LOGGER.error(e.getStackTrace());
        }
    }

    public VoucherSAXBuilder(Set<CommonVoucher> vouchers) {
        super(vouchers);
    }

    public Set<CommonVoucher> getVouchers() {
        return vouchers;
    }

    /**
     * creates the Set of Objects extends CommonVoucher with SAX-parser
     * @param fileName
     */
    @Override
    public void buildSetVouchers(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            LOGGER.error(e.getStackTrace());
        } catch (IOException e) {
            LOGGER.error(e.getStackTrace());
        }
        vouchers = vouchersSAXHandler.getInternationalVouchers();
    }
}