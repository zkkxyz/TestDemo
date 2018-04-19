package com.zkk.test.statePatternTest;

import com.zkk.test.statePattern.OrderContext;
import org.junit.Test;

/**
 * Created by zhangkaikai on 2018/4/19 019 14:13 .
 */
public class sPTest {


    @Test
    public void sTest() {
        OrderContext orderContext = new OrderContext();
        orderContext.confirm();     //已预定状态>已确认状态
        orderContext.modify();      //已确认状态>已预定状态
        orderContext.confirm();    //已预定状态>已确认状态
        orderContext.pay();       //已确认状态>已锁定状态
        orderContext.modify();    //已锁定状态
    }
}
