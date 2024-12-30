package com.hehe.fbalx.entity;

import java.util.List;

public class OrderData {
    private String order_sn; // 发货单号, require, ->order_snshipment_sn
    private String expected_arrival_date; // 到货时间, not require\
    private String etd_date; // 开船时间, not require, null
    private String eta_date; // 预计到港时间, not require, null
    private String delivery_date; // 实际妥投时间, not require, null
    private String actual_shipment_time; // 实际发货时间, not require, null
    private int tax_fee_type; // 实际税费分配方式, not require, null
    private String logistics_channel_id; // 物流渠道id，not require
    private String logistics_list_type; // 物流信息版本：0 旧版, 1 新版, not require
    // 新版头程物流信息【对应 logistics_list_type = 1】
    //  【注意：新版头程物流数据为覆盖式更新，包括tracking_list、estimate_expenses_list、actual_expenses_list，不传或者传空也会置空】
    private List<LogisticsList> logistics_list;
    private HeadLogisticsList head_logistics_list;

    @Override
    public String toString() {
        return "OrderData{" +
                "actual_shipment_time='" + actual_shipment_time + '\'' +
                ", order_sn='" + order_sn + '\'' +
                ", expected_arrival_date='" + expected_arrival_date + '\'' +
                ", etd_date='" + etd_date + '\'' +
                ", eta_date='" + eta_date + '\'' +
                ", delivery_date='" + delivery_date + '\'' +
                ", tax_fee_type=" + tax_fee_type +
                ", logistics_channel_id='" + logistics_channel_id + '\'' +
                ", logisticsList_type='" + logistics_list_type + '\'' +
                ", logistics_list=" + logistics_list +
                ", head_logistics_list=" + head_logistics_list +
                '}';
    }

    public String getActual_shipment_time() {
        return actual_shipment_time;
    }

    public void setActual_shipment_time(String actual_shipment_time) {
        this.actual_shipment_time = actual_shipment_time;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getEta_date() {
        return eta_date;
    }

    public void setEta_date(String eta_date) {
        this.eta_date = eta_date;
    }

    public String getEtd_date() {
        return etd_date;
    }

    public void setEtd_date(String etd_date) {
        this.etd_date = etd_date;
    }

    public String getExpected_arrival_date() {
        return expected_arrival_date;
    }

    public void setExpected_arrival_date(String expected_arrival_date) {
        this.expected_arrival_date = expected_arrival_date;
    }

    public HeadLogisticsList getHead_logistics_list() {
        return head_logistics_list;
    }

    public void setHead_logistics_list(HeadLogisticsList head_logistics_list) {
        this.head_logistics_list = head_logistics_list;
    }

    public String getLogistics_channel_id() {
        return logistics_channel_id;
    }

    public void setLogistics_channel_id(String logistics_channel_id) {
        this.logistics_channel_id = logistics_channel_id;
    }

    public List<LogisticsList> getLogistics_list() {
        return logistics_list;
    }

    public void setLogistics_list(List<LogisticsList> logistics_list) {
        this.logistics_list = logistics_list;
    }

    public String getLogistics_list_type() {
        return logistics_list_type;
    }

    public void setLogistics_list_type(String logistics_list_type) {
        this.logistics_list_type = logistics_list_type;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public int getTax_fee_type() {
        return tax_fee_type;
    }

    public void setTax_fee_type(int tax_fee_type) {
        this.tax_fee_type = tax_fee_type;
    }
}
