package com.mal.univised;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;


public class CustomList extends ArrayAdapter<String> {
    private String[] ids;
    private String[] title;
    private String[] first;
    private String[] last;
    private String[] body;
    private String [] review;
    private Activity context;

    public CustomList(Activity context, String[] ids, String[] title, String[] first, String[] last, String[] body, String[] review) {
        super(context, R.layout.list_view_layout, ids);
        this.context = context;
        this.ids = ids;
        this.title = title;
        this.first = first;
        this.last = last;
        this.body = body;
        this.review = review;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.textViewId);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewTitle);
        TextView textViewAuthor = (TextView) listViewItem.findViewById(R.id.textViewAuthor);
        TextView textViewBody = (TextView) listViewItem.findViewById(R.id.textViewBody);
        RatingBar reviewRating = (RatingBar) listViewItem.findViewById(R.id.revRating);

        textViewId.setText(ids[position]);
        textViewName.setText(title[position]);
        textViewAuthor.setText(first[position] + " " + last[position]);
        textViewBody.setText(body[position]);
        reviewRating.setRating(Float.parseFloat(review[position]));

        return listViewItem;
    }
}