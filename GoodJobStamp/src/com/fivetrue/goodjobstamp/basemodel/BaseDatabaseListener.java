package com.fivetrue.goodjobstamp.basemodel;

public interface BaseDatabaseListener<T> {
	public void insertRow(long row, T data);
	public void deleteRow(long row, T data);
	public void onError(String error);
}
