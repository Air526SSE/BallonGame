package com.tu;

import com.su.R;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;

public class NewMusicServer extends IntentService {

	private MediaPlayer mp;

	public NewMusicServer() {
		super("NewMusicServer");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		mp.stop();
		mp.release();
		mp = null;
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub

		mp = new MediaPlayer();
		// 将音乐保存在res/raw/xingshu.mp3,R.java中自动生成{public static final int
		// xingshu=0x7f040000;}
		mp = MediaPlayer.create(NewMusicServer.this, R.raw.ab);
		// 在MediaPlayer取得播放资源与stop()之后要准备PlayBack的状态前一定要使用MediaPlayer.prepeare()
		mp.prepareAsync();
		mp.start();

		// 音乐播放完毕的事件处理
		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				// 循环播放
				try {
					if (mp != null) {

						mp.stop();
						mp=null;
					}
					mp = new MediaPlayer();
					mp = MediaPlayer.create(NewMusicServer.this, R.raw.ab);
					mp.prepareAsync();
					mp.start();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {

			public boolean onError(MediaPlayer mp, int what, int extra) {
				// TODO Auto-generated method stub
				// 释放资源
				try {
					mp.stop();
					mp.release();
					mp = null;
				} catch (Exception e) {
					e.printStackTrace();
				}

				return false;
			}
		});

	}

}
