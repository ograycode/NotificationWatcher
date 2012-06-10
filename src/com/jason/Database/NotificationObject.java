package com.jason.Database;

import java.util.Date;

public class NotificationObject {
	
	private int primaryKey;
	private String noficationPackage;
	private String notificationText;
	private Date notificationDTM;
	
	public NotificationObject(){}

	public NotificationObject(int primaryKey, String noficationPackage,
			String notificationText, Date notificationDTM) {
		this.primaryKey = primaryKey;
		this.noficationPackage = noficationPackage;
		this.notificationText = notificationText;
		this.notificationDTM = notificationDTM;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getNoficationPackage() {
		return noficationPackage;
	}

	public void setNoficationPackage(String noficationPackage) {
		this.noficationPackage = noficationPackage;
	}

	public String getNotificationText() {
		return notificationText;
	}

	public void setNotificationText(String notificationText) {
		this.notificationText = notificationText;
	}

	public Date getNotificationDTM() {
		return notificationDTM;
	}

	public void setNotificationDTM(Date notificationDTM) {
		this.notificationDTM = notificationDTM;
	}
}
