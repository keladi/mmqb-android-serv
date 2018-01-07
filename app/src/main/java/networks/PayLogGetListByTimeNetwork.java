
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

public class PayLogGetListByTimeNetwork{
    private Context context;
    public PayLogGetListByTimeNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "acc": "m13809522353", 
    "start_time": "2017-11-16", 
    "page_size": 10, 
    "user_token": "40836d9b329d70d7cd89ec38114ca75c", 
    "end_time": "2017-11-17", 
    "page": 1
}
*/
public static class Params {
@JSONField(name = "acc")
 public String acc;
@JSONField(name = "start_time")
 public String startTime;
@JSONField(name = "page_size")
 public int pageSize;
@JSONField(name = "user_token")
 public String userToken;
@JSONField(name = "end_time")
 public String endTime;
@JSONField(name = "page")
 public int page;
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
        "total": 5000, 
        "list": [
            {
                "cate": "银联", 
                "channel_name": "银联大额LD(8点-22点)", 
                "pay_amt": "10.00", 
                "pay_time": "2017-11-20 20:20:10", 
                "pay_log_id": 18, 
                "cate_str": "union_pay", 
                "cate_icon": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509991051848&di=a1b4cc8f54913e6c2a5a11c4c2f67e5f&imgtype=0&src=http%3A%2F%2Fandroid-artworks.25pp.com%2Ffs01%2F2014%2F12%2F25%2F8%2F0_8a989fabd7499bf615cbdff982485fcb.png"
            }, 
            {
                "cate": "银联", 
                "channel_name": "银联大额LD(8点-22点)", 
                "pay_amt": "10.00", 
                "pay_time": "2017-11-20 20:32:00", 
                "pay_log_id": 19, 
                "cate_str": "union_pay", 
                "cate_icon": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509991051848&di=a1b4cc8f54913e6c2a5a11c4c2f67e5f&imgtype=0&src=http%3A%2F%2Fandroid-artworks.25pp.com%2Ffs01%2F2014%2F12%2F25%2F8%2F0_8a989fabd7499bf615cbdff982485fcb.png"
            }, 
            {
                "cate": "银联", 
                "channel_name": "银联大额LD(8点-22点)", 
                "pay_amt": "10.00", 
                "pay_time": "2017-11-22 22:40:04", 
                "pay_log_id": 26, 
                "cate_str": "union_pay", 
                "cate_icon": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509991051848&di=a1b4cc8f54913e6c2a5a11c4c2f67e5f&imgtype=0&src=http%3A%2F%2Fandroid-artworks.25pp.com%2Ffs01%2F2014%2F12%2F25%2F8%2F0_8a989fabd7499bf615cbdff982485fcb.png"
            }, 
            {
                "cate": "银联", 
                "channel_name": "银联大额LD(8点-22点)", 
                "pay_amt": "10.00", 
                "pay_time": "2017-11-23 10:23:43", 
                "pay_log_id": 27, 
                "cate_str": "union_pay", 
                "cate_icon": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509991051848&di=a1b4cc8f54913e6c2a5a11c4c2f67e5f&imgtype=0&src=http%3A%2F%2Fandroid-artworks.25pp.com%2Ffs01%2F2014%2F12%2F25%2F8%2F0_8a989fabd7499bf615cbdff982485fcb.png"
            }, 
            {
                "cate": "银联", 
                "channel_name": "银联大额LD(8点-22点)", 
                "pay_amt": "10.00", 
                "pay_time": "2017-11-28 19:59:26", 
                "pay_log_id": 105, 
                "cate_str": "union_pay", 
                "cate_icon": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1509991051848&di=a1b4cc8f54913e6c2a5a11c4c2f67e5f&imgtype=0&src=http%3A%2F%2Fandroid-artworks.25pp.com%2Ffs01%2F2014%2F12%2F25%2F8%2F0_8a989fabd7499bf615cbdff982485fcb.png"
            }
        ], 
        "rs_count": 5
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
@JSONField(name = "total")
 public int total;
@JSONField(name = "list")
 public java.util.List<List> list; 
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/pay_log/get_list_by_time");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/pay_log/get_list_by_time",
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
map.put("start_time",params.startTime==null || params.startTime.isEmpty() ? "" : params.startTime); 
map.put("page_size",params.pageSize+""); 
map.put("user_token",params.userToken==null || params.userToken.isEmpty() ? "" : params.userToken); 
map.put("end_time",params.endTime==null || params.endTime.isEmpty() ? "" : params.endTime); 
map.put("page",params.page+""); 

                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}