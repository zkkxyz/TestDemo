package com.zkk.test.aop.goods.model.impl;

import com.zkk.test.aop.goods.model.Amount;
import com.zkk.test.aop.goods.model.entity.Goods;

import java.util.List;

/**
 * Created by zkk on 2018/8/26 12:20 .
 * 购物凭据类
 */
public class Compute_Price implements Amount {
    private int uid;
    private int goods_id; //购买清单的编号
    private List<Goods> goodsList;
    public double pay_all(){    //计算总价格
        double total=0;
//        在这里添加需要实现的代码
        for (Goods good:goodsList) {
            total+=good.getG_price();
        }
        return total;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
