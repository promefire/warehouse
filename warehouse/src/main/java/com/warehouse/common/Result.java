package com.warehouse.common;

import com.baomidou.mybatisplus.extension.api.R;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @author promefire
 * @date 2024/5/8 8:32
 * description:
 */
public class Result {

    private int code;

    private String msg;

    private Long total;

    private Object data;


    public static Result fail(){
        return result(400,"失败",0L,null);
    }

    public static Result suc(){
        return result(200,"成功",0L,null);
    }
    public static Result suc(Object data){
        return result(200,"成功",0L,data);
    }

    public static Result suc(Object data, Long total){
        return result(200,"成功",total,data);
    }




    public static Result result(int code, String msg, Long total, Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setTotal(total);
        result.setData(data);

        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
