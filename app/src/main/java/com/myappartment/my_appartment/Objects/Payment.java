package com.myappartment.my_appartment.Objects;

import com.myappartment.my_appartment.Status;

public class Payment {
    public  int paid;
    public Double precent;
    public String name;


    public Payment()
    {}
    public Payment(int money,String name)
    {
        paid=money;
        Double a=Double.valueOf(money);
        Double b=Double.valueOf(Status.price);
        precent= 100 *  (a / b);
       this.name=name;
    }


    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrecent() {
        return precent;
    }

    public void setPrecent(Double precent) {
        this.precent = precent;
    }
}

