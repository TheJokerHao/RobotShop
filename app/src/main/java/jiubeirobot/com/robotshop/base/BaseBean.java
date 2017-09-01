package jiubeirobot.com.robotshop.base;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/18.
 * 创建内容：数据总类
 * ==========================
 */

public class BaseBean<T> implements Serializable {

    private String msg;

    @SerializedName("data")
    private T data;

    private String code;

    public void setMessage(String message) {
        this.msg = message;
    }

    public String getMessage() {
        return this.msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
