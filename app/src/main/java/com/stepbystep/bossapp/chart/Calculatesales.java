package com.stepbystep.bossapp.chart;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stepbystep.bossapp.DO.Order_history;

import java.util.ArrayList;
import java.util.Date;

public class Calculatesales {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<Order_history> order_histories;
    private Date date;



    public ArrayList<Float> MonthCalculateSales(String truckid){

        databaseReference = (DatabaseReference) firebaseDatabase.getReference("FoodTruck").child("order_history").equalTo(truckid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                order_histories.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {  //반복문으로 리스트를 출력함
                    Order_history order_history = snapshot1.getValue(Order_history.class); // 객체에 데이터를 담는다
                    order_histories.add(order_history);
                    System.out.println(order_history);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // error
                Log.e("Calculatesales" , String.valueOf(error.toException()));
            }
        });

       for(int i = 0; i < order_histories.size(); i++){
           String stringdate = order_histories.get(i).getDate();
           date =  StringtoDate.changetodata(stringdate);
           order_histories.get(i).setDdate(date);

       }





        return null;
    }


}
