package com.fivetrue.goodjobstamp.asynctask;

import java.io.IOException;

import com.fivetrue.goodjobstamp.basemodel.BaseNetworkListener;
import com.fivetrue.goodjobstamp.message.GcmMessage;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class NetworkGcmAsyncTask extends NetworkBaseASyncTask {
	GoogleCloudMessaging mGcm = null;
	BaseNetworkListener mListener = null;
	
	static public NetworkGcmAsyncTask getInstance(GoogleCloudMessaging gcm){
		return new NetworkGcmAsyncTask(gcm);
	}
	
	private NetworkGcmAsyncTask(GoogleCloudMessaging gcm){
		this.mGcm = gcm;
	}
	
	@Override
	public void sendMessage(Object msg, BaseNetworkListener listener) {
		// TODO Auto-generated method stub
		if(listener != null){
			mListener = listener;
			if(msg != null){
				this.execute(msg);
			}else{
				mListener.onError("Message is Null");
			}
		}
	}

	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		GcmMessage msg = (GcmMessage) params[0];
		
		try {
			mGcm.send(msg.getReceiver(), msg.getMessageId(), msg.getTimeToLive(), msg.getBundleData());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mListener.onError("Gcm Message Send Error");
		}
		return msg;
	}
	
	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if(result != null){
			GcmMessage msg = (GcmMessage) result;
			mListener.onReceiveMessage(msg);
		}
		
	}

}
