package com.hehe.fbalx.utils;

import com.hehe.fbalx.api.AccessTokenApi;
import com.hehe.fbalx.entity.ApiSignResult;
import com.hehe.fbalx.entity.Constants;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

public class ApiSignProductor {

//    @Test
//    public  void productor() throws Exception {
    public static ApiSignResult productor(String key, String value) throws Exception {

        String accessToken = AccessTokenApi.getAccessToken(Constants.ACCESS_TOKEN_PATH);
//        System.out.println("access token: " + accessToken);
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
//        String start_date = "2024-10-28 00:00:00";
//        String end_date = "2024-11-04 00:00:00";
//        String date_type = "2";*/

        // 1. ASCII排序并过滤空值
        Map<String, String> params = new TreeMap<>();
        params.put("access_token", accessToken);
        params.put("app_key", Constants.APP_ID);
        params.put("timestamp", timestamp);
//        params.put("shipment_sn", "SP240920001");
        params.put(key, value);
//        params.put("start_date", start_date);
//        params.put("end_date", end_date);
//        params.put("date_type", date_type);

        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                if (queryString.length() > 0) {
                    queryString.append("&");
                }
                queryString.append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
//        System.out.println(queryString.toString());
        // 2. MD5加密
        String md5Result = md5(queryString.toString()).toUpperCase();

        // 3. AES加密
        String sign = aesEncrypt(md5Result, Constants.APP_ID);

        // 4. URL编码
        String finalSign = java.net.URLEncoder.encode(sign, "UTF-8");
        // 输出未编码的sign
//        System.out.println("timestamp: " + timestamp);
//        System.out.println("sign:      " + sign);
//        System.out.println("finalSign: " + finalSign);
        ApiSignResult apiSignResult = new ApiSignResult();
        apiSignResult.setSign(finalSign);
        apiSignResult.setAcessToken(accessToken);
        apiSignResult.setTimestamp(timestamp);
        LogUtil.info("The api signature has been generated.");
        return apiSignResult;
    }

    // MD5加密
    private static String md5(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // AES加密
    private static String aesEncrypt(String data, String key) throws Exception {
        // 确保密钥长度为16字节
        SecretKeySpec secretKeySpec = new SecretKeySpec(Arrays.copyOf(key.getBytes("UTF-8"), 16), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedData = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedData);
    }
}
