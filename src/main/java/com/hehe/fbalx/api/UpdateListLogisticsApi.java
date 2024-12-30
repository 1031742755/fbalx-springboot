package com.hehe.fbalx.api;

import com.hehe.fbalx.entity.ApiSignResult;
import com.hehe.fbalx.entity.Constants;
import com.hehe.fbalx.utils.ApiSignProductor;
import com.hehe.fbalx.utils.LogUtil;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class UpdateListLogisticsApi {

        public void updateListLogistics(String jsonBody){
            try {
                // 将转义字符替换为原有的字符串
                jsonBody = jsonBody.replace("\\u003c","<");
                jsonBody = jsonBody.replace("\\u003e",">");
//                System.out.println("UpdateListLogisticsApi: \n" +jsonBody);
                ApiSignResult apiSignResult = ApiSignProductor.productor("data", jsonBody);
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
                URL url = new URL(Constants.UPDATE_LIST_LOGISTICS + "?" + queryParams);

                // 打开连接
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

                // 设置请求方法
                connection.setRequestMethod("POST");

                // 设置请求头
                connection.setRequestProperty("User-Agent", "Apifox/1.0.0 (https://apifox.com)");
                connection.setRequestProperty("Content-Type", "application/json");

                // 允许输出
                connection.setDoOutput(true);

                // 规范化Body参数格式
                String strBody = "{\"data\":" + jsonBody + "}";
//                System.out.println("strBody: " + strBody);
                // 将请求体写入连接输出流
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = strBody.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                // 获取响应状态
                int statusCode = connection.getResponseCode();
                LogUtil.info("Response Code: " + statusCode);

                // 读取响应内容
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
//                    System.out.println("Response: " + response.toString());
                }

            } catch (Exception e) {
                LogUtil.severe(e.getMessage());
            }
        }

}
