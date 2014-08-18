package com.fivetrue.goodjobstamp.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
		
		if(data == null){
			listener.onError("data is null");
			return;
		}
		
		ContentValues values = new ContentValues();
		
		values.put(DBConstants.Member.FIELD_NAME, data.getName());
		values.put(DBConstants.Member.FIELD_ID, data.getSenderId());
		values.put(DBConstants.Member.FIELD_KAKAO, data.getKakaoId());
		values.put(DBConstants.Member.FIELD_COMMENT, data.getComment());
		values.put(DBConstants.Member.FIELD_IP, data.getIp());
		
		getListener().insertRow(insert(values), data);
		
	}

	@Override
	public void setArrayData(ArrayList<MemberVO> arr) {
		// TODO Auto-generated method stub
		
		if(arr == null){
			listener.onError("Array is null");
			return;
		}
			
		for(MemberVO item : arr){
			setData(item);
		}
	}

	@Override
	public ArrayList<MemberVO> getArrayData(String query) {
		// TODO Auto-generated method stub
		Cursor cursor = getReadble().query(DBConstants.Stamp.TABLE_NAME, DBConstants.Stamp.COLUMNS,
				query, null, 
				null, null, 
				null);
		
		ArrayList<MemberVO> arr = new ArrayList<MemberVO>();
		if (cursor.moveToFirst())
		{
			int index = cursor.getColumnIndex(DBConstants.Member.FIELD_INDEX);
			int name = cursor.getColumnIndex(DBConstants.Member.FIELD_NAME);
			int senderId = cursor.getColumnIndex(DBConstants.Member.FIELD_ID);
			int senderKakao = cursor.getColumnIndex(DBConstants.Member.FIELD_KAKAO);
			int comment = cursor.getColumnIndex(DBConstants.Member.FIELD_COMMENT);
			int ip = cursor.getColumnIndex(DBConstants.Member.FIELD_IP);

			do
			{
				MemberVO member = new MemberVO();
				
				member.setIndex(cursor.getString(index));
				member.setName(cursor.getString(name));
				member.setSenderId(cursor.getString(senderId));
				member.setKakaoId(cursor.getString(senderKakao));
				member.setComment(cursor.getString(comment));
				member.setIp(cursor.getString(ip));
				arr.add(member);
				
			} while(cursor.moveToNext());
		}
		
		if(cursor!=null)
			cursor.close();
		
		return arr;
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
	synchronized public SQLiteDatabase getReadble() {
		// TODO Auto-generated method stub
		return mDBHelper.getReadableDatabase();
	}

	@Override
	synchronized public SQLiteDatabase getWriteble() {
		// TODO Auto-generated method stub
		return mDBHelper.getWritableDatabase();
	}
	
	protected long insert(ContentValues values){
		return getWriteble().insert(DBConstants.Member.TABLE_NAME, null, values);
	}
	
	public MemberDBListener getListener() {
		return listener;
	}
	
	public void setListener(MemberDBListener l){
		listener = l;
	}

}
