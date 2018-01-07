
package network;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;
import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UserLoginNetwork{
    private Context context;
    public UserLoginNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "acc": "13809522355", 
    "psw": "123456"
}
*/
public static class Params {
@JSONField(name = "acc")
 public String acc;
@JSONField(name = "psw")
 public String psw;
}
// 响应
/* 

{
    "info": "操作成功!", 
    "code": "0", 
    "raw": null, 
    "Raw": null, 
    "result": {
        "province": "直辖市", 
        "point": 0, 
        "money": 0, 
        "sex": 1, 
        "user_token": "4477a8b7c7a63d4bb79e518e93cf78dc", 
        "qrcode": "http://web.536pay.com/invite.html?nickname=%E8%8B%97%E8%8B%97&mobile=13809522355", 
        "user_qrcodes": [
            {
                "url": "http://web.serv.536pay.com/invite.html?nickname=%E8%8B%97%E8%8B%97&mobile=13809522355", 
                "title": "个人二维码", 
                "tag": "user", 
                "desc": "用于个人推广使用"
            }
        ], 
        "guid": "6d194bbc3271bdb61d0864bc6a443118", 
        "city": "北京", 
        "idcard_img_f": "", 
        "idcard_img_b": "", 
        "area": "东城区", 
        "is_real_name_auth": 0, 
        "bank_card_num": 0, 
        "lv": 0, 
        "has_pay_psw": 0, 
        "email": "", 
        "bank_card_auth_info": {
            "province": "", 
            "bank_name": "", 
            "ChangeFields": {}, 
            "user_id": 0, 
            "area": "", 
            "create_time": 0, 
            "acc_name": "", 
            "status": 0, 
            "reason": "", 
            "bank_card_img_f": "", 
            "card_type": 0, 
            "branch_name": "", 
            "idcard_num": "", 
            "code": "", 
            "city": "", 
            "approve_time": 0, 
            "mobile": "", 
            "bank_card_img_s": "", 
            "bank_card_auth_id": 0
        }, 
        "realname": "", 
        "msg_num": 0, 
        "login_count": 0, 
        "idcard_num": "", 
        "is_bank_card_auth": 0, 
        "user_group": "debug", 
        "sign": "钱钱钱钱快看看", 
        "nickname": "普通用户", 
        "account": "m13809522355", 
        "mobi": "13809522355", 
        "mobile": "13809522355", 
        "title": "", 
        "face": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/user_face_imgs/m13809522355/255c0631b2eb9598f2be8efb559132c0.jpg", 
        "status": 0, 
        "position": "直辖市北京"
    }, 
    "msg": "ok"
}
*/
public static class UserQrcodes {
@JSONField(name = "url")
 public String url;
@JSONField(name = "title")
 public String title;
@JSONField(name = "tag")
 public String tag;
@JSONField(name = "desc")
 public String desc;
}
public static class BankCardAuthInfo {
@JSONField(name = "province")
 public String province;
@JSONField(name = "bank_name")
 public String bankName;
@JSONField(name = "user_id")
 public int userId;
@JSONField(name = "area")
 public String area;
@JSONField(name = "create_time")
 public int createTime;
@JSONField(name = "acc_name")
 public String accName;
@JSONField(name = "status")
 public int status;
@JSONField(name = "reason")
 public String reason;
@JSONField(name = "bank_card_img_f")
 public String bankCardImgF;
@JSONField(name = "card_type")
 public int cardType;
@JSONField(name = "branch_name")
 public String branchName;
@JSONField(name = "idcard_num")
 public String idcardNum;
@JSONField(name = "code")
 public String code;
@JSONField(name = "city")
 public String city;
@JSONField(name = "approve_time")
 public int approveTime;
@JSONField(name = "mobile")
 public String mobile;
@JSONField(name = "bank_card_img_s")
 public String bankCardImgS;
@JSONField(name = "bank_card_auth_id")
 public int bankCardAuthId;
}
public static class Result {
@JSONField(name = "province")
 public String province;
@JSONField(name = "point")
 public int point;
@JSONField(name = "money")
 public int money;
@JSONField(name = "sex")
 public int sex;
@JSONField(name = "user_token")
 public String userToken;
@JSONField(name = "qrcode")
 public String qrcode;
@JSONField(name = "user_qrcodes")
 public java.util.List<UserQrcodes> userQrcodes; 
@JSONField(name = "guid")
 public String guid;
@JSONField(name = "city")
 public String city;
@JSONField(name = "idcard_img_f")
 public String idcardImgF;
@JSONField(name = "idcard_img_b")
 public String idcardImgB;
@JSONField(name = "area")
 public String area;
@JSONField(name = "is_real_name_auth")
 public int isRealNameAuth;
@JSONField(name = "bank_card_num")
 public int bankCardNum;
@JSONField(name = "lv")
 public int lv;
@JSONField(name = "has_pay_psw")
 public int hasPayPsw;
@JSONField(name = "email")
 public String email;
@JSONField(name = "bank_card_auth_info")
 public BankCardAuthInfo bankCardAuthInfo ; 
@JSONField(name = "realname")
 public String realname;
@JSONField(name = "msg_num")
 public int msgNum;
@JSONField(name = "login_count")
 public int loginCount;
@JSONField(name = "idcard_num")
 public String idcardNum;
@JSONField(name = "is_bank_card_auth")
 public int isBankCardAuth;
@JSONField(name = "user_group")
 public String userGroup;
@JSONField(name = "sign")
 public String sign;
@JSONField(name = "nickname")
 public String nickname;
@JSONField(name = "account")
 public String account;
@JSONField(name = "mobi")
 public String mobi;
@JSONField(name = "mobile")
 public String mobile;
@JSONField(name = "title")
 public String title;
@JSONField(name = "face")
 public String face;
@JSONField(name = "status")
 public int status;
@JSONField(name = "position")
 public String position;
}
public static class Response {
@JSONField(name = "info")
 public String info;
@JSONField(name = "err_msg")
 public String errMsg;
@JSONField(name = "result")
 public Result result ; 
@JSONField(name = "msg")
 public String msg;
}
//请求

    public interface Callback{
        void onResult(Response model);
    }
    public void request(final Params params,final Callback callback){
        VolleyUtils.initialize(this.context);
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/user/login");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/user/login",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String data)
                    {
                        Log.d("NETWORK_RESPONSE", data);
                        Response response = JSON.parseObject(data, Response.class);
                        callback.onResult(response);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Response response = new Response();
                        String errMsg="";
                        if(error!=null && error.getMessage()!=null && !error.getMessage().isEmpty()){
                            errMsg=error.getMessage();
                            error.printStackTrace();
                        }
                        Log.d("NETWORK_RESPONSE_ERR", errMsg);
                        response.errMsg=errMsg;
                        response.msg="api_err";
                        response.info="系统接口异常";
                        callback.onResult(response);
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<String, String>();
                map.put("acc",params.acc==null || params.acc.isEmpty() ? "" : params.acc); 
map.put("psw",params.psw==null || params.psw.isEmpty() ? "" : params.psw); 

                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}