package com.lyentech.toolsplatform.Common;

/*
 * @Author 672025
 * @Date 16:38 2020/9/18
 * @Description 同一结果封装
 **/
import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private String code;
    private String message;
    private Object data;

    public static Result success(Object data){
        Result m = new Result();
        m.setCode("20000");
        m.setData(data);
        m.setMessage("操作成功");
        return m;
    }

    public static Result success(String mess, Object data) {
        Result m = new Result();
        m.setCode("20000");
        m.setData(data);
        m.setMessage(mess);
        return m;
    }
    public static Result fail(String mess) {
        Result m = new Result();
        m.setCode("50008");
        m.setData(null);
        m.setMessage(mess);
        return m;
    }
    public static Result fail(String mess, Object data) {
        Result m = new Result();
        m.setCode("50008");
        m.setData(data);
        m.setMessage(mess);
        return m;
    }


}
