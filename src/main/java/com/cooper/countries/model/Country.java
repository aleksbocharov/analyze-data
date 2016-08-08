package com.cooper.countries.model;

import javax.persistence.*;
import java.text.DecimalFormat;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private String code;
    @Column
    private String name;
    @Column
    private Double internetUsers;
    @Column
    private Double adultLiteracyRate;

    public Country(){}
    public Country(String name, double internetUsers, double adultLiteracyRate) {
        this.name = name;
        this.internetUsers = internetUsers;
        this.adultLiteracyRate = adultLiteracyRate;
    }
    @Override
    public String toString() {
        DecimalFormat f = new DecimalFormat("##.00");
        String iO = this.internetUsers!=null ? f.format(this.internetUsers) : "--";
        String aDR = this.adultLiteracyRate!=null ? f.format(this.adultLiteracyRate) : "--";
        String format = "%-30s%-15s%s%n";
        return String.format(format,this.name, iO ,aDR);
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getInternetUsers() {
        return internetUsers;
    }

    public void setInternetUsers(Double internetUsers) {
        this.internetUsers = internetUsers;
    }

    public Double getAdultLiteracyRate() {
        return adultLiteracyRate;
    }

    public void setAdultLiteracyRate(Double adultLiteracyRate) {
        if(adultLiteracyRate==null)
            this.adultLiteracyRate = 0.0;
        else
            this.adultLiteracyRate = adultLiteracyRate;
    }
}
