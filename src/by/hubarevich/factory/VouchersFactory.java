package by.hubarevich.factory;

import by.hubarevich.builder.AbstractVoucherBuilder;
import by.hubarevich.builder.VoucherDOMBuilder;
import by.hubarevich.builder.VoucherSAXBuilder;
import by.hubarevich.builder.VoucherStAXBuilder;

/**
 * Created by Anton on 18.01.2016.
 * Factory-class for creating a concrete builder
 */
public class VouchersFactory  {


    private enum TypeParser {
        SAX, STAX, DOM
    }
public AbstractVoucherBuilder createVouchersBuilder (String typeParser) {
    TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
    switch (type) {
        case DOM:
            return new VoucherDOMBuilder();
        case STAX:
            return new VoucherStAXBuilder();
        case SAX:
            return new VoucherSAXBuilder();
        default:
            return new VoucherDOMBuilder();
    }
}
}
