package com.fivetrue.goodjobstamp.common.pref;

import com.kakao.KakaoTalkProfile;

import android.content.Context;


/**
 * @author Fivetrue
 *
 */
public class UserPrefrenceManager {
	
	private final String PREF_COMMON = "PREF_COMMON";
	
	private final String GCM_DEVICE = "GCM_DEVICE";
	
	private final String KAKAO_ID = "KAKAO_ID"; 
	private final String KAKAO_NICKNAME = "KAKAO_NICKNAME";
	private final String KAKAO_PROFILE_IMG = "KAKAO_PROFILE_IMG";
	private final String KAKAO_THUMNAIL = "KAKAO_THUMNAIL";
	private final String KAKAO_COUNTRY = "KAKAO_COUNTRY";
			


	private SharedPrefrenceManager mPref;

	public UserPrefrenceManager(Context context) {
		mPref = new SharedPrefrenceManager(context, PREF_COMMON);
	}

	public boolean clear() {
		return mPref.clearPref();
	}
	
	public void setGcmDeviceId(String id){
		mPref.savePref(GCM_DEVICE, id);
	}
	
	public String getGcmDeviceId(){
		return mPref.loadStringPref(GCM_DEVICE);
	}
	
	
	
	/**
	 * Kakao Pref
	 * @param url
	 */
	
	public void setKakaoId(long id){
		mPref.savePref(KAKAO_ID, id);
	}
	
	public long getKakaoId(){
		return mPref.loadLongPref(KAKAO_ID);
	}
	
	public void setKakaoNickName(String nickname){
		mPref.savePref(KAKAO_NICKNAME, nickname);
	}
	
	public String getkakaoNickName(){
		return mPref.loadStringPref(KAKAO_NICKNAME);
	}
	
	public void setKakaoProfileUrl(String url){
		mPref.savePref(KAKAO_PROFILE_IMG, url);
	}
	
	public String getKakaoProfileUrl(){
		return mPref.loadStringPref(KAKAO_PROFILE_IMG);
	}
	
	public void setKakaoThumnailUrl(String url){
		mPref.savePref(KAKAO_THUMNAIL, url);
	}
	
	public String getKakaoThumnailUrl(){
		return mPref.loadStringPref(KAKAO_THUMNAIL);
	}
	
	public void setKakaoCountry(String country){
		mPref.savePref(KAKAO_COUNTRY, country);
	}
	
	public String getKakaoCountry(){
		return mPref.loadStringPref(KAKAO_COUNTRY);
	}
	
	
}
