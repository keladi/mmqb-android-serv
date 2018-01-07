
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

public class TuijianrenIndexNetwork{
    private Context context;
    public TuijianrenIndexNetwork(Context context){
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
    "msg": "ok", 
    "code": 0, 
    "err_msg": "", 
    "result": {
        "list": [
            {
                "reg_time": "2017-11-11 11-12-12", 
                "name": "张三", 
                "mobile": "13809533333", 
                "face": "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1822899106,40471411&fm=27&gp=0.jpg", 
                "lv_str": "城市代理", 
                "weixin": "13809533333"
            }, 
            {
                "reg_time": "2017-11-11 12-12-12", 
                "name": "李四", 
                "mobile": "13809533333", 
                "face": "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1290776758,1467283306&fm=27&gp=0.jpg", 
                "lv_str": "合伙人", 
                "weixin": "13809533333"
            }, 
            {
                "reg_time": "2017-11-11 13-12-12", 
                "name": "王五", 
                "mobile": "13809533333", 
                "face": "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1492150995,1038811454&fm=27&gp=0.jpg", 
                "lv_str": "代理", 
                "weixin": "13809533333"
            }
        ]
    }
}
*/
public static class List {
@JSONField(name = "reg_time")
 public String regTime;
@JSONField(name = "name")
 public String name;
@JSONField(name = "mobile")
 public String mobile;
@JSONField(name = "face")
 public String face;
@JSONField(name = "lv_str")
 public String lvStr;
@JSONField(name = "weixin")
 public String weixin;
}
public static class Result {
@JSONField(name = "list")
 public java.util.List<List> list; 
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/tuijianren/index");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/tuijianren/index",
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