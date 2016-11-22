package com.mal.univised;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.TextView;

/**
 * Created by samlucas on 03/11/2016.
 */

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    MainActivity main;
    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp()throws Exception{
        super.setUp();
        main = getActivity();
    }

    @SmallTest
    public void testTextViewNotNull(){
        TextView tv = (TextView)main.findViewById(R.id.username);
        assertNotNull(tv);
    }
}
