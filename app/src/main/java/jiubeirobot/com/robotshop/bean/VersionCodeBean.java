package jiubeirobot.com.robotshop.bean;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/25.
 * 创建内容：
 * ==========================
 */

public class VersionCodeBean {
    private String id; //apk记录id
    private String versionCode;  //apk版本号
    private String versionUrl;  //apk版本地址
    private String versionName;  //apk版本名称
    private String versionDescript;  //apk版本描述

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionUrl() {
        return versionUrl;
    }

    public void setVersionUrl(String versionUrl) {
        this.versionUrl = versionUrl;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionDescript() {
        return versionDescript;
    }

    public void setVersionDescript(String versionDescript) {
        this.versionDescript = versionDescript;
    }
}
