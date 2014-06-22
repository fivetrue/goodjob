package com.fivetrue.goodjobstamp.database;

import java.util.ArrayList;

import android.database.sqlite.SQLiteDatabase;

public interface DatabaseImpl <T>{
	
	public void setData(T data);
	
	public void setArrayData(ArrayList<T> arr);
	public ArrayList<T> getArrayData(String query);
	public ArrayList<T> getAllData();
	
	public void deleteAllData();
	public void deleteData(String query);
	
	public SQLiteDatabase getReadble();
	public SQLiteDatabase getWriteble(); 
	
}
