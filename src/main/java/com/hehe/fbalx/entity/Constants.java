package com.hehe.fbalx.entity;

public class Constants {

    public static final String APP_ID = "ak_Pd7eVJXq1Ec0d";
    public static final String APP_SECRET = "Xv8kMY7TQtVUTNrfNfUEoA==";
    public static final String SEARCH_FIELD = "search_field";
    public static final String SHIPMENT_SN = "shipment_sn";
    public static final String ACCESS_TOKEN_PATH = "https://openapi.lingxing.com/api/auth-server/oauth/access-token";
    public static final String GET_SHIPMENT_LIST_PATH = "https://openapi.lingxing.com/erp/sc/routing/storage/shipment/getInboundShipmentList";
    public static final String GET_SHIPMENT_DETAIL = "https://openapi.lingxing.com/erp/sc/routing/storage/shipment/getInboundShipmentListMwsDetail";
    public static final String GET_TRACKING = "http://lzgylx.nextsls.com/api/v5/shipment/get_tracking";
    public static final String UPDATE_LIST_LOGISTICS = "https://openapi.lingxing.com/erp/sc/routing/storage/shipment/updateListLogistics";
    public static final String jsonBody = "{\n" +
            "    \"data\": [{\n" +
            "        \"order_sn\": \"SP240920001\",\n" +
            "        \"expected_arrival_date\": \"2024-10-31\",\n" +
            "        \"etd_date\": \"\",\n" +
            "        \"eta_date\": \"\",\n" +
            "        \"delivery_date\": \"\",\n" +
            "        \"actual_shipment_time\": \"\",\n" +
            "        \"logistics_channel_id\":14921,\n" +
            "        \"logistics_list_type\": 1,\n" +
            "        \"logistics_list\": [],\n" +
            "        \"head_logistics_list\": {\n" +
            "            \"tracking_list\": [{\n" +
            "                \"tracking_no\":\"FBA18FL44Y77\",\n" +
            "                \"transport_type\":2,\n" +
            "                \"order_type_code\":\"3\",\n" +
            "                \"remark\":\"货件已送仓因亚马逊爆仓还没卸货，已持续催促 2024-11-04<br>已签收，iSA：677058537 2024-10-30<br>预计10.21送仓，实际以POD入仓时间为准 2024-10-19<br>已拆柜，等待派送 2024-10-18<br>预计10.17日提柜 2024-10-15<br>10.11已到港，待卸船提柜中2024-10-12<br>\"}],\n" +
            "            \"estimate_expenses_list\": {\n" +
            "                \"chargeable_weight\": \"832.65\",\n" +
            "                \"price\": \"0.0000\",\n" +
            "                \"price_currency\": \"CNY\",\n" +
            "                \"logistics_fee\": \"\",\n" +
            "                \"logistics_fee_currency\": \"CNY\",\n" +
            "                \"remark\": \"\",\n" +
            "                \"other_fee_arr\": [{\n" +
            "                    \"fee_type_id\": \"241452431687139840\",\n" +
            "                    \"other_amount\": \"\",\n" +
            "                    \"other_currency\": \"CNY\"\n" +
            "                },{\n" +
            "                    \"fee_type_id\": \"0\",\n" +
            "                    \"other_amount\": \"\",\n" +
            "                    \"other_currency\": \"CNY\"\n" +
            "                }]\n" +
            "            },\n" +
            "            \"actual_expenses_list\": {\n" +
            "                \"tax_fee\":\"\",\n" +
            "                \"tax_fee_currency\":\"CNY\",\n" +
            "                \"chargeable_weight\":\"840.00\",\n" +
            "\n" +
            "                \"weight\": \"827.00\",\n" +
            "                \"volume\": \"2.10\",\n" +
            "                \"price\": \"0.0000\",\n" +
            "                \"price_currency\": \"CNY\",\n" +
            "                \"logistics_fee\": \"\",\n" +
            "                \"logistics_fee_currency\": \"CNY\",\n" +
            "                \"remark\": \"\",\n" +
            "                \"other_fee_arr\": [{\n" +
            "                    \"fee_type_id\": \"241452431687139840\",\n" +
            "                    \"other_amount\": \"\",\n" +
            "                    \"other_currency\": \"CNY\"\n" +
            "                },{\n" +
            "                    \"fee_type_id\": \"0\",\n" +
            "                    \"other_amount\": \"\",\n" +
            "                    \"other_currency\": \"CNY\"\n" +
            "                }]\n" +
            "            }\n" +
            "        }\n" +
            "    }]\n" +
            "}";
}
