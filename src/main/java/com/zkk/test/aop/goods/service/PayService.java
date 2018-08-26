package com.zkk.test.aop.goods.service;

import com.zkk.test.aop.goods.model.entity.Goods;
import com.zkk.test.aop.goods.model.entity.User;
import com.zkk.test.aop.goods.model.impl.Compute_Price;

import java.util.List;

/**
 * Created by zkk on 2018/8/26 12:30 .
 * 客户付款服务
 */
public class PayService {
    //模拟数据库情况
    public Compute_Price Pay(User user, List<Goods> goodsList){
        Compute_Price compute_price=new Compute_Price();
        compute_price.setGoods_id(1001675);
        compute_price.setGoodsList(goodsList);
        compute_price.setUid(user.getUid());
        return compute_price;
    }

    //打印订单内容
    public void printOrderList(Compute_Price compute_price){
        System.out.println("顾客id："+compute_price.getUid());
        System.out.println("商品票据单号"+compute_price.getGoods_id());
        List<Goods> goodsList=compute_price.getGoodsList();
        for (Goods good:goodsList) {
            System.out.println("商品编号 ："+good.getG_id());
            System.out.println("名称 : "+good.getG_name());
            System.out.println("价格 : "+good.getG_price());
        }
        System.out.println("总价"+compute_price.pay_all());
    }
}
