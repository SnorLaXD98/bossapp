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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stepbystep.bossapp.DO.StoreAccount;
import com.stepbystep.bossapp.R;

import java.util.ArrayList;

public class MonthChartFragment extends Fragment {

    public static MonthChartFragment newInstance() {
        MonthChartFragment monthchart = new MonthChartFragment();
        return monthchart;
    }

    private View view;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    FirebaseUser user;
    String truck_id;
    ArrayList<StoreAccount>  storeAccounts = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      view = inflater.inflate(R.layout.fragment_monthchart, container, false);

      mAuth = FirebaseAuth.getInstance();
      user = mAuth.getCurrentUser();


      firebaseDatabase = FirebaseDatabase.getInstance();
      databaseReference =  firebaseDatabase.getReference("BossApp").child("StoreAccount");
      databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
       @Override
       public void onDataChange(@NonNull DataSnapshot snapshot) {
               storeAccounts.clear();
                 for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                     StoreAccount storeAccount = dataSnapshot.getValue(StoreAccount.class);
                        System.out.println(storeAccount);
                        if(storeAccount.getIdToken() != null) {
                            if (storeAccount.getIdToken().equals(user.getUid())) {
                                //storeAccounts.add(storeAccount);
                                truck_id = storeAccount.getTruck().getId();
                                Calculatesales calculatesales = new Calculatesales();
                                calculatesales.MonthCalculateSales(truck_id);
                            }
                        }
                 }

           System.out.println(truck_id);
       }

       @Override
       public void onCancelled(@NonNull DatabaseError error) {

       }
     });









      /*    막대그래프   */
      BarChart barChart = (BarChart) view.findViewById(R.id.month_chart);
      String[] months = {"","","1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"}; // 왜인지 모르겠는데 이렇게 해야 맞음
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
      xAxis.setAxisMaximum(14);
      xAxis.setAxisMinimum(1);
      xAxis.setLabelCount(12);
      
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
//      yAxis.setAxisMaximum(1f);
//      yAxis.setAxisMinimum(0f);
      yAxis.setSpaceMax(0.2f);
      yAxis.setSpaceMin(0.2f);

        sales.add(new BarEntry(2f,100f)); //1월임 0과 1 인덱스 때문에
        sales.add(new BarEntry(3f,200f));
        sales.add(new BarEntry(4f,300f));
        sales.add(new BarEntry(5f,200f));
        sales.add(new BarEntry(6f,600f));
        sales.add(new BarEntry(7f,100f));
        sales.add(new BarEntry(8f,200f));
        sales.add(new BarEntry(9f,300f));
        sales.add(new BarEntry(10f,200f));
        sales.add(new BarEntry(11f,600f));
        sales.add(new BarEntry(12f,100f));
        sales.add(new BarEntry(13f,200f));





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



