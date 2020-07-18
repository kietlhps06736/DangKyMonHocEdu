package com.example.dangkymonhoc.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.dangkymonhoc.R;
import com.google.android.material.tabs.TabLayout;

public class TrangThaiActivity extends AppCompatActivity {
    TabLayout mTabs;
    View mIndicator;
    ViewPager mViewPager;
    FragmentOne frag;
    private int indicatorWidth;
    String idSV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_thai);
        Intent intent = getIntent();
        idSV = intent.getStringExtra("idSV");
//        Log.d("idSVTrangThai", idSV);

//
//        Bundle bundle = new Bundle();
//        bundle.putString("ID", idSV );
//        FragmentOne frag = new FragmentOne();
//        frag.setArguments(bundle);

        //Assign view reference
        mTabs = findViewById(R.id.tab);
        mIndicator = findViewById(R.id.indicator);
        mViewPager = findViewById(R.id.viewPager);

        //Set up the view pager and fragments
        TabFragmentAdapter  adapter = new TabFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(FragmentThree.newInstance(), "Tất cả");
//        adapter.addFragment(FragmentTwo.newInstance(), "Đơn Đã Duyệt");
//        adapter.addFragment(FragmentOne.newInstance(), "Đơn Chưa Duyệt");


        mViewPager.setAdapter(adapter);
        mTabs.setupWithViewPager(mViewPager);

        //Determine indicator width at runtime
        mTabs.post(new Runnable() {
            @Override
            public void run() {
                indicatorWidth = mTabs.getWidth() / mTabs.getTabCount();

                //Assign new width
                FrameLayout.LayoutParams indicatorParams = (FrameLayout.LayoutParams) mIndicator.getLayoutParams();
                indicatorParams.width = indicatorWidth;
                mIndicator.setLayoutParams(indicatorParams);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float positionOffset, int positionOffsetPx) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)mIndicator.getLayoutParams();

                //Multiply positionOffset with indicatorWidth to get translation
                float translationOffset =  (positionOffset+i) * indicatorWidth ;
                params.leftMargin = (int) translationOffset;
                mIndicator.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }
    public String getMyId() {
        return idSV;
    }
}