package com.fivetrue.goodjobstamp.service;

import org.json.JSONException;
import org.json.JSONObject;

import com.fivetrue.goodjobstamp.basemodel.BaseVO;
import com.fivetrue.goodjobstamp.common.Config;
import com.fivetrue.goodjobstamp.converter.GcmConverter;
import com.fivetrue.goodjobstamp.entry.GcmEntry;
import com.fivetrue.goodjobstamp.receiver.GcmBroadcastReceiver;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.gson.Gson;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class GoodJobGCMService extends IntentService {

	public GoodJobGCMService() {
		super("GoodJobGCMService");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        final String messageType = gcm.getMessageType(intent);
      
        if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
			// It's an error.
			Toast.makeText(getApplicationContext(), "MESSAGE_TYPE_SEND_ERROR", Toast.LENGTH_SHORT).show();
		} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
			// Deleted messages on the server.
			Toast.makeText(getApplicationContext(), "MESSAGE_TYPE_DELETED", Toast.LENGTH_SHORT).show();
		} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
			// It's a regular GCM message, do some work.
			
			GcmEntry entry = new GcmConverter().onReceive(getContent(intent));
			if(entry != null){
				System.out.println("ojkwon : onHandleIntent entry = " + entry.toString());
			}

			System.out.println("ojkwon : onHandleIntent getMessageType = " + getMessageType(intent));
			System.out.println("ojkwon : onHandleIntent getContent = " + getContent(intent));

			
		}
        GcmBroadcastReceiver.completeWakefulIntent(intent);
	}
	
	
	private String getMessageType(Intent intent){
		if(intent != null){
			Bundle b = intent.getExtras();
			if(b == null)
				return null;
			
			return b.getString(Config.GCM.MESSAGE_TYPE);
		}
		return null;
	}
	
	private String getContent(Intent intent){
		if(intent != null){
			Bundle b = intent.getExtras();
			if(b == null)
				return null;
			
			return b.getString(Config.GCM.CONTENT);
		}
		return null;
	}
	private String getClassName(Intent intent){
		if(intent != null){
			Bundle b = intent.getExtras();
			if(b == null)
				return null;
			
			return b.getString(Config.GCM.CLASS_NAME);
		}
		return null;
	}
	

}
