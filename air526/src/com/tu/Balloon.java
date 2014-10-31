package com.tu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.su.BallonGameActivity;
import com.su.MainScreenActivity;
import com.su.R;


public class Balloon extends Activity {
	private static final String[] backgroundstr = { "蓝天","草场","树叶", "海洋" };
	private static final String[] shapestr = { "笑脸", "热气球","火箭"};
	private static final String[] monostr = { "正计时", "倒计时" };
	
	private static final String[] hotmode = { "热气球模式——超困难模式，有胆来战" };
	private static final String[] rocmode = { "火箭模式——程序员脑袋进水的杰作" };
	

	private static int back = 0;
	private static String mode = new String();
	private static String shape = new String();
	
	private String backBeSelected= new String();;
	private String shapeselect= new String();;
	private String monoselect= new String();;
	private String select= new String();;

	private Button commit;
	private Button cancle;
	// private TextView textColorSelected;
	// private TextView textShapeSelected;
	// private TextView textMonoSelected;
	private ImageView balloonImage;
	private Spinner backSpinner;
	private Spinner shapeSpinner;
	private static Spinner monoSpinner;
	private ArrayAdapter<String> backadapter;
	private ArrayAdapter<String> shapeadapter;
	private ArrayAdapter<String> monoadapter;
	
	private ArrayAdapter<String> hotdapter;
	private ArrayAdapter<String> rocdapter;
	
	Animation myAnimation;
	
	static boolean isBehind = false;

	// private Intent intentMusic = new Intent("com.angel.Android.MUSIC");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);

		Init();
		
	}
	public void Init(){
		

		backSpinner = (Spinner) findViewById(R.id.Color);
		shapeSpinner = (Spinner) findViewById(R.id.Shape);
		monoSpinner = (Spinner) findViewById(R.id.Mono);
		commit = (Button) findViewById(R.id.commit);
		cancle = (Button) findViewById(R.id.cancle);
		balloonImage = (ImageView) findViewById(R.id.ImageBalloon);

		
		backadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, backgroundstr);
		shapeadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, shapestr);
		//笑脸
		monoadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, monostr);
		
		hotdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, hotmode);
		rocdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, rocmode);

		
		backadapter.setDropDownViewResource(R.layout.myspinner_dropdown);
		shapeadapter.setDropDownViewResource(R.layout.myspinner_dropdown);
		monoadapter.setDropDownViewResource(R.layout.myspinner_dropdown);
		
		hotdapter.setDropDownViewResource(R.layout.myspinner_dropdown);
		rocdapter.setDropDownViewResource(R.layout.myspinner_dropdown);

		
		backSpinner.setAdapter(backadapter);
		shapeSpinner.setAdapter(shapeadapter);
		//monoSpinner.setAdapter(monoadapter);

		back=0;
		shape="笑脸";
		mode="正计时";
		
		backSpinner.setBackgroundColor(Color.RED);
		
		backSpinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {																			
							((TextView) arg0.getChildAt(0)).setTextColor(Color.YELLOW);
						    ((TextView) arg0.getChildAt(0)).setTextSize(20);
						
						LinearLayout layout = (LinearLayout) findViewById(R.id.LinearLayout1);
						
						arg0.setVisibility(View.VISIBLE);
						backBeSelected = backSpinner.getSelectedItem()
								.toString();
						
						if (backBeSelected.equals("蓝天")) {							
							layout.setBackgroundResource(R.drawable.skyball);
							back = 0;
						}
						
						if (backBeSelected.equals("草场")) {							
							layout.setBackgroundResource(R.drawable.lawn);
							back = 1;
						}
						
						if (backBeSelected.equals("树叶")) {							
							layout.setBackgroundResource(R.drawable.leaf);
							back = 2;
						}
						if (backBeSelected.equals("海洋")) {							
							layout.setBackgroundResource(R.drawable.umi);
							back = 3;
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}

				});
		shapeSpinner.setBackgroundColor(Color.BLUE);
		
		shapeSpinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						
						((TextView) arg0.getChildAt(0)).setTextColor(Color.YELLOW);
					    ((TextView) arg0.getChildAt(0)).setTextSize(20);
					    
						arg0.setVisibility(View.VISIBLE);
						
						shapeselect = shapeSpinner.getSelectedItem().toString();
						
						if(shapeselect.equals("笑脸")){
							monoSpinner.setAdapter(monoadapter);
							balloonImage.setImageResource(R.drawable.yellow20);
							shape = shapeselect;
							mode = "正计时";
						}
						if(shapeselect.equals("热气球")){
							monoSpinner.setAdapter(hotdapter);
							balloonImage.setImageResource(R.drawable.hot22);
							shape = shapeselect;
							mode = "热气球模式——超困难模式，有胆来战";
						}
						if(shapeselect.equals("火箭")){
							monoSpinner.setAdapter(rocdapter);
							balloonImage.setImageResource(R.drawable.roc26);
							shape = shapeselect;
							mode="火箭模式——程序员脑袋进水的杰作";
						}
						

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}

				});
		monoSpinner.setBackgroundColor(Color.GREEN);
		
		//if(monoSpinner.getAdapter()!=null)
		//{
		monoSpinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						
						((TextView) arg0.getChildAt(0)).setTextColor(Color.YELLOW);
					    ((TextView) arg0.getChildAt(0)).setTextSize(20);
						arg0.setVisibility(View.VISIBLE);

						monoselect = monoSpinner.getSelectedItem().toString();
						mode = monoselect;
						
						//Log.v("shapeselect:", shapeselect);
						
						/*if (shapeselect.equals("火箭")) {
							arg0.setVisibility(View.INVISIBLE);
							
						} else if (shapeselect.equals("热气球")) {
							arg0.setVisibility(View.INVISIBLE);
						} else
							arg0.setVisibility(View.VISIBLE);*/
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}

				});
		//}

		myAnimation = AnimationUtils.loadAnimation(this, R.anim.my_anim);

		backSpinner.setOnTouchListener(new Spinner.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				v.startAnimation(myAnimation);
				v.setVisibility(View.VISIBLE);
				return false;
			}
		});

		shapeSpinner.setOnTouchListener(new Spinner.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				v.startAnimation(myAnimation);
				v.setVisibility(View.VISIBLE);
				return false;
			}
		});

		monoSpinner.setOnTouchListener(new Spinner.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				v.startAnimation(myAnimation);
				v.setVisibility(View.VISIBLE);
				return false;
			}
		});
				

		// commit.setImageDrawable(getResources().getDrawable(R.drawable.start));
		commit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				//stopService(new Intent(Balloon.this, MusicServer.class));
				
				Intent intent = new Intent();
				intent.setClass(Balloon.this, BallonGameActivity.class);
				
				intent.putExtra("back", back);
				intent.putExtra("shape", shape);
				intent.putExtra("mode", mode);
				
				startActivity(intent);
				//startActivityForResult(intent, 1);

			}
		});

		cancle.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Balloon.this, MainScreenActivity.class);
				startActivity(intent);	
				//finish();
				System.exit(0);
			}
		});

		

	}
	protected void onStop() {
		  // TODO Auto-generated method stub
		  Intent intent = new Intent(Balloon.this,MusicServer.class);
		 
			  stopService(intent);
			  super.onStop();
		  
	}
}
