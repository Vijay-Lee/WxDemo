import com.jay.entity.TextMessage;
import com.jay.service.WxService;
import com.thoughtworks.xstream.XStream;
import org.junit.Test;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 李文杰 on 2018/11/26
 * 功能：
 */
public class TestWx {

    @Test
    public void testToken(){
       System.out.println(WxService.getAccessToken());
        System.out.println(WxService.getAccessToken());
    }
    @Test
    public void testMsg(){
        Map<String,String> requestMap = new HashMap<>();
        requestMap.put("ToUserName","to");
        requestMap.put("FromUserName","from");
        requestMap.put("MsgType","type");
        TextMessage tm = new TextMessage(requestMap,"我了个去");
        XStream stream = new XStream();
        stream.processAnnotations(TextMessage.class);
        String xml = stream.toXML(tm);
        System.out.println(tm);
        System.out.println(xml);
    }

}
