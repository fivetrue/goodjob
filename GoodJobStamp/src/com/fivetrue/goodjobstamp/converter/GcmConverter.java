package com.fivetrue.goodjobstamp.converter;


import com.fivetrue.goodjobstamp.basemodel.BaseConverter;
import com.fivetrue.goodjobstamp.entry.GcmEntry;
import com.fivetrue.goodjobstamp.vo.MemberVO;
import com.fivetrue.goodjobstamp.vo.StampVO;
import com.google.gson.Gson;

public class GcmConverter implements BaseConverter<GcmEntry>{

	private final String STAMP = StampVO.class.getName();
	private final String MEMBER = MemberVO.class.getName();

	@Override
	public GcmEntry onReceive(String obj) {
		// TODO Auto-generated method stub

		if(obj != null){
			GcmEntry entry = new GcmEntry();

			StampVO stamp = null;
			MemberVO member = null;
			if(obj.contains(STAMP)){
				stamp = new Gson().fromJson(obj, StampVO.class);	
			}
			if(obj.contains(MEMBER)){
				member = new Gson().fromJson(obj, MemberVO.class);
			}
			entry.setStamp(stamp);
			entry.setMember(member);
			return entry;
		}

		return null;
	}

}
