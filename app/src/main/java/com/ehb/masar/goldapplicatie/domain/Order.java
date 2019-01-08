package com.ehb.masar.goldapplicatie.domain;

import java.util.Date;

public class Order  extends  Entity{


    private Integer TotalPrice ;
    private String PayMethod ;
    private String Order_date ;

    public Integer getTotalePrice() {
        return TotalPrice;
    }

    public void setTotalePrice(Integer totalePrice) {
        TotalPrice = totalePrice;
    }

    public String getPayMethod() {
        return PayMethod;
    }

    public void setPayMethod(String payMethod) {
        PayMethod = payMethod;
    }

    public String getOrder_date() {
        return Order_date;
    }

    public void setOrder_date(String order_date) {
        Order_date = order_date;
    }


    @Override
    public String toString() {
        return "Order{" +
                "TotalePrice=" + TotalPrice +
                ", PayMethod='" + PayMethod + '\'' +
                ", Order_date=" + Order_date +
                '}';
    }
}
