
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

public class ShareIndexNetwork{
    private Context context;
    public ShareIndexNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{
    "acc": "m13809522353", 
    "user_token": "57e25866e4702067c4e826387ded09ec"
}
*/
public static class Params {
@JSONField(name = "acc")
 public String acc;
@JSONField(name = "user_token")
 public String userToken;
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
                "url": "http://web.serv.536pay.com/Promotion/qrcode.html?imgurl=http%3A%2F%2Fqrcode.serv.536pay.com%2Findex.php%3Fsize%3D150%26txt%3Dhttp%253A%252F%252Fweb.serv.536pay.com%252Finvite.html%253Fnickname%253D%2525E4%2525B8%25258A%2525E5%2525AE%252598%2526mobile%253D13809522353", 
                "title": "分享推广二维码", 
                "icon": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/imgs/qrcode_icon.png", 
                "desc": "拒绝审美疲劳,每次分享都是新的视觉体验"
            }, 
            {
                "url": "http://web.serv.536pay.com/Promotion/img_text.html", 
                "title": "朋友圈文案案库", 
                "icon": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/imgs/article.png", 
                "desc": "朋友圈文案案库,每次分享都是新的视觉体验"
            }, 
            {
                "url": "http://web.serv.536pay.com/Promotion/reg_pro.html", 
                "title": "分享注册邀请链接", 
                "icon": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/imgs/em1.png", 
                "desc": "分享注册邀请链接,每次分享都是新的视觉体验"
            }, 
            {
                "url": "http://web.serv.536pay.com/Promotion/news_pro.html", 
                "title": "图文推广", 
                "icon": "http://mmzf-main.oss-cn-hangzhou.aliyuncs.com/imgs/qrcode_icon.png", 
                "desc": "图文推广,每次分享都是新的视觉体验"
            }
        ]
    }
}
*/
public static class List {
@JSONField(name = "url")
 public String url;
@JSONField(name = "title")
 public String title;
@JSONField(name = "icon")
 public String icon;
@JSONField(name = "desc")
 public String desc;
}
public static class Result {
@JSONField(name = "list")
 public java.util.List<List> list; 
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/share/index");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/share/index",
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

                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}