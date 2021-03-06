package com.su;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;

public class sqlEngThread extends Thread {
	private static final double Po = 2.0E-006D;
	private static int SAMPLE_RATE_IN_HZ = 8000;// 44100
	private boolean bRunning = true;
	private Handler handle;
	private Handler time;
	private AudioRecord recordInstance;
	private int sqlValue = 0;

	public sqlEngThread(Handler ParamHandler) {
		this.handle = ParamHandler;
	
	}

	public sqlEngThread(Handler ParamHandler, Handler TimeHandler) {
		this.handle = ParamHandler;
		this.time = TimeHandler;
	}
	
	
	@Override
	public void run() {
		int bs = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ,
				AudioFormat.CHANNEL_CONFIGURATION_MONO,
				AudioFormat.ENCODING_PCM_16BIT);
		recordInstance = new AudioRecord(MediaRecorder.AudioSource.MIC,
				SAMPLE_RATE_IN_HZ, AudioFormat.CHANNEL_CONFIGURATION_MONO,
				AudioFormat.ENCODING_PCM_16BIT, bs);
		recordInstance.startRecording();
		short[] data = new short[bs];
		long sum = 0;
		while (bRunning) {
			try {
				int m = recordInstance.read(data, 0, bs);
				sum = 0;
				for (int i = 0; i < m; i++) {
					sum += data[i] * data[i];
				}
				//sqlValue = (int) (Math.log10(Math.sqrt(sum / m) / Po)) * 20;	
				sqlValue = (int) (Math.log10(sum / m)) * 10;
				//sqlValue = (int) (Math.abs((int)(sum /(float)m)/10000) >> 1);
				//10*Math.log10(v/(double)r);
				Message msg = handle.obtainMessage();
				msg.what = 1;
				msg.arg1 = sqlValue;
				handle.sendMessage(msg);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void startThead() {
		this.bRunning = true;
		start();
	}

	public void stopThead() {
		try {
			this.bRunning = false;
			stop();
			/*if (this.recordInstance != null) {
				this.recordInstance.stop();
				this.recordInstance.release();
				this.recordInstance = null;
			}*/
			if (recordInstance != null) {
				recordInstance.stop();
				recordInstance.release();
				recordInstance = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
