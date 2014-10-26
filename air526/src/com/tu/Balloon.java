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
	private static final String[] shapestr = { "笑脸", "热气球"};
	private static final String[] monostr = { "正计时", "倒计时", "热气球模式" };
	private static final String[] rocket = { "热气球模式" };

	private static int back = 0;
	private static String mode = new String();
	private static String shape = new String();
	
	private String backBeSelected;
	private String shapeselect;
	private String monoselect;
	private String select;

	private Button commit;
	private Button cancle;
	// private TextView textColorSelected;
	// private TextView textShapeSelected;
	// private TextView textMonoSelected;
	private ImageView balloonImage;
	private Spinner backSpinner;
	private Spinner shapeSpinner;
	private Spinner monoSpinner;
	private ArrayAdapter<String> backadapter;
	private ArrayAdapter<String> shapeadapter;
	private ArrayAdapter<String> monoadapter;
	Animation myAnimation;

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
		monoadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, monostr);

		// ���������б�ķ��
		
		
		/*SpinnerAdapter backadapter = new SpinnerAdapter(this,  
	             backgroundstr);  
		SpinnerAdapter shapeadapter = new SpinnerAdapter(this,  
	           shapestr); 
		SpinnerAdapter monoadapter = new SpinnerAdapter(this,  
	            monostr); 
		*/
		backadapter.setDropDownViewResource(R.layout.myspinner_dropdown);
		shapeadapter.setDropDownViewResource(R.layout.myspinner_dropdown);
		monoadapter.setDropDownViewResource(R.layout.myspinner_dropdown);

		// ��adapter2 ��ӵ�spinner��
		backSpinner.setAdapter(backadapter);
		shapeSpinner.setAdapter(shapeadapter);
		monoSpinner.setAdapter(monoadapter);

		// ����¼�Spinner�¼�����
		backSpinner.setBackgroundColor(Color.RED);
		backSpinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						//for(int i=0; i<4; i++){
							
						
							((TextView) arg0.getChildAt(0)).setTextColor(Color.YELLOW);
						    ((TextView) arg0.getChildAt(0)).setTextSize(20);
						//}
						LinearLayout layout = (LinearLayout) findViewById(R.id.LinearLayout1);
						// textColorSelected.setText("��ѡ�����ɫ�ǣ�" +
						// background[arg2]);
						arg0.setVisibility(View.VISIBLE);
						backBeSelected = backSpinner.getSelectedItem()
								.toString();
						// ImageView imageBalloon = (ImageView)
						// findViewById(R.id.ImageBalloon);
						if (backBeSelected.equals("蓝天")) {
							// imageBalloon.setBackgroundColor(Color.parseColor("#FFFF37"));

							layout.setBackgroundResource(R.drawable.skyball);
							back = 0;
						}
						
						if (backBeSelected.equals("草场")) {
							// imageBalloon.setBackgroundColor(Color.parseColor("#FF0000"));

							layout.setBackgroundResource(R.drawable.lawn);
							back = 1;
						}
						
						if (backBeSelected.equals("树叶")) {
							// imageBalloon.setBackgroundColor(Color.parseColor("#00DB00"));

							layout.setBackgroundResource(R.drawable.leaf);
							back = 2;
						}
						if (backBeSelected.equals("海洋")) {
							// imageBalloon.setBackgroundColor(Color.parseColor("#0000E3"));

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
							balloonImage.setImageResource(R.drawable.yellow20);
							shape = shapeselect;
						}
						if(shapeselect.equals("热气球")){
							balloonImage.setImageResource(R.drawable.hot22);
							shape = shapeselect;
						}
						/*if(shapeselect.equals("火箭")){
							balloonImage.setImageResource(R.drawable.roc26);
							shape = shapeselect;
						}*/
						

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}

				});
		monoSpinner.setBackgroundColor(Color.GREEN);
		monoSpinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// textMonoSelected.setText("��ѡ��Ĳ����ǣ�" +
						// monostr[arg2]);
						((TextView) arg0.getChildAt(0)).setTextColor(Color.YELLOW);
					    ((TextView) arg0.getChildAt(0)).setTextSize(20);
						arg0.setVisibility(View.VISIBLE);

						monoselect = monoSpinner.getSelectedItem().toString();
						mode = monoselect;
						Log.v("shapeselect:", shapeselect);
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

		backSpinner.setOnFocusChangeListener(new OnFocusChangeListener() {

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


		// commit.setImageDrawable(getResources().getDrawable(R.drawable.start));
		commit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent();
				intent.setClass(Balloon.this, BallonGameActivity.class);
				
				intent.putExtra("back", back);
				intent.putExtra("mode", mode);
				intent.putExtra("shape", shape);

				startActivityForResult(intent, 1);

			}
		});

		cancle.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Balloon.this, MainScreenActivity.class);
				startActivity(intent);				

				// Balloon.this.finish();

			}
		});

		

	}
	
	
}
