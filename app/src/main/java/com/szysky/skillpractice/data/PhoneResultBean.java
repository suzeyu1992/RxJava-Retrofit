package com.szysky.skillpractice.data;

/**
 * Created by suzeyu on 16/7/15.
 *
 * 检测手机归属地的接口
 */

public class PhoneResultBean {

    /**
     * supplier : 移动
     * phone : 17050004333
     * prefix : 1705000
     * province : 北京
     * city : 北京
     * suit : 170卡
     */

    private RetDataBean retData;
    /**
     * retData : {"supplier":"移动","phone":"17050004333","prefix":"1705000","province":"北京","city":"北京","suit":"170卡"}
     * errNum : 0
     * retMsg : success
     */

    private int errNum;
    private String retMsg;

    public RetDataBean getRetData() {
        return retData;
    }

    public void setRetData(RetDataBean retData) {
        this.retData = retData;
    }

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public static class RetDataBean {
        private String supplier;
        private String phone;
        private String prefix;
        private String province;
        private String city;
        private String suit;

        public String getSupplier() {
            return supplier;
        }

        public void setSupplier(String supplier) {
            this.supplier = supplier;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
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

        public String getSuit() {
            return suit;
        }

        public void setSuit(String suit) {
            this.suit = suit;
        }
    }
}
