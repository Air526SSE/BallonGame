package com.su;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tu.Balloon;

public class BallonGameActivity extends Activity {
	
	SoundPool soundPool;
	HashMap<Integer,Integer> soundMap = new HashMap<Integer,Integer>();
	
	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	
	private ImageView imgshow;
	private TextView score;

	private Button btnRestart;
	private Button btnBack;
	private LinearLayout main;

	AnimationDrawable anim;

	private int level = 0, count = 0;
	//private int bgn = 0;
	private int min=0;
	private int s=0;

	private int[] yellow = { R.drawable.yellow0, R.drawable.yellow1,
			R.drawable.yellow2, R.drawable.yellow3, R.drawable.yellow4,
			R.drawable.yellow5, R.drawable.yellow6, R.drawable.yellow7,
			R.drawable.yellow8, R.drawable.yellow9, R.drawable.yellow10,
			R.drawable.yellow11, R.drawable.yellow12, R.drawable.yellow13,
			R.drawable.yellow14, R.drawable.yellow15, R.drawable.yellow16,
			R.drawable.yellow17, R.drawable.yellow18, R.drawable.yellow19,
			R.drawable.yellow20, R.drawable.yellow21, R.drawable.yellow22 };

	private int[] hot = { R.drawable.hot0, R.drawable.hot1, R.drawable.hot2,
			R.drawable.hot3, R.drawable.hot4, R.drawable.hot5, R.drawable.hot6,
			R.drawable.hot7, R.drawable.hot8, R.drawable.hot9,
			R.drawable.hot10, R.drawable.hot11, R.drawable.hot12,
			R.drawable.hot13, R.drawable.hot14, R.drawable.hot15,
			R.drawable.hot16, R.drawable.hot17, R.drawable.hot18,
			R.drawable.hot19, R.drawable.hot20, R.drawable.hot21,
			R.drawable.hot22 };

	private int[] roc = { R.drawable.roc0, R.drawable.roc1, R.drawable.roc2,
			R.drawable.roc3, R.drawable.roc4, R.drawable.roc5, R.drawable.roc6,
			R.drawable.roc7, R.drawable.roc8, R.drawable.roc9,
			R.drawable.roc10, R.drawable.roc11, R.drawable.roc12,
			R.drawable.roc13, R.drawable.roc14, R.drawable.roc15,
			R.drawable.roc16, R.drawable.roc17, R.drawable.roc18,
			R.drawable.roc19, R.drawable.roc20, R.drawable.roc21,
			R.drawable.roc22, R.drawable.roc23, R.drawable.roc24,
			R.drawable.roc25, R.drawable.roc26 };

	/*
	 * private int[] fly = { R.drawable.fly0, R.drawable.fly1, R.drawable.fly2,
	 * R.drawable.fly3, R.drawable.fly4, R.drawable.fly5, R.drawable.fly6,
	 * R.drawable.fly7, R.drawable.fly8, R.drawable.fly9, R.drawable.fly10,
	 * R.drawable.fly11, R.drawable.fly12, R.drawable.fly13, R.drawable.fly14,
	 * R.drawable.fly15, R.drawable.fly16, R.drawable.fly17, R.drawable.fly18,
	 * R.drawable.fly19, R.drawable.fly20, R.drawable.fly21, R.drawable.fly22,
	 * R.drawable.fly23, R.drawable.fly24, R.drawable.fly25, R.drawable.fly26,
	 * R.drawable.fly27, R.drawable.fly28, R.drawable.fly29, R.drawable.fly30,
	 * R.drawable.fly31, R.drawable.fly32, R.drawable.fly33, R.drawable.fly34,
	 * R.drawable.fly35, R.drawable.fly36, R.drawable.fly37, R.drawable.fly38,
	 * R.drawable.fly39, R.drawable.fly40, R.drawable.fly41, R.drawable.fly42,
	 * R.drawable.fly43, R.drawable.fly44, R.drawable.fly45, R.drawable.fly46,
	 * R.drawable.fly47, R.drawable.fly48, R.drawable.fly49, R.drawable.fly50,
	 * R.drawable.fly51, R.drawable.fly52, R.drawable.fly53, R.drawable.fly54,
	 * R.drawable.fly55, R.drawable.fly56, R.drawable.fly57, R.drawable.fly58,
	 * R.drawable.fly59, R.drawable.fly60, R.drawable.fly61, R.drawable.fly62,
	 * R.drawable.fly63, };
	 */

