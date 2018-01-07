
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

public class BankCardAddNetwork{
    private Context context;
    public BankCardAddNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "acc": "m13809522353", 
    "province": "陕西1", 
    "card_num": 1231, 
    "city": "西安1", 
    "mobile": 13809522353, 
    "acc_name": "上官1", 
    "bank_name": "中国进出口银行1", 
    "card_type": 2, 
    "area": "莲湖区1", 
    "idcard_num": 350524199000000, 
    "user_token": "70629cbff020daea8186fba60ce128ba", 
    "branch_name": "品是行1"
}
*/
public static class Params {
@JSONField(name = "acc")
 public String acc;
@JSONField(name = "province")
 public String province;
@JSONField(name = "card_num")
 public int cardNum;
@JSONField(name = "city")
 public String city;
@JSONField(name = "mobile")
 public int mobile;
@JSONField(name = "acc_name")
 public String accName;
@JSONField(name = "bank_name")
 public String bankName;
@JSONField(name = "card_type")
 public int cardType;
@JSONField(name = "area")
 public String area;
@JSONField(name = "idcard_num")
 public int idcardNum;
@JSONField(name = "user_token")
 public String userToken;
@JSONField(name = "branch_name")
 public String branchName;
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/bank_card/add");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/bank_card/add",
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
map.put("province",params.province==null || params.province.isEmpty() ? "" : params.province); 
map.put("card_num",params.cardNum+""); 
map.put("city",params.city==null || params.city.isEmpty() ? "" : params.city); 
map.put("mobile",params.mobile+""); 
map.put("acc_name",params.accName==null || params.accName.isEmpty() ? "" : params.accName); 
map.put("bank_name",params.bankName==null || params.bankName.isEmpty() ? "" : params.bankName); 
map.put("card_type",params.cardType+""); 
map.put("area",params.area==null || params.area.isEmpty() ? "" : params.area); 
map.put("idcard_num",params.idcardNum+""); 
map.put("user_token",params.userToken==null || params.userToken.isEmpty() ? "" : params.userToken); 
map.put("branch_name",params.branchName==null || params.branchName.isEmpty() ? "" : params.branchName); 

                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}