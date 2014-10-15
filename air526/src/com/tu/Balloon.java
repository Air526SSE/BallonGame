package com.tu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.su.BallonGameActivity;
import com.su.R;

public class Balloon extends Activity {
	private static final String[] colorstr = { "红", "黄", "绿", "蓝" };
	private static final String[] shapestr = { "心形", "鲨鱼", "鬼脸", "普通" };
	private static final String[] monostr = { "正计时","倒计时" };
	
	private static String color = new String();
	private static String mode = new String();

	private Button commit;
	private Button cancle;
	//private TextView textColorSelected;
	//private TextView textShapeSelected;
	//private TextView textMonoSelected;
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
		commit = (Button)findViewById(R.id.commit);
		cancle = (Button)findViewById(R.id.cancle);
		
		//textColorSelected = (TextView) findViewById(R.id.color_selected);
		////textShapeSelected = (TextView) findViewById(R.id.shape_selected);
		//textMonoSelected = (TextView) findViewById(R.id.mono_selected);

		// 将可选内容与ArrayAdapter连接起来
		coloradapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, colorstr);
		shapeadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, shapestr);
		monoadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, monostr);

		// 设置下拉列表的风格
		coloradapter.setDropDownViewResource(R.layout.myspinner_dropdown);
		shapeadapter.setDropDownViewResource(R.layout.myspinner_dropdown);
		monoadapter.setDropDownViewResource(R.layout.myspinner_dropdown);

		// 将adapter2 添加到spinner中
		colorSpinner.setAdapter(coloradapter);
		shapeSpinner.setAdapter(shapeadapter);
		monoSpinner.setAdapter(monoadapter);

		// 添加事件Spinner事件监听
		colorSpinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						//textColorSelected.setText("你选择的颜色是：" + colorstr[arg2]);
						arg0.setVisibility(View.VISIBLE);
						String colorBeSelected = colorSpinner.getSelectedItem()
								.toString();
						ImageView imageBalloon = (ImageView) findViewById(R.id.ImageBalloon);
						if (colorBeSelected.equals("红")) {
							// imageBalloon.setBackgroundColor(Color.parseColor("#FF0000"));
							color = colorBeSelected;
							imageBalloon.setImageResource(R.drawable.red);
						}
						if (colorBeSelected.equals("黄")) {
							// imageBalloon.setBackgroundColor(Color.parseColor("#FFFF37"));
							color = colorBeSelected;
							imageBalloon.setImageResource(R.drawable.yellow);
						}
						if (colorBeSelected.equals("绿")) {
							// imageBalloon.setBackgroundColor(Color.parseColor("#00DB00"));
							color = colorBeSelected;
							imageBalloon.setImageResource(R.drawable.green);
						}
						if (colorBeSelected.equals("蓝")) {
							// imageBalloon.setBackgroundColor(Color.parseColor("#0000E3"));
							color = colorBeSelected;
							imageBalloon.setImageResource(R.drawable.blue);
						}
					}

					public void onNothingSelected(AdapterView<?> arg0) {

					}

				});

		shapeSpinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						//textShapeSelected.setText("你选择的形状是：" + shapestr[arg2]);
						arg0.setVisibility(View.VISIBLE);
					}

					public void onNothingSelected(AdapterView<?> arg0) {

					}

				});

		monoSpinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						//textMonoSelected.setText("你选择的材料是：" + monostr[arg2]);
						arg0.setVisibility(View.VISIBLE);
						
						String modeselect = monoSpinner.getSelectedItem().toString();
						if(modeselect.equals("正计时"))
						{
							mode = modeselect;
						}
						
						if(modeselect.equals("倒计时"))
						{
							mode = modeselect;
						}
					}

					public void onNothingSelected(AdapterView<?> arg0) {

					}

				});

		myAnimation = AnimationUtils.loadAnimation(this, R.anim.my_anim);

		colorSpinner.setOnTouchListener(new Spinner.OnTouchListener() {
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

		/*String colorBeSelected = colorSpinner.getSelectedItem().toString();
		String shapeBeSelected = colorSpinner.getSelectedItem().toString();
		String monoBeSelected = colorSpinner.getSelectedItem().toString();*/

		commit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent();
				intent.setClass(Balloon.this, BallonGameActivity.class);
				
				intent.putExtra("color", color);
				intent.putExtra("mode", mode);
				
				startActivityForResult(intent,1);
				
			}
		});
		
		cancle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Balloon.this.finish();
				
			}
		});
		
		
		/*Bundle bundle = new Bundle();
		bundle.putString("colorBeSelected", colorBeSelected);
		bundle.putString("shapeBeSelected", shapeBeSelected);
		bundle.putString("monoBeSelected", monoBeSelected);*/

		// startActivity(intent);

	}
}
