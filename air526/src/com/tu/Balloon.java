package com.tu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.su.R;

public class Balloon extends Activity {
	private static final String[] colorstr = 
		{"红","黄","绿","蓝"};
	private static final String[] shapestr = 
		{"心形","鲨鱼","鬼脸","普通"};	
	private static final String[] monostr = 
		{"泡泡","橡胶","乳胶","塑料"};

	 	private TextView textColorSelected;
	 	private TextView textShapeSelected;
	 	private TextView textMonoSelected;
	    private Spinner colorSpinner;
	    private Spinner shapeSpinner;
	    private Spinner monoSpinner;
	    private ArrayAdapter<String> coloradapter;
	    private ArrayAdapter<String> shapeadapter;
	    private ArrayAdapter<String> monoadapter;
	    Animation myAnimation;
	 
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        // TODO Auto-generated method stub
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main2);
	 
	        colorSpinner = (Spinner) findViewById(R.id.Color);
	        shapeSpinner = (Spinner) findViewById(R.id.Shape);
	        monoSpinner = (Spinner) findViewById(R.id.Mono);
	        textColorSelected = (TextView) findViewById(R.id.color_selected);
	        textShapeSelected = (TextView) findViewById(R.id.shape_selected);
	        textMonoSelected = (TextView) findViewById(R.id.mono_selected);
	 
	        //将可选内容与ArrayAdapter连接起来
	        coloradapter =new  ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colorstr);
	        shapeadapter =new  ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, shapestr);
	        monoadapter =new  ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, monostr);
	 
	        //设置下拉列表的风格 
	        coloradapter.setDropDownViewResource(R.layout.myspinner_dropdown);
	        shapeadapter.setDropDownViewResource(R.layout.myspinner_dropdown);
	        monoadapter.setDropDownViewResource(R.layout.myspinner_dropdown);
	 
	        //将adapter2 添加到spinner中
	        colorSpinner.setAdapter(coloradapter);
	        shapeSpinner.setAdapter(shapeadapter);
	        monoSpinner.setAdapter(monoadapter);
	 
	        //添加事件Spinner事件监听  
	        colorSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
	        	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
	    	        textColorSelected.setText("你选择的颜色是："+colorstr[arg2]);
	    	        arg0.setVisibility(View.VISIBLE);
	    	        String colorBeSelected = colorSpinner.getSelectedItem().toString();
	    	        ImageView imageBalloon = (ImageView) findViewById(R.id.ImageBalloon);
	    	        if(colorBeSelected.equals("红")){
	    		//    	imageBalloon.setBackgroundColor(Color.parseColor("#FF0000"));
	    	        	imageBalloon.setImageResource(R.drawable.red);
	    		    }
	    		    if(colorBeSelected.equals("黄")){
	    		 //   	imageBalloon.setBackgroundColor(Color.parseColor("#FFFF37"));
	    		    	imageBalloon.setImageResource(R.drawable.yellow);
	    		    }
	    		    if(colorBeSelected.equals("绿")){
	    		 //   	imageBalloon.setBackgroundColor(Color.parseColor("#00DB00"));
	    		    	imageBalloon.setImageResource(R.drawable.green);
	    		    }
	    		    if(colorBeSelected.equals("蓝")){
	    		  //  	imageBalloon.setBackgroundColor(Color.parseColor("#0000E3"));
	    		    	imageBalloon.setImageResource(R.drawable.blue);
	    		    }
	    	    }
	    	 
	    	     public void onNothingSelected(AdapterView<?> arg0) {
	    	             
	    	     }
	         
	        });
	        
	        shapeSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
	        	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
	    	        textShapeSelected.setText("你选择的形状是："+shapestr[arg2]);
	    	        arg0.setVisibility(View.VISIBLE);
	    	    }
	    	 
	    	     public void onNothingSelected(AdapterView<?> arg0) {
	    	             
	    	     }
	         
	        });
	        
	        monoSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
	        	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
	    	        textMonoSelected.setText("你选择的材料是："+monostr[arg2]);
	    	        arg0.setVisibility(View.VISIBLE);
	    	    }
	    	 
	    	     public void onNothingSelected(AdapterView<?> arg0) {
	    	             
	    	     }
	         
	        });
	        
	    myAnimation = AnimationUtils.loadAnimation(this, R.anim.my_anim);
	    
	    colorSpinner.setOnTouchListener(new Spinner.OnTouchListener(){	    	
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				v.startAnimation(myAnimation);
	    		v.setVisibility(View.VISIBLE);
				return false;
			}
	    });
	    
	    shapeSpinner.setOnTouchListener(new Spinner.OnTouchListener(){	    	
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				v.startAnimation(myAnimation);
	    		v.setVisibility(View.VISIBLE);
				return false;
			}
	    });
	    
	    monoSpinner.setOnTouchListener(new Spinner.OnTouchListener(){	    	
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				v.startAnimation(myAnimation);
	    		v.setVisibility(View.VISIBLE);
				return false;
			}
	    });
	    
	    colorSpinner.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFoucus) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    shapeSpinner.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFoucus) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    monoSpinner.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFoucus) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    String colorBeSelected = colorSpinner.getSelectedItem().toString();
	    String shapeBeSelected = colorSpinner.getSelectedItem().toString();
	    String monoBeSelected = colorSpinner.getSelectedItem().toString();
	    
	    Intent intent = new Intent();
	 //   intent.setClass(Balloon.this, null);
	    Bundle bundle = new Bundle();
	    bundle.putString("colorBeSelected", colorBeSelected);
	    bundle.putString("shapeBeSelected", shapeBeSelected);
	    bundle.putString("monoBeSelected", monoBeSelected);
	    
	//    startActivity(intent);
	    
	   
	 }
}
