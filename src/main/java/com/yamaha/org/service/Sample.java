package com.yamaha.org.service;

import java.math.BigDecimal;

public class Sample {
    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal(3000);

        System.out.println(bd.setScale(2));
    }
}
