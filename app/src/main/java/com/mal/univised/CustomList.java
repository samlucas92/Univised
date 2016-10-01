package com.mal.univised;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class CustomList extends ArrayAdapter<String> {
    private String[] ids;
    private String[] title;
    private String[] first;
    private String[] last;
    private Activity context;

    public CustomList(Activity context, String[] ids, String[] title, String[] first, String[] last) {
        super(context, R.layout.list_view_layout, ids);
        this.context = context;
        this.ids = ids;
        this.title = title;
        this.first = first;
        this.last = last;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.textViewId);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewTitle);
        TextView textViewAuthor = (TextView) listViewItem.findViewById(R.id.textViewAuthor);

        textViewId.setText(ids[position]);
        textViewName.setText(title[position]);
        textViewAuthor.setText(first[position] + " " + last[position]);

        return listViewItem;
    }
}