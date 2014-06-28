package com.fivetrue.goodjobstamp.database;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fivetrue.goodjobstamp.basemodel.BaseDatabaseListener;
import com.fivetrue.goodjobstamp.vo.MemberVO;

public class MemberDB implements DatabaseImpl<MemberVO>{
	
	public interface MemberDBListener extends BaseDatabaseListener<MemberVO>{
		
	};
	
	private DatabaseHelper mDBHelper = null;
	private Context mContext = null;
	private MemberDBListener listener = null;
	
	public MemberDB(Context context){
		mContext = context;
		mDBHelper = new DatabaseHelper(mContext);
	}

	@Override
	public void setData(MemberVO data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setArrayData(ArrayList<MemberVO> arr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<MemberVO> getArrayData(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MemberVO> getAllData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteData(String query) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SQLiteDatabase getReadble() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SQLiteDatabase getWriteble() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public MemberDBListener getListener() {
		return listener;
	}
	
	public void setListener(MemberDBListener l){
		listener = l;
	}

}
