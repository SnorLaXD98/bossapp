package com.stepbystep.bossapp.chart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }





    // 상단의 탭 레이아웃 인디케이터 쪽에 텍스트를 선언해주는 곳

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        switch(position){
//            case 0:
//                return "월별 매출";
//            case 1:
//                return "일별 매출";
//            default :
//                return null;
//        }
//    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new MonthChartFragment();
            case 1:
                return new DayChartFragment();
            default :
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
