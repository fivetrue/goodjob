package com.fivetrue.goodjobstamp.common.pref;

import android.content.Context;


public class UserPrefrenceManager {
	
	private final String PREF_COMMON = "PREF_COMMON";
	
	private final String GCM_DEVICE = "GCM_DEVICE";


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
}
