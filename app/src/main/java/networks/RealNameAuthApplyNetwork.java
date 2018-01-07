
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

public class RealNameAuthApplyNetwork{
    private Context context;
    public RealNameAuthApplyNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "acc": "m13809522355", 
    "idcard_img_f": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/tmp_not_del/id_f.jpg", 
    "name": "戴一秒", 
    "idcard_img_b": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/tmp_not_del/id_s.jpg", 
    "user_token": "4477a8b7c7a63d4bb79e518e93cf78dc", 
    "idcard_num": "350523199555256328"
}
*/
public static class Params {
@JSONField(name = "acc")
 public String acc;
@JSONField(name = "idcard_img_f")
 public String idcardImgF;
@JSONField(name = "name")
 public String name;
@JSONField(name = "idcard_img_b")
 public String idcardImgB;
@JSONField(name = "user_token")
 public String userToken;
@JSONField(name = "idcard_num")
 public String idcardNum;
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/real_name_auth/apply");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/real_name_auth/apply",
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
map.put("idcard_img_f",params.idcardImgF==null || params.idcardImgF.isEmpty() ? "" : params.idcardImgF); 
map.put("name",params.name==null || params.name.isEmpty() ? "" : params.name); 
map.put("idcard_img_b",params.idcardImgB==null || params.idcardImgB.isEmpty() ? "" : params.idcardImgB); 
map.put("user_token",params.userToken==null || params.userToken.isEmpty() ? "" : params.userToken); 
map.put("idcard_num",params.idcardNum==null || params.idcardNum.isEmpty() ? "" : params.idcardNum); 

                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}