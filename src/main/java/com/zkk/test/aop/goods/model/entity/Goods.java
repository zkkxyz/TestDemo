package com.zkk.test.aop.goods.model.entity;

/**
 * Created by zkk on 2018/8/26 12:17 .
 */
public class Goods {
    private int G_id; //商品编号
    private  String G_name;  //商品名称
    private double G_price; //商品单价
    private int G_quantity;  //商品数量

    public int getG_id() {
        return G_id;
    }

    public void setG_id(int g_id) {
        G_id = g_id;
    }

    public String getG_name() {
        return G_name;
    }

    public void setG_name(String g_name) {
        G_name = g_name;
    }

    public double getG_price() {
        return G_price;
    }

    public void setG_price(double g_price) {
        G_price = g_price;
    }

    public int getG_quantity() {
        return G_quantity;
    }

    public void setG_quantity(int g_quantity) {
        G_quantity = g_quantity;
    }
}
