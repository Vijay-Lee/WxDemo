package com.jay.manager;

import com.jay.service.WxService;
import com.jay.util.Util;
import org.junit.Test;

/**
 * Created by 李文杰 on 2018/11/26
 * 功能：
 */
public class TemplateMsgManager {


    //设置行业信息
    @Test
    public void setIndustry(){
        String at = WxService.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token="+at;
        String data = "{\n" +
                "          \"industry_id1\":\"1\",\n" +
                "          \"industry_id2\":\"2\"\n" +
                "       }";
        String result = Util.post(url,data);
        System.out.println(result);
    }

    //获取行业信息
    @Test
    public void getIndustry(){
        String at = WxService.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token="+at;
        String result = Util.get(url);
        System.out.println(result);
    }

    //发送模板消息
    @Test
    public void sendTemplateMsg(){
        String at = WxService.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+at;
        String data = "{\n" +
                "        \"touser\":\"oO4H80QFzHuGbj_QuD7w39ztMT90\",\n" +
                "        \"template_id\":\"9EJFslfwnr6IdRZ8z6WBh1WKb8aemG5kDPihQEQEhKE\",\n" +
                "        \"url\":\"http://jaylee.free.idcfengye.com/index.jsp\",\n" +
                "        \"data\":{\n" +
                "                \"first\": {\n" +
                "                \"value\":\"服务指标执行异常！\",\n" +
                "                \"color\":\"#173177\"\n" +
                "                },\n" +
                "                \"performance\":{\n" +
                "                    \"value\":\"预测评系统数据采集失败！\",\n" +
                "                    \"color\":\"#173177\"\n" +
                "                },\n" +
                "                \"time\":{\n" +
                "                    \"value\":\"20181127 15:30:29\",\n" +
                "                    \"color\":\"#173177\"\n" +
                "                },\n" +
                "                \"remark\":{\n" +
                "                    \"value\":\"请管理员相互转告，谢谢！\",\n" +
                "                    \"color\":\"#173177\"\n" +
                "                }\n" +
                "            }\n" +
                "    }";
        String result = Util.post(url,data);
        System.out.println(result);
    }
}


