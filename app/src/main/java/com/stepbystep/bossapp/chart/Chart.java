package com.stepbystep.bossapp.chart;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.stepbystep.bossapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class Chart extends Fragment {
    private View view;

    public Chart() {

    }

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_chart, container, false);

//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chart);

        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_sales);
        viewPager2 = (ViewPager2) view.findViewById(R.id.viewPager);


        FragmentManager manager = getChildFragmentManager();
        ViewPagerAdapter adapter = new ViewPagerAdapter(manager, getLifecycle());
        viewPager2.setAdapter(adapter);
        viewPager2.setSaveEnabled(false);

//        tabLayout.addTab(tabLayout.newTab().setText("월별 매출"));
//        tabLayout.addTab(tabLayout.newTab().setText("일별 매출"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {


            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });



        return view;

    }


}
