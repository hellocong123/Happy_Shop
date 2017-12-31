package com.cong.happy_shop.bean;

/**
 * Author ：Cong
 * Time   : 2017/12/25
 * desc   ：
 */

public class Channel {
    /**
     * channel_name : 服饰
     * image : /app/img/menu-cyc.png
     * option : 2
     * type : 1
     * value : {"channel_id":"8"}
     */

    private String channel_name;
    private String image;
    private int option;
    private int type;
    private ValueBean value;

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

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
