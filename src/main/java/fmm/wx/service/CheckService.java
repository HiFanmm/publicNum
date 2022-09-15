package fmm.wx.service;

import cn.hutool.crypto.SecureUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 开发者通过检验 signature 对请求进行校验（下面有校验方式）。若确认此次 GET 请求来自微信服务器
 * 请原样返回 echostr 参数内容，则接入生效，成为开发者成功，否则接入失败。加密/校验流程如下：
 *
 * 1）将token、timestamp、nonce三个参数进行字典序排序
 * 2）将三个参数字符串拼接成一个字符串进行sha1加密
 * 3）开发者获得加密后的字符串可与 signature 对比，标识该请求来源于微信
 */
@Service
public class CheckService {

    private static final String TOKEN = "fmmzs";

    public boolean check(String timestamp, String nonce, String signature){
        // * 1）将token、timestamp、nonce三个参数进行字典序排序
        String[] params = {timestamp, nonce, TOKEN};
        Arrays.sort(params);
        // * 2）将三个参数字符串拼接成一个字符串进行sha1加密
        String str = params[0]+params[1]+params[2];
        //* 3）开发者获得加密后的字符串可与 signature 对比，标识该请求来源于微信
        return signature.equals(SecureUtil.sha1(str));
    }

}
