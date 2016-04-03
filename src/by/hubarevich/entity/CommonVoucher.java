
package by.hubarevich.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "common-voucher", namespace = "http://www.hubarevich.by/tourist-vouchers", propOrder = {
        "voucherType",
        "city",
        "numberDaysNights",
        "transfer",
        "hotel",
        "cost"
})
@XmlSeeAlso({
        InternationalVoucher.class,
        NationalVoucher.class
})
public class CommonVoucher {


    @XmlElement(name = "voucher-type", namespace = "http://www.hubarevich.by/tourist-vouchers", required = true)
    private String voucherType;
    @XmlElement(name = "city", namespace = "http://www.hubarevich.by/tourist-vouchers", required = true)
    private String city;
    @XmlElement(name = "number-days-nights", namespace = "http://www.hubarevich.by/tourist-vouchers", required = true)
    private String numberDaysNights;
    @XmlElement(namespace = "http://www.hubarevich.by/tourist-vouchers", required = true)
    private String transfer;
    @XmlElement(namespace = "http://www.hubarevich.by/tourist-vouchers", required = true)
    private String hotel;
    @XmlElement(namespace = "http://www.hubarevich.by/tourist-vouchers")
    private float cost;
    @XmlAttribute(name = "id", required = true)
    private int id;

    /**
     * Gets the value of the voucherType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVoucherType() {
        return voucherType;
    }

    /**
     * Sets the value of the voucherType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVoucherType(String value) {
        this.voucherType = value.replace("_"," ");
    }

    /**
     * Gets the value of the numberDaysNights property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNumberDaysNights() {
        return numberDaysNights;
    }

    /**
     * Sets the value of the numberDaysNights property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNumberDaysNights(String value) {
        this.numberDaysNights = value;
    }

    /**
     * Gets the value of the transfer property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTransfer() {
        return transfer;
    }

    /**
     * Sets the value of the transfer property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTransfer(String value) {
        this.transfer = value.replace("_"," ");
    }

    /**
     * Gets the value of the hotel property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getHotel() {
        return hotel;
    }

    /**
     * Sets the value of the hotel property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setHotel(String value) {
        this.hotel = value.replace("_"," ");
    }

    /**
     * Gets the value of the cost property.
     */
    public float getCost() {
        return cost;
    }

    /**
     * Sets the value of the cost property.
     */
    public void setCost(float value) {
        this.cost = value;
    }

    /**
     * Gets the value of the id property.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     */
    public void setId(int value) {
        this.id = value;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city.replace("_"," ");
    }
}
