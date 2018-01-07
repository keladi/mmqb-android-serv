package network.mmqb.network;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by boois on 2017/11/4.
 */

public class VolleyUtils {

    private static RequestQueue mRequestQueue ;

    public static void initialize(Context context){
        if (mRequestQueue == null){
            synchronized (VolleyUtils.class){
                if (mRequestQueue == null){
                    mRequestQueue = Volley.newRequestQueue(context) ;
                }
            }
        }
        mRequestQueue.start();
    }

    public static RequestQueue getRequestQueue(){
        if (mRequestQueue == null)
            throw new RuntimeException("请先初始化mRequestQueue") ;
        return mRequestQueue ;
    }

    public static class FormImage {

        // 参数的名称
        private String mName;

        // 文件名
        private String mFileName;

        // 文件的 mime，需要根据文档查询
        private String mMime;

        // 需要上传的图片资源，因为这里测试为了方便起见，直接把 bitmap 传进来，真正在项目中一般不会这般做，
        // 而是把图片的路径传过来，在这里对图片进行二进制转换
        private Bitmap mBitmap = null;

        public FormImage(Bitmap bitmap) {
            this.mBitmap = bitmap;
        }

        public String getName() {
            return "file";
        }

        public String getFileName() {
            return "add.png";
        }

        // 对图片进行二进制转换
        public byte[] getValue() {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            return bos.toByteArray();
        }

        // 因为我知道是 png 文件，所以直接根据文档查的
        public String getMime() {
            return "image/png";
        }
    }

    public static class PostUploadRequest extends Request<String> {

        private Map<String, String> sendHeader = new HashMap<String, String>();

        // 正确数据的时候回掉用
        private Response.Listener<String> mListener;

        // 请求 数据通过参数的形式传入
        private String content;
        private FormImage mImage;

        // 数据分隔线
        private String BOUNDARY = "----CloudLifeUpLoadImage";
        private String MULTIPART_FORM_DATA = "multipart/form-data";

        public PostUploadRequest(String url, String text, FormImage Item, Response.Listener<String> listener,Response.ErrorListener errorListener) {

            super(Method.POST, url, errorListener);
            this.mListener = listener;
            setShouldCache(false);
            mImage = Item;
            content = text;

            // 设置请求的响应事件，因为文件上传需要较长的时间，所以在这里加大了，设为5秒
            setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        }

