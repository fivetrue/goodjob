package com.fivetrue.goodjobstamp.database;

public class DBConstants {
	static public String DB_NAME = "goodjobstamp.db";
	static public int DB_VER = 1;
	
	static public class Stamp{
		static public String TABLE_NAME = "stamp";
		
		static public String FIELD_INDEX = "_index";
		static public String FIELD_NAME = "_name";
		static public String FIELD_SENDER = "_sender";
		static public String FIELD_SENDER_ID = "_senderId";
		static public String FIELD_SENDER_KAKAO = "_senderKakao";
		static public String FIELD_CREATE_DATE = "_createDate";
		static public String FIELD_UPDATE_DATE = "_updateDate";
		static public String FIELD_PURPOSE = "_purpose";
		static public String FIELD_PRIZE = "_prize";
		static public String FIELD_CURRENT = "_current";
		static public String FIELD_MAX = "_max";
		static public String FIELD_EXTRA = "_extra";
		static public String FIELD_DESCRIPTION = "_description";
		
		public static final String CREATE_QUERY = 
				"CREATE TABLE IF NOT EXISTS " + DBConstants.Stamp.TABLE_NAME
	            + "(" + DBConstants.Stamp.FIELD_INDEX + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	            + DBConstants.Stamp.FIELD_NAME + " TEXT, "
	            + DBConstants.Stamp.FIELD_SENDER + " TEXT, "
	            + DBConstants.Stamp.FIELD_SENDER_ID + " TEXT, "
	            + DBConstants.Stamp.FIELD_SENDER_KAKAO + " TEXT, "
	            + DBConstants.Stamp.FIELD_CREATE_DATE + " TEXT, "
	            + DBConstants.Stamp.FIELD_UPDATE_DATE + " TEXT, "
	            + DBConstants.Stamp.FIELD_PURPOSE + " TEXT, "
	            + DBConstants.Stamp.FIELD_PRIZE + " TEXT, "
	            + DBConstants.Stamp.FIELD_CURRENT + " TEXT, "
	            + DBConstants.Stamp.FIELD_MAX + " TEXT, "
	            + DBConstants.Stamp.FIELD_EXTRA + " TEXT, "
	            + DBConstants.Stamp.FIELD_DESCRIPTION + " TEXT);";
		
		static public String[] COLUMNS = {
			FIELD_INDEX,
			FIELD_NAME,
			FIELD_SENDER,
			FIELD_SENDER_ID,
			FIELD_SENDER_KAKAO,
			FIELD_CREATE_DATE,
			FIELD_UPDATE_DATE,
			FIELD_PURPOSE,
			FIELD_PRIZE,
			FIELD_CURRENT,
			FIELD_MAX,
			FIELD_EXTRA,
			FIELD_DESCRIPTION,
		};
	}
	
	static public class Member{
		static public String TABLE_NAME = "member";
		static public String FIELD_INDEX = "_index";
		static public String FIELD_NAME = "_name";
		static public String FIELD_SENDER_ID = "_senderId";
		static public String FIELD_SENDER_KAKAO = "_senderKakao";
		static public String FIELD_COMMENT = "_comment";
		
		public static final String CREATE_QUERY = 
				"CREATE TABLE IF NOT EXISTS " + DBConstants.Stamp.TABLE_NAME
	            + "(" + DBConstants.Member.FIELD_INDEX + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	            + DBConstants.Member.FIELD_NAME + " TEXT, "
	            + DBConstants.Member.FIELD_SENDER_ID + " TEXT, "
	            + DBConstants.Member.FIELD_SENDER_KAKAO + " TEXT, "
	            + DBConstants.Member.FIELD_COMMENT + " TEXT);";
		
		static public String[] COLUMNS = {
			FIELD_INDEX,
			FIELD_NAME,
			FIELD_SENDER_ID,
			FIELD_SENDER_KAKAO,
			FIELD_COMMENT,
		};
	}
}
