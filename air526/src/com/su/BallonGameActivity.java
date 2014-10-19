package com.su;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
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
	private sqlEngThread sqlEngine;

	private int recLen = 0;
	private int lesLen = 10;
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
					recLen=0;
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
					lesLen=10;
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

		imgshow.setBackgroundResource(imgs[0]);
		btnRestart.setVisibility(View.INVISIBLE);
		btnBack.setVisibility(View.INVISIBLE);
		showend.setVisibility(View.INVISIBLE);

		sqlEngine = new sqlEngThread(new BallonHandler());
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
								//imgshow.setBackgroundResource(imgs[++level]);
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

	final Handler handler = new Handler() { // handle
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				recLen++;
				txtView.setText("" + recLen);
			}
			super.handleMessage(msg);
		}
	};

	class MyThread implements Runnable { // thread
		@Override
		public void run() {
			while (level < 8) {
				try {
					Thread.sleep(1000); // sleep 1000ms
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
