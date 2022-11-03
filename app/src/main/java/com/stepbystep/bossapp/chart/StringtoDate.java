package com.stepbystep.bossapp.chart;

import com.stepbystep.bossapp.DO.Order_history;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringtoDate {

    private static Date parse_date;

    public static Date changetodata(String stringdate){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        try {
            parse_date = dateFormat.parse(stringdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  parse_date;
    }


}
