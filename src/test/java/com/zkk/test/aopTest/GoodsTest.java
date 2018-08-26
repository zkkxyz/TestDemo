package com.zkk.test.aopTest;

import com.zkk.test.aop.goods.model.Amount;
import com.zkk.test.aop.goods.model.dao.MockDataBase;
import com.zkk.test.aop.goods.model.entity.Goods;
import com.zkk.test.aop.goods.model.entity.User;
import com.zkk.test.aop.goods.model.impl.Compute_Price;
import com.zkk.test.aop.goods.service.Discount;
import com.zkk.test.aop.goods.service.PayService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zkk on 2018/8/26 12:50 .
 */
public class GoodsTest {
    //模拟场景，还未进入打折优惠活动期间
    public void MockNotDisCount(){
        PayService payService=new PayService();
        Map<Integer, Goods> goodsMap= MockDataBase.goodsMap;
        Map<Integer, User> userMap= MockDataBase.userMap;
        //模拟获取两件商品
        Goods goods=goodsMap.get(10001);
        Goods goods02=goodsMap.get(10002);
        //模拟用户
        User user=userMap.get(10086);
        List<Goods> goodsList=new ArrayList<Goods>();
        goodsList.add(goods);
        goodsList.add(goods02);
        //模拟付款
        Compute_Price cp=new Compute_Price();
        cp.setGoodsList(goodsList);
        cp=payService.Pay(user,goodsList);
        //打印凭据
        payService.printOrderList(cp);
    }

    //模拟场景，已经进入打折优惠活动期间
    public void MockDisCount(){
        ApplicationContext context= new ClassPathXmlApplicationContext("classpath:spring/beans.xml");
        Amount amount= (Amount) context.getBean("proxyFactorBean");
        PayService payService=new PayService();
        Map<Integer,Goods> goodsMap= MockDataBase.goodsMap;
        Map<Integer,User> userMap= MockDataBase.userMap;
        //模拟获取两件商品
        Goods goods=goodsMap.get(10001);
        Goods goods02=goodsMap.get(10002);
        //模拟用户
        User user=userMap.get(10086);
        List<Goods> goodsList=new ArrayList<Goods>();
        goodsList.add(goods);
        goodsList.add(goods02);
        //模拟付款
        Compute_Price cp=new Compute_Price();
        cp.setGoodsList(goodsList);
        cp=payService.Pay(user,goodsList);
        //打印凭据
        payService.printOrderList(cp);
        ((Discount)amount).discount(cp);
    }


    public static void main(String[] args) {
        GoodsTest test=new GoodsTest();
        test.MockNotDisCount();
        System.out.println("--------------------------------------------");
        test.MockDisCount();
    }
}
