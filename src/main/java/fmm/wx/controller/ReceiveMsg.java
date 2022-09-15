package fmm.wx.controller;

import fmm.wx.pojo.WxMessageImg;
import fmm.wx.service.DetailMsgService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("wx")
public class ReceiveMsg {

    @Resource
    DetailMsgService detailMsgService;
//
//    @PostMapping()
//    public void getMessage(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        ServletInputStream inputStream = req.getInputStream();
//        byte[] bytes = new byte[1024];
//        int len ;
//        StringBuilder sb = new StringBuilder();
//        while ((len = inputStream.read(bytes)) != -1) {
//            sb.append(new String(bytes, 0, len));
//        }
//        System.out.println(sb.toString());
//    }

    // 接收和处理消息
    @PostMapping()
    public void detailMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置请求和响应为中文
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 接收并处理请求过来中的信息
        Map<String, String> reqMap = detailMsgService.detailMsg(request.getInputStream());

        // 返回消息
        String responseXml = detailMsgService.detailResponseMsq(reqMap);

        PrintWriter writer = response.getWriter();
        writer.print(responseXml);
        writer.flush();
        writer.close();

    }
}
