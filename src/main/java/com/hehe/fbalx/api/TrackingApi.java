package com.hehe.fbalx.api;

import com.hehe.fbalx.entity.Constants;
import com.hehe.fbalx.utils.LogUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrackingApi {

    public String getTrackingInformation(String trackingNo){
        String trackingStr = null;
        try {
            // 创建 URL 对象
            URL url = new URL(Constants.GET_TRACKING);

            // 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer 667e2973483588203f7a52d3667e2973433163259");
            connection.setRequestProperty("User-Agent", "Apifox/1.0.0 (https://apifox.com)");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // 构造请求体
            String jsonInputString = "{\n" +
                    "    \"shipment\": {\n" +
                    "        \"shipment_id\": \"\",\n" +
                    "        \"client_reference\": \"" + trackingNo +"\",\n" +
                    "        \"tracking_number\": \"\",\n" +
                    "        \"parcel_number\": \"\",\n" +
                    "        \"language\": \"zh\"\n" +
                    "    }\n" +
                    "}";

            // 发送请求体
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 获取响应
            int code = connection.getResponseCode();
            LogUtil.info("" + code);
//            System.out.println("Response Code: " + code);
            if (code == HttpURLConnection.HTTP_OK) {
                // 处理响应
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                // 解析 JSON 数据
                JSONObject jsonObject = new JSONObject(content.toString());
                JSONArray traces = null;
                try {
                    traces= jsonObject.getJSONObject("data").getJSONObject("shipment").getJSONArray("traces");
                }catch (JSONException e){
                    LogUtil.severe(e.getMessage());
                    return null;
                }
                // 设置日期格式
                SimpleDateFormat sdf;
                sdf = new SimpleDateFormat("yyyy-MM-dd");

                // 提取前三条信息及时间
                for (int i = 0; i < 3; i++) {
                    JSONObject trace = traces.getJSONObject(i);
                    String info = trace.getString("info");
                    long time = trace.getLong("time");

                    // 将时间戳转换为日期格式
                    Date date = new Date(time * 1000); // 时间戳是秒级别，需要乘以1000转换为毫秒级别
                    String formattedDate = sdf.format(date);

                    // 输出结果
                    if(i == 0){
                        trackingStr = info + " " + formattedDate + "<br>";
                    }else{
                        trackingStr = trackingStr + info + " " + formattedDate + "<br>";
                    }
                }
            } else {
                // 处理错误响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    LogUtil.severe(line);
                }
                LogUtil.severe("Failed to retrieve access token, server returned: " + code);
            }

        } catch (Exception e) {
            LogUtil.severe(e.getMessage());
        }
        return trackingStr;
    }
}
