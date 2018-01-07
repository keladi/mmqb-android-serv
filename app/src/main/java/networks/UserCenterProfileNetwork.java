
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

public class UserCenterProfileNetwork{
    private Context context;
    public UserCenterProfileNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "acc": "m13809522355", 
    "user_token": "4477a8b7c7a63d4bb79e518e93cf78dc"
}
*/
public static class Params {
@JSONField(name = "acc")
 public String acc;
@JSONField(name = "user_token")
 public String userToken;
}
// 响应
/* 

{
    "info": "操作成功", 
    "code": 0, 
    "raw": null, 
    "err_msg": "", 
    "Raw": null, 
    "result": {
        "province": "直辖市", 
        "point": 0, 
        "money": 0, 
        "sex": 1, 
        "user_token": "4477a8b7c7a63d4bb79e518e93cf78dc", 
        "qrcode": "http://web.serv.536pay.com/invite.html?nickname=%E8%8B%97%E8%8B%97&mobile=13809522355", 
        "user_qrcodes": [
            {
                "url": "http://web.serv.536pay.com/invite.html?nickname=%E8%8B%97%E8%8B%97&mobile=13809522355", 
                "title": "个人二维码", 
                "tag": "user", 
                "desc": "用于个人推广使用"
            }
        ], 
        "guid": "", 
        "city": "北京", 
        "idcard_img_f": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/id_card_imgs/m13809522355/idcard_img_f/b75b3bd16bebff117ad5510ecc412f26.jpg", 
        "idcard_img_b": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/id_card_imgs/m13809522355/idcard_img_b/c1275db2fd9a3f3f60a5ba547c6b3d8b.jpg", 
        "area": "东城区", 
        "is_real_name_auth": 1, 
        "bank_card_num": 0, 
        "lv": 4, 
        "has_pay_psw": 0, 
        "email": "", 
        "bank_card_auth_info": {
            "province": "福建", 
            "bank_name": "中国工商银行", 
            "ChangeFields": {}, 
            "user_id": 1, 
            "area": "", 
            "create_time": 1512110795, 
            "acc_name": "黄剑峰", 
            "status": 0, 
            "reason": "", 
            "bank_card_img_f": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/bank_card_imgs/m13809522355/bank_card_img_f/68e019528a972666730b98d4866d9eda.jpg", 
            "card_type": 0, 
            "branch_name": "台江支行", 
            "idcard_num": "", 
            "code": "6228450688003967872", 
            "city": "福州", 
            "approve_time": 1511754088, 
            "mobile": "13809522353", 
            "bank_card_img_s": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/bank_card_imgs/m13809522355/bank_card_img_s/3edcd3cb9c778efeb66809419bef5fb1.jpg", 
            "bank_card_auth_id": 8
        }, 
        "realname": "黄剑峰", 
        "msg_num": 0, 
        "login_count": 0, 
        "idcard_num": "350525198708116211", 
        "is_bank_card_auth": 1, 
        "user_group": "debug", 
        "sign": "钱钱钱钱快看看", 
        "nickname": "合伙人", 
        "account": "m13809522355", 
        "mobi": "13809522355", 
        "mobile": "13809522355", 
        "title": "", 
        "face": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/user_face_imgs/m13809522355/12e211af031538b7f6fc4c90a1ff2bd1.jpg", 
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/user_center/profile");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/user_center/profile",
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
map.put("user_token",params.userToken==null || params.userToken.isEmpty() ? "" : params.userToken); 

                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}