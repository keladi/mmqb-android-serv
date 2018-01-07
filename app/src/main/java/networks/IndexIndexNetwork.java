
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

public class IndexIndexNetwork{
    private Context context;
    public IndexIndexNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{}
*/
public static class Params {
}
// 响应
/* 

{
    "info": "操作成功", 
    "msg": "ok", 
    "code": 0, 
    "err_msg": "", 
    "result": {
        "lunbo": [
            {
                "url": "http://www.qq.com", 
                "text": "", 
                "img_url": "http://boois-main.oss-cn-shanghai.aliyuncs.com/xqj/app_tmp_img/banner1.imageset/banner1.jpg"
            }, 
            {
                "url": "http://www.qq.com", 
                "text": "", 
                "img_url": "http://boois-main.oss-cn-shanghai.aliyuncs.com/xqj/app_tmp_img/banner2.imageset/banner2.jpg"
            }, 
            {
                "url": "http://www.qq.com", 
                "text": "", 
                "img_url": "http://boois-main.oss-cn-shanghai.aliyuncs.com/xqj/app_tmp_img/banner3.imageset/banner3.jpg"
            }
        ]
    }
}
*/
public static class Lunbo {
@JSONField(name = "url")
 public String url;
@JSONField(name = "text")
 public String text;
@JSONField(name = "img_url")
 public String imgUrl;
}
public static class Result {
@JSONField(name = "lunbo")
 public java.util.List<Lunbo> lunbo; 
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/index/index");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/index/index",
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
                
                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}