package com.chatroom.server.protocol;

import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/24 0024.
 */
public class ProtocolResult {
    public final static int Code_Success = 0x01;
    public final static int Code_Error = 0x02;
    public final static int Code_UNKNOW = 0x03;

    public int resultCode;
    public String errorMsg;
    public Object resultParams;

    public ProtocolResult(String errorMsg){
        this.errorMsg = errorMsg;
        this.resultCode = Code_Error;
    }

    public ProtocolResult(){
        this.resultCode = Code_Success;
    }

    public static  String unknowResult(){
        ProtocolResult result = new ProtocolResult("");
        result.resultCode = Code_UNKNOW;
        return result.toJson();
    }

    public void setResult(Object resultParams){
        this.resultParams = resultParams;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }
}
