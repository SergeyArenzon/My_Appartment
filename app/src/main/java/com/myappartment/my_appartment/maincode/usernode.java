package com.myappartment.my_appartment.maincode;



public class usernode {
    private String name;
    private  int payment;
    private double precent;
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    public usernode(String name,int payment,double precent)
    {
        this.name=name;
        this.payment=payment;
        this.precent=precent;

    }
    ///-----get and set-----
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPayment() {
        return payment;
    }
    public void setPayment(int payment) {
        this.payment = payment;
    }
    public double getPrecent() {
        return precent;
    }
    public void setPrecent(double precent) {
        this.precent = precent;
    }
    @Override
    public String toString() {
        return "usernode [name=" + name + ", payment=" + payment + ", precent=" + precent + "]";
    }

}
