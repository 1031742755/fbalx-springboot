package com.hehe.fbalx.entity;


import java.util.List;

public class EstimateExpensesList {
    private String chargeable_weight; // 计费重(单位KG), not require
    private String price; // 单价, require, 0.000
    private String price_currency; // 单价币种, require
    private String logistics_fee; // 物流费用, require, null
    private String logistics_fee_currency; // 物流费用币种, require
    private String remark; // 预估费用备注, not require, null
    private List<OtherFree> other_fee_arr; // 预估费用-其他费, require

    @Override
    public String toString() {
        return "EstimateExpensesList{" +
                "chargeable_weight='" + chargeable_weight + '\'' +
                ", price='" + price + '\'' +
                ", price_currency='" + price_currency + '\'' +
                ", logistics_fee='" + logistics_fee + '\'' +
                ", logistics_fee_currency='" + logistics_fee_currency + '\'' +
                ", remark='" + remark + '\'' +
                ", other_fee_arr=" + other_fee_arr +
                '}';
    }

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
}
