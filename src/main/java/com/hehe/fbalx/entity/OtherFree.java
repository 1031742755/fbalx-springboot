package com.hehe.fbalx.entity;


public class OtherFree {
    private String fee_type_id; // 其他费id（20位）, require
    private String name; // 其他费类型：其他费用、附加费, not require
    private String other_amount; // 其他费金额, require, null
    private String other_currency; // 其他费币种, require

    @Override
    public String toString() {
        return "OtherFree{" +
                "fee_type_id='" + fee_type_id + '\'' +
                ", name='" + name + '\'' +
                ", other_amount='" + other_amount + '\'' +
                ", other_urrency='" + other_currency + '\'' +
                '}';
    }

    public String getFee_type_id() {
        return fee_type_id;
    }

    public void setFee_type_id(String fee_type_id) {
        this.fee_type_id = fee_type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOther_amount() {
        return other_amount;
    }

    public void setOther_amount(String other_amount) {
        this.other_amount = other_amount;
    }

    public String getOther_currency() {
        return other_currency;
    }

    public void setOther_urrency(String other_urrency) {
        this.other_currency = other_urrency;
    }
}
