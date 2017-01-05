package com.mal.univised;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FragmentPageActivity extends AppCompatActivity implements View.OnClickListener {


    private int dotsCount;
    private ImageView[] dots;
    private LinearLayout pager_indicator;
    private Button btnNext, btnFinish;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_page);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        btnFinish = (Button) findViewById(R.id.btn_finish);
        btnNext = (Button) findViewById(R.id.btn_next);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        /*mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("onPageScrolled","onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("onPageSelected","onPageSelected");
                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
                }

                dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

                if (position + 1 == dotsCount) {
                    btnNext.setVisibility(View.GONE);
                    btnFinish.setVisibility(View.VISIBLE);
                } else {
                    btnNext.setVisibility(View.VISIBLE);
                    btnFinish.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("onPageScrollStateChanged","onPageScrollStateChanged");
            }
        });*/


        setUiPageViewController();
        btnNext.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

    }

    private void setUiPageViewController() {

        dotsCount = mSectionsPagerAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_next:
                mViewPager.setCurrentItem((mViewPager.getCurrentItem() < dotsCount)
                        ? mViewPager.getCurrentItem() + 1 : 0);

                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
                }
                dots[mViewPager.getCurrentItem()].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

                if (mViewPager.getCurrentItem() + 1 == dotsCount) {
                    btnNext.setVisibility(View.GONE);
                    btnFinish.setVisibility(View.VISIBLE);
                } else {
                    btnNext.setVisibility(View.VISIBLE);
                    btnFinish.setVisibility(View.GONE);
                }
                break;

            case R.id.btn_finish:
                Intent i = new Intent(FragmentPageActivity.this, FinishActivity.class);
                startActivity(i);
                //finish();
                break;
        }
    }


}