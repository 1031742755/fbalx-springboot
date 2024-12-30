package com.hehe.fbalx.api;

import com.hehe.fbalx.entity.ApiSignResult;
import com.hehe.fbalx.entity.Constants;
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

public class ShipmentListApi {


    public List<String> getShipmentList(){
        List<String> shipmentSnList = new ArrayList<String>();
        try {
            ApiSignResult apiSignResult = ApiSignProductor.productor(Constants.SEARCH_FIELD, Constants.SHIPMENT_SN);
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
            URL url = new URL(Constants.GET_SHIPMENT_LIST_PATH + "?" + queryParams);
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
            String jsonInputString = "{\n" +
                    "    \"search_field\": \"shipment_sn\"\n" +
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
                JSONObject jsonObject = new JSONObject(content.toString());
                JSONArray shipmentList = null;
                try {
                    shipmentList = jsonObject.getJSONObject("data").getJSONArray("list");
                }catch (JSONException e){
                    LogUtil.severe(e.getMessage());
                    return null;
                }
                for (int i = 0; i < shipmentList.length(); i++) {
                    JSONObject shipment = shipmentList.getJSONObject(i);
                    String logisticsProviderName = shipment.getString("logistics_provider_name");
                    String shipmentSn = shipment.getString("shipment_sn");
                    if ("联纵".equals(logisticsProviderName)) {
                        shipmentSnList.add(shipmentSn);
                        LogUtil.info("Logistics providers: " + logisticsProviderName + ", shipmentSn: " + shipmentSn);
                    }
                }
            } else {
                // 处理错误响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    LogUtil.severe(line);
                }
                LogUtil.severe("Enquiry failure on 联纵, server returned: " + responseCode);
            }

        } catch (Exception e) {
            LogUtil.severe(e.getMessage());
        }
        return shipmentSnList;
    }

}
