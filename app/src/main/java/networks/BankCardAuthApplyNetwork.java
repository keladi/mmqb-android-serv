
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

public class BankCardAuthApplyNetwork{
    private Context context;
    public BankCardAuthApplyNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "acc": "m13809522355", 
    "bank_name": "中国工商银行1", 
    "code": "62220212143434341", 
    "province": "福建1", 
    "user_token": "4477a8b7c7a63d4bb79e518e93cf78dc", 
    "mobile": "13809522353", 
    "acc_name": "黄剑峰", 
    "bank_card_img_f": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/bank_imgs/中国银行.png?x-oss-process", 
    "branch_name": "台江支行1", 
    "bank_card_img_s": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/bank_imgs/中国银行.png?x-oss-process", 
    "city": "福州1"
}
*/
public static class Params {
@JSONField(name = "acc")
 public String acc;
@JSONField(name = "bank_name")
 public String bankName;
@JSONField(name = "code")
 public String code;
@JSONField(name = "province")
 public String province;
@JSONField(name = "user_token")
 public String userToken;
@JSONField(name = "mobile")
 public String mobile;
@JSONField(name = "acc_name")
 public String accName;
@JSONField(name = "bank_card_img_f")
 public String bankCardImgF;
@JSONField(name = "branch_name")
 public String branchName;
@JSONField(name = "bank_card_img_s")
 public String bankCardImgS;
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/bank_card_auth/apply");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/bank_card_auth/apply",
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
map.put("bank_name",params.bankName==null || params.bankName.isEmpty() ? "" : params.bankName); 
map.put("code",params.code==null || params.code.isEmpty() ? "" : params.code); 
map.put("province",params.province==null || params.province.isEmpty() ? "" : params.province); 
map.put("user_token",params.userToken==null || params.userToken.isEmpty() ? "" : params.userToken); 
map.put("mobile",params.mobile==null || params.mobile.isEmpty() ? "" : params.mobile); 
map.put("acc_name",params.accName==null || params.accName.isEmpty() ? "" : params.accName); 
map.put("bank_card_img_f",params.bankCardImgF==null || params.bankCardImgF.isEmpty() ? "" : params.bankCardImgF); 
map.put("branch_name",params.branchName==null || params.branchName.isEmpty() ? "" : params.branchName); 
map.put("bank_card_img_s",params.bankCardImgS==null || params.bankCardImgS.isEmpty() ? "" : params.bankCardImgS); 
map.put("city",params.city==null || params.city.isEmpty() ? "" : params.city); 

                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}