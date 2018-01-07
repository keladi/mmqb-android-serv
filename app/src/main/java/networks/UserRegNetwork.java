
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

public class UserRegNetwork{
    private Context context;
    public UserRegNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "province": "", 
    "src": "core", 
    "city": "", 
    "mobile": "13809522357", 
    "area": "", 
    "sms_code": "138095", 
    "refer": "13809522353", 
    "psw": "123456", 
    "sms_id": "6b3b044821d82d0f212a198c37fc448d"
}
*/
public static class Params {
@JSONField(name = "province")
 public String province;
@JSONField(name = "src")
 public String src;
@JSONField(name = "city")
 public String city;
@JSONField(name = "mobile")
 public String mobile;
@JSONField(name = "area")
 public String area;
@JSONField(name = "sms_code")
 public String smsCode;
@JSONField(name = "refer")
 public String refer;
@JSONField(name = "psw")
 public String psw;
@JSONField(name = "sms_id")
 public String smsId;
}
// 响应
/* 

{
    "info": "操作成功", 
    "code": 0, 
    "raw": null, 
    "err_msg": "", 
    "Raw": null, 
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/user/reg");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/user/reg",
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
                map.put("province",params.province==null || params.province.isEmpty() ? "" : params.province); 
map.put("src",params.src==null || params.src.isEmpty() ? "" : params.src); 
map.put("city",params.city==null || params.city.isEmpty() ? "" : params.city); 
map.put("mobile",params.mobile==null || params.mobile.isEmpty() ? "" : params.mobile); 
map.put("area",params.area==null || params.area.isEmpty() ? "" : params.area); 
map.put("sms_code",params.smsCode==null || params.smsCode.isEmpty() ? "" : params.smsCode); 
map.put("refer",params.refer==null || params.refer.isEmpty() ? "" : params.refer); 
map.put("psw",params.psw==null || params.psw.isEmpty() ? "" : params.psw); 
map.put("sms_id",params.smsId==null || params.smsId.isEmpty() ? "" : params.smsId); 

                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}