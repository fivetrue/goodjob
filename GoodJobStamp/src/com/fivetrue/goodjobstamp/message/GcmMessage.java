	package com.fivetrue.goodjobstamp.message;


import com.fivetrue.goodjobstamp.basemodel.BaseVO;
import com.fivetrue.goodjobstamp.vo.StampVO;
import com.google.gson.Gson;

public class GcmMessage implements BaseMessage{
	
	private String receiver = null;
	private String messageId = String.valueOf(System.currentTimeMillis());
	private String errorMessage = null;
	private int timeToLive = 1800;
	private String json = null;
	private String targetClassName = null;
	
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
	public int getTimeToLive() {
		return timeToLive;
	}

	/**
	 * @param timeToLive the timeToLive to set
	 */
	public void setTimeToLive(int timeToLive) {
		this.timeToLive = timeToLive;
	}

	@Override
	public void setJsonString(BaseVO vo) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		StampVO stamp = (StampVO) vo;
		this.targetClassName = stamp.getClassName();
		setJson(gson.toJson(stamp)); 
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	@Override
	public String getTargetClassName() {
		return targetClassName;
	}

	@Override
	public String toString() {
		return "GcmMessage [receiver=" + receiver + ", messageId=" + messageId
				+ ", errorMessage=" + errorMessage + ", timeToLive="
				+ timeToLive + ", json=" + json + "]";
	}

}
