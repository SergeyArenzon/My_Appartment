package com.myappartment.my_appartment.Objects;



public class reqnode {
    private String title;
    private int price;
    private String dira;


    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public reqnode()
    {

    }
    public reqnode(String title,int price,String dira)
    {
        this.title=title;
        this.price=price;
        this.dira=dira;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDira() {
        return dira;
    }

    public void setDira(String dira) {
        this.dira = dira;
    }

    @Override
    public String toString() {
        return "reqnode [title=" + title + ", price=" + price + "]";
    }


}