        /*
         * 这里开始解析数据
         * @param response
         *            Response from the network
         * @return
         * */
        @Override
        protected Response<String> parseNetworkResponse(NetworkResponse response) {
            try {
                // 防止中文乱码
                @SuppressWarnings("deprecation")
                String jsonString = new String(response.data, "utf-8");
                return Response.success(jsonString, HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            }
        }

        /*
         * 回调正确的数据
         * @param response
         *            The parsed response returned by
         * */
        @Override
        protected void deliverResponse(String response) {
            mListener.onResponse(response);
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            return sendHeader;
        }

        public void setSendCookie(String cookie) {
            sendHeader.put("Cookie", cookie);
        }

        @Override
        public byte[] getBody() throws AuthFailureError {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            StringBuffer sb = new StringBuffer();

            if (content == null) {
                // 先输入正常的文本字段
                Map<String,String> params = PostUploadRequest.this.getParams();
                if (params!=null && params.size()!=0){
                    for (String key : params.keySet()){
                        sb.append("--" + BOUNDARY + "\r\n");
                        sb.append("Content-Disposition: form-data; name=\""+key+"\"");
                        // "\r\n" 空白的一行
                        sb.append("\r\n");
                        sb.append("\r\n");
                        sb.append(params.get(key));
                        sb.append("\r\n");
                    }
                }
                /**
                 * 图片
                 */
            /* 第一行 */
                // `"--" + BOUNDARY + "\r\n"`
                sb.append("--" + BOUNDARY + "\r\n");

            /* 第二行 */
                // Content-Disposition: form-data; name="参数的名称"; filename="上传的文件名" +
                // "\r\n"
                sb.append("Content-Disposition: form-data;");
                sb.append(" name=\"");
                sb.append(mImage.getName());
                sb.append("\"");
                sb.append("; filename=\"");
                sb.append(mImage.getFileName());
                sb.append("\"");
                sb.append("\r\n");

            /* 第三行 */
                // Content-Type: 文件的 mime 类型 + "\r\n"
                sb.append("Content-Type: ");
                sb.append(mImage.getMime());
                sb.append("\r\n");

            /* 第四行 */
                // "\r\n" 空白的一行
                sb.append("\r\n");

                try {
                    bos.write(sb.toString().getBytes("utf-8"));
                /* 第五行 */
                    // 文件的二进制数据 + "\r\n"
                    bos.write(mImage.getValue());
                    bos.write("\r\n".getBytes("utf-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            /* 结尾行 */
                // `"--" + BOUNDARY + "--" + "\r\n"`
                String endLine = "--" + BOUNDARY + "--" + "\r\n";
                try {
                    bos.write(endLine.toString().getBytes("utf-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                Log.v("upLoad", "=====formImage====\n" + bos.toString());
                return bos.toByteArray();
            }
            /**
             * 文字
             */
        /* 第一行 */
            // `"--" + BOUNDARY + "\r\n"`
            sb.append("--" + BOUNDARY + "\r\n");

        /* 第二行 */
            // Content-Disposition: form-data; name="text" + "\r\n"
            sb.append("Content-Disposition: form-data;");
            sb.append(" name=\"");
            sb.append("text");
            sb.append("\"");
            sb.append("\r\n");

        /* 第三行 */
            // "\r\n" 空白的一行
            sb.append("\r\n");

        /* 第四行 */
            // 文本内容
            sb.append(content);
            sb.append("\r\n");

            if (mImage == null) {
            /* 结尾行 */
                // `"--" + BOUNDARY + "--" + "\r\n"`
                String endLine = "--" + BOUNDARY + "--" + "\r\n";
                try {
                    bos.write(sb.toString().getBytes("utf-8"));
                    bos.write(endLine.toString().getBytes("utf-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                Log.v("upLoad", "=====formImage====\n" + bos.toString());
                return bos.toByteArray();
            } else {
                // 先输入正常的文本字段
                Map<String,String> params = PostUploadRequest.this.getParams();
                if (params!=null && params.size()!=0){
                    for (String key : params.keySet()){
                        sb.append("--" + BOUNDARY + "\r\n");
                        sb.append("Content-Disposition: form-data; name=\""+key+"\"");
                        // "\r\n" 空白的一行
                        sb.append("\r\n");
                        sb.append(params.get(key));
                    }
                }
                /**
                 * 图片
                 */
            /* 第一行 */
                // `"--" + BOUNDARY + "\r\n"`
                sb.append("--" + BOUNDARY + "\r\n");

            /* 第二行 */
                // Content-Disposition: form-data; name="参数的名称"; filename="上传的文件名" +
                // "\r\n"
                sb.append("Content-Disposition: form-data;");
                sb.append(" name=\"");
                sb.append(mImage.getName());
                sb.append("\"");
                sb.append("; filename=\"");
                sb.append(mImage.getFileName());
                sb.append("\"");
                sb.append("\r\n");

            /* 第三行 */
                // Content-Type: 文件的 mime 类型 + "\r\n"
                sb.append("Content-Type: ");
                sb.append(mImage.getMime());
                sb.append("\r\n");

            /* 第四行 */
                // "\r\n" 空白的一行
                sb.append("\r\n");

                try {
                    bos.write(sb.toString().getBytes("utf-8"));
                /* 第五行 */
                    // 文件的二进制数据 + "\r\n"
                    bos.write(mImage.getValue());
                    bos.write("\r\n".getBytes("utf-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        /* 结尾行 */
            // `"--" + BOUNDARY + "--" + "\r\n"`
            String endLine = "--" + BOUNDARY + "--" + "\r\n";
            try {
                bos.write(endLine.toString().getBytes("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
//            Log.v("upLoad", "=====formImage====\n" + bos.toString());
            return bos.toByteArray();
        }

        // Content-Type: multipart/form-data; boundary=----------8888888888888
        @Override
        public String getBodyContentType() {
            return MULTIPART_FORM_DATA + "; boundary=" + BOUNDARY;
        }
    }
}
