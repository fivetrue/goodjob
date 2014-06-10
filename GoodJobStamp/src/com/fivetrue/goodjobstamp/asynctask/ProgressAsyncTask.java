package com.fivetrue.goodjobstamp.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public abstract class ProgressAsyncTask extends	AsyncTask<Object, Object, Object> {
	private Activity mActivity = null;
	private LayoutInflater mInflater = null;
	
	public ProgressAsyncTask(Activity activity){
		this.mActivity = activity;
		this.mInflater = (LayoutInflater) mActivity.getLayoutInflater();
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
//		ViewGroup view = (ViewGroup) this.mInflater.inflate(R.layout.inc_progress, null);
		if(isShowing()){
			if(mergeToParent()){
				
			}else{
				if(canDissmiss()){
					
				}else{
					
				}
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
	}	
	
	abstract protected boolean isShowing();
	abstract protected boolean canDissmiss();
	abstract protected boolean mergeToParent();
	abstract protected ViewGroup getParentView();
	abstract protected String setTextToProgress();
	
}
