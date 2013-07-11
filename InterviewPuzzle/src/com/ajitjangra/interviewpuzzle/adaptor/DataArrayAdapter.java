package com.ajitjangra.interviewpuzzle.adaptor;

import java.util.ArrayList;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajitjangra.interviewpuzzle.MainActivity;
import com.ajitjangra.interviewpuzzle.R;

/**
 * 
 * @author "Ajit S. Jangra"
 * 
 */

public class DataArrayAdapter extends ArrayAdapter<String> {
   private final Context context;
   private final ArrayList<String> values;
   
   public DataArrayAdapter(Context context, ArrayList<String> values) {
      super(context, R.layout.list_interview_puzzle, values);
      this.context = context;
      this.values = values;
   }
   
   @Override
   public View getView(int position, View convertView, ViewGroup parent) {
      
      LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      
      View rowView = inflater.inflate(R.layout.list_interview_puzzle, parent,
            false);
      
      TextView tv_userName = (TextView) rowView.findViewById(R.id.tv_username);
      ImageView iv_image = (ImageView) rowView.findViewById(R.id.iv_image);
      TextView tv_htmlText = (TextView) rowView.findViewById(R.id.tv_htmltext);
      
      /*Set username to view*/
      tv_userName.setText(values.get(position));
      
      /*Set image to view*/
      iv_image.setImageBitmap(MainActivity.imageBitmapCollcection
            .get(position));
      
      /*Set Html Text to View*/
      tv_htmlText.setText(Html.fromHtml(MainActivity.htmlText.get(position)));
      
      return rowView;
   }
}
