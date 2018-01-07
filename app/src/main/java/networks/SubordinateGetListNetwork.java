
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

public class SubordinateGetListNetwork{
    private Context context;
    public SubordinateGetListNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "acc": "m13809522353", 
    "user_token": "999999", 
    "page": 1, 
    "page_size": 10, 
    "search_lv": 1
}
*/
public static class Params {
@JSONField(name = "acc")
 public String acc;
@JSONField(name = "user_token")
 public String userToken;
@JSONField(name = "page")
 public int page;
@JSONField(name = "page_size")
 public int pageSize;
@JSONField(name = "search_lv")
 public int searchLv;
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
                "name": "张三", 
                "mobile": "13809533696", 
                "money": "0.2", 
                "auth": "已认证", 
                "face": "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1822899106,40471411&fm=27&gp=0.jpg", 
                "lv": "普通", 
                "auth_date": "2017-10-12"
            }
        ], 
        "rs_count": 3, 
        "lv_data": [
            {
                "lv": 1, 
                "title": "合伙人"
            }, 
            {
                "lv": 2, 
                "title": "代理"
            }, 
            {
                "lv": 3, 
                "title": "VIP"
            }, 
            {
                "lv": 4, 
                "title": "普通用户"
            }
        ]
    }
}
*/
public static class List {
@JSONField(name = "name")
 public String name;
@JSONField(name = "mobile")
 public String mobile;
@JSONField(name = "money")
 public String money;
@JSONField(name = "auth")
 public String auth;
@JSONField(name = "face")
 public String face;
@JSONField(name = "lv")
 public String lv;
@JSONField(name = "auth_date")
 public String authDate;
}
public static class LvData {
@JSONField(name = "lv")
 public int lv;
@JSONField(name = "title")
 public String title;
}
public static class Result {
@JSONField(name = "list")
 public java.util.List<List> list; 
@JSONField(name = "rs_count")
 public int rsCount;
@JSONField(name = "lv_data")
 public java.util.List<LvData> lvData; 
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/subordinate/get_list");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/subordinate/get_list",
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
map.put("page",params.page+""); 
map.put("page_size",params.pageSize+""); 
map.put("search_lv",params.searchLv+""); 

                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}