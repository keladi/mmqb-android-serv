
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

public class BankCardDetNetwork{
    private Context context;
    public BankCardDetNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "acc": "m13809522353", 
    "user_token": "70629cbff020daea8186fba60ce128ba", 
    "card_id": 1
}
*/
public static class Params {
@JSONField(name = "acc")
 public String acc;
@JSONField(name = "user_token")
 public String userToken;
@JSONField(name = "card_id")
 public int cardId;
}
// 响应
/* 

{
    "info": "操作成功", 
    "msg": "ok", 
    "code": 0, 
    "err_msg": "", 
    "result": {
        "province": "直辖市", 
        "bank_name": "中国农业发展银行", 
        "card_num": "6228450688003969872", 
        "area": "东城区", 
        "mobile": "15959858866", 
        "acc_name": "黄剑锋", 
        "card_id": 14, 
        "card_type": 2, 
        "branch_name": "泉州汇金支行", 
        "idcard_num": "350525198708116211", 
        "card_type_txt": "储蓄卡", 
        "city": "北京", 
        "icon": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510063041&di=f1ecea6f7fb43017f99f313a3a619d5e&imgtype=jpg&er=1&src=http%3A%2F%2Fico.ooopic.com%2Fajax%2Ficonpng%2F%3Fid%3D134653.png"
    }
}
*/
public static class Result {
@JSONField(name = "province")
 public String province;
@JSONField(name = "bank_name")
 public String bankName;
@JSONField(name = "card_num")
 public String cardNum;
@JSONField(name = "area")
 public String area;
@JSONField(name = "mobile")
 public String mobile;
@JSONField(name = "acc_name")
 public String accName;
@JSONField(name = "card_id")
 public int cardId;
@JSONField(name = "card_type")
 public int cardType;
@JSONField(name = "branch_name")
 public String branchName;
@JSONField(name = "idcard_num")
 public String idcardNum;
@JSONField(name = "card_type_txt")
 public String cardTypeTxt;
@JSONField(name = "city")
 public String city;
@JSONField(name = "icon")
 public String icon;
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/bank_card/det");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/bank_card/det",
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
map.put("card_id",params.cardId+""); 

                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}