
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

public class PayPayNetwork{
    private Context context;
    public PayPayNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "acc": "m13809522353", 
    "txn_amt": 10.1, 
    "acc_no": "6226880018868257", 
    "user_token": "57e25866e4702067c4e826387ded09ec", 
    "phone_no": "15959858866", 
    "pay_channel": 1
}
*/
public static class Params {
@JSONField(name = "acc")
 public String acc;
@JSONField(name = "txn_amt")
 public float txnAmt;
@JSONField(name = "acc_no")
 public String accNo;
@JSONField(name = "user_token")
 public String userToken;
@JSONField(name = "phone_no")
 public String phoneNo;
@JSONField(name = "pay_channel")
 public int payChannel;
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
        "url": "", 
        "html": "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/></head><body><form id = \"pay_form\" action=\"http://www.laidouzhen.cn/paycloud-openapi/api/plat/route/rest/getdoform/88/0b98b7ac468c4eeda9d8e17aa3ed5c9f\" method=\"post\"><input type=\"hidden\" name=\"data\" id=\"data\" value=\"jp99KqA6uOal1OnRICGnGK/o8LFcbi504MZDsh5XUf30nBvVXM+EF0u0iqPjsJouhU/6e0Jb4w88mMKkO6f6C18oeJyt0eYjlq3yuaTz0PhAtF8MIHz59+pNICZtisJeOlnm0b7OlGZCnOIJKPD/KfrHrzrjpmpNJdFkLKRkNbl7dO7uNIjrQt7d1+hRZJSrkOTSkr621IiqLuCzBnK6ojnAVriMO7j9xs7o2FOfnOLsj4e5i++MgMr3LjP+lMKwvjQdy8j5SX5BpJWw5zZdm09nP4jTxFx4xpTw+d174N7mL4BkYd3tgmSgxkGDz6ZYsz5VfFjvfJBrqdVSTY6beglHJzO7IlleXQssyNJ0GQ4nM29gzhR13vwWdIb+MVoOW2gZVRPjkHgsddgz7wH1l1n+yg/LH59ASsMAt2DoPdv44i2HQohhUmoiV9T4Oc28X6G+3J0JZU7NhTZOBIgc0IZE2zxAXqyllW+jkxqXHs0/HEWkv4GEMTEUngNacqjQhRbrvyJcra/0fmAG/ELFhzP+hyzsuc/ACn3SDNdv7AuXaIpCb4s2FbF4SJwwscQn32aWpkkJSKGqSdUKo8YdplH9fJ0I3BGwSzESPRmMygqlQL+LtfzOBf92rnbtAj043cehadJMxSbzXeAs+vY4B4hOAXDkI1SgA3j17PNqf65u1+0aU1Tqgt5uXwmfJzv16ASd2Srvk6caUH4zUZEtbwufkFcXDvOoWAVPOODV6BLwwWSZ8ahwgR2Vv/9lEFIzcqIvBg3Nvg9vDQipta3rR1a79LVZGJeGvcTz0K8wxcH+fwr9QAijk7nhR8lDV0zLKT3X2PXoe4FSqF1vvTow7w==\"/></form></body><script type=\"text/javascript\">document.all.pay_form.submit();</script></html>", 
        "user_fee": "1.00"
    }, 
    "msg": "ok"
}
*/
public static class Result {
@JSONField(name = "url")
 public String url;
@JSONField(name = "html")
 public String html;
@JSONField(name = "user_fee")
 public String userFee;
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/pay/pay");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/pay/pay",
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
map.put("txn_amt",params.txnAmt+""); 
map.put("acc_no",params.accNo==null || params.accNo.isEmpty() ? "" : params.accNo); 
map.put("user_token",params.userToken==null || params.userToken.isEmpty() ? "" : params.userToken); 
map.put("phone_no",params.phoneNo==null || params.phoneNo.isEmpty() ? "" : params.phoneNo); 
map.put("pay_channel",params.payChannel+""); 

                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}