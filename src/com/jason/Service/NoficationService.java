package com.jason.Service;

import java.util.Date;

import com.jason.Database.NotificationObject;
import com.jason.Database.SqlDb;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class NoficationService extends AccessibilityService {
	
	private final String tag = "NotificationService";

	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {
		Log.d(tag, "Inside onAccessibilityEvent");
		if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED){
			SqlDb db = new SqlDb(this);
			NotificationObject no = new NotificationObject();
			no.setNoficationPackage(String.valueOf(event.getPackageName()));
			no.setNotificationText(String.valueOf(event.getText().toString()));
			no.setNotificationDTM(new Date());
			db.addNotification(no);
			Log.d(tag, "Saved event");
		}
	}

	@Override
	public void onInterrupt() {
		isInit = false;
	}
	
	private boolean isInit = false;
	
	@Override
	public void onServiceConnected(){
		Log.d(tag, "Service connected");
	    if (isInit) {
	        return;
	    }
	    AccessibilityServiceInfo info = new AccessibilityServiceInfo();
	    info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
	    info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
	    setServiceInfo(info);
	    isInit = true;
	}

}
