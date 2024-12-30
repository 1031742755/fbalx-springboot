package com.hehe.fbalx.entity;


import java.util.List;

// 新版头程物流信息【对应 logistics_list_type = 1】
//  【注意：新版头程物流数据为覆盖式更新，包括tracking_list、estimate_expenses_list、actual_expenses_list，不传或者传空也会置空】
public class HeadLogisticsList {

    private List<TrackingList> tracking_list; // 轨迹信息数组, reuqire, null
    private EstimateExpensesList estimate_expenses_list; // 费用明细-预估费用, require
    private ActualExpensesList actual_expenses_list; // 费用明细-实际费用, require

    @Override
    public String toString() {
        return "HeadLogisticsList{" +
                "actual_expenses_list=" + actual_expenses_list +
                ", tracking_list=" + tracking_list +
                ", estimate_expenses_list=" + estimate_expenses_list +
                '}';
    }

    public ActualExpensesList getActual_expenses_list() {
        return actual_expenses_list;
    }

    public void setActual_expenses_list(ActualExpensesList actual_expenses_list) {
        this.actual_expenses_list = actual_expenses_list;
    }

    public EstimateExpensesList getEstimate_expenses_list() {
        return estimate_expenses_list;
    }

    public void setEstimate_expenses_list(EstimateExpensesList estimate_expenses_list) {
        this.estimate_expenses_list = estimate_expenses_list;
    }

    public List<TrackingList> getTracking_list() {
        return tracking_list;
    }

    public void setTracking_list(List<TrackingList> tracking_list) {
        this.tracking_list = tracking_list;
    }
}
