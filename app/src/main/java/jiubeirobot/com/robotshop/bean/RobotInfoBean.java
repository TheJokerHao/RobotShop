package jiubeirobot.com.robotshop.bean;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/29.
 * 创建内容：机器人初始信息实体类
 * ==========================
 */

public class RobotInfoBean {
    String longitude;
    String latitude;
    String province;
    String city;
    String district;
    String number;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
