/*package com.fivetrue.goodjobstamp.asynctask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.fivetrue.goodjobstamp.basemodel.BaseNetworkListener;

public class NetworkSocketASyncTask extends NetworkBaseASyncTask{

	private Socket mSocket = null;
	private OutputStream mOutput = null;
	private InputStream mInput = null;
	private PrintWriter mWriter = null;
	private BufferedReader mBufferedReader = null;
	private BaseNetworkListener mListener = null;
	
	private NetworkSocketASyncTask(Socket socket){
		mSocket = socket;
	}
	
	static public NetworkSocketASyncTask getInstance(Socket socket){
		return new NetworkSocketASyncTask(socket);
	}
	
	
	@Override
	public void sendMessage(Object msg, BaseNetworkListener listener) {
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
		try {
			mOutput = mSocket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mListener.onError("Socket OutputStream Error");
		}
		
		try {
			mInput = mSocket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mListener.onError("Socket InputStream Error");
		}
		
		
		mWriter = new PrintWriter(new OutputStreamWriter(mOutput));
		mBufferedReader = new BufferedReader(new InputStreamReader(mInput));
		
		mWriter.print((String)params[0]);
		mWriter.flush();
		Object receive = null;
		try {
			receive = mBufferedReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mListener.onError("Socket Read Error");
		}
		return receive;
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
*/