package com.zkk.test.aop.goods;

import com.zkk.test.aop.goods.model.impl.Compute_Price;
import com.zkk.test.aop.goods.service.Discount;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;

import java.text.DecimalFormat;

/**
 * Created by zkk on 2018/8/26 12:50 .
 * 切面口处
 */
public class OtherIntroduction implements IntroductionInterceptor, Discount {

    //判断调用的方法是否为指定类中的方法
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        if(implementsInterface(methodInvocation.getMethod().getDeclaringClass())){
            return methodInvocation.getMethod().invoke(this,methodInvocation.getArguments());
        }
        return methodInvocation.proceed();
    }

    @Override
    public boolean implementsInterface(Class<?> aClass) {
        return aClass.isAssignableFrom(Discount.class);
    }

    @Override
    public double discount(Compute_Price cp) {
        System.out.println("-----------------------");
        double count=cp.pay_all();
        DecimalFormat df= new DecimalFormat("######0.00");  //精确到小数点后两位数字
        if(count>100){
            System.out.println("优惠活动期间，满100消费即返20的优惠券！");
        }
        if(cp.getUid()==10086){//如果用户是vip用户
            count=count*0.7;
            System.out.println("打折之后，价格是："+df.format(count));
        }else{ //如果用户不是vip
            count=count*0.9;
            System.out.println("今日优惠，价格是："+df.format(count));
        }
        return 0;
    }
}
