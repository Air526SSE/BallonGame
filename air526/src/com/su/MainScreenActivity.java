package com.su;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.tu.Balloon;
import com.tu.MusicServer;

public class MainScreenActivity extends Activity {

	Button start;
	Button cancle;
	Button about;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		
		
		//startService(new Intent(MainScreenActivity.this, MusicServer.class));
	
		start = (Button)findViewById(R.id.button3);
		cancle = (Button)findViewById(R.id.button2);
		about = (Button)findViewById(R.id.button1);
		
		start.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent();
				intent.setClass(MainScreenActivity.this, Balloon.class);
				startActivity(intent);
				
			}
		});
		
		cancle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*finish();
				System.exit(0);
				android.os.Process.killProcess(android.os.Process.myPid()); 
				ActivityManager activityMgr= (ActivityManager) getSystemService(ACTIVITY_SERVICE );  
	            activityMgr.killBackgroundProcesses(getPackageName());*/
	            
	            /*int currentVersion = android.os.Build.VERSION.SDK_INT;
	            if (currentVersion > android.os.Build.VERSION_CODES.ECLAIR_MR1) {
	            Intent startMain = new Intent(Intent.ACTION_MAIN);
	            startMain.addCategory(Intent.CATEGORY_HOME);
	            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(startMain);
	            System.exit(0);
	            } else {// android2.1
	            ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
	            am.restartPackage(getPackageName());
	            	System.exit(0);
	            
	            }*/
				System.exit(0);
			}
		});
		
		about.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_screen, menu);
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
	/*@Override
	 protected void onStop() {
	  // TODO Auto-generated method stub
	  Intent intent = new Intent(MainScreenActivity.this,MusicServer.class);
	  stopService(intent);
	  super.onStop();
	 }*/
}
