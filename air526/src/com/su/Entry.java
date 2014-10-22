package com.su;

import com.tu.MusicServer;

import android.app.Activity;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;


public class Entry extends Activity implements OnGestureListener, android.view.GestureDetector.OnGestureListener {

	private int verticalMinDistance = 20;
    private int minVelocity         = 0;
	GestureDetector detector;
	//Intent intentMusic = new Intent(Entry.this, MusicServer.class);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry);
		
		
		startService(new Intent(Entry.this, MusicServer.class));
		
		detector = new GestureDetector(this,this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent me)
	{
		return detector.onTouchEvent(me);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.entry, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGesture(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		
		 if (e1.getX() - e2.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {

	        	Intent intent = new Intent();

				//intent.setAction("scos.intent.action.MAIN");
				//intent.addCategory("scos.intent.category.LAUNCHER");

				//Bundle data = new Bundle();
				//String str = "FromEntry";
				//data.putString("FromEntry", str);
				//intent.putExtras(data);
				intent.setClass(Entry.this, MainScreenActivity.class);

				startActivity(intent);
	            
	        } else if (e2.getX() - e1.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {
	                     
	        }

	        return false;
	}
	@Override
	 protected void onStop() {
	  // TODO Auto-generated method stub
	  Intent intent = new Intent(Entry.this,MusicServer.class);
	  stopService(intent);
	  super.onStop();
	 }
}
