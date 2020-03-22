package com.car.demo.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class AopAspect {
    /**
     * 前置通知：目标方法调用之前执行的代码
     * @param jp
     */
    public void doBefore(JoinPoint jp){
        System.out.println("===========执行前置通知============");
    }

    /**
     * 后置返回通知：目标方法正常结束后执行的代码
     * 返回通知是可以访问到目标方法的返回值的
     * @param jp
     * @param result
     */
    public void doAfterReturning(JoinPoint jp,String result){
        System.out.println("===========执行后置通知============");
        System.out.println("返回值result==================="+result);
    }

    /**
     * 最终通知：目标方法调用之后执行的代码（无论目标方法是否出现异常均执行）
     * 因为方法可能会出现异常，所以不能返回方法的返回值
     * @param jp
     */
    public void doAfter(JoinPoint jp){
        System.out.println("===========执行最终通知============");
    }

    /**
     *
     * 异常通知：目标方法抛出异常时执行的代码
     * 在目标方法执行的时候,如果抛出异常,立即进入此方法
     * 可以访问到异常对象
     * @param jp
     * @param ex
     */
    public void doAfterThrowing(JoinPoint jp,Exception ex){
        System.out.println("===========执行异常通知============");
    }

    /**
     * 环绕通知：目标方法调用前后执行的代码，可以在方法调用前后完成自定义的行为。
     * 包围一个连接点（join point）的通知。它会在切入点方法执行前执行同时方法结束也会执行对应的部分。
     * 主要是调用proceed()方法来执行切入点方法，来作为环绕通知前后方法的分水岭。
     *
     * 环绕通知类似于动态代理的全过程：ProceedingJoinPoint类型的参数可以决定是否执行目标方法。
     * 而且环绕通知必须有返回值，返回值即为目标方法的返回值
     * @param pjp
     * @return
     * @throws Throwable
     */
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("======执行环绕通知开始=========");
        // 调用方法的参数
        Object[] args = pjp.getArgs();
        // 调用的方法名
        String method = pjp.getSignature().getName();
        // 获取目标对象
        Object target = pjp.getTarget();
        // 执行完方法的返回值
        // 调用proceed()方法，就会触发切入点方法执行
        Object result=pjp.proceed();
        //如果调用pjp.proceed()执行业务方法的时候抛出异常,那么下面的代码将不会执行
        System.out.println("输出,方法名：" + method + ";目标对象：" + target + ";返回值：" + result);
        System.out.println("======执行环绕通知结束=========");
        return result;
    }
}
