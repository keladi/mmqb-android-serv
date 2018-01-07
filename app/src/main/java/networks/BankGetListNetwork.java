
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

public class BankGetListNetwork{
    private Context context;
    public BankGetListNetwork(Context context){
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
    "msg": "ok", 
    "code": 0, 
    "err_msg": "", 
    "result": {
        "list": [
            {
                "sort": 0, 
                "bank_name": "三井住友银行", 
                "bank_id": 3, 
                "img": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/bank_imgs/三井住友银行.png?x-oss-process=image/resize,m_pad,h_300,w_300,color_FFFFFF"
            }, 
            {
                "sort": 0, 
                "bank_name": "三菱东京UFJ银行", 
                "bank_id": 4, 
                "img": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/bank_imgs/三菱东京UFJ银行.png?x-oss-process=image/resize,m_pad,h_300,w_300,color_FFFFFF"
            }, 
            {
                "sort": 0, 
                "bank_name": "上海农商银行", 
                "bank_id": 5, 
                "img": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/bank_imgs/上海农商银行.png?x-oss-process=image/resize,m_pad,h_300,w_300,color_FFFFFF"
            }, 
            {
                "sort": 0, 
                "bank_name": "上海商业储蓄银行", 
                "bank_id": 6, 
                "img": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/bank_imgs/上海商业储蓄银行.png?x-oss-process=image/resize,m_pad,h_300,w_300,color_FFFFFF"
            }, 
            {
                "sort": 0, 
                "bank_name": "东亚银行", 
                "bank_id": 7, 
                "img": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/bank_imgs/东亚银行.png?x-oss-process=image/resize,m_pad,h_300,w_300,color_FFFFFF"
            }, 
            {
                "sort": 0, 
                "bank_name": "中信银行", 
                "bank_id": 8, 
                "img": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/bank_imgs/中信银行.png?x-oss-process=image/resize,m_pad,h_300,w_300,color_FFFFFF"
            }, 
            {
                "sort": 0, 
                "bank_name": "中国信托商业银行", 
                "bank_id": 9, 
                "img": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/bank_imgs/中国信托商业银行.png?x-oss-process=image/resize,m_pad,h_300,w_300,color_FFFFFF"
            }, 
            {
                "sort": 0, 
                "bank_name": "中国光大银行", 
                "bank_id": 10, 
                "img": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/bank_imgs/中国光大银行.png?x-oss-process=image/resize,m_pad,h_300,w_300,color_FFFFFF"
            }, 
            {
                "sort": 0, 
                "bank_name": "中国农业银行", 
                "bank_id": 11, 
                "img": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/bank_imgs/中国农业银行.png?x-oss-process=image/resize,m_pad,h_300,w_300,color_FFFFFF"
            }, 
            {
                "sort": 0, 
                "bank_name": "中国工商银行", 
                "bank_id": 12, 
                "img": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/bank_imgs/中国工商银行.png?x-oss-process=image/resize,m_pad,h_300,w_300,color_FFFFFF"
            }
        ], 
        "page": 1, 
        "page_size": 10, 
        "rs_count": 60
    }
}
*/
public static class List {
@JSONField(name = "sort")
 public int sort;
@JSONField(name = "bank_name")
 public String bankName;
@JSONField(name = "bank_id")
 public int bankId;
@JSONField(name = "img")
 public String img;
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/bank/get_list");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/bank/get_list",
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