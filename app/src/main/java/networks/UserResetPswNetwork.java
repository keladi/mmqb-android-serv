
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

public class UserResetPswNetwork{
    private Context context;
    public UserResetPswNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "acc": "m13809522353", 
    "old_psw": "123456", 
    "new_psw": "12345"
}
*/
public static class Params {
@JSONField(name = "acc")
 public String acc;
@JSONField(name = "old_psw")
 public String oldPsw;
@JSONField(name = "new_psw")
 public String newPsw;
}
// 响应
/* 

{
    "info": "操作成功", 
    "code": 0, 
    "err_msg": "", 
    "raw": null, 
    "result": null, 
    "msg": "ok"
}
*/
public static class Result {
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/user/reset_psw");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/user/reset_psw",
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
map.put("old_psw",params.oldPsw==null || params.oldPsw.isEmpty() ? "" : params.oldPsw); 
map.put("new_psw",params.newPsw==null || params.newPsw.isEmpty() ? "" : params.newPsw); 

                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}