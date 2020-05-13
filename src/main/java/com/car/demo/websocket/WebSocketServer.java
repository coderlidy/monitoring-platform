package com.car.demo.websocket;

import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
//import cn.hutool.log.Log;
//import cn.hutool.log.LogFactory;


/**
 *
 * @ServerEndpoint 这个注解有什么作用？
 *
 * 这个注解用于标识作用在类上，它的主要功能是把当前类标识成一个WebSocket的服务端
 * 注解的值用户客户端连接访问的URL地址
 *
 */

//@Slf4j
@Component
@ServerEndpoint("/websocket/{name}")
public class WebSocketServer {

    /**
     *  与某个客户端的连接对话，需要通过它来给客户端发送消息
     */
    private Session session;

    /**
     * 标识当前连接客户端的用户名
     */
    private String name;

    /**
     *  用于存所有的连接服务的客户端，这个对象存储是安全的
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketSet = new ConcurrentHashMap<>();


    @OnOpen
    public void OnOpen(Session session, @PathParam(value = "name") String name){
        this.session = session;
        this.name = name;
        // name是用来表示唯一客户端，如果需要指定发送，需要指定发送通过name来区分
        webSocketSet.put(name,this);
        System.out.println("[WebSocket] 连接成功，当前连接人数为：={}"+webSocketSet.size());
    }


    @OnClose
    public void OnClose(){
        webSocketSet.remove(this.name);
        System.out.println("[WebSocket] 退出成功，当前连接人数为：={}"+webSocketSet.size());
    }

    @OnMessage
    public void OnMessage(String message){
        System.out.println("[WebSocket] 收到消息：{0}"+message);
        //判断是否需要指定发送，具体规则自定义
        Entity entity= (Entity)JSON.parseObject(message,Entity.class);
        if(entity.isTarget()){
            while (true){
                AppointSending(entity.getToUserId(),entity.getContentText());
            }
        }else{
            GroupSending(message);
        }

    }
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("用户错误:"+this.name+",原因:"+error.getMessage());
        error.printStackTrace();
    }
    /**
     * 群发
     * @param message
     */
    public void GroupSending(String message){
        for (String name : webSocketSet.keySet()){
            try {
                //这的session和httpsession不同（毕竟协议都不一样）（https://docs.oracle.com/javaee/7/api/javax/websocket/Session.html）
                webSocketSet.get(name).session.getBasicRemote().sendText(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 指定发送
     * @param name
     * @param message
     */
    public static void AppointSending(String toUserId,String message){
        try {
            webSocketSet.get(toUserId).session.getBasicRemote().sendText(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
