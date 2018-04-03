package com.mongcent.tnaot.exception;

public enum RespCode {

    SUCCESS("0","成功"),
    PARAMETER_ERROR("10101", "参数错误"),
    REQUEST_ERROR("0001", "请求异常，请联系管理员"),
    NULL_POINT("00002","空指针异常！"),
    UNKNOWN_ERROR("1","未知异常请联系管理员");

    private String resCode;
    private String resMsg;

    RespCode(String resCode,String resMsg){
        this.resCode = resCode;
        this.resMsg = resMsg;
    }

    public String getResCode() {
        return resCode;
    }

    public String getResMsg() {
        return resMsg;
    }
}
