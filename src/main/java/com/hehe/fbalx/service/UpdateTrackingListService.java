package com.hehe.fbalx.service;

import com.hehe.fbalx.api.ShipmentDetailApi;
import com.hehe.fbalx.api.ShipmentListApi;
import com.hehe.fbalx.api.UpdateListLogisticsApi;
import com.hehe.fbalx.utils.LogUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UpdateTrackingListService {


    public void updateTrackingList(){
        List<String> shipmentSnList = new ArrayList<String>();
        ShipmentListApi shipmentListApi = new ShipmentListApi();
        shipmentSnList = shipmentListApi.getShipmentList();
        if (shipmentSnList == null || shipmentSnList.isEmpty()){
            LogUtil.info("Not enquired about 联纵.");
            return;
        }
        ShipmentDetailApi shipmentDetailApi = new ShipmentDetailApi();
        String jsonBody= null;
        UpdateListLogisticsApi updateListLogisticsApi = new UpdateListLogisticsApi();
        String shipmentSn = null;
        for(int i = 0; i < shipmentSnList.size(); i++){
            shipmentSn = shipmentSnList.get(i);
            jsonBody = shipmentDetailApi.getShipmentDetail(shipmentSn);
            if(jsonBody == null || jsonBody.isEmpty()){
                LogUtil.info("ShipmentDetail is empty.");
                continue;
            }
            try {
                LogUtil.info("ShipmentSn " + shipmentSn + " being updated.");
                updateListLogisticsApi.updateListLogistics(jsonBody);
            }catch (Exception e){
                LogUtil.severe(e.getMessage());
            }

        }
    }

}
