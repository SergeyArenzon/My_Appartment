package com.myappartment.my_appartment.maincode;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Pages {
    public Sql sq;
    public static String user_name="";
    public static String user_dira="";
    public static String user_level="";
    //------------------------
    public static int monthpayments=0;
    public static ArrayList<usernode> monthlist=new ArrayList<usernode>();
    public static ArrayList<usernode> userlist=new ArrayList<usernode>();
    public static ArrayList<reqnode> reqlist=new ArrayList<reqnode>();

    public static void main(String[] args) throws SQLException {


        //----------------------
        // p.createquickdb();
         /*
         p.sq.sqlAddpayment(p.user_name, "tv0", "105", "6");
         p.AddPayment("tv0", "100");
         */
        /*
        p.GetUsersByRequest("tv0");
        for(usernode n:userlist)
        {

        	//mont
        	System.out.println(n.toString());
        }
        */

       /*
        p.GetAllRequests();
        for(reqnode n:reqlist)
        {

            //mont
            System.out.println(n.toString());
        }

        */
        Pages  p = new Pages();

        String name="m2i";
        String pass="123";
        boolean a=p.Login(name,pass);
        // String b=String.valueOf(a);
        // Toast.makeText(MainActivity.this,b,Toast.LENGTH_LONG).show();


        if(a)

        {
            System.out.print("rffr");
            //Toast.makeText(MainActivity.this,userLogin.getText().toString(),Toast.LENGTH_LONG).show();

        }

    }

    public Pages()
    {
        try {
            sq=new Sql();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    ///-----------------------
    public 	boolean Login(String name,String pass)
    {
        try {
                ResultSet rs=sq.getuser(name, pass);
                  if(rs.next())
                  {
                      while (rs.next()) {
                          user_name = rs.getString(1);
                          user_dira = rs.getString(3);
                          user_level = rs.getString(4);
                      }
                     return true;
                  }
                else{
                      return false;
                  }
           }
        catch (SQLException e) {
              return false;
        }
    }
    public void Signup(String name,String pass,String dira,String level)
    {
        try {


            sq.sqlAdduser(name, pass, dira, level);

        }
        catch (SQLException e) {


        }
    }
    //------------------------
    public void CreateReq(String title,String price)
    {

        if(user_level.equals("m"))
        {
            try {
                Sql sq2=new Sql();
                sq2.sqlAddrequest(title, price, user_dira);
                ResultSet rs2=sq2.getdira(user_dira);


                while(rs2.next())
                {



                    sq.sqlAddpayment(rs2.getString(1), title,"0", user_dira);

                }

            } catch (SQLException e) {

                System.out.println("sql error");
            }
        }
        else
        {throw new java.lang.RuntimeException("Only manger can create requests");}
    }
    //------------------------
    public void GetAllRequests()
    {
        reqlist=new ArrayList<>();
        try {
            ResultSet rs=sq.getrequests(user_dira);
            reqlist=new ArrayList<reqnode>();
            while(rs.next())
            {
                reqnode node=new reqnode(rs.getString(2), rs.getString(3));
                reqlist.add(node);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("error loading messages");
        }

    }
    //------------------------
    public void GetUsersByRequest(String title)
    {
        /**
         * after you run this by the title you choose
         * a list of usernode will be created
         */
        userlist=new ArrayList<>();
        try {
            int titleprice=sq.getpriceof_req(title);

            ResultSet rs=sq.getuserspayments(title,user_dira );
            userlist=new ArrayList<usernode>();
            while(rs.next())
            {
                double payment=Double.valueOf(rs.getString(3));
                double precent=100*(double)(payment/titleprice);
                String name=rs.getString(1);
                int s=(int)payment;
                usernode node=new usernode(name, s, precent);
                userlist.add(node);
            }
        } catch (SQLException e) {
            System.out.println("sql error");
        }
    }
    //------------------------
    public void AddPayment(String title,String paid)
    {
        try {

            sq.updatepayment(user_name, title, paid);
        } catch (SQLException e) {
            System.out.println("error sql");
        }
    }
    //------------------------

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void GetMonthlist() throws SQLException
    {
        monthpayments=0;
        monthlist=new ArrayList<>();
        ResultSet rs=null;
        int totalnum=0;
        HashMap<String, Integer> users = new HashMap<String, Integer>();
        System.out.println(user_dira);
        rs=sq.getmonthpayments(user_dira);
        while(rs.next())
        {

            String currentname=rs.getString(1);
            int currentpayment=Integer.valueOf(rs.getInt(3));
            totalnum=totalnum+currentpayment;
            if(users.containsKey(currentname))
            {
                int paid=users.get(currentname);
                paid=paid+currentpayment;
                users.replace(currentname, paid);
            }
            else
            {

                users.put(currentname, currentpayment);
            }
        }
        monthpayments=totalnum;

        Iterator it = users.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            int num=Integer.valueOf( pair.getValue().toString());
            String name=(String)pair.getKey();
            if(totalnum==0) {return;}

            int precent=(num*100/totalnum);

            usernode node=new usernode(name, num, precent);
            monthlist.add(node);
            it.remove(); // avoids a ConcurrentModificationException
        }


    }

    //------------------------
    public void createquickdb()
    {

        //insert users

        for(int i=0;i<10;i++)
        {
            Signup("user"+i, "123"+i, "6", "d");
        }
        //create requests
        for(int i=0;i<10;i++)
        {


            CreateReq("tv"+i, "100"+i);

        }

    }
}
