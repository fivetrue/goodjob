package com.fivetrue.goodjobstamp.asynctask;

import com.fivetrue.goodjobstamp.basemodel.BaseNetworkListener;

import android.os.AsyncTask;

abstract public class NetworkBaseASyncTask extends AsyncTask <Object, Object, Object>{

	abstract public void sendMessage(Object msg, BaseNetworkListener listener);

}
