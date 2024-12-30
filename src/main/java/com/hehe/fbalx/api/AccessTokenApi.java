package com.hehe.fbalx.api;

import com.hehe.fbalx.entity.Constants;
import com.hehe.fbalx.utils.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AccessTokenApi {


    public static String parseToken(String response) {
        // 解析返回的JSON字符串并提取access_token (示例假设响应为JSON格式)
        // 你可以使用JSON库如org.json或Gson解析返回的access_token
        JSONObject jsonObject = new JSONObject(response);
        return jsonObject.getJSONObject("data").get("access_token").toString(); // 此处应根据具体响应格式提取token
    }

    public static String getAccessToken(String apiPath) throws IOException{
        URL url = new URL(apiPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=---Boundary");
        conn.setDoOutput(true);

        // 设置请求参数
        String boundary = "---Boundary";
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String accessToken = null;
        // 发送请求数据
        try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
            // appId 参数
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"appId\"" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(Constants.APP_ID + lineEnd);

            // appSecret 参数
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"appSecret\"" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(Constants.APP_SECRET+ lineEnd);

            // 结束部分
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            dos.flush();
        }catch (Exception e){
            LogUtil.severe(e.getMessage());
        }

        // 获取响应代码
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // 处理响应
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            try {
                accessToken = parseToken(content.toString());
            }catch (JSONException e){
                LogUtil.severe(e.getMessage());
                return null;
            }
            LogUtil.info("" + responseCode + " accessToken=" + accessToken);
            in.close();
        } else {
            // 处理错误响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                LogUtil.severe(line);
            }
            LogUtil.severe("Failed to retrieve access token, server returned: " + responseCode);
        }
        return accessToken;
    }



}
