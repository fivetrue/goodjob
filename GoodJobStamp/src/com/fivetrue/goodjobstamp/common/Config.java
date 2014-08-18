package com.fivetrue.goodjobstamp.common;

public class Config {
	
	static public class KAKAO{
		static public String NATIVE_APP_KEY = "6649cdcc8c482589f546640278a2a548";
		static public String REST_API_KEY = "265ce8e8b90416339fac633632a8f63d";
		static public String JAVASCRIPT_KEY = "62fb82464fb96c24fcfe015955c83ce3";
		static public String ADMIN_KEY = "9c443b77854a7f742028231a3a70e1d6";
		
		static public class COLUMN{
			static public String ID = "id";
			static public String NICKNAME = "nickname";
			static public String PROFILE_IMG = "profile_image";
			static public String THUMBNAIL_IMG = "thumbnail_image";
			static public String CITY = "city";
			static public String TOWN = "town";
			static public String GENDER = "gender";
			static public String BIRTHDAY = "birthday";
		}
	}
	
	static public class GCM{
		
		static public String GOOGLE_PROJECT_NAME = "lucky-trail-608";
		static public String GOOGLE_PROJECT_ID = "650874166288";
		static public String GOOGLE_GCM_HOST = "@gcm.googleapis.com";
		static public String GOOGLE_CLIENT_ID = "650874166288-ll26u0c2acp8p8kmoov6eis9280vjug8.apps.googleusercontent.com";
		static public String GOOGLE_EMAIL_ADDRESS = "650874166288-ll26u0c2acp8p8kmoov6eis9280vjug8@developer.gserviceaccount.com";
		static public String GOOGLE_GCM_SENDER = GOOGLE_PROJECT_ID + GOOGLE_GCM_HOST;
		static public String GOOGLE_GCM_API_KEY = "AIzaSyALhhcYby_-2t52Q2mny9SkjdtDhhM7CMs";
		
		static public String TITLE = "TITLE";
		static public String CONTENT = "CONTENT";
		
		static public String MESSAGE_TYPE = "MESSAGE_TYPE";
		
		static public String MESSAGE_OK = "MESSAGE_OK";
		static public String MESSAGE_CANCEL = "MESSAGE_CANCEL";
		
		static public String MESSAGE_DATA = "MESSAGE_DATA";
		static public String MESSAGE_NOTIFY = "MESSAGE_NOTIFY";
		static public String MESSAGE_REJECT = "MESSAGE_REJECT";
		static public String MESSAGE_REQUEST = "MESSAGE_REQUEST";
	}
	
	static public class CAULY{
		static public String APP_ID = "mNjBKm6C";
		static public String EFFECT = "FadeIn";
	}
	
	static public class SOCKET{
		static public int PORT = 7878;
		
	}
	
	static public class OPEN{
		static public String SERVICE_KEY = "uaRLin6/lNv8OZxDj/l9O8P8dkjdroCtUtEXlGqRsOXARFWqAl+H6SfQGKh4h/Pm6uNlBdDWBRM0HH2umgJF+g==";
		
		static public String OPEN_API_HOST = "http://openapi.epost.go.kr/postal/retrieveLotNumberAdressService/retrieveLotNumberAdressService/";
		static public String API_GET_CITY_LIST = OPEN_API_HOST + "getBorodCityList?serviceKey=%s";
		static public String API_GET_TOWN_LIST = OPEN_API_HOST + "getSiGunGuList?brtcCd=%s&serviceKey=%s";
	}
}
