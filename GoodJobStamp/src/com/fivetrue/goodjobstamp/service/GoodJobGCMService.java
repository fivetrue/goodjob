package com.fivetrue.goodjobstamp.service;

import com.fivetrue.goodjobstamp.common.Config;
import com.fivetrue.goodjobstamp.converter.GcmConverter;
import com.fivetrue.goodjobstamp.entry.MainEntry;
import com.fivetrue.goodjobstamp.receiver.GcmBroadcastReceiver;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class GoodJobGCMService extends IntentService {
	
	private final int INVALID_MESSAGE = -1;
	private final int MESSAGE_NOTIFY = 0x00;
	private final int MESSAGE_RECEIVE_DATA = 0x01;

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
			int result = onReceive(intent);
			log(result);
		}
        
        GcmBroadcastReceiver.completeWakefulIntent(intent);
	}
	
	private int onReceive(Intent intent){
		
		final int msg = getMessageType(intent) ;
		
		switch(msg){
		
		case MESSAGE_RECEIVE_DATA :
		{
			MainEntry entry = new GcmConverter().onReceive(getContent(intent));
			if(entry != null){
				log(entry.toString());
			}
			return msg;
		}
		
		case MESSAGE_NOTIFY :
		{
			
			return msg;
		}
			
		
		}
		return INVALID_MESSAGE;
	}
	
	
	private int getMessageType(Intent intent){
		if(intent != null){
			Bundle b = intent.getExtras();
			if(b == null)
				return INVALID_MESSAGE;
			
			if(b.getString(Config.GCM.MESSAGE_TYPE).equals(Config.GCM.MESSAGE_DATA)){
				return MESSAGE_RECEIVE_DATA;
			}else if(b.getString(Config.GCM.MESSAGE_TYPE).equals(Config.GCM.MESSAGE_NOTIFY)){
				return MESSAGE_NOTIFY;
			}
			
		}
		return INVALID_MESSAGE;
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
	
	private void log(Object obj){
		System.out.println("ojkwon : " + obj);
	}

}
