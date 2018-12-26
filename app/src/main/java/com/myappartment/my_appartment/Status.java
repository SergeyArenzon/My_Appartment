package com.myappartment.my_appartment;

import com.myappartment.my_appartment.Objects.reqnode;


import java.util.ArrayList;

public class Status {

    //---------
      public static String name="";
      public static String email="";
      public static boolean manager;
      public static String dira="";
     //------------------
     public static ArrayList<reqnode> requests2=new ArrayList<reqnode>();
     //-----------------------
     public static String title="";
    public static int price;

    //-------
    public Status()
    {

    }
    public Status(String name,String email,boolean manager,String dira)
    {
         this.name=name;
         this.email=email;
         this.manager=manager;
         this.dira=dira;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Status.email = email;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Status.name = name;
    }

    public static boolean isManager() {
        return manager;
    }

    public static void setManager(boolean manager) {
        Status.manager = manager;
    }


}
