package by.hubarevich.builder;

import by.hubarevich.entity.CommonVoucher;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Anton on 18.01.2016.
 * Abstract class-builder for creating concrete builder for objects.
 */
public abstract class AbstractVoucherBuilder {

    protected Set<CommonVoucher> vouchers;

    public AbstractVoucherBuilder() {
        vouchers = new HashSet<CommonVoucher>();
    }

    public AbstractVoucherBuilder(Set<CommonVoucher> vouchers) {
        this.vouchers = vouchers;
    }

    public Set<CommonVoucher> getVouchers() {
        return vouchers;
    }

    abstract public void buildSetVouchers(String fileName);
}
