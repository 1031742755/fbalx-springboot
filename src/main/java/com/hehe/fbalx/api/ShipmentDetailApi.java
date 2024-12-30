package com.hehe.fbalx.api;

import com.google.gson.Gson;
import com.hehe.fbalx.entity.*;
import com.hehe.fbalx.utils.ApiSignProductor;
import com.hehe.fbalx.utils.LogUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class ShipmentDetailApi {

    public  String getShipmentDetail(String shipmentSn){
        String jsonRequestBody = null;
        try {
            ApiSignResult apiSignResult = ApiSignProductor.productor(Constants.SHIPMENT_SN, shipmentSn);
            // 请求参数
            Map<String, String> params = new HashMap<>();
            params.put("app_key", Constants.APP_ID);
            params.put("sign", apiSignResult.getSign());
            params.put("timestamp", apiSignResult.getTimestamp());
            params.put("access_token", apiSignResult.getAcessToken());



            // 将参数拼接成查询字符串
            StringJoiner queryParams = new StringJoiner("&");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                queryParams.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" +
                        URLEncoder.encode(entry.getValue(), "UTF-8"));
            }

            // 完整的 URL，包括查询参数
            URL url = new URL(Constants.GET_SHIPMENT_DETAIL + "?" + queryParams);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为 POST
            connection.setRequestMethod("POST");

            // 设置请求头
            connection.setRequestProperty("User-Agent", "Apifox/1.0.0 (https://apifox.com)");
            connection.setRequestProperty("Content-Type", "application/json");

            // 允许写入请求体
            connection.setDoOutput(true);

            // 请求体内容
            String jsonInputString = "{" +
                    "\"shipment_sn\":\"" + shipmentSn + "\"" +
                    "}";
            // 写入请求体
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 处理响应
            int responseCode = connection.getResponseCode();
            LogUtil.info("" + responseCode);
            // 读取响应
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 处理响应
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                // 解析 JSON 数据
                JSONObject data = null;
                try {
                    JSONObject jsonObject = new JSONObject(content.toString());
                    data = jsonObject.getJSONObject("data");
                }catch (JSONException e){
                    LogUtil.severe(e.getMessage());
                    return null;
                }
                // 提取 expected_arrival_date
                String expectedArrivalDate = data.getString("expected_arrival_date");
                // 提取 etd_date
                String etdDate = data.getString("etd_date");
                // 提取 eta_date
                String eta_date = data.getString("eta_date");
                // 提取 delivery_date
                String deliveryDate = data.getString("delivery_date");
                // 提取 actual_shipment_time
                String actualShipmentTime = data.getString("actual_shipment_time");
                // 提取 logistics_channel_id
                int logisticsChannelId = data.getInt("logistics_channel_id");
                // 提取 logistics_list_type
                int logisticsListType = data.getInt("logistics_list_type");
                // 提取 shipment_id FBA18FL44Y77
                String shipmentId = data.getJSONArray("items").getJSONObject(0).getString("shipment_id");
                String transportType = "2";
                String orderTypeCode = "3";
                // 获取物流信息
                TrackingApi trackingApi = new TrackingApi();
                String trackingInformations = trackingApi.getTrackingInformation(shipmentId);

                // 提取 estimate_expenses_list
                JSONObject estimateExpenses = data.getJSONObject("head_logistics_list").getJSONObject("estimate_expenses_list");
                String estimateChargeableWeight = estimateExpenses.getString("chargeable_weight");
                String estimatePrice = estimateExpenses.getString("price");
                String estimatePriceCurrency = estimateExpenses.getString("price_currency");
                String estimateLogisticsFee = estimateExpenses.getString("logistics_fee");
                String estimateLogisticsFeeCurrency = estimateExpenses.getString("logistics_fee_currency");
                String estimateRemark = estimateExpenses.getString("remark");

                List<OtherFree> estimateOtherFeeList = new ArrayList<>();
                JSONArray estimateOtherFeeArr = estimateExpenses.getJSONArray("other_fee_arr");
                for (int i = 0; i < estimateOtherFeeArr.length(); i++){
                    OtherFree otherFree = new OtherFree();
                    JSONObject jsonObj = estimateOtherFeeArr.getJSONObject(i);
                    otherFree.setFee_type_id(jsonObj.getString("fee_type_id"));
                    otherFree.setOther_amount(jsonObj.getString("other_amount"));
                    otherFree.setOther_urrency(jsonObj.getString("other_currency"));
                    estimateOtherFeeList.add(otherFree);
                }

                // 提取 actual_expenses_list
                JSONObject actualExpenses = data.getJSONObject("head_logistics_list").getJSONObject("actual_expenses_list");
                String actualTaxFee = actualExpenses.getString("tax_fee");
                String actualTaxFeeCurrency = actualExpenses.getString("tax_fee_currency");
                String actualChargeWeight = actualExpenses.getString("chargeable_weight");
                String actualWeight = actualExpenses.getString("weight");
                String actualVolume = actualExpenses.getString("volume");
                String actualPrice = actualExpenses.getString("price");
                String actualPriceCurrency = actualExpenses.getString("price_currency");
                String actualLogisticsFee = actualExpenses.getString("logistics_fee");
                String actualLogisticsFeeCurrency = actualExpenses.getString("logistics_fee_currency");
                String actualRemark = actualExpenses.getString("remark");

                List<OtherFree> actualOtherFeeList = new ArrayList<>();
                JSONArray actualOtherFeeArr = actualExpenses.getJSONArray("other_fee_arr");
                for (int i = 0; i < actualOtherFeeArr.length(); i++){
                    OtherFree otherFree = new OtherFree();
                    JSONObject jsonObj = actualOtherFeeArr.getJSONObject(i);
                    otherFree.setFee_type_id(jsonObj.getString("fee_type_id"));
                    otherFree.setOther_amount(jsonObj.getString("other_amount"));
                    otherFree.setOther_urrency(jsonObj.getString("other_currency"));
                    actualOtherFeeList.add(otherFree);
                }

                OrderData orderData = new OrderData();
                orderData.setOrder_sn(shipmentSn);
                orderData.setExpected_arrival_date(expectedArrivalDate);
                orderData.setEtd_date(etdDate);
                orderData.setEta_date(eta_date);
                orderData.setDelivery_date(deliveryDate);
                orderData.setActual_shipment_time(actualShipmentTime);
                orderData.setLogistics_channel_id(String.valueOf(logisticsChannelId));
                orderData.setLogistics_list_type(String.valueOf(logisticsListType));
                orderData.setLogistics_list(new ArrayList<>());

                HeadLogisticsList headLogisticsList = new HeadLogisticsList();
                List<TrackingList> trackingList = new ArrayList<>();
                TrackingList tracking = new TrackingList();
                tracking.setTracking_no(shipmentId);
                tracking.setTransport_type(transportType);// 常量
                tracking.setOrder_type_code(orderTypeCode);// 常量
                tracking.setRemark(trackingInformations);
                trackingList.add(tracking);
                headLogisticsList.setTracking_list(trackingList);

                EstimateExpensesList estimateExpensesList = new EstimateExpensesList();
                estimateExpensesList.setChargeable_weight(estimateChargeableWeight);
                estimateExpensesList.setPrice(estimatePrice);
                estimateExpensesList.setPrice_currency(estimatePriceCurrency);
                estimateExpensesList.setLogistics_fee(estimateLogisticsFee);
                estimateExpensesList.setLogistics_fee_currency(estimateLogisticsFeeCurrency);
                estimateExpensesList.setRemark(estimateRemark);
                estimateExpensesList.setOther_fee_arr(estimateOtherFeeList);
                headLogisticsList.setEstimate_expenses_list(estimateExpensesList);

                ActualExpensesList actualExpensesList = new ActualExpensesList();
                actualExpensesList.setTax_fee(actualTaxFee);
                actualExpensesList.setTax_fee_currency(actualTaxFeeCurrency);
                actualExpensesList.setChargeable_weight(actualChargeWeight);
                actualExpensesList.setWeight(actualWeight);
                actualExpensesList.setVoluem(actualVolume);
                actualExpensesList.setPrice(actualPrice);
                actualExpensesList.setPrice_currency(actualPriceCurrency);
                actualExpensesList.setLogistics_fee(actualLogisticsFee);
                actualExpensesList.setLogistics_fee_currency(actualLogisticsFeeCurrency);
                actualExpensesList.setRemark(actualRemark);
                actualExpensesList.setOther_fee_arr(actualOtherFeeList);
                headLogisticsList.setActual_expenses_list(actualExpensesList);

                orderData.setHead_logistics_list(headLogisticsList);
                List<OrderData> dataTemp = new ArrayList<>();
                dataTemp.add(orderData);
                Gson gson = new Gson();
                jsonRequestBody = gson.toJson(dataTemp);
            } else {
                // 处理错误响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    LogUtil.severe(line);
                }
                LogUtil.severe("Failed to retrieve shipment detail, server returned: " + responseCode);
            }

        } catch (Exception e) {
            LogUtil.severe(e.getMessage());
        }
        return jsonRequestBody;
    }

}
