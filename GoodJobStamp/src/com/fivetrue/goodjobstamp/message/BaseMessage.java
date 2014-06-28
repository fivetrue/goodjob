package com.fivetrue.goodjobstamp.message;

import com.fivetrue.goodjobstamp.basemodel.BaseVO;


public interface BaseMessage {
	long INVALID_DATA = -1;
	public void setJsonString(BaseVO model);
	public String getTargetClassName();
}
