package com.ajitjangra.interviewpuzzle;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.ajitjangra.interviewpuzzle.adaptor.DataArrayAdapter;
import com.ajitjangra.interviewpuzzle.jsonparser.JsonDataParser;

/**
 * 
 * @author "Ajit S. Jangra"
 * 
 */

public class MainActivity extends ListActivity {
   
   /* ArrayList holding all user's name */
   public static ArrayList<String> userName = new ArrayList<String>();
   
   /* ArrayList holding all Images url */
   public static ArrayList<String> imageUrl = new ArrayList<String>();
   
   /* ArrayList holding all server images in Bitmap */
   public static ArrayList<Bitmap> imageBitmapCollcection = new ArrayList<Bitmap>();
   
   /* ArrayList holding all html text */
   public static ArrayList<String> htmlText = new ArrayList<String>();
   
   ProgressDialog progress;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      
      progress = new ProgressDialog(this);
      
      /* Parse the Json and get all images of server in bitmap */
      new serverAsyncTask().execute();
   }
   
   @Override
   protected void onListItemClick(ListView l, View v, int position, long id) {
      
      String selectedValue = (String) getListAdapter().getItem(position);
      Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
      
   }
   
   // =====================================================
   // Defined Methods
   // =====================================================
   
   /**
    * Get the Bitmap from an URL src: url of image
    */
   public static Bitmap getBitmapFromURL(String src) {
      try {
         URL url = new URL(src);
         HttpURLConnection connection = (HttpURLConnection) url
               .openConnection();
         connection.setDoInput(true);
         connection.connect();
         InputStream input = connection.getInputStream();
         Bitmap myBitmap = BitmapFactory.decodeStream(input);
         return myBitmap;
      } catch (IOException e) {
         e.printStackTrace();
         return null;
      }
   }
   
   /**
    * add content to list by adapter
    */
   public void setListAdapter() {
      
      setListAdapter(new DataArrayAdapter(this, userName));
   }
   
   // =====================================================
   // Aysc Task
   // =====================================================
   
   /**
    * Parse the Json and get all images of server in bitmap
    */
   private class serverAsyncTask extends AsyncTask<Void, Void, Void> {
      
      @Override
      protected void onPreExecute() {
         super.onPreExecute();
  
         progress.setTitle("Please Wait!!!");
         progress.setMessage("Content Loading...");
         progress.show();
      }
      
      @Override
      protected Void doInBackground(Void... params) {
         
         /* Parser that will parse the Json Data from server */
         JsonDataParser jsonDataParser = new JsonDataParser();
         
         /* Parse the all Json data */
         jsonDataParser.getParsedData();
         
         /* Collect all Images in Bitmap Array from server */
         for (int i = 0; i < imageUrl.size(); i++) {
            imageBitmapCollcection.add(getBitmapFromURL(imageUrl.get(i)));
         }
         
         return null;
      }
      
      @Override
      protected void onPostExecute(Void result) {
         super.onPostExecute(result);
         progress.cancel();
         /* add content to list by adapter */
         setListAdapter();
      }
   }
   
}
