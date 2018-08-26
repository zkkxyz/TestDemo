package com.zkk.test.aop.some;

import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * Created by zkk on 2018/8/26 11:23 .
 */
public class Other extends DelegatingIntroductionInterceptor implements IOther{

//    public class Other implements IntroductionInterceptor,IOther{
    public void doOther(){
        System.out.println("Other对象的功能");
    }

//    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
//        if(implementsInterface(methodInvocation.getMethod().getDeclaringClass())){
//            return methodInvocation.getMethod().invoke(this, methodInvocation.getArguments());
//        }
//        else{
//            return methodInvocation.proceed();
//        }
//    }
//    public boolean implementsInterface(Class arg0){
//        boolean assignableFrom = arg0.isAssignableFrom(IOther.class);
//        return assignableFrom;
//    }

}
