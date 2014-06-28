package com.fivetrue.goodjobstamp.database;

import android.content.Context;

public class DatabaseManager {
	private StampDB mStampDB = null;
	private MemberDB mMemberDB = null;
	private Context mContext = null;
	
	public DatabaseManager(Context context) {
		mContext = context;
		mStampDB = new StampDB(context);
		mMemberDB = new MemberDB(context);
	}
	
	
	
}
