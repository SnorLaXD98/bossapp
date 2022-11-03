package com.stepbystep.bossapp.DO;


import java.util.ArrayList;
import java.util.Date;

public class Order_history {

    String truck_name;
    ArrayList<Order> orders;
    String date;
    String image;
    String truck_id;
    Date ddate;
    public Order_history() {
    }


    public Order_history(String truck_name, ArrayList<Order> orders, String date, String image, String truck_id, Date ddate) {
        this.truck_name = truck_name;
        this.orders = orders;
        this.date = date;
        this.image = image;
        this.truck_id = truck_id;
        this.ddate = ddate;
    }

    public String getTruck_name() {
        return truck_name;
    }

    public void setTruck_name(String truck_name) {
        this.truck_name = truck_name;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(String truck_id) {
        this.truck_id = truck_id;
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }

    @Override
    public String toString() {
        return "Order_history{" +
                "truck_name='" + truck_name + '\'' +
                ", orders=" + orders +
                ", date='" + date + '\'' +
                ", image='" + image + '\'' +
                ", truck_id='" + truck_id + '\'' +
                ", ddate=" + ddate +
                '}';
    }
}
