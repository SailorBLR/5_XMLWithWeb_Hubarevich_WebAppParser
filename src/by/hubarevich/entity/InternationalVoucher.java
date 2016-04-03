
package by.hubarevich.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "foreign-voucher", namespace = "http://www.hubarevich.by/tourist-vouchers")
public class InternationalVoucher
        extends CommonVoucher {

    @XmlAttribute(name = "country", required = true)
    private String country;

    /**
     * Gets the value of the country property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCountry(String value) {
        this.country = value.replace("_"," ");
    }

    @Override
    public String toString() {
        String summary;
        summary = "International voucher \n" +
                "Country of visit: " + country + "\n" +
                "City of visit: " + city + "\n" +
                "Voucher type: " + voucherType + "\n" +
                "Accomodation details: " + hotel + "\n" +
                "Transfer details: " + transfer + "\n" +
                "Voucher cost: " + cost + "\n";

        return summary;
    }
}
