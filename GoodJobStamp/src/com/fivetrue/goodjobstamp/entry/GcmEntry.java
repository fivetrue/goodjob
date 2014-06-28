package com.fivetrue.goodjobstamp.entry;

import com.fivetrue.goodjobstamp.vo.MemberVO;
import com.fivetrue.goodjobstamp.vo.StampVO;

public class GcmEntry {
	
	private StampVO stamp = null;
	private MemberVO member = null;
	public StampVO getStamp() {
		return stamp;
	}
	public void setStamp(StampVO stamp) {
		this.stamp = stamp;
	}
	public MemberVO getMember() {
		return member;
	}
	public void setMember(MemberVO member) {
		this.member = member;
	}
	@Override
	public String toString() {
		return "GcmEntry [stamp=" + stamp + ", member=" + member + "]";
	}
	
	

}
