package com.fivetrue.goodjobstamp;

import com.fivetrue.goodjobstamp.asynctask.NetworkGcmAsyncTask;
import com.fivetrue.goodjobstamp.basemodel.BaseNetworkListener;
import com.fivetrue.goodjobstamp.common.pref.UserPrefrenceManager;
import com.fivetrue.goodjobstamp.database.StampDB;
import com.fivetrue.goodjobstamp.database.StampDB.StampDBListener;
import com.fivetrue.goodjobstamp.message.GcmMessage;
import com.fivetrue.goodjobstamp.vo.StampVO;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class BaseActivity extends FragmentActivity {

	protected UserPrefrenceManager mPref = null;
	protected StampDB mStampDB = null;
	private GoogleCloudMessaging Gcm = null;
	
	private StampDBListener mStampListener = new StampDBListener() {
		
		@Override
		public void onError(String error) {
			// TODO Auto-generated method stub
			toast(error);
		}

		@Override
		public void insertRow(long row, StampVO data) {
			// TODO Auto-generated method stub
			log(row + " = " + data.toString());
			GcmMessage msg = new GcmMessage();
			msg.setReceiver(mPref.getGcmDeviceId());
			msg.setJsonString(data);
			NetworkGcmAsyncTask.getInstance(Gcm).sendMessage(msg, new BaseNetworkListener<GcmMessage>() {
				
				@Override
				public void onTimeout(GcmMessage obj) {
					// TODO Auto-generated method stub
					log(obj.toString());
				}
				
				@Override
				public void onReceiveMessage(GcmMessage obj) {
					// TODO Auto-generated method stub
					log(obj.toString());
				}
				
				@Override
				public void onError(GcmMessage obj) {
					// TODO Auto-generated method stub
					log(obj.toString());
				}
			});
			
		}

		@Override
		public void deleteRow(long row, StampVO data) {
			// TODO Auto-generated method stub
			
		}
		
	};
	

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		init();
		registerGcm();
	}

	public GoogleCloudMessaging getGcm() {
		return Gcm;
	}

	public void setGcm(GoogleCloudMessaging gcm) {
		Gcm = gcm;
	}


	protected void init(){
		Gcm = GoogleCloudMessaging.getInstance(this);
		mPref = new UserPrefrenceManager(this);
		mStampDB = new StampDB(this);
		mStampDB.setListener(mStampListener);
	}

	protected void registerGcm(){

		if(mPref.getGcmDeviceId().equals("")){
			NetworkGcmAsyncTask.getInstance(getGcm()).register(new BaseNetworkListener<GcmMessage>(){

				@Override
				public void onTimeout(GcmMessage obj) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onReceiveMessage(GcmMessage obj) {
					// TODO Auto-generated method stub
					log(obj.toString());
					toast(obj.toString());
					mPref.setGcmDeviceId(obj.getMessageId());
				}

				@Override
				public void onError(GcmMessage obj) {
					// TODO Auto-generated method stub
					log(obj.toString());
					toast(obj.toString());
				}
			});

		}
	}

	protected void log(Object obj){
		System.out.println("ojkwon : " + obj);
	}

	protected void toast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	protected void toast(int resId){
		Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
	}
	
}
