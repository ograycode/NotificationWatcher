package com.jason.NotificationWatcher;

import java.text.SimpleDateFormat;
import java.util.List;

import com.jason.Database.NotificationObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListViewAdapter extends ArrayAdapter<NotificationObject> {

	private List<NotificationObject> objects;
	Context ctx;
	
	public ListViewAdapter(Context context, int textViewResourceId,
			List<NotificationObject> objects) {
		super(context, textViewResourceId, objects);
		this.objects = objects;
		this.ctx = context;
	}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list, null);
            }
            NotificationObject o = objects.get(position);
            if (o != null) {
                    TextView lt = (TextView) v.findViewById(R.id.textView_Large);
                    TextView mt = (TextView) v.findViewById(R.id.textView_Medium);
                    TextView st = (TextView) v.findViewById(R.id.textView_Small);
                    if (lt != null) {
                          lt.setText(o.getNoficationPackage());                            }
                    if(mt != null){
                          mt.setText(o.getNotificationText());
                    }
                    if(st != null){
                    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        st.setText(df.format(o.getNotificationDTM()));
                  }
            }
            return v;
    }
	
}
