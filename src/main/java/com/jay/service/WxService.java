package com.jay.service;

import com.jay.entity.AccessToken;
import com.jay.entity.BaseMessage;
import com.jay.entity.TextMessage;
import com.jay.util.Util;
import com.thoughtworks.xstream.XStream;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李文杰 on 2018/11/23
 * 功能：验证签名
 * 1）将token、timestamp、nonce三个参数进行字典序排序
 * 2）将三个参数字符串拼接成一个字符串进行sha1加密
 * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
 */
public class WxService {

    private static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static final String APPID = "wxfe1abdf754a400a2";
    private static final String APPSECRET = "38b142706852abc013fb8f6f4093d524";
    private static  AccessToken at;

    private static final String TOKEN  = "123";

    //创建token并保存
    private static void getToken(){
        String url = GET_TOKEN_URL.replace("APPID",APPID).replace("APPSECRET",APPSECRET);
        String tokenStr = Util.get(url);
        JSONObject jsonObject = JSONObject.fromObject(tokenStr);
        String token = jsonObject.getString("access_token");
        String expiresIn = jsonObject.getString("expires_in");
        at = new AccessToken(token,expiresIn);
        //System.out.println(tokenStr);
    }

    public static String getAccessToken(){
        if(at==null || at.isExpired()){
            getToken();
        }
        return at.getAccessToken();
    }

    public static boolean check(String timestamp,String nonce,String signature){
        String[] strs = new String[]{TOKEN,timestamp,nonce};
        Arrays.sort(strs);
        String str = strs[0]+strs[1]+strs[2];
        String mysig = sha1(str);
        //System.out.println("mysig:"+mysig);
        //System.out.println(signature);
        return mysig.equalsIgnoreCase(signature);
    }
    //sha加密
    private static String sha1(String str) {
        //获取加密对象
        try {
            MessageDigest md = MessageDigest.getInstance ("sha1");
            //加密
            byte[] digest = md.digest(str.getBytes());
            char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            StringBuffer sb = new StringBuffer();
            //处理加密结果
            for(byte b : digest){
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b&15]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    //读取用户请求
    public static Map<String,String> parseResuest(InputStream is) {
        SAXReader reader = new SAXReader();
        Map<String,String> map = new HashMap();
        try {
            //读取输入流，获取文档对象
            Document document = reader.read(is);
            //根据文档对象获取根节点
            Element root = document.getRootElement();
            List<Element> elements = root.elements();
            for(Element e : elements){
                map.put(e.getName(),e.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    //打包需要回复的消息
    public static String getResponse(Map<String, String> requestMap) {
        BaseMessage msg = null;
        String msgType = requestMap.get("MsgType");
        switch (msgType){
            case "text":
                msg = dealTextMessage(requestMap);
                break;
            case "image":

                break;
            case "voice":

                break;
            case "video":

                break;
            case "music":

                break;
            case "news":

                break;
            default:
                break;
        }
        if(msg!=null){
            //消息对象转xml格式
            return beanToXml(msg);
        }
        return null;

    }

    private static String beanToXml(BaseMessage msg) {
        XStream stream = new XStream();
        stream.processAnnotations(TextMessage.class);
        String xml = stream.toXML(msg);
        return xml;
    }

    //处理文本消息
    private static BaseMessage dealTextMessage(Map<String, String> requestMap) {
        TextMessage textMessage = new TextMessage(requestMap,"处理成功!");
        return textMessage;
    }
}