	/*private int[] bg = { R.drawable.skyball, R.drawable.lawn, R.drawable.leaf,
			R.drawable.umi };*/

	private sqlEngThread sqlEngine;

	private int recLen = 0;
	private int lesLen = 9;
	private int seLen = 10;
	private int se = 0;
	private int flynu = 0;

	public int ballsum = 0;

	private static boolean mefly = false;

	private String shape = new String();
	private String amode = new String();
	private int back = 0;

	private TextView txtView;
	private TextView showend;
	private Thread ti;
	private Thread fi;

	private float initY;
	private float curX;
	private float curY;
	float nextX;
	float nextY;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		soundPool = new SoundPool(4,AudioManager.STREAM_SYSTEM,5);
		soundMap.put(1, soundPool.load(this,R.raw.smile,1));
		soundMap.put(2, soundPool.load(this,R.raw.wsmile,1));
		soundMap.put(3, soundPool.load(this,R.raw.hot,1));
		soundMap.put(4, soundPool.load(this,R.raw.rocf,1));
		
		
		imgshow = (ImageView) findViewById(R.id.imgshow);
		score = (TextView) findViewById(R.id.score);

		btnRestart = (Button) findViewById(R.id.btnRestart);
		btnBack = (Button) findViewById(R.id.btnBack);

		txtView = (TextView) findViewById(R.id.show_time);
		showend = (TextView) findViewById(R.id.show_end);

		main = (LinearLayout) findViewById(R.id.Lmain);
		main.setBackgroundResource(R.drawable.skyball);
		
		preferences = getSharedPreferences("SCORE", MODE_WORLD_READABLE);
		editor = preferences.edit();

		// flyimage.setVisibility(View.INVISIBLE);

		//initYellowGame();
		initFlyGame();
		//initGame1();
		//initHotGame();
		
		Intent intent = getIntent();
		Select(intent);

		/*
		 * if(mefly=true) { imgshow.setBackgroundResource(R.anim.fly); anim =
		 * (AnimationDrawable) imgshow.getBackground(); anim.start(); }
		 */

		btnBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent();
				intent.setClass(BallonGameActivity.this, Balloon.class);
				startActivity(intent);
			}
		});

		/*xunhuan.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				bgn = (int) (Math.random() * 4);
				main.setBackgroundResource(bg[bgn]);
			}
		});*/

	}

	private void Select(Intent intent) {// 根据intent传来的值选择相应的方法

		if (intent.getIntExtra("back", 0) != 4) {
			back = intent.getIntExtra("back", 0);
			switch (back) {
			case 0:
				main.setBackgroundResource(R.drawable.skyball);
				break;
			case 1:
				main.setBackgroundResource(R.drawable.lawn);
				break;
			case 2:
				main.setBackgroundResource(R.drawable.leaf);
				break;
			case 3:
				main.setBackgroundResource(R.drawable.umi);
				break;

			}
		}

		if (intent.getStringExtra("shape") != null) {
			shape = intent.getStringExtra("shape");
		}

		if (intent.getStringExtra("mode") != null) {
			amode = intent.getStringExtra("mode");
		}

		if (amode.equals("正计时")) {

			initYellowGame();
			// initRocGame();
			// initFlyGame();
			// flyRoc();
			btnRestart.setOnClickListener(new View.OnClickListener() {// 重新选择按钮监听
						@Override
						// 将这个监听放在每个启动函数中，可以使重新开始变成每一个函数
						public void onClick(View v) {

							initYellowGame();
							// initRocGame();
							// flynu = 0;
							// initFlyGame();
							// flyRoc();

						}
					});
		}

		if (amode.equals("热气球模式")) {

			initHotGame();
			// initYellowGame();
			// initRocGame();
			// initFlyGame();
			// flyRoc();
		}

		if (amode.equals("正计时") && shape.equals("hot")) {
			initHotGame();
			btnRestart.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					initHotGame();
				}
			});
		}

		if (amode.equals("倒计时")) {

			initGame1();

		}
	}

	private void initRocGame() {// 正吹火箭
		// TODO Auto-generated method stub
		level = 0;
		recLen = 0;
		se = 0;
		flynu = 0;

		imgshow.setBackgroundResource(roc[0]);
		btnRestart.setVisibility(View.INVISIBLE);
		btnBack.setVisibility(View.INVISIBLE);
		showend.setVisibility(View.INVISIBLE);
		txtView.setVisibility(View.VISIBLE);

		ti = new Thread(new RocThread());

		sqlEngine = new sqlEngThread(new BallonHandlerRoc());
		sqlEngine.startThead();
		ti.start();
	}

	private void initFlyGame() {// 火箭飞
		// TODO Auto-generated method stub

		level = 0;
		recLen = 0;
		se = 0;

		imgshow.setBackgroundResource(roc[0]);
		btnRestart.setVisibility(View.INVISIBLE);
		btnBack.setVisibility(View.INVISIBLE);
		showend.setVisibility(View.INVISIBLE);
		txtView.setVisibility(View.INVISIBLE);
		score.setVisibility(View.INVISIBLE);

		sqlEngine = new sqlEngThread(new BallonHandlerFly());
		sqlEngine.startThead();

	}

	private void initHotGame() {// 正吹热气球
		// TODO Auto-generated method stub
		level = 0;
		recLen = 0;
		se = 0;

		if(preferences.contains("hmin"))
		{
		min=preferences.getInt("hmin", 0);
		}
		if(preferences.contains("hs"))
		{
			s=preferences.getInt("hs", 0);
		}				
		score.setText("最好记录： "+min+":"+s+"秒");
		
		imgshow.setBackgroundResource(hot[0]);
		btnRestart.setVisibility(View.INVISIBLE);
		btnBack.setVisibility(View.INVISIBLE);
		showend.setVisibility(View.INVISIBLE);
		txtView.setVisibility(View.VISIBLE);

		ti = new Thread(new MyThread());

		sqlEngine = new sqlEngThread(new BallonHandlerHot());
		sqlEngine.startThead();
		ti.start();

		btnRestart.setOnClickListener(new View.OnClickListener() {// 重新选择按钮监听
					@Override
					// 将这个监听放在每个启动函数中，可以使重新开始变成每一个函数
					public void onClick(View v) {

						initHotGame();
						// initYellowGame();
						// initRocGame();
						// flynu = 0;
						// initFlyGame();
						// flyRoc();

					}
				});
	}

	private void initYellowGame() {// 正吹笑脸
		// TODO Auto-generated method stub
		level = 0;
		recLen = 0;
		se = 0;

		if(preferences.contains("smin"))
		{
		min=preferences.getInt("smin", 0);
		}
		if(preferences.contains("ss"))
		{
			s=preferences.getInt("ss", 0);
		}				
		score.setText("最好记录： "+min+":"+s+"秒");
		
		imgshow.setBackgroundResource(yellow[0]);
		btnRestart.setVisibility(View.INVISIBLE);
		btnBack.setVisibility(View.INVISIBLE);
		showend.setVisibility(View.INVISIBLE);
		txtView.setVisibility(View.VISIBLE);

		ti = new Thread(new MyThread());

		sqlEngine = new sqlEngThread(new BallonHandlerYellow());
		sqlEngine.startThead();
		Log.v("sqlthread:", "start");
		ti.start();

		btnRestart.setOnClickListener(new View.OnClickListener() {// 重新选择按钮监听
			@Override
			// 将这个监听放在每个启动函数中，可以使重新开始变成每一个函数
			public void onClick(View v) {

				initYellowGame();
				// initRocGame();
				// flynu = 0;
				// initFlyGame();
				// flyRoc();

			}
		});
		
	}

	private void initGame1() {// 倒吹笑脸
		// TODO Auto-generated method stub
		level = 0;
		lesLen = 9;
		seLen = 10;
		
		if(preferences.contains("snum"))
		{
			score.setText("最好记录： "+preferences.getInt("snum", 0)+"个");
		}else{
			
			score.setText("最好记录： "+0+"个");
		}
						
		
		ballsum=0;
		imgshow.setBackgroundResource(yellow[0]);
		btnRestart.setVisibility(View.INVISIBLE);
		btnBack.setVisibility(View.INVISIBLE);
		showend.setVisibility(View.INVISIBLE);
		txtView.setVisibility(View.VISIBLE);

		ti = new Thread(new MyThread1());

		sqlEngine = new sqlEngThread(new YellowWhileHandler());
		sqlEngine.startThead();
		ti.start();

		btnRestart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				initGame1();
			}
		});

	}

	class YellowWhileHandler extends Handler {// 倒吹笑脸的实现
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				int d = msg.arg1;
				if (d >= 180) {
					count++;
					if (count >= 5) {
						level = level + 1;
						if (level <= 22) {
							imgshow.setBackgroundResource(yellow[level]);
							if (level == 22) {
								ballsum++;
								level = 0;
								imgshow.setBackgroundResource(yellow[level]);

								if (seLen <= 0) {

									soundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);		
									VibratorUtil.Vibrate(BallonGameActivity.this,100);
									
									if(!preferences.contains("snum"))
									{
										editor.putInt("snum", ballsum);										
										editor.commit();
									}
									else if(ballsum>preferences.getInt("snum", 0))
									{
										editor.putInt("snum", ballsum);										
										editor.commit();
									}
									
									sqlEngine.stopThead();
									btnRestart.setVisibility(View.VISIBLE);
									btnBack.setVisibility(View.VISIBLE);
									showend.setVisibility(View.VISIBLE);
									txtView.setVisibility(View.INVISIBLE);

									showend.setText("Yes！您吹了 " + ballsum
											+ "个球！");
									imgshow.setBackgroundResource(yellow[level]);
									sqlEngine = null;
									ti = null;
								}
							}
						}
						count = 0;
					}
				}

			}
		}

	}

	class BallonHandlerYellow extends Handler {// 正吹笑脸的实现
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				int d = msg.arg1;
				if (d >= 180) {
					count++;
					if (count >= 5) {
						level++;
						if (level <= 22) {
							imgshow.setBackgroundResource(yellow[level]);
							if (level == 22) {

						soundPool.play(soundMap.get(1), 1, 1, 0, 0, 1);		
						VibratorUtil.Vibrate(BallonGameActivity.this,100);
								 
						if(!preferences.contains("smin"))
						{
							editor.putInt("smin", se);
							editor.putInt("ss", recLen);
							editor.commit();
						}
						else if(se<min){
							editor.putInt("smin", se);
							editor.putInt("ss", recLen);
							editor.commit();
						}else if(se == min && recLen<s)
						{
							editor.putInt("smin", se);
							editor.putInt("ss", recLen);
							editor.commit();
						}
						
								sqlEngine.stopThead();
								btnRestart.setVisibility(View.VISIBLE);
								btnBack.setVisibility(View.VISIBLE);
								showend.setVisibility(View.VISIBLE);
								txtView.setVisibility(View.INVISIBLE);

								showend.setText("Wow! 您用了" + se + "." + recLen
										+ "秒！");
								sqlEngine = null;
								ti = null;

							}
						}
						count = 0;
					}
				}
			}
		}
	}

	class BallonHandlerRoc extends Handler {// 正吹火箭的实现
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				int d = msg.arg1;
				if (d >= 180) {
					count++;
					if (count >= 5) {
						level++;
						if (level <= 26) {
							imgshow.setBackgroundResource(roc[level]);
							if (level == 26) {

								sqlEngine.stopThead();
								btnRestart.setVisibility(View.VISIBLE);
								btnBack.setVisibility(View.VISIBLE);
								showend.setVisibility(View.VISIBLE);
								txtView.setVisibility(View.INVISIBLE);

								showend.setText("Wow! 您用了" + se + "." + recLen
										+ "秒！");
								sqlEngine = null;
								ti = null;

							}
						}
						count = 0;
					}
				}
			}
		}
	}

	class BallonHandlerFly extends Handler {// 火箭飞的实现
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				int d = msg.arg1;
				if (d >= 180) {
					count++;
					if (count >= 5) {
						level++;
						if (level <= 26) {
							imgshow.setBackgroundResource(roc[level]);
							if (level == 26) {

								imgshow.setBackgroundResource(R.drawable.bfly1);
								curX = imgshow.getX();
								initY = curY = imgshow.getY();
								nextX = curX;

								soundPool.play(soundMap.get(4), 1, 1, 0, 0, 1);		
								VibratorUtil.Vibrate(BallonGameActivity.this,200);
								
								final Handler bflyhandler = new Handler() {									
									@Override
									public void handleMessage(Message msg) {
										if (msg.what == 0x123) {
											// 横向上一直向右飞
											if (nextY < -600) {
												curY = nextY = initY;
											} else {
												nextY -= 8;
											}											
											TranslateAnimation anim = new TranslateAnimation(
													curX, nextX, curY, nextY);
											curX = nextX;
											curY = nextY;
											anim.setDuration(40);
											// 开始位移动画
											imgshow.startAnimation(anim); // ①

										}
										showend.setVisibility(View.VISIBLE);
										showend.setText("好吧好吧，火箭你都吹起来了。你这家伙太能吹了……就和火箭一起飞吧~");
									}
								};
																
								new Timer().schedule(new TimerTask()
								{
									@Override
									public void run()
									{
										if(nextY>-599)
										bflyhandler.sendEmptyMessage(0x123);
									}

								}, 0, 40);
								
								
								// imgshow.setBackgroundResource(R.anim.fat_po);
								// anim = (AnimationDrawable)
								// imgshow.getBackground();
								// anim.start();
								sqlEngine.stopThead();
								sqlEngine = null;

							}
						}
						count = 0;
					}
				}
			}
		}
	}

	class BallonHandlerHot extends Handler {// 正吹热气球的实现
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				int d = msg.arg1;
				if (d >= 180) {
					count++;
					if (count >= 12) {
						level++;
						if (level <= 22) {
							imgshow.setBackgroundResource(hot[level]);
							if (level == 22) {

								soundPool.play(soundMap.get(3), 1, 1, 0, 0, 1);		
								VibratorUtil.Vibrate(BallonGameActivity.this,100);
								
								if(!preferences.contains("hmin"))
								{
									editor.putInt("hmin", se);
									editor.putInt("hs", recLen);
									editor.commit();
								}
								else if(se<min){
									editor.putInt("hmin", se);
									editor.putInt("hs", recLen);
									editor.commit();
								}else if(se == min && recLen<s)
								{
									editor.putInt("hmin", se);
									editor.putInt("hs", recLen);
									editor.commit();
								}
								
								sqlEngine.stopThead();
								btnRestart.setVisibility(View.VISIBLE);
								btnBack.setVisibility(View.VISIBLE);
								showend.setVisibility(View.VISIBLE);
								txtView.setVisibility(View.INVISIBLE);

								showend.setText("    我滴神呐!!\n你竟然吹起了一个热气球！ 只用了"
										+ se + "." + recLen + "秒！");
								sqlEngine = null;
								ti = null;

							}
						}
						count = 0;
					}
				}
			}
		}
	}

	final Handler handler = new Handler() { // 10秒倒计时
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				recLen++;
				if (recLen == 10) {
					se++;
					recLen = 0;
				}
				txtView.setText(se + ":" + recLen);

			}
			super.handleMessage(msg);
		}
	};

	class RocThread implements Runnable { // 正吹火箭的计时
		@Override
		public void run() {
			while (level < 28) {
				try {
					Thread.sleep(10);
					Message message = new Message();
					message.what = 1;
					handler.sendMessage(message);
				} catch (Exception e) {
				}
			}
		}
	}

	class MyThread implements Runnable { // 正吹线程
		@Override
		public void run() {
			while (level < 22) {
				try {
					Thread.sleep(100);
					Message message = new Message();
					message.what = 1;
					handler.sendMessage(message);
				} catch (Exception e) {
				}
			}
		}
	}

	final Handler handler1 = new Handler() { // 倒吹的计时
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				lesLen--;
				if (lesLen == 0) {
					seLen--;
					lesLen = 9;
				}
				txtView.setText(seLen + ":" + lesLen);
			}

		}
	};

	class MyThread1 implements Runnable { // 倒吹的线程
		@Override
		public void run() {
			while (seLen > 0) {
				try {
					Thread.sleep(100); // sleep 1000ms
					Message message = new Message();
					message.what = 1;
					handler1.sendMessage(message);
				} catch (Exception e) {
				}
			}
		}
	}

}
