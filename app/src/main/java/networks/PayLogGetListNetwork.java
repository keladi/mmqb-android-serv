
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

public class PayLogGetListNetwork{
    private Context context;
    public PayLogGetListNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "acc": "m13809522355", 
    "user_token": "4477a8b7c7a63d4bb79e518e93cf78dc", 
    "sort_pay_log_id": "asc", 
    "page": 1, 
    "page_size": 10
}
*/
public static class Params {
@JSONField(name = "acc")
 public String acc;
@JSONField(name = "user_token")
 public String userToken;
@JSONField(name = "sort_pay_log_id")
 public String sortPayLogId;
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
    "raw": null, 
    "err_msg": "", 
    "Raw": null, 
    "result": {
        "list": [
            {
                "cate": "银联", 
                "channel_name": "银联大额A(全天秒到)", 
                "pay_amt": "10.00", 
                "pay_time": "1970-01-01 08:00:00", 
                "pay_log_id": 2, 
                "cate_str": "union_pay", 
                "cate_icon": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509991051848&di=a1b4cc8f54913e6c2a5a11c4c2f67e5f&imgtype=0&src=http%3A%2F%2Fandroid-artworks.25pp.com%2Ffs01%2F2014%2F12%2F25%2F8%2F0_8a989fabd7499bf615cbdff982485fcb.png"
            }, 
            {
                "cate": "银联", 
                "channel_name": "银联大额A(全天秒到)", 
                "pay_amt": "100.00", 
                "pay_time": "2017-11-27 11:15:44", 
                "pay_log_id": 42, 
                "cate_str": "union_pay", 
                "cate_icon": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509991051848&di=a1b4cc8f54913e6c2a5a11c4c2f67e5f&imgtype=0&src=http%3A%2F%2Fandroid-artworks.25pp.com%2Ffs01%2F2014%2F12%2F25%2F8%2F0_8a989fabd7499bf615cbdff982485fcb.png"
            }, 
            {
                "cate": "银联", 
                "channel_name": "银联大额A(全天秒到)", 
                "pay_amt": "10.00", 
                "pay_time": "2017-11-27 14:17:09", 
                "pay_log_id": 72, 
                "cate_str": "union_pay", 
                "cate_icon": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509991051848&di=a1b4cc8f54913e6c2a5a11c4c2f67e5f&imgtype=0&src=http%3A%2F%2Fandroid-artworks.25pp.com%2Ffs01%2F2014%2F12%2F25%2F8%2F0_8a989fabd7499bf615cbdff982485fcb.png"
            }, 
            {
                "cate": "银联", 
                "channel_name": "银联大额A(全天秒到)", 
                "pay_amt": "100.00", 
                "pay_time": "2017-11-27 16:32:43", 
                "pay_log_id": 85, 
                "cate_str": "union_pay", 
                "cate_icon": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509991051848&di=a1b4cc8f54913e6c2a5a11c4c2f67e5f&imgtype=0&src=http%3A%2F%2Fandroid-artworks.25pp.com%2Ffs01%2F2014%2F12%2F25%2F8%2F0_8a989fabd7499bf615cbdff982485fcb.png"
            }, 
            {
                "cate": "银联", 
                "channel_name": "银联大额A(全天秒到)", 
                "pay_amt": "100.00", 
                "pay_time": "2017-11-27 21:18:13", 
                "pay_log_id": 90, 
                "cate_str": "union_pay", 
                "cate_icon": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509991051848&di=a1b4cc8f54913e6c2a5a11c4c2f67e5f&imgtype=0&src=http%3A%2F%2Fandroid-artworks.25pp.com%2Ffs01%2F2014%2F12%2F25%2F8%2F0_8a989fabd7499bf615cbdff982485fcb.png"
            }, 
            {
                "cate": "银联", 
                "channel_name": "银联大额A(全天秒到)", 
                "pay_amt": "2058.00", 
                "pay_time": "2017-11-29 13:59:04", 
                "pay_log_id": 108, 
                "cate_str": "union_pay", 
                "cate_icon": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509991051848&di=a1b4cc8f54913e6c2a5a11c4c2f67e5f&imgtype=0&src=http%3A%2F%2Fandroid-artworks.25pp.com%2Ffs01%2F2014%2F12%2F25%2F8%2F0_8a989fabd7499bf615cbdff982485fcb.png"
            }, 
            {
                "cate": "银联", 
                "channel_name": "银联大额A(全天秒到)", 
                "pay_amt": "1235.00", 
                "pay_time": "2017-11-29 20:59:04", 
                "pay_log_id": 111, 
                "cate_str": "union_pay", 
                "cate_icon": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509991051848&di=a1b4cc8f54913e6c2a5a11c4c2f67e5f&imgtype=0&src=http%3A%2F%2Fandroid-artworks.25pp.com%2Ffs01%2F2014%2F12%2F25%2F8%2F0_8a989fabd7499bf615cbdff982485fcb.png"
            }, 
            {
                "cate": "银联", 
                "channel_name": "银联大额A(全天秒到)", 
                "pay_amt": "2308.00", 
                "pay_time": "2017-12-04 17:36:56", 
                "pay_log_id": 172, 
                "cate_str": "union_pay", 
                "cate_icon": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509991051848&di=a1b4cc8f54913e6c2a5a11c4c2f67e5f&imgtype=0&src=http%3A%2F%2Fandroid-artworks.25pp.com%2Ffs01%2F2014%2F12%2F25%2F8%2F0_8a989fabd7499bf615cbdff982485fcb.png"
            }
        ]
    }, 
    "msg": "ok"
}
*/
public static class List {
@JSONField(name = "cate")
 public String cate;
@JSONField(name = "channel_name")
 public String channelName;
@JSONField(name = "pay_amt")
 public String payAmt;
@JSONField(name = "pay_time")
 public String payTime;
@JSONField(name = "pay_log_id")
 public int payLogId;
@JSONField(name = "cate_str")
 public String cateStr;
@JSONField(name = "cate_icon")
 public String cateIcon;
}
public static class Result {
@JSONField(name = "list")
 public java.util.List<List> list; 
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/pay_log/get_list");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/pay_log/get_list",
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
map.put("sort_pay_log_id",params.sortPayLogId==null || params.sortPayLogId.isEmpty() ? "" : params.sortPayLogId); 
map.put("page",params.page+""); 
map.put("page_size",params.pageSize+""); 

                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}