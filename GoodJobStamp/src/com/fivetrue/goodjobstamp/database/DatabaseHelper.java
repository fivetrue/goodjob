package com.fivetrue.goodjobstamp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	

	public DatabaseHelper(Context context) {
		super(context, DBConstants.DB_NAME, null, DBConstants.DB_VER);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DBConstants.Stamp.CREATE_QUERY);
		db.execSQL(DBConstants.Member.CREATE_QUERY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
