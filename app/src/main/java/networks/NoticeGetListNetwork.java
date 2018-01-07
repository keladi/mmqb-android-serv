
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

public class NoticeGetListNetwork{
    private Context context;
    public NoticeGetListNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "page": 1, 
    "page_size": 10
}
*/
public static class Params {
@JSONField(name = "page")
 public int page;
@JSONField(name = "page_size")
 public int pageSize;
}
// 响应
/* 

{
    "info": "操作成功", 
    "code": 0, 
    "err_msg": "", 
    "Raw": "", 
    "result": {
        "list": [
            {
                "title": "福州区域代理商火热招商中", 
                "url": "", 
                "ChangeFields": {}, 
                "content": "详情请联系客服", 
                "create_time": 11111, 
                "read_users": ",1,,13,,14,,15,,16,,18,,3,,19,,20,,21,,22,,23,,24,,25,", 
                "notice_id": 2
            }, 
            {
                "title": "请未完成实名认证的用户尽快完成实名认证", 
                "url": "", 
                "ChangeFields": {}, 
                "content": "请未完成实名认证的用户尽快完成实名认证,否则将影响您的使用", 
                "create_time": 1111, 
                "read_users": ",1,,15,,16,,18,,3,,19,,20,,13,,21,,22,,23,,24,,25,", 
                "notice_id": 1
            }
        ], 
        "page": 1, 
        "page_size": 10, 
        "rs_count": 2
    }, 
    "msg": "ok"
}
*/
public static class List {
@JSONField(name = "title")
 public String title;
@JSONField(name = "url")
 public String url;
@JSONField(name = "content")
 public String content;
@JSONField(name = "create_time")
 public int createTime;
@JSONField(name = "read_users")
 public String readUsers;
@JSONField(name = "notice_id")
 public int noticeId;
}
public static class Result {
@JSONField(name = "list")
 public java.util.List<List> list; 
@JSONField(name = "page")
 public int page;
@JSONField(name = "page_size")
 public int pageSize;
@JSONField(name = "rs_count")
 public int rsCount;
}
public static class Response {
@JSONField(name = "info")
 public String info;
@JSONField(name = "err_msg")
 public String errMsg;
@JSONField(name = "Raw")
 public String Raw;
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/notice/get_list");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/notice/get_list",
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
                map.put("page",params.page+""); 
map.put("page_size",params.pageSize+""); 

                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}