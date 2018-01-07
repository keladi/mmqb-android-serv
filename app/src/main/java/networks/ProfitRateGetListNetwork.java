
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

public class ProfitRateGetListNetwork{
    private Context context;
    public ProfitRateGetListNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "acc": "m13809522353", 
    "start_time": "2017-11-16", 
    "page_size": 10, 
    "user_token": "40836d9b329d70d7cd89ec38114ca75c", 
    "end_time": "2017-11-17", 
    "page": 1
}
*/
public static class Params {
@JSONField(name = "acc")
 public String acc;
@JSONField(name = "start_time")
 public String startTime;
@JSONField(name = "page_size")
 public int pageSize;
@JSONField(name = "user_token")
 public String userToken;
@JSONField(name = "end_time")
 public String endTime;
@JSONField(name = "page")
 public int page;
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
                "ChangeFields": {}, 
                "user_id": 13, 
                "des": "扣除一部分费率分成作为他的上级的分销分成", 
                "amount": -3, 
                "create_time": 1511870438, 
                "pay_log_id": 105, 
                "profit_rate_id": 22
            }
        ], 
        "page_size": 10, 
        "rs_count": 1, 
        "raw": null, 
        "total_money": 0, 
        "page": 1
    }, 
    "msg": "ok"
}
*/
public static class List {
@JSONField(name = "user_id")
 public int userId;
@JSONField(name = "des")
 public String des;
@JSONField(name = "amount")
 public int amount;
@JSONField(name = "create_time")
 public int createTime;
@JSONField(name = "pay_log_id")
 public int payLogId;
@JSONField(name = "profit_rate_id")
 public int profitRateId;
}
public static class Result {
@JSONField(name = "list")
 public java.util.List<List> list; 
@JSONField(name = "page_size")
 public int pageSize;
@JSONField(name = "rs_count")
 public int rsCount;
@JSONField(name = "total_money")
 public int totalMoney;
@JSONField(name = "page")
 public int page;
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/profit_rate/get_list");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/profit_rate/get_list",
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
map.put("start_time",params.startTime==null || params.startTime.isEmpty() ? "" : params.startTime); 
map.put("page_size",params.pageSize+""); 
map.put("user_token",params.userToken==null || params.userToken.isEmpty() ? "" : params.userToken); 
map.put("end_time",params.endTime==null || params.endTime.isEmpty() ? "" : params.endTime); 
map.put("page",params.page+""); 

                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}