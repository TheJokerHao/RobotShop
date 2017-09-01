package jiubeirobot.com.robotshop.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/25.
 * 创建内容：
 * ==========================
 */

public class ObjBean<T> implements Serializable{
    private String state;

    @SerializedName("obj")
    private T obj;

    private String msg;

    public String getMsg() {
        return msg;
    }

    public String getState() {
        return state;
    }

    public T getObj() {
        return obj;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public void setState(String state) {
        this.state = state;
    }


}
