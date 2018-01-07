
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

public class UserCenterResetProfileNetwork{
    private Context context;
    public UserCenterResetProfileNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "acc": "m13809522353", 
    "province": "福建1", 
    "area": "鼓楼1", 
    "face": "https://o1wh05aeh.qnssl.com/image/view/app_icons/c81bf1465ff9891a2885600534faa8e1/120", 
    "sex": 2, 
    "user_token": "57e25866e4702067c4e826387ded09ec", 
    "sign": "我的签名1", 
    "nickname": "张三1", 
    "city": "福州1"
}
*/
public static class Params {
@JSONField(name = "acc")
 public String acc;
@JSONField(name = "province")
 public String province;
@JSONField(name = "area")
 public String area;
@JSONField(name = "face")
 public String face;
@JSONField(name = "sex")
 public int sex;
@JSONField(name = "user_token")
 public String userToken;
@JSONField(name = "sign")
 public String sign;
@JSONField(name = "nickname")
 public String nickname;
@JSONField(name = "city")
 public String city;
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/user_center/reset_profile");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/user_center/reset_profile",
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
map.put("province",params.province==null || params.province.isEmpty() ? "" : params.province); 
map.put("area",params.area==null || params.area.isEmpty() ? "" : params.area); 
map.put("face",params.face==null || params.face.isEmpty() ? "" : params.face); 
map.put("sex",params.sex+""); 
map.put("user_token",params.userToken==null || params.userToken.isEmpty() ? "" : params.userToken); 
map.put("sign",params.sign==null || params.sign.isEmpty() ? "" : params.sign); 
map.put("nickname",params.nickname==null || params.nickname.isEmpty() ? "" : params.nickname); 
map.put("city",params.city==null || params.city.isEmpty() ? "" : params.city); 

                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}