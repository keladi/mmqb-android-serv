
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

public class SessionMsgGetListNetwork{
    private Context context;
    public SessionMsgGetListNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "acc": "m13809522355", 
    "skip_session_msg_id": 22, 
    "user_account": 10000, 
    "page_size": 10, 
    "user_token": "4477a8b7c7a63d4bb79e518e93cf78dc", 
    "page": 1
}
*/
public static class Params {
@JSONField(name = "acc")
 public String acc;
@JSONField(name = "skip_session_msg_id")
 public int skipSessionMsgId;
@JSONField(name = "user_account")
 public int userAccount;
@JSONField(name = "page_size")
 public int pageSize;
@JSONField(name = "user_token")
 public String userToken;
@JSONField(name = "page")
 public int page;
}
// 响应
/* 

{
    "info": "操作成功", 
    "code": 0, 
    "err_msg": "", 
    "raw": null, 
    "result": {
        "list": [
            {
                "sender_face": "", 
                "session_msg_id": 27, 
                "msg_type": 1, 
                "sender_name": "13809522355", 
                "session_id": 4, 
                "content": "内容内容18", 
                "sender_acc": "m13809522355", 
                "create_time_str": "2017-11-12 04:58:29"
            }, 
            {
                "sender_face": "", 
                "session_msg_id": 26, 
                "msg_type": 1, 
                "sender_name": "13809522355", 
                "session_id": 4, 
                "content": "内容内容17", 
                "sender_acc": "m13809522355", 
                "create_time_str": "2017-11-12 04:56:40"
            }, 
            {
                "sender_face": "", 
                "session_msg_id": 25, 
                "msg_type": 2, 
                "sender_name": "13809522355", 
                "session_id": 4, 
                "content": "http://img3.imgtn.bdimg.com/it/u=2687972484,527558523&fm=27&gp=0.jpg", 
                "sender_acc": "m13809522355", 
                "create_time_str": "2017-11-12 04:55:00"
            }, 
            {
                "sender_face": "", 
                "session_msg_id": 24, 
                "msg_type": 1, 
                "sender_name": "13809522355", 
                "session_id": 4, 
                "content": "内容内容15", 
                "sender_acc": "m13809522355", 
                "create_time_str": "2017-11-12 04:53:20"
            }, 
            {
                "sender_face": "", 
                "session_msg_id": 23, 
                "msg_type": 1, 
                "sender_name": "13809522355", 
                "session_id": 4, 
                "content": "内容内容14", 
                "sender_acc": "m13809522355", 
                "create_time_str": "2017-11-12 04:51:40"
            }
        ], 
        "page": 1, 
        "page_size": 10, 
        "rs_count": 5
    }, 
    "msg": "ok"
}
*/
public static class List {
@JSONField(name = "sender_face")
 public String senderFace;
@JSONField(name = "session_msg_id")
 public int sessionMsgId;
@JSONField(name = "msg_type")
 public int msgType;
@JSONField(name = "sender_name")
 public String senderName;
@JSONField(name = "session_id")
 public int sessionId;
@JSONField(name = "content")
 public String content;
@JSONField(name = "sender_acc")
 public String senderAcc;
@JSONField(name = "create_time_str")
 public String createTimeStr;
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/session_msg/get_list");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/session_msg/get_list",
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
map.put("skip_session_msg_id",params.skipSessionMsgId+""); 
map.put("user_account",params.userAccount+""); 
map.put("page_size",params.pageSize+""); 
map.put("user_token",params.userToken==null || params.userToken.isEmpty() ? "" : params.userToken); 
map.put("page",params.page+""); 

                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}