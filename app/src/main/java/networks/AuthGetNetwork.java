
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

public class AuthGetNetwork{
    private Context context;
    public AuthGetNetwork(Context context){
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
        "bank_card_auth_info": {
            "province": "福建1", 
            "bank_name": "中国工商银行1", 
            "ChangeFields": {}, 
            "user_id": 1, 
            "area": "", 
            "create_time": 1511697962, 
            "acc_name": "黄剑峰", 
            "status": 0, 
            "reason": "", 
            "bank_card_img_f": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/bank_card_imgs/m13809522355/bank_card_img_f/98030836359e745bae4dbb358ef8b3d9.jpg", 
            "card_type": 0, 
            "branch_name": "台江支行1", 
            "idcard_num": "", 
            "code": "62220212143434341", 
            "city": "福州1", 
            "approve_time": 1511697962, 
            "mobile": "13809522353", 
            "bank_card_img_s": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/bank_card_imgs/m13809522355/bank_card_img_s/951bc231fd30340a6b5429d90d8b15d6.jpg", 
            "bank_card_auth_id": 8
        }, 
        "real_name_auth_info": {
            "status": 1, 
            "idcard_img_f": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/id_card_imgs/m13809522355/idcard_img_f/18857b5ff5b50b483294efc2f0bb8ec1.jpg", 
            "user_id": 1, 
            "name": "黄剑峰", 
            "idcard_img_b": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/id_card_imgs/m13809522355/idcard_img_b/1e7109569efc4f4052609eb01aec1e65.jpg", 
            "mobile": "", 
            "ChangeFields": {}, 
            "real_name_auth_id": 7, 
            "reason": "", 
            "idcard_num": "350523199555256328", 
            "create_time": 1511697058, 
            "approve_time": 1511697958
        }
    }, 
    "msg": "ok"
}
*/
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
public static class RealNameAuthInfo {
@JSONField(name = "status")
 public int status;
@JSONField(name = "idcard_img_f")
 public String idcardImgF;
@JSONField(name = "user_id")
 public int userId;
@JSONField(name = "name")
 public String name;
@JSONField(name = "idcard_img_b")
 public String idcardImgB;
@JSONField(name = "mobile")
 public String mobile;
@JSONField(name = "real_name_auth_id")
 public int realNameAuthId;
@JSONField(name = "reason")
 public String reason;
@JSONField(name = "idcard_num")
 public String idcardNum;
@JSONField(name = "create_time")
 public int createTime;
@JSONField(name = "approve_time")
 public int approveTime;
}
public static class Result {
@JSONField(name = "bank_card_auth_info")
 public BankCardAuthInfo bankCardAuthInfo ; 
@JSONField(name = "real_name_auth_info")
 public RealNameAuthInfo realNameAuthInfo ; 
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/auth/get");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/auth/get",
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