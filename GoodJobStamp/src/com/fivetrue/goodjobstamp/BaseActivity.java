package com.fivetrue.goodjobstamp;

import com.fivetrue.goodjobstamp.asynctask.NetworkGcmAsyncTask;
import com.fivetrue.goodjobstamp.basemodel.BaseNetworkListener;
import com.fivetrue.goodjobstamp.common.pref.UserPrefrenceManager;
import com.fivetrue.goodjobstamp.database.MemberDB;
import com.fivetrue.goodjobstamp.database.StampDB;
import com.fivetrue.goodjobstamp.database.StampDB.StampDBListener;
import com.fivetrue.goodjobstamp.entry.MainEntry;
import com.fivetrue.goodjobstamp.message.GcmMessage;
import com.fivetrue.goodjobstamp.util.IntentMaker;
import com.fivetrue.goodjobstamp.vo.StampVO;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.kakao.KakaoTalkProfile;
import com.kakao.KakaoTalkService;
import com.kakao.UserProfile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

abstract public class BaseActivity extends Activity {

	protected UserPrefrenceManager mPref = null;
	protected StampDB mStampDB = null;
	protected MemberDB mMemberDb = null;

	private GoogleCloudMessaging Gcm = null;
	private DrawerLayout mDrawLayout = null;
	private ViewGroup mDrawer = null;

	private ViewGroup mContentView = null;
	private ViewGroup mActionBar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		initModels();
		initUI();
		registerGcm();
		
		initDrawerOnClick();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		onDestroyView();
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	private void initModels(){
		Gcm = GoogleCloudMessaging.getInstance(this);
		mPref = new UserPrefrenceManager(this);
		mStampDB = new StampDB(this);
		mStampDB.setListener(mStampListener);

	}

