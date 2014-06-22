	package com.fivetrue.goodjobstamp.message;

import android.os.Bundle;

public class GcmMessage implements BaseMessage{
	
	private Bundle mBundle = null;
	private String receiver = null;
	private String messageId = null;
	private String errorMessage = null;
	private long timeToLive = -1;
	
	public GcmMessage(){}
	
	

	/**
	 * @return the receiver
	 */
	public String getReceiver() {
		return receiver;
	}



	/**
	 * @param receiver the receiver to set
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}



	/**
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}



	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the timeToLive
	 */
	public long getTimeToLive() {
		return timeToLive;
	}

	/**
	 * @param timeToLive the timeToLive to set
	 */
	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}

	@Override
	public void setBundleDate(Bundle b) {
		// TODO Auto-generated method stub
		mBundle = b;
	}

	@Override
	public Bundle getBundleData() {
		// TODO Auto-generated method stub
		return mBundle;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "GcmMessage [receiver=" + receiver + ", messageId=" + messageId
				+ ", errorMessage=" + errorMessage + ", timeToLive="
				+ timeToLive + "]";
	}

}
