package com.zkk.test.aop.goods.service;

import com.zkk.test.aop.goods.model.impl.Compute_Price;

/**
 * Created by zkk on 2018/8/26 12:49 .
 * 使用说明：商品打折
 */
public interface Discount {
    double discount(Compute_Price cp);
}
