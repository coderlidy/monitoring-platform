package com.car.demo.aop;

import com.car.demo.mapper.CraneMapper;
import com.car.demo.mapper.LogMapper;
import com.car.demo.mapper.UserMapper;
import com.car.demo.model.Crane;
import com.car.demo.model.Log;
import com.car.demo.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component  //声明组件
@Aspect //  声明切面
@ComponentScan  //组件自动扫描
//@EnableAspectJAutoProxy //spring自动切换JDK动态代理和CGLIB
public class AspectLog {
    @Autowired
    private LogMapper logMapper;
    @Autowired
    private CraneMapper craneMapper;
    @Autowired
    private UserMapper userMapper;
    /**
     * 必须为final String类型的,注解里要使用的变量只能是静态常量类型的
     */
    public static final String pointcut="execution(* com.car.demo.mapper..*.*(..))";
    /**
     * 在方法正常执行通过之后执行的通知叫做返回通知
     * 可以返回到方法的返回值 注解属性returning
     * @param jp
     * @param result
     */
    @AfterReturning(value=pointcut)
    public void doAfterReturning(JoinPoint jp){
        String methodName=jp.getSignature().getName();
        String interfaceName=jp.getTarget().getClass().getInterfaces()[0].getSimpleName();
        Object[] args=jp.getArgs();
        System.out.println(interfaceName+"."+methodName);
        if(interfaceName.equals("UserMapper")||interfaceName.equals("CraneMapper")){
            if(args.length>0){
                Log log=new Log();
                if(methodName.startsWith("insert")){
                    log.setCode("insert");
                    log.setDescribe("添加");
                }else if(methodName.startsWith("update")){
                    log.setCode("update");
                    log.setDescribe("更新");
                }else {
                    return;
                }
                Object object=args[0];
                if(object instanceof User){
                    log.setObject("用户"+((User) object).getUsername());
                }
                if(object instanceof Crane){
                    log.setObject("起重机"+((Crane) object).getCarNumber());
                }
                log.setGmtCreate(new Timestamp(System.currentTimeMillis()));
                log.setIp(Log.Gip);
                log.setOperator(Log.Goperator);
                logMapper.insert(log);
            }
        }
    }
    @Before(pointcut)
    public void doBefore(JoinPoint jp){
        String methodName=jp.getSignature().getName();
        String interfaceName=jp.getTarget().getClass().getInterfaces()[0].getSimpleName();
        Object[] args=jp.getArgs();
        Log log=new Log();
        if(args.length<1 || !methodName.startsWith("delete"))return;
        Object object=args[0];
        log.setDescribe("删除");
        log.setCode("delete");
        if(interfaceName.equals("UserMapper")) {
            log.setObject("用户"+userMapper.findUsernameById((Long) object));
        }else if(interfaceName.equals("CraneMapper")){
            log.setObject("用户"+craneMapper.findCarNumberById((Long) object));
        }
        log.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        logMapper.insert(log);
        System.out.println(interfaceName+"."+methodName);
    }
//
//    /**
//     * 最终通知：目标方法调用之后执行的通知（无论目标方法是否出现异常均执行）
//     * @param jp
//     */
//    @After(value=pointcut)
//    public void doAfter(JoinPoint jp){
//        System.out.println("After");
//    }
//
//    /**
//     * 在目标方法非正常执行完成, 抛出异常的时候会走此方法
//     * @param jp
//     * @param ex
//     */
//    @AfterThrowing(value=pointcut,throwing="ex")
//    public void doAfterThrowing(JoinPoint jp, Exception ex) {
//        System.out.println("AfterThrowing");
//    }
//
//    /**
//     * 环绕通知：目标方法调用前后执行的通知，可以在方法调用前后完成自定义的行为。
//     * @param pjp
//     * @return
//     * @throws Throwable
//     */
//    @Around(pointcut)
//    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
//
//        System.out.println("Around开始");
//        // 调用的方法名
//        String method = pjp.getSignature().getClass();
//        // 调用方法的参数
//        Object[] args = pjp.getArgs();
//
//        // 获取目标对象
//        Object target = pjp.getTarget();
//        // 执行完方法的返回值
//        // 调用proceed()方法，就会触发切入点方法执行
//        Object result=pjp.proceed();
//        System.out.println("输出,方法名：" + method + ";目标对象：" + target + ";返回值：" + result);
//        System.out.println("Around结束");
//        //result="admin";
//        return result;
//    }
}
