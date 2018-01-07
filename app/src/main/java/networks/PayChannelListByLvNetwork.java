
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

public class PayChannelListByLvNetwork{
    private Context context;
    public PayChannelListByLvNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "acc": "m13809522353", 
    "user_token": "57e25866e4702067c4e826387ded09ec"
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
        "pay_channel_info_list": [
            [
                {
                    "channel_date": "T0", 
                    "cate": 3, 
                    "rate": "0.50", 
                    "max_amt": "20000.00", 
                    "settle_amt": "2.00", 
                    "pay_channel_id": 1, 
                    "name": "银联大额A(全天秒到)"
                }
            ], 
            [
                {
                    "channel_date": "T0", 
                    "cate": 3, 
                    "rate": "0.40", 
                    "max_amt": "20000.00", 
                    "settle_amt": "2.00", 
                    "pay_channel_id": 1, 
                    "name": "银联大额A(全天秒到)"
                }
            ], 
            [
                {
                    "channel_date": "T0", 
                    "cate": 3, 
                    "rate": "0.38", 
                    "max_amt": "20000.00", 
                    "settle_amt": "2.00", 
                    "pay_channel_id": 1, 
                    "name": "银联大额A(全天秒到)"
                }
            ], 
            [
                {
                    "channel_date": "T0", 
                    "cate": 3, 
                    "rate": "0.30", 
                    "max_amt": "20000.00", 
                    "settle_amt": "2.00", 
                    "pay_channel_id": 1, 
                    "name": "银联大额A(全天秒到)"
                }
            ], 
            [
                {
                    "channel_date": "T0", 
                    "cate": 3, 
                    "rate": "0.28", 
                    "max_amt": "20000.00", 
                    "settle_amt": "2.00", 
                    "pay_channel_id": 1, 
                    "name": "银联大额A(全天秒到)"
                }
            ]
        ], 
        "lv_info_list": [
            {
                "lv": 1, 
                "lv_str": "普通用户", 
                "img": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/lv_imgs/em1.png"
            }, 
            {
                "lv": 2, 
                "lv_str": "VIP", 
                "img": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/lv_imgs/em2.png"
            }, 
            {
                "lv": 3, 
                "lv_str": "代理", 
                "img": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/lv_imgs/em3.png"
            }, 
            {
                "lv": 4, 
                "lv_str": "合伙人", 
                "img": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/lv_imgs/em4.png"
            }, 
            {
                "lv": 5, 
                "lv_str": "城市代理", 
                "img": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/lv_imgs/em5.png"
            }
        ]
    }, 
    "msg": "ok"
}
*/
public static class PayChannelInfoList {
@JSONField(name = "channel_date")
 public String channelDate;
@JSONField(name = "cate")
 public int cate;
@JSONField(name = "rate")
 public String rate;
@JSONField(name = "max_amt")
 public String maxAmt;
@JSONField(name = "settle_amt")
 public String settleAmt;
@JSONField(name = "pay_channel_id")
 public int payChannelId;
@JSONField(name = "name")
 public String name;
}
public static class LvInfoList {
@JSONField(name = "lv")
 public int lv;
@JSONField(name = "lv_str")
 public String lvStr;
@JSONField(name = "img")
 public String img;
}
public static class Result {
@JSONField(name = "pay_channel_info_list")
 public java.util.List<java.util.List<PayChannelInfoList>> payChannelInfoList; 
@JSONField(name = "lv_info_list")
 public java.util.List<LvInfoList> lvInfoList; 
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/pay_channel/list_by_lv");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/pay_channel/list_by_lv",
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