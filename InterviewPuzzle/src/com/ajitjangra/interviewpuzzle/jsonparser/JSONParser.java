package com.ajitjangra.interviewpuzzle.jsonparser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.ajitjangra.interviewpuzzle.utils.Constant;

/**
 * 
 * @author "Ajit S. Jangra"
 *
 */
public class JSONParser {
   
   private String jsonValueString;
   static JSONObject jsonObject = null;
   
   protected JSONObject getJSONFromUrl(String url, String httpGetMethod)
         throws InvalidParameterException, IOException, JSONException {
      try {
         DefaultHttpClient httpClient = new DefaultHttpClient();
         
         HttpRequestBase httpRequestBase = null;
         
         if (httpGetMethod == Constant.HTTP_GET_METHOD) {
            httpRequestBase = new HttpGet(url);
         } else if (httpGetMethod == Constant.HTTP_POST_METHOD) {
            
            httpRequestBase = new HttpPost(url);
            
            if (getJson() != null) {
               StringEntity se = new StringEntity(getJson());
               ((HttpPost) httpRequestBase).setEntity(se);
            }
         } else {
            throw new InvalidParameterException("Method Not Supported");
         }
         httpRequestBase.setHeader("Content-type", "application/json");
         HttpResponse httpResponse = httpClient.execute(httpRequestBase);
         jsonValueString = EntityUtils.toString(httpResponse.getEntity());
         jsonObject = new JSONObject(jsonValueString);
         
         return jsonObject;
         
      } catch (UnsupportedEncodingException e) {
         System.out.println("--UnsupportedEncodingException---/");
         e.printStackTrace();
         throw new UnsupportedEncodingException("");
      } catch (ClientProtocolException e) {
         System.out.println("--ClientProtocolException---/");
         e.printStackTrace();
         throw new ClientProtocolException("");
      } catch (IOException e) {
         System.out.println("--IOException---/");
         throw new IOException("Services Not Working");
      } catch (JSONException e) {
         System.out.println("--JSONException---/");
         throw new JSONException("JSON Exception");
      }
      
   }
   
   protected String getJson() {
      return null;
   }
   
}