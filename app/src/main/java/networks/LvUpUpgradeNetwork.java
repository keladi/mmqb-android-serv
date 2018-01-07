
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

public class LvUpUpgradeNetwork{
    private Context context;
    public LvUpUpgradeNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "acc": "m13809522353", 
    "user_token": "57e25866e4702067c4e826387ded09ec", 
    "pay_method": 1
}
*/
public static class Params {
@JSONField(name = "acc")
 public String acc;
@JSONField(name = "user_token")
 public String userToken;
@JSONField(name = "pay_method")
 public int payMethod;
}
// 响应
/* 

{
    "info": "操作成功", 
    "msg": "ok", 
    "code": 0, 
    "err_msg": "", 
    "result": {
        "alipay_params": "", 
        "pay_method": 1, 
        "weixin_params": {
            "prepay_id": "wx201712061346574e6f1d2bee0795048407", 
            "nonce_str": "2dda895776b6c2eddb6fe2e673674d0b", 
            "app_id": "wx6804fcf79ac97b51", 
            "sign": "EBB3C21C1902CDAF3424036D4AE3D714", 
            "time_stamp": "1512539217", 
            "package_value": "Sign=WXPay", 
            "partner_id": "1493819342"
        }
    }
}
*/
public static class WeixinParams {
@JSONField(name = "prepay_id")
 public String prepayId;
@JSONField(name = "nonce_str")
 public String nonceStr;
@JSONField(name = "app_id")
 public String appId;
@JSONField(name = "sign")
 public String sign;
@JSONField(name = "time_stamp")
 public String timeStamp;
@JSONField(name = "package_value")
 public String packageValue;
@JSONField(name = "partner_id")
 public String partnerId;
}
public static class Result {
@JSONField(name = "alipay_params")
 public String alipayParams;
@JSONField(name = "pay_method")
 public int payMethod;
@JSONField(name = "weixin_params")
 public WeixinParams weixinParams ; 
}
public static class Response {
@JSONField(name = "info")
 public String info;
@JSONField(name = "msg")
 public String msg;
@JSONField(name = "err_msg")
 public String errMsg;
@JSONField(name = "result")
 public Result result ; 
}
//请求

    public interface Callback{
        void onResult(Response model);
    }
    public void request(final Params params,final Callback callback){
        VolleyUtils.initialize(this.context);
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/lv_up/upgrade");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/lv_up/upgrade",
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
map.put("pay_method",params.payMethod+""); 

                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}