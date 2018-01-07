
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

public class TixianLogGetListNetwork{
    private Context context;
    public TixianLogGetListNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "acc": "m13809522355", 
    "user_token": "4477a8b7c7a63d4bb79e518e93cf78dc", 
    "search": "", 
    "page": 1, 
    "page_size": 10
}
*/
public static class Params {
@JSONField(name = "acc")
 public String acc;
@JSONField(name = "user_token")
 public String userToken;
@JSONField(name = "search")
 public String search;
@JSONField(name = "page")
 public int page;
@JSONField(name = "page_size")
 public int pageSize;
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
                "status": 0, 
                "bank_name": "中国农业发展银行", 
                "operator_id": 0, 
                "ChangeFields": {}, 
                "user_id": 1, 
                "money": 1, 
                "acc_name": "黄剑锋", 
                "remark": "", 
                "tixian_log_id": 1, 
                "branch_name": "泉州汇金支行", 
                "card_num": "6228450688003969872", 
                "create_time": 1510588220, 
                "approve_time": 0, 
                "operator": ""
            }
        ], 
        "page": "1", 
        "page_size": "10", 
        "rs_count": 1
    }
}
*/
public static class List {
@JSONField(name = "status")
 public int status;
@JSONField(name = "bank_name")
 public String bankName;
@JSONField(name = "operator_id")
 public int operatorId;
@JSONField(name = "user_id")
 public int userId;
@JSONField(name = "money")
 public int money;
@JSONField(name = "acc_name")
 public String accName;
@JSONField(name = "remark")
 public String remark;
@JSONField(name = "tixian_log_id")
 public int tixianLogId;
@JSONField(name = "branch_name")
 public String branchName;
@JSONField(name = "card_num")
 public String cardNum;
@JSONField(name = "create_time")
 public int createTime;
@JSONField(name = "approve_time")
 public int approveTime;
@JSONField(name = "operator")
 public String operator1;
}
public static class Result {
@JSONField(name = "list")
 public java.util.List<List> list; 
@JSONField(name = "page")
 public String page;
@JSONField(name = "page_size")
 public String pageSize;
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/tixian_log/get_list");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/tixian_log/get_list",
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
map.put("search",params.search==null || params.search.isEmpty() ? "" : params.search); 
map.put("page",params.page+""); 
map.put("page_size",params.pageSize+""); 

                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}