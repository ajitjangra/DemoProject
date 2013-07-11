package com.ajitjangra.interviewpuzzle.jsonparser;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ajitjangra.interviewpuzzle.MainActivity;
import com.ajitjangra.interviewpuzzle.utils.Constant;

/**
 * 
 * @author "Ajit S. Jangra"
 *
 */
public class JsonDataParser extends JSONParser {
   
   /**
    * Parse the complete Json
    */
   public void getParsedData() {
      
      JSONObject json = null;
      
      try {
         
         /*Hold the main/initial objects*/
         json = getJSONFromUrl(
               "https://alpha-api.app.net/stream/0/users/1/posts",
               Constant.HTTP_GET_METHOD);
         
         /*Json Array named data*/
         JSONArray jsonDataArray = json.getJSONArray("data");
         
         for (int i = 0; i < jsonDataArray.length(); i++) {
            /*Hold the main/initial objects in json array named data*/
            JSONObject initialObjJsonDataArray = jsonDataArray.getJSONObject(i);
            
            /*Json Object named user*/
            JSONObject jsonUserObj = initialObjJsonDataArray.getJSONObject("user");
            
            /*Get the userName from jsonUserObj*/
            String username = jsonUserObj.getString("username");
            
            /*Get the ImageUrl in object from jsonUserObj*/
            JSONObject jsonAvatarImageObj = jsonUserObj.getJSONObject("avatar_image");
            
            /*Get the url from jsonAvatarImageObj*/
            String url = jsonAvatarImageObj.getString("url");
            
            /*Get the htmltext holding obj from jsonUserObj*/
            JSONObject jsonDescriptionObj = jsonUserObj.getJSONObject("description");
            
            /*Get the htmltext from jsonDescriptionObj*/
            String html = jsonDescriptionObj.getString("html");
            
            System.out.println("ajitajit username " + i + username);
            System.out.println("ajitajit html" + i + html);
            System.out.println("ajitajit url" + i + url);
            
            /*Add username to userName Arraylist at position (i)*/
            MainActivity.userName.add(i, username);
            
            /*Add url to imageUrl Arraylist at position (i)*/
            MainActivity.imageUrl.add(i, url);
            
            /*Add html to htmlText Arraylist at position (i)*/
            MainActivity.htmlText.add(i, html);
         }
         
         // System.out.println("ajitajit html" + html);
      } catch (JSONException e) {
         e.getMessage();
      } catch (IOException e) {
         e.getMessage();
      } catch (Exception e) {
         e.printStackTrace();
         
      }
   }
}