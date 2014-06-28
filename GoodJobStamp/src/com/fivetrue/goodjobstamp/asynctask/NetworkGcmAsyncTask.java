package com.fivetrue.goodjobstamp.asynctask;

import java.io.IOException;

import com.fivetrue.goodjobstamp.basemodel.BaseNetworkListener;
import com.fivetrue.goodjobstamp.common.Config;
import com.fivetrue.goodjobstamp.message.GcmMessage;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.gson.Gson;

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
		if(params != null){
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
				GcmMessage obj = params[0];
				
				try {
					Sender sender = new Sender(Config.GOOGLE_GCM_API_KEY);
					Message message = new Message.Builder()
					// GCM 서버에서 중복 메시지를 보낼 경우 일부 수신이 안될 수 있다.
					.collapseKey(String.valueOf(Math.random() % 100 + 1))
					.addData(Config.GCM.MESSAGE_TYPE, Config.GCM.MESSAGE_SEND)
					.addData(Config.GCM.CONTENT, obj.getJson())
					.addData(Config.GCM.CLASS_NAME, obj.getTargetClassName())
					.delayWhileIdle(false)
					.timeToLive(obj.getTimeToLive())
					.build();
					// 3번째 인자는 메시지 전송실패시 재시도 횟수
					Result result = sender.send(message, obj.getReceiver(), 3);
					if (result.getMessageId() != null) {
						System.out.println("ojkwon : Send success");
					} else {
						String error = result.getErrorCodeName();
						System.out.println("Send fail : " + error);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					obj.setErrorMessage("Gcm Message Send Error");
					mListener.onError(obj);
				}
				return obj;
			}
			
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
