package com.myappartment.my_appartment.maincode;



public class reqnode {
    private String title;
    private String price;



    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public reqnode(String title,String price)
    {
        this.title=title;
        this.price=price;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "reqnode [title=" + title + ", price=" + price + "]";
    }


}
