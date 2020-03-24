package com.car.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectLog {
    /**
     * 必须为final String类型的,注解里要使用的变量只能是静态常量类型的
     */
    public static final String pointcut="execution(* com.car.demo.controller.IndexController..*(..))";

    /**
     * 切面的前置方法 即方法执行前拦截到的方法
     * 在目标方法执行之前的通知
     * @param jp
     */
    @Before(pointcut)
    public void doBefore(JoinPoint jp){

        System.out.println("Before");
    }


    /**
     * 在方法正常执行通过之后执行的通知叫做返回通知
     * 可以返回到方法的返回值 注解属性returning
     * @param jp
     * @param result
     */
    @AfterReturning(value=pointcut,returning="result")
    public void doAfterReturning(JoinPoint jp,String result){
        System.out.println("AfterReturning");
    }

    /**
     * 最终通知：目标方法调用之后执行的通知（无论目标方法是否出现异常均执行）
     * @param jp
     */
    @After(value=pointcut)
    public void doAfter(JoinPoint jp){
        System.out.println("After");
    }

    /**
     * 在目标方法非正常执行完成, 抛出异常的时候会走此方法
     * @param jp
     * @param ex
     */
    @AfterThrowing(value=pointcut,throwing="ex")
    public void doAfterThrowing(JoinPoint jp, Exception ex) {
        System.out.println("AfterThrowing");
    }

    /**
     * 环绕通知：目标方法调用前后执行的通知，可以在方法调用前后完成自定义的行为。
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around(pointcut)
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{

        System.out.println("Around开始");
        // 调用方法的参数
        Object[] args = pjp.getArgs();
        // 调用的方法名
        String method = pjp.getSignature().getName();
        // 获取目标对象
        Object target = pjp.getTarget();
        // 执行完方法的返回值
        // 调用proceed()方法，就会触发切入点方法执行
        Object result=pjp.proceed();
        System.out.println("输出,方法名：" + method + ";目标对象：" + target + ";返回值：" + result);
        System.out.println("Around结束");
        //result="admin";
        return result;
    }
}
