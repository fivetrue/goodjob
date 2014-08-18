package com.fivetrue.goodjobstamp;



import com.android.common.Config;
import com.fivetrue.goodjobstamp.util.IntentMaker;
import com.fivetrue.goodjobstamp.util.Utils;
import com.kakao.APIErrorResult;
import com.kakao.MeResponseCallback;
import com.kakao.SessionCallback;
import com.kakao.UserProfile;
import com.kakao.exception.KakaoException;
import com.kakao.widget.LoginButton;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

/**
 * @author Fivetrue
 *
 */
public class IntroActivity extends BaseActivity {
	
	private ViewGroup mContentView = null;
	private final SessionCallback mySessionCallback = new MySessionStatusCallback();
	/**
	 * 로그인 위젯 
	 */
	private LoginButton loginButton;

	@Override
	protected View onCreateView(LayoutInflater inflater) {
		// TODO Auto-generated method stub
		getActionBarView().setVisibility(View.GONE);
		getDrawerLayout().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
		initViews(inflater);
		return mContentView;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		if(Utils.Kakao.initializeSession(IntroActivity.this, mySessionCallback)){
			// 1. 세션을 갱신 중이면, 프로그레스바를 보이거나 버튼을 숨기는 등의 액션을 취한다
			loginButton.setVisibility(View.GONE);
		} else if (Utils.Kakao.isSessionOpen()){
			// 2. 세션이 오픈된된 상태이면, 다음 activity로 이동한다.
			loginButton.setVisibility(View.GONE);
			onSessionOpened();
		} 
	}

	private void initViews(LayoutInflater inflater){
		mContentView = (ViewGroup) inflater.inflate(R.layout.activity_intro, null);
		loginButton = (LoginButton) mContentView.findViewById(R.id.btn_kakao_login);
		loginButton.setLoginSessionCallback(mySessionCallback);
	}

	@Override
	protected void onDestroyView() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onMakePopupMenu(PopupMenu popup) {
		// TODO Auto-generated method stub

	}

	protected void onSessionOpened(){
		
		
		Utils.Kakao.getMyInfomation(new MeResponseCallback() {
	        @Override
	        protected void onSuccess(final UserProfile userProfile) {
	            // 성공.
	        	if(userProfile != null){
	        		Utils.Kakao.updateProfile(userProfile, null);
					if(Utils.Kakao.hasUserProperties(userProfile, mPref)){
						IntentMaker.makeIntent(IntroActivity.this, MainActivity.class).startActivity();
					}else{
						IntentMaker.makeIntent(IntroActivity.this, UserPropertiesActivity.class)
						.putBoolean(true)
						.putParcelable(userProfile)
						.startActivity();
					}
					finish();
	        	}
	        }

	        @Override
	        protected void onNotSignedUp() {
	            // 가입 페이지로 이동
	        }

	        @Override
	        protected void onSessionClosedFailure(final APIErrorResult errorResult) {
	            // 다시 로그인 시도
	        }

	        @Override
	        protected void onFailure(final APIErrorResult errorResult) {
	            // 실패
	            Toast.makeText(getApplicationContext(), "failed to update profile. msg = " + errorResult, Toast.LENGTH_LONG).show();
	        }
	    });
	}
	
	private class MySessionStatusCallback implements SessionCallback {
		/**
		 * 세션이 오픈되었으면 가입페이지로 이동 한다.
		 */
		@Override
		public void onSessionOpened() {
			// 프로그레스바를 보이고 있었다면 중지하고 세션 오픈후 보일 페이지로 이동
			IntroActivity.this.onSessionOpened();
		}
		/**
		 * 세션이 삭제되었으니 로그인 화면이 보여야 한다.
		 * @param exception  에러가 발생하여 close가 된 경우 해당 exception
		 */
		@Override
		public void onSessionClosed(KakaoException exception) {
			// TODO Auto-generated method stub
			loginButton.setVisibility(View.VISIBLE);
		}

	}
}
