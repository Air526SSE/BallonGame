package com.tu;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SpinnerAdapter extends ArrayAdapter<String> {  
	    Context context;  
	    String[] items;
	  
	   public SpinnerAdapter(Context context,  String[] objects) {  
	        super(context, android.R.layout.simple_spinner_item, objects);  
	        items = objects;  
	        this.context = context;  
	    }  
	  
	    @Override  
	    public View getDropDownView(int position, View convertView,  
	            ViewGroup parent) {  
	  
	        if (convertView == null) {  
	            LayoutInflater inflater = LayoutInflater.from(context);  
	            convertView = inflater.inflate(  
	                    android.R.layout.simple_spinner_item, parent, false);  
	        }  
	  
	        TextView tv = (TextView) convertView  
	                .findViewById(android.R.id.text1);  
	        tv.setText(items[position]); 
	       tv.setGravity(Gravity.CENTER); 
	        tv.setTextColor(Color.BLUE);  
	        tv.setTextSize(30);  
	        return convertView;  
	    }  
	  
	    @Override  
	    public View getView(int position, View convertView, ViewGroup parent) {  
	        if (convertView == null) {  
	            LayoutInflater inflater = LayoutInflater.from(context);  
	            convertView = inflater.inflate(  
	                    android.R.layout.simple_spinner_item, parent, false);  
	        }  
	  
	        // android.R.id.text1 is default text view in resource of the android.   
	        // android.R.layout.simple_spinner_item is default layout in resources of android.   
	  
	        TextView tv = (TextView) convertView  
	                .findViewById(android.R.id.text1);  
	        tv.setText(items[position]); 
	       tv.setGravity(Gravity.CENTER); 
	        tv.setTextColor(Color.BLUE);  
	        tv.setTextSize(30);  
	        return convertView;  
	    }  
	}