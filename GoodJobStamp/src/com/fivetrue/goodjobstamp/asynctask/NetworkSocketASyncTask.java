package com.fivetrue.goodjobstamp.asynctask;

import java.net.Socket;

import com.fivetrue.goodjobstamp.basemodel.BaseNetworkListener;

public class NetworkSocketASyncTask extends NetworkBaseASyncTask{

	private Socket mSocket = null;
	private BaseNetworkListener mListener = null;
	
	private NetworkSocketASyncTask(Socket socket){
		
	}
	
	static public NetworkSocketASyncTask getInstance(Socket socket){
		return new NetworkSocketASyncTask(socket);
	}
	
	
	@Override
	public void sendMessage(String msg, BaseNetworkListener listener) {
		// TODO Auto-generated method stub
		if(msg != null && listener != null && this.mSocket != null){
			mListener = listener;
			this.execute(msg);
		}else{
			if(listener != null)
				mListener.onError("Parameter is null");
		}
	}
	
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		if(result != null){
			mListener.onReceiveMessage(result);
		}
	}
	
	

}
