
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

public class PayChannelListsNetwork{
    private Context context;
    public PayChannelListsNetwork(Context context){
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
        "list": [
            {
                "channel_date": "T0", 
                "name": "来逗阵 T+0", 
                "rate": "0.20", 
                "max_amt": "50000.00", 
                "settle_amt": "2", 
                "pay_channel_id": 1, 
                "cate": 1
            }, 
            {
                "channel_date": "T0", 
                "name": "微信 T+0", 
                "rate": "0.20", 
                "max_amt": "30000.00", 
                "settle_amt": "2", 
                "pay_channel_id": 2, 
                "cate": 1
            }, 
            {
                "channel_date": "T1", 
                "name": "支付宝 T+1", 
                "rate": "0.30", 
                "max_amt": "40000.00", 
                "settle_amt": "2", 
                "pay_channel_id": 3, 
                "cate": 1
            }, 
            {
                "channel_date": "T7", 
                "name": "百度钱包 T+7", 
                "rate": "0.20", 
                "max_amt": "100000.00", 
                "settle_amt": "2", 
                "pay_channel_id": 4, 
                "cate": 1
            }
        ], 
        "rs_count": 4
    }, 
    "msg": "ok"
}
*/
public static class List {
@JSONField(name = "channel_date")
 public String channelDate;
@JSONField(name = "name")
 public String name;
@JSONField(name = "rate")
 public String rate;
@JSONField(name = "max_amt")
 public String maxAmt;
@JSONField(name = "settle_amt")
 public String settleAmt;
@JSONField(name = "pay_channel_id")
 public int payChannelId;
@JSONField(name = "cate")
 public int cate;
}
public static class Result {
@JSONField(name = "list")
 public java.util.List<List> list; 
@JSONField(name = "rs_count")
 public int rsCount;
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/pay_channel/lists");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/pay_channel/lists",
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