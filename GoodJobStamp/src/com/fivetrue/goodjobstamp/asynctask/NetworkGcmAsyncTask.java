package com.fivetrue.goodjobstamp.asynctask;

import java.io.IOException;

import com.fivetrue.goodjobstamp.basemodel.BaseNetworkListener;
import com.fivetrue.goodjobstamp.common.Config;
import com.fivetrue.goodjobstamp.message.GcmMessage;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class NetworkGcmAsyncTask extends NetworkBaseASyncTask <GcmMessage> {
	private final int INVALIDE_MESSAGE = -1;
	
	private final int GCM_REGISTER = 0x00;
	private final int GCM_SEND = 0x01;
	private int GCM_MESSAGE = INVALIDE_MESSAGE;
	
	GoogleCloudMessaging mGcm = null;
	BaseNetworkListener<GcmMessage> mListener = null;
	
	static public NetworkGcmAsyncTask getInstance(GoogleCloudMessaging gcm){
		return new NetworkGcmAsyncTask(gcm);
	}
	
	private NetworkGcmAsyncTask(GoogleCloudMessaging gcm){
		this.mGcm = gcm;
	}
	
	public void register(BaseNetworkListener<GcmMessage> listener){
		if(listener != null){
			GCM_MESSAGE = GCM_REGISTER;
			mListener = listener;
			this.execute();
		}
	}
	
	@Override
	public void sendMessage(GcmMessage msg, BaseNetworkListener<GcmMessage> listener) {
		// TODO Auto-generated method stub
		if(listener != null){
			GCM_MESSAGE = GCM_SEND;
			mListener = listener;
			if(msg != null){
				this.execute(msg);
			}else{
				GcmMessage gcm = new GcmMessage();
				gcm.setErrorMessage("Message is Null");
				mListener.onError(gcm);
			}
		}
	}

	@Override
	protected GcmMessage doInBackground(GcmMessage... params) {
		// TODO Auto-generated method stub
		
		switch(GCM_MESSAGE){
		case GCM_REGISTER :
			GcmMessage gcm = new GcmMessage();
			try {
				gcm.setMessageId(mGcm.register(Config.GOOGLE_PROJECT_ID)); 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				gcm.setErrorMessage("Gcm Register Error");
				mListener.onError(gcm);
			}
			return gcm;
			
		case GCM_SEND :
			try {
				mGcm.send(params[0].getReceiver(), params[0].getMessageId(),
						params[0].getTimeToLive(), params[0].getBundleData());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				params[0].setErrorMessage("Gcm Message Send Error");
				mListener.onError(params[0]);
			}
			return params[0];
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(GcmMessage result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if(result != null){
			switch(GCM_MESSAGE){
			case GCM_REGISTER : 
				mListener.onReceiveMessage(result);
				break;
				
			case GCM_SEND : 
				mListener.onReceiveMessage(result);
				break;
			}
			
		}
		
	}

}
