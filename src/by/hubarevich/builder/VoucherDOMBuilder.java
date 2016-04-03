package by.hubarevich.builder;

/**
 * Created by Anton on 14.01.2016.
 * Class-builder to create objects with DOM-parser
 */

import by.hubarevich.entity.CommonVoucher;
import by.hubarevich.entity.InternationalVoucher;
import by.hubarevich.entity.NationalVoucher;
import by.hubarevich.util.VouchersEnum;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;



public class VoucherDOMBuilder extends AbstractVoucherBuilder{
    final static Logger LOGGER = LogManager.getLogger(VoucherDOMBuilder.class);
    private Set<CommonVoucher> vouchers;
    private DocumentBuilder docBuilder;


    public VoucherDOMBuilder() {
        this.vouchers = new HashSet<CommonVoucher>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error(e.getStackTrace());
        }
    }

    public VoucherDOMBuilder(Set<CommonVoucher> vouchers) {
        super(vouchers);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error(e.getStackTrace());
        }
    }


    public Set<CommonVoucher> getVouchers() {
        return vouchers;
    }


    /**
     * creates the set of Objects with CommonVoucher type
     * @param fileName
     */
    @Override
    public void buildSetVouchers(String fileName) {
        Document doc = null;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList internationalVoucherList = root.getElementsByTagName(
                    VouchersEnum.INTERNATIONAL_VOUCHER.getValue());
            NodeList localVoucherList = root.getElementsByTagName(VouchersEnum.NATIONAL_VOUCHER.getValue());
            for (int i = 0; i < internationalVoucherList.getLength(); i++) {
                Element voucherElement = (Element) internationalVoucherList.item(i);
                CommonVoucher voucher = buildVoucher(voucherElement);
                vouchers.add(voucher);
            }
            for (int i = 0; i < localVoucherList.getLength(); i++) {
                Element voucherElement = (Element) localVoucherList.item(i);
                CommonVoucher voucher = buildVoucher(voucherElement);
                vouchers.add(voucher);
            }
        } catch (IOException e) {
            LOGGER.error(e.getStackTrace());
        } catch (SAXException e) {
            LOGGER.error(e.getStackTrace());
        }
    }

    /**
     * creates a CommonVoucher Object
     * @param voucherElement
     * @return Object extends CommonVoucher
     */

    private CommonVoucher buildVoucher(Element voucherElement) {
        CommonVoucher commonVoucher;
        if (VouchersEnum.INTERNATIONAL_VOUCHER.getValue().equals(voucherElement.getTagName())) {
            commonVoucher = new InternationalVoucher();
            ((InternationalVoucher) commonVoucher).setCountry(
                    voucherElement.getAttribute(VouchersEnum.COUNTRY.getValue()));
            fillVoucher(commonVoucher, voucherElement);
            return commonVoucher;
        }
        if (VouchersEnum.NATIONAL_VOUCHER.getValue().equals(voucherElement.getTagName())) {
            commonVoucher = new NationalVoucher();
            ((NationalVoucher) commonVoucher).setProfessionalUnion(
                    Boolean.valueOf(voucherElement.getAttribute(VouchersEnum.PROFESSIONAL_UNION.getValue())));
            fillVoucher(commonVoucher, voucherElement);
            return commonVoucher;
        }
        commonVoucher = new CommonVoucher();
        return commonVoucher;
    }

    /**
     * obtaines the text content of XML-element
     * @param element
     * @param elementName
     * @return text content String
     */
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

    /**
     * fills the Object extends CommonVoucher with the parsed data
     * @param commonVoucher
     * @param voucherElement
     */

    private void fillVoucher(CommonVoucher commonVoucher, Element voucherElement) {
        commonVoucher.setCity(getElementTextContent(voucherElement, VouchersEnum.CITY.getValue()));
        commonVoucher.setNumberDaysNights(getElementTextContent(
                voucherElement, VouchersEnum.NUMBER_DAYS_NIGHTS.getValue()));
        commonVoucher.setTransfer(getElementTextContent(voucherElement, VouchersEnum.TRANSFER.getValue()));
        commonVoucher.setHotel(getElementTextContent(voucherElement, VouchersEnum.HOTEL.getValue()));
        commonVoucher.setVoucherType(getElementTextContent(voucherElement, VouchersEnum.VOUCHER_TYPE.getValue()));
        commonVoucher.setCost(Float.parseFloat(
                getElementTextContent(voucherElement, VouchersEnum.COST.getValue())));
    }
}

