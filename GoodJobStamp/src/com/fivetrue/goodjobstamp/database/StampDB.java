package com.fivetrue.goodjobstamp.database;

import java.util.ArrayList;

import com.fivetrue.goodjobstamp.basemodel.BaseDatabaseListener;
import com.fivetrue.goodjobstamp.vo.StampVO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class StampDB implements DatabaseImpl<StampVO>{
	
	public interface StampDBListener extends BaseDatabaseListener<StampVO>{
		
	};

	
	private StampDbHelper mStampDb = null;
	private Context mContext = null;
	private StampDBListener listener = null;
	
	public StampDB(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
		mStampDb = new StampDbHelper(mContext);
	}


	@Override
	public void setData(StampVO data) {
		// TODO Auto-generated method stub
		
		if(data == null){
			listener.onError("data is null");
			return;
		}
		
		ContentValues values = new ContentValues();
		
		values.put(DBConstants.Stamp.FIELD_NAME, data.getName());
		values.put(DBConstants.Stamp.FIELD_SENDER, data.getSender());
		values.put(DBConstants.Stamp.FIELD_SENDER_ID, data.getSenderId());
		values.put(DBConstants.Stamp.FIELD_SENDER_KAKAO, data.getSenderKakao());
		values.put(DBConstants.Stamp.FIELD_CREATE_DATE, data.getCreateDate());
		values.put(DBConstants.Stamp.FIELD_UPDATE_DATE, data.getUpdateDate());
		values.put(DBConstants.Stamp.FIELD_PURPOSE, data.getPurpose());
		values.put(DBConstants.Stamp.FIELD_PRIZE, data.getPrize());
		values.put(DBConstants.Stamp.FIELD_CURRENT, data.getCurrentCount());
		values.put(DBConstants.Stamp.FIELD_MAX, data.getMaxCount());
		values.put(DBConstants.Stamp.FIELD_EXTRA, data.getExtra());
		values.put(DBConstants.Stamp.FIELD_DESCRIPTION, data.getDescription());
		
		getListener().insertRow(insert(values), data);
	}


	@Override
	public void setArrayData(ArrayList<StampVO> arr) {
		// TODO Auto-generated method stub
		if(arr == null){
			listener.onError("Array is null");
			return;
		}
			
		for(StampVO item : arr){
			setData(item);
		}
	}


	@Override
	public ArrayList<StampVO> getArrayData(String query) {
		// TODO Auto-generated method stub
		Cursor cursor = getReadble().query(DBConstants.Stamp.TABLE_NAME, DBConstants.Stamp.COLUMNS,
				query, null, 
				null, null, 
				null);
		
		ArrayList<StampVO> arr = new ArrayList<StampVO>();
		if (cursor.moveToFirst())
		{
			int index = cursor.getColumnIndex(DBConstants.Stamp.FIELD_INDEX);
			int name = cursor.getColumnIndex(DBConstants.Stamp.FIELD_NAME);
			int sender = cursor.getColumnIndex(DBConstants.Stamp.FIELD_SENDER);
			int senderId = cursor.getColumnIndex(DBConstants.Stamp.FIELD_SENDER_ID);
			int senderKakao = cursor.getColumnIndex(DBConstants.Stamp.FIELD_SENDER_KAKAO);
			int create = cursor.getColumnIndex(DBConstants.Stamp.FIELD_CREATE_DATE);
			int update = cursor.getColumnIndex(DBConstants.Stamp.FIELD_UPDATE_DATE);
			int purpose = cursor.getColumnIndex(DBConstants.Stamp.FIELD_PURPOSE);
			int prize = cursor.getColumnIndex(DBConstants.Stamp.FIELD_PRIZE);
			int current = cursor.getColumnIndex(DBConstants.Stamp.FIELD_CURRENT);
			int max = cursor.getColumnIndex(DBConstants.Stamp.FIELD_MAX);
			int extra = cursor.getColumnIndex(DBConstants.Stamp.FIELD_EXTRA);
			int description = cursor.getColumnIndex(DBConstants.Stamp.FIELD_DESCRIPTION);

			do
			{
				StampVO stamp = new StampVO();
				
				stamp.setIndex(cursor.getString(index));
				stamp.setName(cursor.getString(name));
				stamp.setSender(cursor.getString(sender));
				stamp.setSenderId(cursor.getString(senderId));
				stamp.setSenderKakao(cursor.getString(senderKakao));
				stamp.setCreateDate(cursor.getString(create));
				stamp.setUpdateDate(cursor.getString(update));
				stamp.setPurpose(cursor.getString(purpose));
				stamp.setPrize(cursor.getString(prize));
				stamp.setCurrentCount(cursor.getString(current));
				stamp.setMaxCount(cursor.getString(max));
				stamp.setExtra(cursor.getString(extra));
				stamp.setDescription(cursor.getString(description));
				arr.add(stamp);
				
			} while(cursor.moveToNext());
		}
		
		if(cursor!=null)
			cursor.close();
		
		return arr;
	}


	@Override
	public ArrayList<StampVO> getAllData() {
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
		return mStampDb.getReadableDatabase();
	}


	@Override
	synchronized public SQLiteDatabase getWriteble() {
		// TODO Auto-generated method stub
		return mStampDb.getWritableDatabase();
	}
	
	protected long insert(ContentValues values){
		return getWriteble().insert(DBConstants.Stamp.TABLE_NAME, null, values);
	}


	public StampDBListener getListener() {
		return listener;
	}


	public void setListener(StampDBListener listener) {
		this.listener = listener;
	}
	
	

	
}
