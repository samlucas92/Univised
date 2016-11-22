package com.mal.univised;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by samlucas on 03/10/2016.
 */
public class CustomSearchList extends ArrayAdapter<String> {
private String[] ids;
private String[] name;
private String[] location;
private String[] imgID;
private String[] rating;
private Activity context;

public CustomSearchList(Activity context, String[] ids, String[] name, String[] location, String[] imgID, String[] rating) {
        super(context, R.layout.list_view_layout, ids);
        this.context = context;
        this.ids = ids;
        this.name = name;
        this.location = location;
        this.imgID = imgID;
        this.rating = rating;
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
        textViewName.setText(name[position]);
        textViewAuthor.setText(location[position]);
        textViewBody.setText(imgID[position]);
        reviewRating.setRating(Float.parseFloat(rating[position]));

        return listViewItem;
    }
}
