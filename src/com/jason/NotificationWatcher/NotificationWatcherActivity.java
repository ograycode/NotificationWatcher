package com.jason.NotificationWatcher;

import java.util.ArrayList;
import java.util.List;

import com.jason.Database.NotificationObject;
import com.jason.Database.SqlDb;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

public class NotificationWatcherActivity extends ListActivity {
	
	private List<NotificationObject> list;
	private List<NotificationObject> lvaList;
	
    @Override
	protected void onResume() {
		super.onResume();
		if (list != null){list.clear();}
		if (lvaList != null){lvaList.clear();}
		this.populateList();
	}

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void populateList(){
    	SqlDb db = new SqlDb(this);
    	list = db.getAllNotifications();
		lvaList = new ArrayList<NotificationObject>();
		ListViewAdapter lva = new ListViewAdapter(this, R.layout.list, lvaList);
		this.setListAdapter(lva);
    	if (list != null){
    		for (NotificationObject o : list){
    			lva.add(o);
    		}
    		lva.notifyDataSetChanged();
    	} else {
    		lva.clear();
    		lva.notifyDataSetChanged();
    	}
    }
    
    public void clearClick(View view){
    	new SqlDb(this).dropTable();
    	this.populateList();
    }
}