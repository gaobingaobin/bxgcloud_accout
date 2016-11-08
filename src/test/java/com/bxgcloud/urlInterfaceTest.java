package com.bxgcloud;

import com.google.gson.Gson;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.Test;

import java.util.Map;


/**
 * Created by gaobin on 2016/11/8.
 */
public class urlInterfaceTest {
    @Test
    public void getUrlData() throws Exception {
        String sendUrl = "http://www.kuaidi100.com/query";
        HttpClient httpclient = null;
        PostMethod post =null;
        String type = "zhongtong";
        String postid = "417151308174";
        try{
            httpclient = new HttpClient();
            post = new PostMethod(sendUrl);
            //设置编码方式
            post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
            //添加参数
            post.addParameter("type",type);
            post.addParameter("postid",postid);
            httpclient.executeMethod(post);
            String info = new String(post.getResponseBody(),"UTF-8");
            System.out.print(info);

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            //关闭连接，释放资源
            post.releaseConnection();
            ((SimpleHttpConnectionManager)httpclient.getHttpConnectionManager()).shutdown();
        }



    }
    /**
     * 将传入的json字符串解析为Map集合
     * @param jsonStr
     * @return
     */
    public static Map<?, ?> jsonToMap(String jsonStr) {
        Map<?, ?> ObjectMap = null;
        Gson gson = new Gson();
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Map<?,?>>() {}.getType();
        ObjectMap = gson.fromJson(jsonStr, type);
        return ObjectMap;
    }
}
