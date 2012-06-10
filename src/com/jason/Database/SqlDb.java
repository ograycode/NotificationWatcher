package com.jason.Database;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;

public class SqlDb extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "notificationDB";
	private static final String TABLE_NOTIFICATIONS = "notificationsTable";
	private static final int DB_VERSION = 1; 
	private static final String COLUMN_PK = "_id";
	private static final String COLUMN_NOTIFICATION_PACKAGE = "c_notificationPackage";
	private static final String COLUMN_NOTIFICATION_DTM = "c_noficationDTM";
	private static final String COLUMN_NOTIFICATION_TEXT = "c_notificationText";
	
	private SQLiteDatabase db;

	public SqlDb(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createNotificationTable = "CREATE TABLE "+TABLE_NOTIFICATIONS+" ("+
				COLUMN_PK+" INTEGER PRIMARY KEY, " +COLUMN_NOTIFICATION_PACKAGE+" TEXT, " +
				COLUMN_NOTIFICATION_TEXT+" TEXT, "+COLUMN_NOTIFICATION_DTM+" TEXT)";
		db.execSQL(createNotificationTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATIONS);
 
        // Create tables again
        onCreate(db);
	}
	
	private SQLiteDatabase getWDB(){
		return this.getWritableDatabase();
	}
	
	public void addNotification(NotificationObject notification){
		db = this.getWDB();
		ContentValues values = new ContentValues();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		values.put(COLUMN_NOTIFICATION_PACKAGE, notification.getNoficationPackage());
		values.put(COLUMN_NOTIFICATION_TEXT, notification.getNotificationText());
		values.put(COLUMN_NOTIFICATION_DTM, dateFormat.format(notification.getNotificationDTM()));
		db.insert(TABLE_NOTIFICATIONS, null, values);
		db.close();
	}
	
	public List<NotificationObject> getAllNotifications(){
		List<NotificationObject> list = new ArrayList<NotificationObject>();
		
		db = this.getWDB();
		Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NOTIFICATIONS, null);
		
		if(c.moveToFirst()){
			do {
				NotificationObject no = new NotificationObject();
				no.setPrimaryKey(Integer.parseInt(c.getString(0)));
				no.setNoficationPackage(c.getString(1));
				no.setNotificationText(c.getString(2));
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					no.setNotificationDTM(df.parse(c.getString(3)));
				} catch (ParseException e) {
					db.close();
					return null;
				}
				list.add(no);
				
			} while (c.moveToNext());
		}
		db.close();
		return list;
	}

	public void dropTable() {        
		db = this.getWDB();
		// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATIONS);
 
        // Create tables again
        onCreate(db);
        db.close();
	}

}
