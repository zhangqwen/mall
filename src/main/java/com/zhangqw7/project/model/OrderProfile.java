package com.zhangqw7.project.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

public class OrderProfile implements Serializable{

    private Integer id;
    private LocalDateTime order_time;
    private Integer order_id;
    /*
    private Double total_price;
    private String consignee;
    private String telephone;
    private String address;
    */
    // true : 普通用户收货；false:订单被拒绝；
    private Boolean status;
    private Integer user_id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getOrder_time() {
        return order_time;
    }

    public void setOrder_time(LocalDateTime order_time) {
        this.order_time = order_time;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    /*
    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    */

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "OrderProfile{ "+
                "id=" + id +
                ", order_time=" + order_time +
                /*
                ", total_price=" + total_price +
                ", consignee=" + consignee +
                ", telephone=" + telephone +
                ", address=" + address +
                */
                "}";
    }
}
