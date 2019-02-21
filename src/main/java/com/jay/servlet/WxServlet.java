package com.jay.servlet;

import com.jay.service.WxService;

import javax.jws.WebService;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by 李文杰 on 2018/11/23
 * 功能：
 */
public class WxServlet extends javax.servlet.http.HttpServlet {

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //处理消息和事件推送
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        Map<String,String> requestMap = WxService.parseResuest(request.getInputStream());
        /*String ToUserName = requestMap.get("ToUserName");
        String FromUserName = requestMap.get("FromUserName");
        String time = String.valueOf(System.currentTimeMillis()/1000);*/
        //准备回复的数据包
        String respXml = WxService.getResponse(requestMap);
        //String respXml = "<xml><ToUserName><![CDATA["+FromUserName+"]]></ToUserName><FromUserName><![CDATA["+ToUserName+"]]></FromUserName> <CreateTime>"+time+"</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[你是SB么，这只是个测试号！]]></Content></xml>";
        PrintWriter out = response.getWriter();
        //System.out.println(respXml);
        out.print(respXml);
        out.flush();
        out.close();
        //System.out.println(requestMap);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
         /*signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        timestamp	时间戳
        nonce	随机数
        echostr	随机字符串
        System.out.println(signature);
        System.out.println(timestamp);
        System.out.println(nonce);
        System.out.println(echostr);
        */
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if(WxService.check(timestamp,nonce,signature)){
            PrintWriter out = response.getWriter();
            out.print(echostr);
            out.flush();
            out.close();
            System.out.println("接入成功");
        }else{
            System.out.println("接入失败");
        };
    }

    private static String chat(String msg){
        return null;
    }
}
