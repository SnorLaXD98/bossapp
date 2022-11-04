package com.stepbystep.bossapp.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.stepbystep.bossapp.R;

import java.util.ArrayList;

public class DayChartFragment extends Fragment {

    public static DayChartFragment newInstance() {
        DayChartFragment daychart = new DayChartFragment();
        return daychart;
    }



    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      view = inflater.inflate(R.layout.fragment_daychart, container, false);

        BarChart barChart = (BarChart) view.findViewById(R.id.day_chart);
        String[] months = {"","","월","화","수","목","금","토","일"}; // 왜인지 모르겠는데 이렇게 해야 맞음
        ArrayList<BarEntry> sales = new ArrayList<>();
        XAxis xAxis;
        YAxis yAxis;


        xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10);
        // 레이블 텍스트 색
        xAxis.setTextColor(Color.BLACK);
        // 축 색
        xAxis.setAxisLineColor(Color.BLACK);
        // 그래프 뒷 배경의 그리드 표시하지 않기
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);
        xAxis.setAxisMaximum(9);
        xAxis.setAxisMinimum(1);
        xAxis.setLabelCount(7);

        xAxis.setGranularity(-1f); // x 축 벨류 간 간격

        xAxis.setValueFormatter(new IndexAxisValueFormatter(months));

        xAxis.setTextSize(8f);
        xAxis.setGranularityEnabled(false);

        yAxis = barChart.getAxisLeft();
        barChart.getAxisRight().setEnabled(false);

        yAxis.setTextColor(Color.BLACK);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setDrawAxisLine(false);
        yAxis.setDrawGridLines(false);
//        yAxis.setAxisMaximum(1f);
        yAxis.setAxisMinimum(0f);
        yAxis.setSpaceMax(0.2f);
        yAxis.setSpaceMin(0.2f);

        sales.add(new BarEntry(2f,10f)); //1월임 0과 1 인덱스 때문에
        sales.add(new BarEntry(3f,20f));
        sales.add(new BarEntry(4f,30f));
        sales.add(new BarEntry(5f,20f));
        sales.add(new BarEntry(6f,60f));
        sales.add(new BarEntry(7f,10f));
        sales.add(new BarEntry(8f,20f));







        BarDataSet barDataSet = new BarDataSet(sales,"매출액");
        barDataSet.setFormSize(5f);
        barDataSet.setColors(Color.parseColor("#00b2ce"));
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(0.2f);
        barDataSet.setDrawValues(true);

        // barDataSet.setFormLineWidth(10f);


        BarData bardata = new BarData(barDataSet);
        bardata.setBarWidth(0.3f); // 바의 두께

        barChart.setFitBars(true);
        barChart.setData(bardata);
        barChart.getDescription().setText("단위 : 만원");
        barChart.animateY(1000);
        barChart.setTouchEnabled(true); // 터치는 가능하게 함
        barChart.setPinchZoom(false);  // 줌 도 못하게 고정
        barChart.setDoubleTapToZoomEnabled(false); // 더블 탭 확대 못하게 고정
        MyMarkerView mv1 = new MyMarkerView( getContext(),R.layout.custom_marker_view); // 마커뷰
        mv1.setChartView(barChart);
        barChart.setMarker(mv1);










      return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }
}
