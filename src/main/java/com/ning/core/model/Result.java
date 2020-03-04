package com.ning.core.model;

import com.ning.core.constant.ApiMsg;
import lombok.Data;

import java.io.Serializable;

/**
 * 封装返回数据
 *
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    public static final long serialVersionUID = 42L;

    private String msg = ApiMsg.msg(ApiMsg.KEY_SUCCESS);

    private int code = ApiMsg.KEY_SUCCESS;

    private T data;

    public Result() {
        super();
    }

    public Result(T data) {
        super();
        this.data = data;
    }

    public Result(T data, int keyCode, int msgCode) {
        super();
        this.data = data;
        this.code = Integer.parseInt(keyCode + "" + msgCode);
        this.msg = ApiMsg.code2Msg(keyCode, msgCode);
    }

    public Result(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }
}
