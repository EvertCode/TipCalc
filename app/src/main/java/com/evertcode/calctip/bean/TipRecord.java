package com.evertcode.calctip.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Hebert on 04/06/2016.
 */
public class TipRecord {


    private double subTotal;
    //private double bill;
    private int tipPercentage;
    private int person;
    private Date timestamp;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(int tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public double getBill() {
        return (this.subTotal+this.getTip());
    }

   /*public void setBill(double bill) {
        this.bill = bill;
    }*/

    public double getTip(){
        return this.subTotal*(this.tipPercentage/100d);
    }

    public double getTipByPerson(){
        return this.getBill()/this.getPerson();
    }

    public String getDateFormatted(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return simpleDateFormat.format(this.timestamp);
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }
}
