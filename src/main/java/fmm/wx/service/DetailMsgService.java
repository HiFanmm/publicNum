package fmm.wx.service;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DetailMsgService {

    // 处理接收的消息
    public Map<String, String> detailMsg(InputStream inputStream) {
        Map<String, String> map =new HashMap<>();
        // dom4j的xml读取类
        SAXReader reader = new SAXReader();
        try {
            // 读取请求传过来的字节流
            Document read = reader.read(inputStream);
            // 读取根节点之后才能读取子节点
            Element root = read.getRootElement();
            // 读取所有的字节点
            List<Element> elements = root.elements();
            // 读取所有子节点的key和value
            for(Element e : elements){
                map.put(e.getName(), e.getStringValue());
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    // 处理返回的消息
    public String detailResponseMsq(Map<String, String> reqMap){
        String toUser = reqMap.get("FromUserName");
        String fromUser = reqMap.get("ToUserName");
        long createTime = System.currentTimeMillis()/1000;
        String msgType = reqMap.get("MsgType");
        String content = reqMap.get("Content");


        String res =
                "<xml>" +
                "  <ToUserName><![CDATA[" + toUser + "]]></ToUserName>" +
                "  <FromUserName><![CDATA[" + fromUser + "]]></FromUserName>" +
                "  <CreateTime>" + createTime + "</CreateTime>" +
                "  <MsgType><![CDATA[text]]></MsgType>" +
                "  <Content><![CDATA[你好]]></Content>" +
                "</xml>";

        return res;
    }


}
