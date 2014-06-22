package com.fivetrue.goodjobstamp.asynctask;

import com.fivetrue.goodjobstamp.R;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class ProgressAsyncTask extends	AsyncTask<Object, Object, Object> {
	private Activity mActivity = null;
	private LayoutInflater mInflater = null;
	private ViewGroup mView = null;
	private TextView mTvProgress = null;
	private Dialog mDialog = null;

	public ProgressAsyncTask(Activity activity){
		this.mActivity = activity;
		this.mInflater = (LayoutInflater) mActivity.getLayoutInflater();
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		mView = (ViewGroup) this.mInflater.inflate(R.layout.inc_progress, null);
		mTvProgress = (TextView) mView.findViewById(R.id.tv_progress);
		mTvProgress.setText(setTextToProgress());
		if(isShowing()){
			if(mergeToParent()){
				if(getParentView() != null){
					getParentView().addView(mView);
				}
			}else{
				mDialog = new Dialog(mActivity);
				mView.setBackgroundColor(canDissmiss() ? Color.TRANSPARENT : Color.GRAY);
				mDialog.setContentView(mView);
				mDialog.setCanceledOnTouchOutside(canDissmiss());
				mDialog.show();
			}
		}
	}

	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		if(mDialog != null)
			mDialog.dismiss();
		
		if(mView != null)
			getParentView().removeView(mView);
			
	}	

	abstract protected boolean isShowing();
	abstract protected boolean canDissmiss();
	abstract protected boolean mergeToParent();
	abstract protected ViewGroup getParentView();
	abstract protected String setTextToProgress();

}
