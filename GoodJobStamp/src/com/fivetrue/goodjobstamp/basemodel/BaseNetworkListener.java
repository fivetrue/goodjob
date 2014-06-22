package com.fivetrue.goodjobstamp.basemodel;

public interface BaseNetworkListener <T> {
	
	public void onReceiveMessage(T obj);
	public void onError(T obj);
	public void onTimeout(T obj);

}