	private void initUI(){
		mActionBar = (ViewGroup) findViewById(R.id.layout_action_bar);
		mContentView = (ViewGroup) findViewById(R.id.layout_main);
		mDrawLayout = (DrawerLayout) findViewById(R.id.layout_parent);
		mDrawer = (ViewGroup) findViewById(R.id.layout_drawer);
		mDrawLayout.setDrawerListener(mDrawerListener);

		mContentView.addView(makeHorizontalShadowView());
		mContentView.addView(onCreateView(getLayoutInflater()));


		findViewById(R.id.layout_button).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mDrawLayout.isDrawerOpen(mDrawer)){
					mDrawLayout.closeDrawer(mDrawer);
				}else{
					mDrawLayout.openDrawer(mDrawer);
				}
			}
		});

		findViewById(R.id.iv_actionbar_back).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onDestroyView();
			}
		});

		findViewById(R.id.layout_actionbar_more).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showMorePopup(v);
			}
		});
	}
	
	private void initDrawerOnClick(){
		mDrawer.findViewById(R.id.layout_setting).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				IntentMaker.makeIntent(getApplicationContext(), UserPropertiesActivity.class)
				.putBoolean(false)
				.putParcelable(UserProfile.loadFromCache())
				.startActivitySettingFlag(Intent.FLAG_ACTIVITY_NEW_TASK);
			}
		});
	}

	
	/**
	 * OnCrate 대신 레이아웃을 생성하는 메소드
	 * @param inflater
	 * @return
	 */
	abstract protected View onCreateView(LayoutInflater inflater);


	/**
	 * Finish 동작 구현
	 */
	abstract protected void onDestroyView();

	/**
	 * 액션바 more 팝업의 메뉴 구현
	 * @param popup
	 */
	abstract protected void onMakePopupMenu(PopupMenu popup);
	
	protected void simpleStartActivity(Class<?> cls){
		if(getClass().equals(cls) || cls == null){
			mDrawLayout.closeDrawers();
			return;
		}
		
		IntentMaker.makeIntent(this, cls)
		.startActivity();
		
	}


	protected View makeHorizontalShadowView(){
		View v = new View(this);
		v.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				(int)getResources().getDimension(R.dimen.action_bar_shadow_height)));

		v.setBackgroundResource(R.drawable.tab_img_shadow_pattern);
		return v;
	}
	protected ViewGroup getContentView(){
		return mContentView;
	}

	protected ViewGroup getActionBarView(){
		return mActionBar;
	}
	
	protected void setActionBarTitle(String str){
		((TextView)mActionBar.findViewById(R.id.tv_actionbar)).setText(str);
	}

	protected DrawerLayout getDrawerLayout(){
		return mDrawLayout;
	}

	protected void showMorePopup(View view){
		PopupMenu popup = new PopupMenu(this, view);
		onMakePopupMenu(popup);
		popup.show();
	}

	protected UserPrefrenceManager getUserPreference(){
		return mPref;
	}

	protected void onUpdateKakaoProfile(){
		KakaoTalkService.requestProfile(new com.kakao.KakaoTalkHttpResponseHandler<KakaoTalkProfile>() {

			@Override
			protected void onHttpSuccess(KakaoTalkProfile profile) {
				// TODO Auto-generated method stub
				if(profile != null){
					Toast.makeText(getApplicationContext(), "success to get talk profile", Toast.LENGTH_SHORT).show();
					mPref.setKakaoNickName(profile.getNickName());
					mPref.setKakaoProfileUrl(profile.getProfileImageURL());
					mPref.setKakaoThumnailUrl(profile.getThumbnailURL());
					mPref.setKakaoCountry(profile.getCountryISO());
					log(profile.toString());
				}
			}

			@Override
			protected void onHttpSessionClosedFailure(
					com.kakao.APIErrorResult errorResult) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void onNotKakaoTalkUser() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "not a KakaoTalk user", Toast.LENGTH_SHORT).show();
			}

			@Override
			protected void onFailure(com.kakao.APIErrorResult errorResult) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "failure : " + errorResult, Toast.LENGTH_LONG).show();
			}
		});
	}

	private void registerGcm(){

		if(mPref.getGcmDeviceId().equals("")){
			NetworkGcmAsyncTask.getInstance(getGcm()).register(new BaseNetworkListener<GcmMessage>(){

				@Override
				public void onTimeout(GcmMessage obj) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onReceiveMessage(GcmMessage obj) {
					// TODO Auto-generated method stub
					log(obj.toString());
					toast(obj.toString());
					mPref.setGcmDeviceId(obj.getMessageId());
				}

				@Override
				public void onError(GcmMessage obj) {
					// TODO Auto-generated method stub
					log(obj.toString());
					toast(obj.toString());
				}
			});

		}
	}


	private void moveActionbarHome(float slideOffset){

		View v = mActionBar.findViewById(R.id.iv_actionbar_home);
		if(v != null){
			int move = (int) getResources().getDimension(R.dimen.action_bar_home_move_value);
			v.setX(-(slideOffset * move));
		}
	}

	private DrawerListener mDrawerListener = new DrawerListener() {

		public void onDrawerClosed(View drawerView) {
		}

		public void onDrawerOpened(View drawerView) {
		}

		public void onDrawerSlide(View drawerView, float slideOffset) {
			moveActionbarHome(slideOffset);

		}

		public void onDrawerStateChanged(int newState) {
			String state;
			switch (newState) {
			case DrawerLayout.STATE_IDLE:
				state = "STATE_IDLE";
				break;
			case DrawerLayout.STATE_DRAGGING:
				state = "STATE_DRAGGING";
				break;
			case DrawerLayout.STATE_SETTLING:
				state = "STATE_SETTLING";
				break;
			default:
				state = "unknown!";
			}
		}
	};

	protected void log(Object obj){
		System.out.println("ojkwon : " + obj.getClass().getSimpleName() + " = " + obj);
	}

	protected void toast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	protected void toast(int resId){
		Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
	}

	protected float getDensity(){
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.density;
	}

	private StampDBListener mStampListener = new StampDBListener() {

		@Override
		public void onError(String error) {
			// TODO Auto-generated method stub
			toast(error);
		}

		@Override
		public void insertRow(long row, StampVO data) {
			// TODO Auto-generated method stub
			log(row + " = " + data.toString());

			MainEntry entry = new MainEntry();

			entry.setStamp(data);
			GcmMessage msg = new GcmMessage();
			msg.setReceiver(mPref.getGcmDeviceId());
			msg.setJsonString(entry);
			NetworkGcmAsyncTask.getInstance(Gcm).sendMessage(msg, new BaseNetworkListener<GcmMessage>() {

				@Override
				public void onTimeout(GcmMessage obj) {
					// TODO Auto-generated method stub
					log(obj.toString());
				}

				@Override
				public void onReceiveMessage(GcmMessage obj) {
					// TODO Auto-generated method stub
					log(obj.toString());
				}

				@Override
				public void onError(GcmMessage obj) {
					// TODO Auto-generated method stub
					log(obj.toString());
				}
			});

		}

		@Override
		public void deleteRow(long row, StampVO data) {
			// TODO Auto-generated method stub

		}

	};

	public GoogleCloudMessaging getGcm() {
		return Gcm;
	}

	public void setGcm(GoogleCloudMessaging gcm) {
		Gcm = gcm;
	}
	
	public void showToast(int resId){
		Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
	}

	public void showToast(String resId){
		Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
	}

}
