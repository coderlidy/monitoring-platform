package com.car.demo.WeiboCrawler;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
//httpclient封装请求
//Jsoup的API文档 https://jsoup.org/apidocs/org/jsoup
public class MyHttpClient {
    public enum METHOD{
        GET,POST,get,post
    }

    // 编码格式。发送编码格式统一用UTF-8
    private static final String ENCODING = "UTF-8";

    // 设置连接超时时间，单位毫秒。
    private static final int CONNECT_TIMEOUT = 6000;

    // 请求获取数据的超时时间(即响应时间)，单位毫秒。
    private static final int SOCKET_TIMEOUT = 60000;
    public static String getByParams(String url,Map<String, String> headers,Map<String, String> params) {
        if (headers==null)headers=new HashMap<String, String>();
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.129 Safari/537.36");
        //headers.put("Cookie", "SUB=_2AkMp4Jycf8NxqwJRmfgSzWPgbolyzADEieKfvG1HJRMxHRl-yT9kqkMstRB6AmCyc1qs6jjJXXWiOdqb1DF7os18BiYL; SUBP=0033WrSXqPxfM72-Ws9jqgMF55529P9D9Whu7HYOecfET3LuNkN8RDAH; YF-Page-G0=753ea17f0c76317e0e3d9670fa168584|1589384107|1589384103; _s_tentry=passport.weibo.com; Apache=5552095980252.369.1589384109651; SINAGLOBAL=5552095980252.369.1589384109651; ULV=1589384109797:1:1:1:5552095980252.369.1589384109651:; login_sid_t=63d66287d4427ab6712c0a6a82f40e90; cross_origin_proto=SSL; Ugrow-G0=cf25a00b541269674d0feadd72dce35f; YF-V5-G0=2583080cfb7221db1341f7a137b6762e; wb_view_log=1366*7681; UOR=,,www.baidu.com");

        return request(url,headers,params,null, METHOD.get);
    };
    public static String postByParams(String url,Map<String, String> params)throws Exception {return request(url,null,params,null, METHOD.post);};
    public static String postByJson(String url,String json)throws Exception {return request(url,null,null,json, METHOD.post);};
    public static String postByJsonForJson(String url,String json)throws Exception {
        Map<String,String> headers=new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        return request(url,headers,null,json, METHOD.post);
    };

    /**
     * 下载图片，成功返回path，失败返回null
     * @param url
     * @param path
     * @return
     * @throws Exception
     */
    public static String getPic(String url,String path){
        //http客户端对象
        CloseableHttpClient httpClient=null;
        //获取回复对象
        CloseableHttpResponse httpResponse=null;
        try {
            //打开浏览器：创建HttpClient对象
            httpClient = HttpClients.createDefault();
            //创建访问方式
            HttpRequestBase httpMethod=null;
            URIBuilder uriBuilder = new URIBuilder(url);
            //输入地址：发起get请求
            httpMethod = new HttpGet(uriBuilder.build());
            //设置请求配置（本地的）
            /**
             * setConnectTimeout：设置连接超时时间，单位毫秒。
             * setConnectionRequestTimeout：设置从connect Manager(连接池)获取Connection
             * 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
             * setSocketTimeout：请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
             */
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
            httpMethod.setConfig(requestConfig);
            //设置请求头，反爬虫
            httpMethod.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.129 Safari/537.36");
            //执行请求并获得响应结果
            httpResponse = httpClient.execute(httpMethod);
            if (httpResponse != null && httpResponse.getStatusLine() != null) {
                //System.out.println(httpResponse.getStatusLine().getStatusCode());
                //如果响应体存在
                if (httpResponse.getEntity() != null) {
                    //获得response数据的回复体，将其转换为byte
                    byte[] bytes = EntityUtils.toByteArray(httpResponse.getEntity());
                    File file = new File(path);
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(bytes);
                    fos.close();
                    //System.out.println(bytes);
                    return path;
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            // 释放资源
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    /**
     *
     * @param url
     * @param headers
     * @param params
     * @param stringParams 一般是请求参数是JSON
     * @param requestMethod
     * @return
     * @throws Exception
     */
    public static String request(String url, Map<String, String> headers,Map<String, String> params,String stringParams,METHOD requestMethod){
        //http客户端对象
        CloseableHttpClient httpClient=null;
        //获取回复对象
        CloseableHttpResponse httpResponse=null;
        //回复字符串
        String content = null;
        try {
            //打开浏览器：创建HttpClient对象
            httpClient = HttpClients.createDefault();
            //创建访问方式
            HttpRequestBase httpMethod=null;
            // 创建访问的地址,为地址添加请求参数(parms操作)
            if(METHOD.GET.equals(requestMethod) || METHOD.get.equals(requestMethod)){
                URIBuilder uriBuilder = new URIBuilder(url);
                if (params != null) {
                    Set<Map.Entry<String, String>> entrySet = params.entrySet();
                    for (Map.Entry<String, String> entry : entrySet) {
                        uriBuilder.setParameter(entry.getKey(), entry.getValue());
                    }
                }
                //输入地址：发起get请求
                httpMethod = new HttpGet(uriBuilder.build());
            }else if(METHOD.POST.equals(requestMethod)|| METHOD.post.equals(requestMethod)){
                //输入地址：发起POST请求
                httpMethod =new HttpPost(url);
                // 封装请求体
                if (params != null) {
                    List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                    Set<Map.Entry<String, String>> entrySet = params.entrySet();
                    for (Map.Entry<String, String> entry : entrySet) {
                        nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                    }
                    // 设置到请求的http对象中
                    if(stringParams!=null)((HttpEntityEnclosingRequestBase)httpMethod).setEntity(new StringEntity(stringParams));
                    else ((HttpEntityEnclosingRequestBase)httpMethod).setEntity(new UrlEncodedFormEntity(nvps, ENCODING));
                }
            }
            // 封装请求头(headers操作)
            if (headers != null) {
                Set<Map.Entry<String, String>> entrySet = headers.entrySet();
                for (Map.Entry<String, String> entry : entrySet) {
                    // 设置到请求头到HttpRequestBase对象中
                    httpMethod.setHeader(entry.getKey(), entry.getValue());
                }
            }
            //设置请求配置（本地的）
            /**
             * setConnectTimeout：设置连接超时时间，单位毫秒。
             * setConnectionRequestTimeout：设置从connect Manager(连接池)获取Connection
             * 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
             * setSocketTimeout：请求获取数据的超时时间(即响应时间)，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
             */
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
            httpMethod.setConfig(requestConfig);
            //执行请求并获得响应结果
            httpResponse = httpClient.execute(httpMethod);
            if (httpResponse != null && httpResponse.getStatusLine() != null) {
                if(httpResponse.getStatusLine().getStatusCode()==418)throw new Exception("418:请求被反爬虫了！");
                //System.out.println(httpResponse.getStatusLine().getStatusCode());
                //如果响应体存在
                if (httpResponse.getEntity() != null) {
                    content = EntityUtils.toString(httpResponse.getEntity(), ENCODING);
                }
            }else
                throw new RuntimeException("服务器响应错误");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            // 释放资源
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }
}
