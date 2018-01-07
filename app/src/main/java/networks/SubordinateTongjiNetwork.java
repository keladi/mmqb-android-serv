
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

public class SubordinateTongjiNetwork{
    private Context context;
    public SubordinateTongjiNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "acc": "m13809522355", 
    "user_token": "999999"
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
    "msg": "ok", 
    "code": 0, 
    "err_msg": "", 
    "result": {
        "list": [
            {
                "title": "合伙人", 
                "direct": 5, 
                "lv": 1, 
                "direct_contribute": "111.23", 
                "indirect": 10, 
                "indirect_contribute": "121.32"
            }, 
            {
                "title": "代理", 
                "direct": 1, 
                "lv": 2, 
                "direct_contribute": "56.23", 
                "indirect": 3, 
                "indirect_contribute": "78.32"
            }, 
            {
                "title": "VIP", 
                "direct": 1, 
                "lv": 3, 
                "direct_contribute": "56.23", 
                "indirect": 3, 
                "indirect_contribute": "78.32"
            }, 
            {
                "title": "普通用户", 
                "direct": 1, 
                "lv": 4, 
                "direct_contribute": "16.23", 
                "indirect": 3, 
                "indirect_contribute": "118.32"
            }
        ], 
        "rs_count": 4
    }
}
*/
public static class List {
@JSONField(name = "title")
 public String title;
@JSONField(name = "direct")
 public int direct;
@JSONField(name = "lv")
 public int lv;
@JSONField(name = "direct_contribute")
 public String directContribute;
@JSONField(name = "indirect")
 public int indirect;
@JSONField(name = "indirect_contribute")
 public String indirectContribute;
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/subordinate/tongji");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/subordinate/tongji",
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