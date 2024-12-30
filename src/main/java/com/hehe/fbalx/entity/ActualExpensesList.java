package com.hehe.fbalx.entity;

import java.util.List;

public class ActualExpensesList {
    private String tax_fee; // 税费, require, null
    private String tax_fee_currency; // 税费币种, require
    private String chargeable_weight; // 单价币种, require
    private String weight; // 实重（单位：KG）, require
    private String voluem; // 体积（单位：m³）, require
    private String price; // 单价，require, 0.000
    private String price_currency; // 单价币种, require
    private String logistics_fee; // 物流费用, require, null
    private String logistics_fee_currency; // 物流费用币种, require
    private String remark; // 备注, not require, null
    private List<OtherFree> other_fee_arr; // 实际费用-其他费, require

    public String getChargeable_weight() {
        return chargeable_weight;
    }

    public void setChargeable_weight(String chargeable_weight) {
        this.chargeable_weight = chargeable_weight;
    }

    public String getLogistics_fee() {
        return logistics_fee;
    }

    public void setLogistics_fee(String logistics_fee) {
        this.logistics_fee = logistics_fee;
    }

    public String getLogistics_fee_currency() {
        return logistics_fee_currency;
    }

    public void setLogistics_fee_currency(String logistics_fee_currency) {
        this.logistics_fee_currency = logistics_fee_currency;
    }

    public List<OtherFree> getOther_fee_arr() {
        return other_fee_arr;
    }

    public void setOther_fee_arr(List<OtherFree> other_fee_arr) {
        this.other_fee_arr = other_fee_arr;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice_currency() {
        return price_currency;
    }

    public void setPrice_currency(String price_currency) {
        this.price_currency = price_currency;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTax_fee() {
        return tax_fee;
    }

    public void setTax_fee(String tax_fee) {
        this.tax_fee = tax_fee;
    }

    public String getTax_fee_currency() {
        return tax_fee_currency;
    }

    public void setTax_fee_currency(String tax_fee_currency) {
        this.tax_fee_currency = tax_fee_currency;
    }

    public String getVoluem() {
        return voluem;
    }

    public void setVoluem(String voluem) {
        this.voluem = voluem;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "ActualExpensesList{" +
                "chargeable_weight='" + chargeable_weight + '\'' +
                ", tax_fee='" + tax_fee + '\'' +
                ", tax_fee_currency='" + tax_fee_currency + '\'' +
                ", weight='" + weight + '\'' +
                ", voluem='" + voluem + '\'' +
                ", price='" + price + '\'' +
                ", price_currency='" + price_currency + '\'' +
                ", logistics_fee='" + logistics_fee + '\'' +
                ", logistics_fee_currency='" + logistics_fee_currency + '\'' +
                ", remark='" + remark + '\'' +
                ", other_fee_arr=" + other_fee_arr +
                '}';
    }
}
