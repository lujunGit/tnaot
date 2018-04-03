package com.mongcent.tnaot;

/**
 * 统一返回对象
 */
public class GlobalResult {

    private int state;
    private String error_msg;
    private String client_msg;
    private Object result;

    public GlobalResult(int state, String error_msg, String client_msg, Object result) {
        this.state = state;
        this.error_msg = error_msg;
        this.client_msg = client_msg;
        this.result = result;
    }

    public GlobalResult(int state, String error_msg, String client_msg) {
        this.state = state;
        this.error_msg = error_msg;
        this.client_msg = client_msg;
    }

    public static GlobalResult sucResult(Object result) {
        return new GlobalResult(Constants.sucState, null, null, result);
    }

    public static GlobalResult falResult(String error_msg, String client_msg) {
        return new GlobalResult(Constants.falState, error_msg, client_msg);
    }

}
