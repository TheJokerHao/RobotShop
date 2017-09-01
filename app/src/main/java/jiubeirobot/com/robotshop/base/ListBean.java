package jiubeirobot.com.robotshop.base;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/30.
 * 创建内容：返回数据为集合的基实体类
 * ==========================
 */

public class ListBean<T> implements Serializable {

    private String state;

    @SerializedName("list")
    private List<T> list;

    private int rowCount;

    private String msg;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getRowCount() {
//        return 50;
        return this.rowCount;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }


}
