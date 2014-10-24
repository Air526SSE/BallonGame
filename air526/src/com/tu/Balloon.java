package com.tu;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.su.BallonGameActivity;
import com.su.MainScreenActivity;
import com.su.R;

public class Balloon extends Activity {
	private static final String[] backgroundstr = { "草场", "蓝天", "树叶", "海洋" };
	private static final String[] shapestr = { "普通", "心型", "热气球", "火箭" };
	private static final String[] monostr = { "正计时", "倒计时", "火箭模式" };
	private static final String[] rocket = { "火箭模式" };

	private static String back = new String();
	private static String mode = new String();
	private String backBeSelected;
	private String shapeselect;
	private String monoselect;
	private String select;

	private Button commit;
	private Button cancle;
	// private TextView textColorSelected;
	// private TextView textShapeSelected;
	// private TextView textMonoSelected;
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

		// startService(intentMusic);

		backSpinner = (Spinner) findViewById(R.id.Color);
		shapeSpinner = (Spinner) findViewById(R.id.Shape);
		monoSpinner = (Spinner) findViewById(R.id.Mono);
		commit = (Button) findViewById(R.id.commit);
		cancle = (Button) findViewById(R.id.cancle);

		// textColorSelected = (TextView) findViewById(R.id.color_selected);
		// //textShapeSelected = (TextView) findViewById(R.id.shape_selected);
		// textMonoSelected = (TextView) findViewById(R.id.mono_selected);

		// ����ѡ������ArrayAdapter��������
		backadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, backgroundstr);
		shapeadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, shapestr);
		monoadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, monostr);

		// ���������б�ķ��
		backadapter.setDropDownViewResource(R.layout.myspinner_dropdown);
		shapeadapter.setDropDownViewResource(R.layout.myspinner_dropdown);
		monoadapter.setDropDownViewResource(R.layout.myspinner_dropdown);

		// ��adapter2 ��ӵ�spinner��
		backSpinner.setAdapter(backadapter);
		shapeSpinner.setAdapter(shapeadapter);
		monoSpinner.setAdapter(monoadapter);

		// ����¼�Spinner�¼�����
		backSpinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						RelativeLayout layout = (RelativeLayout) findViewById(R.id.RelativeLayout);
						// textColorSelected.setText("��ѡ�����ɫ�ǣ�" +
						// background[arg2]);
						arg0.setVisibility(View.VISIBLE);
						backBeSelected = backSpinner.getSelectedItem()
								.toString();
						// ImageView imageBalloon = (ImageView)
						// findViewById(R.id.ImageBalloon);
						if (backBeSelected.equals("草场")) {
							// imageBalloon.setBackgroundColor(Color.parseColor("#FF0000"));

							layout.setBackgroundResource(R.drawable.lawn);
						}
						if (backBeSelected.equals("蓝天")) {
							// imageBalloon.setBackgroundColor(Color.parseColor("#FFFF37"));

							layout.setBackgroundResource(R.drawable.skyball);
						}
						if (backBeSelected.equals("树叶")) {
							// imageBalloon.setBackgroundColor(Color.parseColor("#00DB00"));

							layout.setBackgroundResource(R.drawable.leaf);
						}
						if (backBeSelected.equals("海洋")) {
							// imageBalloon.setBackgroundColor(Color.parseColor("#0000E3"));

							layout.setBackgroundResource(R.drawable.umi);
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}

				});

		shapeSpinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {

						arg0.setVisibility(View.VISIBLE);
						shapeselect = shapeSpinner.getSelectedItem().toString();

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}

				});

		monoSpinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// textMonoSelected.setText("��ѡ��Ĳ����ǣ�" +
						// monostr[arg2]);
						arg0.setVisibility(View.VISIBLE);

						monoselect = monoSpinner.getSelectedItem().toString();
						mode = monoselect;
						Log.v("shapeselect:", shapeselect);
						if (shapeselect.equals("火箭")) {
							arg0.setVisibility(View.INVISIBLE);
							monoadapter = new ArrayAdapter<String>(Balloon.this,
									android.R.layout.simple_spinner_item,rocket);
						} else if (shapeselect.equals("热气球")) {
							arg0.setVisibility(View.INVISIBLE);
						} else
							arg0.setVisibility(View.VISIBLE);
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

		/*
		 * String colorBeSelected = colorSpinner.getSelectedItem().toString();
		 * String shapeBeSelected = colorSpinner.getSelectedItem().toString();
		 * String monoBeSelected = colorSpinner.getSelectedItem().toString();
		 */

		// commit.setImageDrawable(getResources().getDrawable(R.drawable.start));
		commit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent();
				intent.setClass(Balloon.this, BallonGameActivity.class);

				intent.putExtra("back", back);
				intent.putExtra("mode", mode);

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
				;

				// Balloon.this.finish();

			}
		});

		/*
		 * Bundle bundle = new Bundle(); bundle.putString("colorBeSelected",
		 * colorBeSelected); bundle.putString("shapeBeSelected",
		 * shapeBeSelected); bundle.putString("monoBeSelected", monoBeSelected);
		 */

		// startActivity(intent);

	}
}
