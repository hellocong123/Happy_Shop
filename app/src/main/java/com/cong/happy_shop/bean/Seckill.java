package com.cong.happy_shop.bean;

import java.util.List;

/**
 * Author ：Cong
 * Time   : 2017/12/25
 * desc   ：
 */

public class Seckill {
    /**
     * end_time : 1479052800
     * list : [{"cover_price":"20.00","figure":"/1478489000522.png","name":"尚硅谷购物节特供优惠券  满600-120优惠券","origin_price":"20.00","product_id":"7100"},{"cover_price":"10.00","figure":"/1478489035167.png","name":"尚硅谷购物节特供优惠券  满300-80优惠券","origin_price":"10.00","product_id":"7101"},{"cover_price":"5.00","figure":"/1478489878735.png","name":"尚硅谷购物节特供优惠券  满160-40优惠券","origin_price":"5.00","product_id":"7102"},{"cover_price":"49.00","figure":"/1475045805488.jpg","name":"【古风原创】 自动直柄伞 晴雨伞 【云鹤游】包邮  新增折叠伞","origin_price":"69.00","product_id":"9593"},{"cover_price":"5.00","figure":"/1478678511949.png","name":"尚硅谷购物节特供优惠券  满60-20优惠券","origin_price":"5.00","product_id":"10536"},{"cover_price":"49.00","figure":"/1438680345318.jpg","name":"【古风原创】 自动直柄伞 晴雨伞 【青竹词】包邮  新增折叠伞","origin_price":"59.00","product_id":"555"}]
     * start_time : 1478772000
     */

    private String end_time;
    private String start_time;
    private List<ListBean> list;

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }
}