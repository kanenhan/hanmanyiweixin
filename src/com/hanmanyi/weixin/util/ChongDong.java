package com.hanmanyi.weixin.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
 
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
 
 
/**
 *
 * @author Jave
 * @function 虫洞开放api
 */
public class ChongDong {
//        private String WebAPI_Path = "http://wap.unidust.cn/api/searchout.do?type=wap&ch=1001&info=";
        private String WebAPI_Path = "http://api.ajaxsns.com/api.php?key=free&appid=0&msg=";
        private HttpClient httpClient = null;
 
        @SuppressWarnings("finally")
        public String getResult(String question) {
                String strResult = null;
                HttpParams httpParams = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
                HttpConnectionParams.setSoTimeout(httpParams, 30000);
                httpClient = new DefaultHttpClient(httpParams);
                try {
                        String strQuestion = WebAPI_Path + question;
                        HttpGet httpRequest = new HttpGet(strQuestion);// 请求内容
                        HttpResponse httpResponse = httpClient.execute(httpRequest);// 得到response
                        if (httpResponse.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
                                String str = EntityUtils.toString(httpResponse.getEntity());
                                strResult = new String(str.getBytes("ISO-8859-1"), "UTF-8");// 防止乱码，设置编码格式
                        System.out.println(strResult);
                        }
                } catch (ClientProtocolException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                } finally {
                        return strResult;
                }
        }
        
        public static void main(String[] args) {
        	String question;
			try {
				question = java.net.URLEncoder.encode("天气深圳", "utf-8");
				new ChongDong().getResult(question);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} 
		}
}