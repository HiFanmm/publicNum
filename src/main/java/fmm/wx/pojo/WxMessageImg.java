package fmm.wx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMessageImg {

    private String ToUserName;

    private String FromUserName;

    private long CreateTime;

    private String MsgType;

    private String Event;

    private String PicUrl;

    private String MediaId;

    private long MsgId;

    private String Content;


}