package fmm.wx.controller;

import fmm.wx.service.CheckService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/wx")
public class CheckController {

    @Resource
    private CheckService checkService;

//    验证消息的确来自微信服务器
    @GetMapping()
    public void check(HttpServletRequest request, HttpServletResponse response){
        //signature,timestamp,nonce,echostr

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        if (checkService.check(timestamp, nonce, signature)){
            System.out.println("接入成功");
        }else {
            System.out.println("接入失败");
        }


    }

}
