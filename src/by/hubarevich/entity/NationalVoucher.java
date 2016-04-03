
package by.hubarevich.entity;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "local-voucher", namespace = "http://www.hubarevich.by/tourist-vouchers")
public class NationalVoucher
    extends CommonVoucher
{
    @XmlAttribute(name = "professional-union", required = true)
    private boolean professionalUnion;

    /**
     * Gets the value of the professionalUnion property.
     * 
     */
    public boolean isProfessionalUnion() {
        return professionalUnion;
    }

    /**
     * Sets the value of the professionalUnion property.
     * 
     */
    public void setProfessionalUnion(boolean value) {
        this.professionalUnion = value;
    }

    @Override
    public String toString() {
        String summary;
        String profUnion;

        if (professionalUnion) {
            profUnion = " is ";
        } else {
            profUnion = " is not ";
        }

        summary = "Local voucher \n" +
                "Country of visit - Belarus" + "\n" +
                "City of visit: " + city + "\n" +
                "The guest " + profUnion + "member of professional union \n" +
                "Voucher type: " + voucherType + "\n" +
                "Accomodation details: " + hotel + "\n" +
                "Transfer details: " + transfer + "\n" +
                "Voucher cost: " + cost + "\n";

        return summary;
    }
}
