package com.jay.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * Created by 李文杰 on 2018/11/23
 * 功能：
 */
@XStreamAlias("xml")
public class TextMessage extends BaseMessage {
    @XStreamAlias("Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TextMessage(Map<String,String> requestMap,String content) {
        super(requestMap);
        //设置文本消息类型text
        this.setMsgType("text");
        this.content = content;
    }

    public String toString(){
        return "TextMessage [content="+content+",getToUserName()="+getToUserName()+",getFromUserName()="+getFromUserName()+",getCreateTime()="+getCreateTime()+"]";
    }

}
