package com.car.demo.WeiboCrawler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
//微博爬虫多线程版 爬取地址：https://m.weibo.cn/
public class WeiboThreads implements Runnable{
    private String uid;
    private String uname;
    private String fans;
    private static int picNum=0;
    private static int page;
    private static String original_pic;
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 10, 200, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
    public WeiboThreads(String uid, String uname, String fans){
        this.uid=uid;
        this.uname=uname;
        this.fans=fans;
    }
    //每个线程的数据
    private int my_page;
    private int my_picNum;
    private String my_original_pic;
    public WeiboThreads(int my_page,int my_picNum,String my_original_pic) {
        this.my_page = my_page;
        this.my_picNum = my_picNum;
        this.my_original_pic = my_original_pic;
    }
    public void run() {
        System.out.println("正在执行图片编号："+this.my_picNum);
        //if(this.my_original_pic!=null)MyHttpClient.getPic(this.my_original_pic,"image\\原图"+this.my_picNum+this.my_original_pic.substring(this.my_original_pic.length()-4));
        System.out.println("图片编号："+this.my_picNum+"  页数："+this.my_page+"  地址："+this.my_original_pic);
    }

    public static void main(String[] args){
        getUserAllPic("1749127163");
        //getUserList("雷军");
    }
    public static void getUserAllPic(String uid){
        String containerid="230413"+ uid;
        try{
            for(page=1;;page++){
                Thread.sleep(2000);
                getUserPic("https://m.weibo.cn/api/container/getIndex?containerid="+containerid+"&page="+page);
                System.out.println("---------------------------------------------------------");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }finally {
            executor.shutdown();
            System.out.println("图片数："+picNum);
            System.out.println("页数："+page);
        }
    }
    private static boolean getUserPic(String url) throws Exception {
        String content=MyHttpClient.getByParams(url,null,null);
        //使用火狐浏览器打开url，返回的json直观
        //通过JSONObject来一步步解析json结构
        //原创微博图片(已验证是原图)json->data->cards[]->mblog->pics[]->url
        //获得https://wx1.sinaimg.cn/orj360/93b2d7e8ly1gesa4ftug0j20hs0npdig.jpg
        //替换orj360为large
        //转载微博图片(只有第一张是原图)json->data->cards[]->mblog->retweeted_status->original_pic
        try {
            JSONObject jsonObject = JSONObject.parseObject(content);
            JSONObject data=jsonObject.getJSONObject("data");
            JSONArray cards=data.getJSONArray("cards");
            for(int i=0;i<cards.size();i++){
                JSONObject mblog=cards.getJSONObject(i).getJSONObject("mblog");
                JSONArray pics=mblog.getJSONArray("pics");
                if(pics==null)continue;
                for(int j=0;j<pics.size();j++){
                    original_pic=pics.getJSONObject(j).getString("url").replaceFirst("orj360","large");
                    executor.execute(new WeiboThreads(page,++picNum,original_pic));
                    System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                            executor.getQueue().size()+"，已执行完的任务数目："+executor.getCompletedTaskCount());
                }
            }
            return true;
        }catch (Exception ex){
            throw new Exception("错误地址：" + url,ex);
        }

    }
    public static List<WeiboThreads> getUserList(String uname){
        String url= null;
        try {
            //中文需要转码
            url = "https://m.weibo.cn/api/container/getIndex?containerid=100103type"+URLEncoder.encode("=3&q="+uname+"&t=0","UTF-8")+"&page_type=searchall";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String content=MyHttpClient.getByParams(url,null,null);
        //获得用户列表 json->data->cards[1]->card_group[]->user->id/screen_name/followers_count
        JSONObject jsonObject = JSONObject.parseObject(content);
        JSONObject data=jsonObject.getJSONObject("data");
        JSONArray cards=data.getJSONArray("cards");
        if(cards.size()>1){
            JSONArray card_group=cards.getJSONObject(1).getJSONArray("card_group");
            List<WeiboThreads> weiboList=new ArrayList<WeiboThreads>();
            for(int i=0;i<card_group.size();i++){
                JSONObject user=card_group.getJSONObject(i).getJSONObject("user");
                WeiboThreads weibo=new WeiboThreads(user.getString("id"),user.getString("screen_name"),user.getString("followers_count"));
                weiboList.add(weibo);
                System.out.println("用户："+weibo.uname+"|粉丝数："+weibo.fans+"|id："+weibo.uid);
            }
            return weiboList;
        }
        return null;
    }
}
