package com.fivetrue.goodjobstamp.asynctask;

import com.fivetrue.goodjobstamp.basemodel.BaseNetworkListener;

import android.os.AsyncTask;

abstract public class NetworkBaseASyncTask <T> extends AsyncTask <T, Object, T>{

	abstract public void sendMessage(T msg, BaseNetworkListener<T> listener);

}
