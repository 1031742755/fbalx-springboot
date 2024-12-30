package com.hehe.fbalx.entity;



public class TrackingList {
    String tracking_no; // 查询单号, not require, null
    String transport_type; // 运输类型：, require, null
    String order_type_code; // 单号类型：【注意：与运输类型联动关系】, require, null
    String shippers; // 承运商，运输类型为海运时才有意义, not require, null
    String remark; // 备注, not require, null

    @Override
    public String toString() {
        return "TrackingList{" +
                "order_type_code='" + order_type_code + '\'' +
                ", tracking_no='" + tracking_no + '\'' +
                ", transport_type='" + transport_type + '\'' +
                ", shippers='" + shippers + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getOrder_type_code() {
        return order_type_code;
    }

    public void setOrder_type_code(String order_type_code) {
        this.order_type_code = order_type_code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getShippers() {
        return shippers;
    }

    public void setShippers(String shippers) {
        this.shippers = shippers;
    }

    public String getTracking_no() {
        return tracking_no;
    }

    public void setTracking_no(String tracking_no) {
        this.tracking_no = tracking_no;
    }

    public String getTransport_type() {
        return transport_type;
    }

    public void setTransport_type(String transport_type) {
        this.transport_type = transport_type;
    }
}
