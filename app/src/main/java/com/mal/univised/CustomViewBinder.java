package com.mal.univised;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.IOException;


class CustomViewBinder implements SimpleAdapter.ViewBinder {
    public boolean setViewValue(View view, Object inputData, String textRepresentation) {
        int id = view.getId();
        String data = (String) inputData;
        switch (id) {
            case R.id.imageuni:
                populateImage(view, data);
                break;

            case R.id.location:
                populateLocation(view, data);
                break;

            case R.id.name:
                populateName(view, data);
                break;

        }
        return true;
    }
    public void populateImage(View view, String imageData) {
        //Context context;
        //ImageView uniImage = (ImageView) view.findViewById(R.id.imageuni);
        //int resId = context.getResources().getIdentifier("testimage", "drawable", getPackageName());
        //image.setImageResource(resId);
        //uniImage.setImageDrawable(Drawable.createFromPath(imageData));
    }

    public void populateLocation(View view, String data) {
        TextView locationTxt = (TextView) view.findViewById(R.id.location);
        locationTxt.setText(data);
    }

    public void populateName(View view, String data) {
        TextView dateTxt = (TextView) view.findViewById(R.id.name);
        dateTxt.setText(data);
    }
}