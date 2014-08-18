package com.fivetrue.goodjobstamp.vo;

import com.fivetrue.goodjobstamp.basemodel.BaseVO;

public class MemberVO implements BaseVO{
	
	private String index = null;
	private String name = null;
	private String kakaoId = null;
	private String senderId = null;
	private String comment = null;
	private String ip = null;
	
			
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

	public String getKakaoId() {
		return kakaoId;
	}

	public void setKakaoId(String kakaoId) {
		this.kakaoId = kakaoId;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	

	@Override
	public String toString() {
		return "MemberVO [index=" + index + ", name=" + name + ", kakaoId="
				+ kakaoId + ", senderId=" + senderId + ", comment=" + comment
				+ ", ip=" + ip + "]";
	}

	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
