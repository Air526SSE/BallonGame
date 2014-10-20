package com.su;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tu.Balloon;

public class BallonGameActivity extends Activity {
	private ImageView imgshow;
	private Button btnRestart;
	private Button btnBack;
	private int level = 0, count = 0;
	private int[] imgs = { R.drawable.balloon0, R.drawable.balloon1,
			R.drawable.balloon2, R.drawable.balloon3, R.drawable.balloon4,
			R.drawable.balloon5, R.drawable.balloon6, R.drawable.balloon7,
			R.drawable.baopo3_1 };

	private int[] yellow = { R.drawable.yellow0, R.drawable.yellow1,
			R.drawable.yellow2, R.drawable.yellow3, R.drawable.yellow4,
			R.drawable.yellow5, R.drawable.yellow6, R.drawable.yellow7,
			R.drawable.yellow8, R.drawable.yellow9, R.drawable.yellow10,
			R.drawable.yellow11, R.drawable.yellow12, R.drawable.yellow13,
			R.drawable.yellow14, R.drawable.yellow15, R.drawable.yellow16,
			R.drawable.yellow17, R.drawable.yellow18, R.drawable.yellow19,
			R.drawable.yellow20, R.drawable.yellow21, R.drawable.yellow22,
			R.drawable.yellow23, R.drawable.yellow24, R.drawable.yellow25,
			R.drawable.yellow26, R.drawable.yellow27, R.drawable.yellow28,
			R.drawable.yellow29, R.drawable.yellow30, R.drawable.yellow31,
			R.drawable.yellow32, R.drawable.yellow33, R.drawable.yellow34,
			R.drawable.yellow35, R.drawable.yellow36, R.drawable.yellow37,
			R.drawable.yellow38, R.drawable.yellow39, R.drawable.yellow40,
			R.drawable.yellow41, R.drawable.yellow42, R.drawable.yellow43,
			R.drawable.yellow44, R.drawable.yellow45, R.drawable.yellow46,
			R.drawable.yellow47, R.drawable.yellow48, R.drawable.yellow49,
			R.drawable.yellow50, R.drawable.baopo3_1 };
	private sqlEngThread sqlEngine;

	private int recLen = 0;
	private int lesLen = 10;
	private int se = 0;

	public int ballsum = 0;

	private String acolor = new String();
	private String amode = new String();

	private TextView txtView;
	private TextView showend;
	private Thread ti;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		imgshow = (ImageView) findViewById(R.id.imgshow);
		btnRestart = (Button) findViewById(R.id.btnRestart);
		btnBack = (Button) findViewById(R.id.btnBack);
		txtView = (TextView) findViewById(R.id.show_time);
		showend = (TextView) findViewById(R.id.show_end);

		Intent intent = getIntent();
		if (intent.getStringExtra("color") != null) {
			acolor = intent.getStringExtra("color");
		}

		if (intent.getStringExtra("mode") != null) {
			amode = intent.getStringExtra("mode");
		}

		if (amode.equals("正计时")) {
			ti = new Thread(new MyThread());
			ti.start();
			initGame();
			btnRestart.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					recLen = 0;
					ti = new Thread(new MyThread());
					ti.start();
					initGame();

				}
			});
		}

		if (amode.equals("倒计时")) {
			ti = new Thread(new MyThread1());
			ti.start();
			initGame1();
			btnRestart.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					lesLen = 10;
					ballsum = 0;
					ti = new Thread(new MyThread1());
					ti.start();
					initGame1();
				}
			});
		}

		btnBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent();
				intent.setClass(BallonGameActivity.this, Balloon.class);
				startActivity(intent);
			}
		});

	}

	private void initGame() {
		// TODO Auto-generated method stub
		level = 0;
		recLen = 0;
		se = 0;

		imgshow.setBackgroundResource(yellow[0]);
		btnRestart.setVisibility(View.INVISIBLE);
		btnBack.setVisibility(View.INVISIBLE);
		showend.setVisibility(View.INVISIBLE);
		txtView.setVisibility(View.VISIBLE);

		// this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		sqlEngine = new sqlEngThread(new BallonHandlerYellow());
		sqlEngine.startThead();
	}

	private void initGame1() {
		// TODO Auto-generated method stub
		level = 0;
		lesLen = 10;

		imgshow.setBackgroundResource(imgs[0]);
		btnRestart.setVisibility(View.INVISIBLE);
		btnBack.setVisibility(View.INVISIBLE);
		showend.setVisibility(View.INVISIBLE);

		sqlEngine = new sqlEngThread(new BallonWhileHandler());
		sqlEngine.startThead();

	}

	class BallonWhileHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1) {

				int d = msg.arg1;
				if (d >= 180) {
					count++;
					if (count >= 5) {
						level = level + 1;
						if (level <= 8) {
							imgshow.setBackgroundResource(imgs[level]);
							if (level == 8) {
								ballsum++;
								level = 0;
								imgshow.setBackgroundResource(imgs[level]);
								// imgshow.setBackgroundResource(imgs[++level]);
								if (lesLen <= 0) {
									sqlEngine.stopThead();
									btnRestart.setVisibility(View.VISIBLE);
									btnBack.setVisibility(View.VISIBLE);
									showend.setVisibility(View.VISIBLE);
									showend.setText("恭喜您！您破了 " + ballsum
											+ "个球！");
									sqlEngine = null;
								}
							}
						}
						count = 0;
					}
				}

			}
		}

	}

	class BallonHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				int d = msg.arg1;
				if (d >= 180) {
					count++;
					if (count >= 5) {
						level = level + 1;
						if (level <= 8) {
							imgshow.setBackgroundResource(imgs[level]);
							if (level == 8) {

								sqlEngine.stopThead();
								btnRestart.setVisibility(View.VISIBLE);
								btnBack.setVisibility(View.VISIBLE);
								showend.setVisibility(View.VISIBLE);
								showend.setText("恭喜！你用了 " + (++recLen) + "秒！");
								sqlEngine = null;
							}
						}
						count = 0;
					}
				}
			}
		}
	}

	class BallonHandlerYellow extends Handler {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				int d = msg.arg1;
				if (d >= 180) {
					count++;
					if (count >= 3) {
						level = level + 1;
						if (level <= 51) {
							imgshow.setBackgroundResource(yellow[level]);
							//imgshow.setBackgroundResource(yellow[++level]);
							if (level == 51) {

								sqlEngine.stopThead();
								btnRestart.setVisibility(View.VISIBLE);
								btnBack.setVisibility(View.VISIBLE);
								showend.setVisibility(View.VISIBLE);
								txtView.setVisibility(View.INVISIBLE);
								showend.setText("Wow! 您用了" + se + "." + recLen
										+ "秒！");
								sqlEngine = null;
							}
						}
						count = 0;
					}
				}
			}
		}
	}

	final Handler handler = new Handler() { // handle
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

	class MyThread implements Runnable { // thread
		@Override
		public void run() {
			while (level<51) {
				try {
					Thread.sleep(100); // sleep 1000ms
					Message message = new Message();
					message.what = 1;
					handler.sendMessage(message);
				} catch (Exception e) {
				}
			}
		}
	}

	final Handler handler1 = new Handler() { // handle
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				lesLen--;
				txtView.setText("" + lesLen);
			}

		}
	};

	class MyThread1 implements Runnable { // thread
		@Override
		public void run() {

			while (lesLen > 0) {
				try {
					Thread.sleep(1000); // sleep 1000ms
					Message message = new Message();
					message.what = 1;
					handler1.sendMessage(message);
				} catch (Exception e) {
				}
			}
		}
	}

}
