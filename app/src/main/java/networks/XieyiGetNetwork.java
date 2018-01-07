
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

public class XieyiGetNetwork{
    private Context context;
    public XieyiGetNetwork(Context context){
        this.context = context;
    }
    
// 参数
/* 

{}
*/
public static class Params {
}
// 响应
/* 

{
    "info": "操作成功", 
    "msg": "ok", 
    "code": 0, 
    "err_msg": "", 
    "result": {
        "content": "一、总则\n1.1 保宝网的所有权和运营权归xxxx科技有限公司所有。 \n1.2 用户在注册之前，应当仔细阅读本协议，并同意遵守本协议后方可成为注册用户。一旦注册成功，则用户与保宝网之间自动形成协议关系，用户应当受本协议的约束。用户在使用特殊的服务或产品时，应当同意接受相关协议后方能使用。 \n1.3 本协议则可由保宝网随时更新，用户应当及时关注并同意本站不承担通知义务。本站的通知、公告、声明或其它类似内容是本协议的一部分。\n二、服务内容\n2.1 保宝网的具体内容由本站根据实际情况提供。 \n2.2 本站仅提供相关的网络服务，除此之通过身份认证的用户即成为正式用户，可以获得本站规定用户所应享有的一切权限；未经认证仅享有本站规定的部分会员权限。保宝网有权对会员的权限设计进行变更。 \n3.2 用户只能按照注册要求使用真实姓名，及身份证号注册。用户有义务保证密码和帐号的安全，用户利用该密码和帐号所进行，本站不承担任何责任。如用户发现帐号遭到未授权的使用或发生其他任何安全问题，应立即修改帐号密码并妥善保管，如有必要，请通知本站。因黑客行为或用户的保管疏忽导致帐号非法使用，本站不承担任何责任。\n四、使用规则\n4.1 遵守中华人民共和国相关法律法规，包括但不限于《中华息服务管理规定》、《互联网著作权行政保护办法》和《信息网络传播权保护条例》等有关计算机互联网规定和知识产权的法律和法规、实施办法。 \n4.2 用户对其自行发表、上传或传送的内容负全部责任，所有用户不得在本站任何页面发布、转载、传送含有下列内容之一的信息，否则本站有权自、民族歧视，破坏民族团结的； \n(5)破坏国家宗教政策，宣扬邪教和封建迷信的； \n(6)散布谣言，扰乱社会秩序，破坏社会稳定的；\n(7)散布淫秽、色情、赌博、暴力、恐怖或者教唆犯罪的； \n(8)侮辱或者诽谤他人，侵害他人合法权益的； \n(9)煽动非法集会、结社、游行、示威、聚众扰乱用户承诺对其发表或者上传于本站的所有信息(即属于《中华人民共和国著作权法》规定的作品，包括但不限于文字、图片、音乐、电影、表演和录音录像制品和电脑程序等)均享有完整的知识产权，或者已经得到相关权利人的合法授权；如用户违反本条规定造成本站被第三人索赔的，用户应全额补，除非用户提交书面证据材料排除侵权的可能性，本站将不会自动恢复上述删除的信息；\n(1)不得为任何非法目的而使用网络服务系统； \n(2)遵守所有与网络服务有关的网络协议、规定和程序； (3)不得利用本站进行任何可能对互联网的正常运转造成不利影响的行为； \n(4)不得利用本站进行任权要求用户改正或直接采取一切必要的措施(包括但不限于删除用户张贴的内容、暂停或终止用户使用网络服务的权利)以减轻用户不当行为而造成的影响。\n五、隐私保护\n5.1 本站不对外公开或向第三方提供单个用户的注册资料及用户在使用网络服务时存储在本站的非公开内容，但下列情况除外隐私的责任，则本站有权将用户的注册资料等提供给该第三方。\n5.3 在不透露单个用户隐私资料的前提下，本站有权对整个用户数据库进行分析并对用户数据库进行商业上的利用。\n六、版权声明\n6.1 本站的文字、图片、音频、视频等版权均归永兴元科技有限公司享有或与作者共同享有，未经”及署上作者姓名，按法律规定需要支付稿酬的，应当通知本站及作者及支付稿酬，并独立承担一切法律责任。\n6.4 本站享有所有作品用于其它用途的优先权，包括但不限于网站、电子杂志、平面出版等，但在使用前会通知作者，并按同行业的标准支付稿酬。\n6.5 本站所有内容仅代表作者自己的律责任。 \n6.6 恶意转载本站内容的，本站保留将其诉诸法律的权利。\n七、责任声明\n7.1 用户明确同意其使用本站网络服务所存在的风险及一切后果将完全由用户本人承担，保宝网对此不承担任何责任。 \n7.2 本站无法保证网络服务一定能满足用户的要求，也不保证网络服务的及时性、安全本站不承担任何责任，但将尽力减少因此而给用户造成的损失和影响。\n7.5 对于站向用户提供的下列产品或者服务的质量缺陷本身及其引发的任何损失，本站无需承担任何责任：\n(1)本站向用户免费提供的各项网络服务； \n(2)本站向用户赠送的任何产品或者服务。\n7.6 本站有权于任何时间暂无论其通知与否，本站对用户和任何第三人均无需承担任何责任。\n八、附则\n8.1 本协议的订立、执行和解释及争议的解决均应适用中华人民共和国法律。 \n8.2 如本协议中的任何条款无论因何种原因完全或部分无效或不具有执行力，本协议的其余条款仍应有效并且有约束力。\n", 
        "title": "秒秒钱包用户协议", 
        "desc": "本协议解释权及修订权归秒秒钱包所有"
    }
}
*/
public static class Result {
@JSONField(name = "content")
 public String content;
@JSONField(name = "title")
 public String title;
@JSONField(name = "desc")
 public String desc;
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
        Log.d("NETWORK_URL", NetworkConfig.Server+"/rest/xieyi/get");
        Log.d("NETWORK_PARAMS", JSON.toJSONString(params));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkConfig.Server+"/rest/xieyi/get",
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
                
                return map;
            }
        };
        VolleyUtils.getRequestQueue().add(stringRequest);
    }
    
    
}