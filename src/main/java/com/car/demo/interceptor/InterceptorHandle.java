package com.car.demo.interceptor;

import com.car.demo.mapper.UserMapper;
import com.car.demo.model.Log;
import com.car.demo.service.PasswordCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Service
public class InterceptorHandle implements HandlerInterceptor {
    @Autowired
    private PasswordCodeService passwordCodeService;
    @Autowired
    private UserMapper userMapper;
    /**
     * 预处理回调方法，实现处理器的预处理
     * 返回值：true表示继续流程；false表示流程中断，不会继续调用其他的拦截器或处理器
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
           // System.out.println("开始拦截.........");
//        System.out.println("访问的Url中的服务器IP："+request.getServerName());
//        System.out.println("用户客户端的IP地址："+request.getRemoteAddr());
        Log.Gip=request.getRemoteAddr();
        Cookie[] cookies =request.getCookies();
        //System.out.println(request.getHeader("Cookie"));
        int flag=0;
        String password=null;
        String username=null;
        if(cookies!=null && cookies.length>0){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("loginPassword")){
                    password=passwordCodeService.decode(cookie.getValue());
                    flag++;
                }
                if(cookie.getName().equals("loginUsername")){
                    username=cookie.getValue();
                    Log.Goperator=Long.valueOf(username);
                    flag++;
                }
                if(flag>=2)break;
            }
            if (flag>=2 && userMapper.findPasswordByUsername(Long.valueOf(username)).equals(password)){
                if(request.getRequestURI().equals("/"))response.sendRedirect(request.getContextPath()+"/first");
                return true;//验证通过放行
            }
        }
        if(request.getRequestURI().equals("/"))return true;//没有cookie或cookie验证失败，又是首页则放行
        response.sendRedirect(request.getContextPath()+"/");
        System.out.println("----拦截----");
        return false;
    }

    /**
     * 后处理回调方法，实现处理器（controller）的后处理，但在渲染视图之前
     * 此时我们可以通过modelAndView对模型数据进行处理或对视图进行处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }
    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，
     * 如性能监控中我们可以在此记录结束时间并输出消耗时间，
     * 还可以进行一些资源清理，类似于try-catch-finally中的finally，
     * 但仅调用处理器执行链中
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }
}
