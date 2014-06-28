package com.fivetrue.goodjobstamp.basemodel;

public interface BaseConverter<T> {
	
	public T onReceive(String obj);
	
}
