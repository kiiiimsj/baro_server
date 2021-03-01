package com.wantchu.wantchu_server2.business;

public class ObjectMaker {

    public static org.json.simple.JSONObject getSimpleJSONObject() {
        return new org.json.simple.JSONObject();
    }

    public static org.json.simple.JSONArray getSimpleJSONArray() {
        return new org.json.simple.JSONArray();
    }

    public static org.json.JSONObject getJSONObject() {
        return new org.json.JSONObject();
    }

    public static org.json.JSONArray getJSONArray() {
        return new org.json.JSONArray();
    }

    public static org.json.simple.JSONObject getJSONObjectWithException(Exception exception) {
        org.json.simple.JSONObject jsonObject = new org.json.simple.JSONObject();
        jsonObject.put("result", false);
//        jsonObject.put("message", exception.getMessage());
        jsonObject.put("message", exception.getStackTrace().toString());
        return jsonObject;
    }
}