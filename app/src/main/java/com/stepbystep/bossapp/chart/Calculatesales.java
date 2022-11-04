package com.stepbystep.bossapp.chart;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stepbystep.bossapp.DO.Order_history;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Calculatesales {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<Order_history> order_histories = new ArrayList<>();
    private LocalDateTime date;



    public ArrayList<Float> MonthCalculateSales(String truckid){

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference =  firebaseDatabase.getReference("FoodTruck").child("Order_history");
        String key = databaseReference.getKey();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1 : snapshot.getChildren()) {  //반복문으로 리스트를 출력함
                    Order_history order_history = snapshot1.getValue(Order_history.class); // 객체에 데이터를 담는다
                    order_histories.add(order_history);
                    System.out.println("메롱" + order_histories);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // error
                Log.e("Calculatesales" , String.valueOf(error.toException()));
            }
        });







        return null;
    }


}
