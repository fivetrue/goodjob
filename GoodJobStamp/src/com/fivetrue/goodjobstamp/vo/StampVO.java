package com.fivetrue.goodjobstamp.vo;


import com.fivetrue.goodjobstamp.basemodel.BaseVO;

public class StampVO implements BaseVO {
	private String index;
	private String name;
	private String sender;
	private String senderId;
	private String senderKakao;
	private String createDate;
	private String updateDate;
	private String purpose;
	private String prize;
	private String currentCount;
	private String maxCount;
	private String extra;
	private String description;
	private String className;
	
	public StampVO() {
		className = StampVO.class.getName();
	}
	public String getClassName() {
		return className;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getSenderKakao() {
		return senderKakao;
	}
	public void setSenderKakao(String senderKakao) {
		this.senderKakao = senderKakao;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getPrize() {
		return prize;
	}
	public void setPrize(String prize) {
		this.prize = prize;
	}
	public String getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(String currentCount) {
		this.currentCount = currentCount;
	}
	public String getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(String maxCount) {
		this.maxCount = maxCount;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "StampVO [index=" + index + ", name=" + name + ", sender="
				+ sender + ", senderId=" + senderId + ", senderKakao="
				+ senderKakao + ", createDate=" + createDate + ", updateDate="
				+ updateDate + ", purpose=" + purpose + ", prize=" + prize
				+ ", currentCount=" + currentCount + ", maxCount=" + maxCount
				+ ", extra=" + extra + ", description=" + description + "]";
	}
}
