package com.mongcent.tnaot.exception;

public class JsonResult {

    private String resultCode;
    private String resultMsg;
    private Object resultData = null;
    private String requestUrl;

    public JsonResult(){}

    public JsonResult(String requestUrl, RespCode respCode, Object resultData) {
        this.requestUrl = requestUrl;
        this.resultData = resultData;
        this.resultCode = respCode.getResCode();
        this.resultMsg = respCode.getResMsg();
    }

    public JsonResult(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public JsonResult(String requestUrl, RespCode respCode) {
        this.requestUrl = requestUrl;
        this.resultCode = respCode.getResCode();
        this.resultMsg = respCode.getResMsg();
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void setRespCode(RespCode respCode){
        this.resultCode = respCode.getResCode();
        this.resultMsg = respCode.getResMsg();
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "resultCode='" + resultCode + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                ", resultData=" + resultData +
                ", requestUrl='" + requestUrl + '\'' +
                '}';
    }
}


