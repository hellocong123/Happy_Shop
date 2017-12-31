package com.cong.happy_shop.bean;

/**
 * Author ：Cong
 * Time   : 2017/12/25
 * desc   ：
 */

public class BannerBean {
    /**
     * image : /1478770583834.png
     * option : 3
     * type : 0
     * value : {"url":"/act20161111?cyc_app=1"}
     */

    private String image;
    private int option;
    private int type;
    private ValueBean value;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ValueBean getValue() {
        return value;
    }

    public void setValue(ValueBean value) {
        this.value = value;
    }
}