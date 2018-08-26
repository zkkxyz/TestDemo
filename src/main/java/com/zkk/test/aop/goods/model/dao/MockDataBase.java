package com.zkk.test.aop.goods.model.dao;

import com.zkk.test.aop.goods.model.entity.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zkk on 2018/8/26 12:21 .
 * 模拟数据库
 */
public class MockDataBase {
    public static Map<Integer, Goods> goodsMap=new HashMap<>();
    public static Map<Integer, User> userMap=new HashMap<>();
    //模拟数据库初始化操作
    static{
        initUsers();
        initGoods();
    }

    public static void initGoods(){
        Goods goods= new Goods();
        goods.setG_id(1002);
        goods.setG_name("冰红茶");
        goods.setG_price(3.5);
        goods.setG_quantity(200);
        Goods goods02= new Goods();
        goods02.setG_id(1003);
        goods02.setG_name("清风纸巾");
        goods02.setG_price(19.5);
        goods02.setG_quantity(200);
        goodsMap.put(10001,goods);
        goodsMap.put(10002,goods02);
    }

    public static void initUsers(){
        User user= new User();
        user.setLevel("vip");
        user.setUname("小明");
        user.setUid(10086);
        User user02= new User();
        user02.setLevel("普通用户");
        user02.setUname("小王");
        user02.setUid(10087);
        userMap.put(user.getUid(),user);
        userMap.put(user02.getUid(),user02);
    }
}
