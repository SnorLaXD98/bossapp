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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.stepbystep.bossapp.R;

import java.util.ArrayList;

public class MonthChartFragment extends Fragment {

    public static MonthChartFragment newInstance() {
        MonthChartFragment monthchart = new MonthChartFragment();
        return monthchart;
    }

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      view = inflater.inflate(R.layout.fragment_monthchart, container, false);


      /*    막대그래프   */
      BarChart barChart = (BarChart) view.findViewById(R.id.month_chart);



        String[] months = {"","월","1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"}; // 왜인지 모르겠는데 이렇게 해야 맞음

        ArrayList<BarEntry> visitors = new ArrayList<>();

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
      xAxis.setAxisMaximum(14);
      xAxis.setAxisMinimum(1);
      xAxis.setGranularity(10f);

      xAxis.setValueFormatter(new IndexAxisValueFormatter(months));

      xAxis.setTextSize(10f);
      xAxis.setGranularityEnabled(true);

      yAxis = barChart.getAxisLeft();
      barChart.getAxisRight().setEnabled(false);

      yAxis.setTextColor(Color.BLACK);
      yAxis.setAxisLineColor(Color.BLACK);
      yAxis.setDrawAxisLine(false);
      yAxis.setDrawGridLines(false);
//      yAxis.setAxisMaximum(1f);
//      yAxis.setAxisMinimum(0f);
      yAxis.setSpaceMax(0.2f);
      yAxis.setSpaceMin(0.2f);

        visitors.add(new BarEntry(2,100f));
        visitors.add(new BarEntry(3,200f));
        visitors.add(new BarEntry(4,300f));
        visitors.add(new BarEntry(5,200f));
        visitors.add(new BarEntry(6,600f));
        visitors.add(new BarEntry(7,100f));
        visitors.add(new BarEntry(8,200f));
        visitors.add(new BarEntry(9,300f));
        visitors.add(new BarEntry(10,200f));
        visitors.add(new BarEntry(11,600f));
        visitors.add(new BarEntry(12,100f));
        visitors.add(new BarEntry(13,200f));





        BarDataSet barDataSet = new BarDataSet(visitors,"단위 : 만원");
        barDataSet.setFormSize(3f);
        barDataSet.setColors(Color.parseColor("#00b2ce"));
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(0.2f);
        barDataSet.setDrawValues(true);

       // barDataSet.setFormLineWidth(10f);


        BarData bardata = new BarData(barDataSet);
        bardata.setBarWidth(0.3f); // 바의 두께

        barChart.setFitBars(true);
        barChart.setData(bardata);
        barChart.getDescription().setText("매출액");
        barChart.animateY(1000);
        barChart.setTouchEnabled(false); // 그래프 확대 못하게 고정
        barChart.setPinchZoom(false);
        MyMarkerView mv1 = new MyMarkerView( getContext(),R.layout.custom_marker_view);
        mv1.setChartView(barChart);



       //선그래프

//        LineChart lineChart = (LineChart) view.findViewById(R.id.day_time_chart) ;
//
//
//        lineChart.setExtraBottomOffset(15f); // 간격
//        lineChart.getDescription().setEnabled(false); // chart 밑에 description 표시 유무
//
//        // Legend는 차트의 범례
//        Legend legend = lineChart.getLegend();
//        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
//        legend.setForm(Legend.LegendForm.CIRCLE);
//        legend.setFormSize(10);
//        legend.setTextSize(13);
//        legend.setTextColor(Color.parseColor("#A3A3A3"));
//        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
//        legend.setDrawInside(false);
//        legend.setYEntrySpace(5);
//        legend.setWordWrapEnabled(true);
//        legend.setXOffset(80f);
//        legend.setYOffset(20f);
//        legend.getCalculatedLineSizes();
//
//        // XAxis (아래쪽) - 선 유무, 사이즈, 색상, 축 위치 설정
//        XAxis xAxis = lineChart.getXAxis();
//        xAxis.setDrawAxisLine(false);
//        xAxis.setDrawGridLines(false);
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // x축 데이터 표시 위치
//        xAxis.setGranularity(1f);
//        xAxis.setTextSize(14f);
//        xAxis.setTextColor(Color.rgb(118, 118, 118));
//        xAxis.setSpaceMin(0.1f); // Chart 맨 왼쪽 간격 띄우기
//        xAxis.setSpaceMax(0.1f); // Chart 맨 오른쪽 간격 띄우기
//
//        // YAxis(Right) (왼쪽) - 선 유무, 데이터 최솟값/최댓값, 색상
//        YAxis yAxisLeft = lineChart.getAxisLeft();
//        yAxisLeft.setTextSize(14f);
//        yAxisLeft.setTextColor(Color.rgb(163, 163, 163));
//        yAxisLeft.setDrawAxisLine(false);
//        yAxisLeft.setAxisLineWidth(2);
////        yAxisLeft.setAxisMinimum(0f); // 최솟값
////        yAxisLeft.setAxisMaximum((float) RANGE[0][range]); // 최댓값
////        yAxisLeft.setGranularity((float) RANGE[1][range]);
//
//        // YAxis(Left) (오른쪽) - 선 유무, 데이터 최솟값/최댓값, 색상
//        YAxis yAxis = lineChart.getAxisRight();
//        yAxis.setDrawLabels(false); // label 삭제
//        yAxis.setTextColor(Color.rgb(163, 163, 163));
//        yAxis.setDrawAxisLine(false);
//        yAxis.setAxisLineWidth(2);
////        yAxis.setAxisMinimum(0f); // 최솟값
////        yAxis.setAxisMaximum((float) RANGE[0][range]); // 최댓값
////        yAxis.setGranularity((float) RANGE[1][range]);
//
//        // XAxis에 원하는 String 설정하기 (날짜)
//        xAxis.setValueFormatter(new ValueFormatter() {
//
//            @Override
//            public String getFormattedValue(float value) {
//                return LABEL[range][(int) value];
//            }
//        });
//    }




      return view;
    }



}



