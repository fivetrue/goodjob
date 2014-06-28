package com.fivetrue.goodjobstamp.receiver;

import com.fivetrue.goodjobstamp.service.GoodJobGCMService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver  {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		/*String messageType = initMessageGCM(context, intent);
		// Filter messages based on message type. It is likely that GCM will be extended in the future
		// with new message types, so just ignore message types you're not interested in, or that you
		// don't recognize.
		*/
		 ComponentName comp = new ComponentName(context.getPackageName(),
	                GoodJobGCMService.class.getName());
	     // Start the service, keeping the device awake while it is launching.
	     startWakefulService(context, (intent.setComponent(comp)));
	     setResultCode(Activity.RESULT_OK);
	}
	

}
