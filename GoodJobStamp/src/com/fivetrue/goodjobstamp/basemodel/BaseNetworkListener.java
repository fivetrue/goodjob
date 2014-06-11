package com.fivetrue.goodjobstamp.basemodel;

public interface BaseNetworkListener {
	
	public void onReceiveMessage(Object obj);
	public void onError(Object obj);
	public void onTimeout(Object obj);

}
