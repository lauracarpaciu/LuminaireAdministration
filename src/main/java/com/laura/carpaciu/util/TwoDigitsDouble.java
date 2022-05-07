package com.laura.carpaciu.util;

public class TwoDigitsDouble {

    private TwoDigitsDouble() {
    }


    public static double formatPrice(double price){
       return Math.round(price * 100) / 100.0d;

    }


}
