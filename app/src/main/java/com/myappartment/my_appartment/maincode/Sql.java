package com.myappartment.my_appartment.maincode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sql {
    public  Connection con;
    public   Statement stmt;
    public String dbhost="den1.mysql2.gear.host";
    public String dbuser="myappartment";
    public String dbname="myappartment";
    public String passw="qwe123!";


    public static void main(String[] args) throws SQLException {

        Sql a=new Sql();

        //a.updatepayment("roey", "tv", "70");
        //a.sqlAddpayment("mf", "mf", "mf", "mf");
        //a.sqlAdduser("roey", "123", "5", "d");
        //a.sqlAddpayment("roey", "tv", "30", "5");
        //a.updatepayment("roey", "tv", "70");
        //a.sqlAddrequest("tv", "200", "6");
        //System.out.println(a.getpriceof_req("tv"));


        ResultSet r=null;
        r=a.getuser("m2i","123");
        while(r.next())
        {
            System.out.println(r.getString(1));
        }



    }
    public Sql() throws SQLException
    {
        //"root"-ùí äîùúîù
        //"1234"-ñéñîà
        //DIROT-NAME OF THE DATA BASE
        con=DriverManager.getConnection("jdbc:mysql://"+dbhost+":3306/"+dbname
                ,dbuser,passw);
        stmt=con.createStatement();



    }
    //---------add---
    public void sqlAdduser(String name,String pass,String dira,String level) throws SQLException
    {
        String sql="insert into "+dbname+".users values('mako1','mako2','mako3','mako4')";
        sql=sql.replaceAll("mako1", name).replaceAll("mako2", pass).replaceAll("mako3",dira).replaceAll("mako4", level);
        int a=stmt.executeUpdate(sql);
    }
    public void sqlAddrequest(String title,String price,String dira) throws SQLException
    {
        String sql="insert into "+dbname+".requests values(now(),'mako1','mako2','mako3')";
        sql=sql.replaceAll("mako1", title).replaceAll("mako2", price).replaceAll("mako3", dira);
        int a=stmt.executeUpdate(sql);
    }
    public void sqlAddpayment(String name,String title,String paid,String dira) throws SQLException
    {
        String sql="insert into "+dbname+".payments values('mako0','mako1','mako2','mako3')";
        sql=sql.replaceAll("mako0", name).replaceAll("mako1", title).replaceAll("mako2", paid).replaceAll("mako3", dira);
        int a=stmt.executeUpdate(sql);
    }
    //--------update--------------
    public void updatepayment(String name,String title,String paid) throws SQLException
    {
        String sql="update "+dbname+".payments set paid='emo' where name='mako1' and title='mako2'";
        sql=sql.replaceAll("emo", paid).replaceAll("mako1", name).replaceAll("mako2", title);
        int a=stmt.executeUpdate(sql);

    }
    //---------get---
    public ResultSet getrequests(String dira) throws SQLException
    {
        /**
         * get all the requests for main page
         */
        ResultSet rs=null;
        String sql="SELECT * FROM "+dbname+".requests where dira='fok' order by mydate";
        sql=sql.replaceAll("fok", dira);
        rs=stmt.executeQuery(sql);


        return rs;
    }
    public ResultSet getuser(String name,String pass) throws SQLException
    {
        //---for signin
        ResultSet rs=null;
        String sql="SELECT * FROM "+dbname+".users where name='fok' and pass='shit'";
        sql=sql.replaceAll("fok", name).replaceAll("shit", pass);
        rs=stmt.executeQuery(sql);


        return rs;
    }
    public ResultSet getdira(String dir) throws SQLException
    {
        ResultSet rs=null;
        String sql="SELECT * FROM "+dbname+".users where dira='fok'";
        sql=sql.replaceAll("fok", dir);
        rs=stmt.executeQuery(sql);


        return rs;
    }
    public ResultSet getuserspayments(String title,String dir) throws SQLException
    {
        ResultSet rs=null;
        String sql="SELECT * FROM "+dbname+".payments where dira='fok' and title='fik' ";
        sql=sql.replaceAll("fok", dir).replaceAll("fik", title);
        rs=stmt.executeQuery(sql);


        return rs;
    }
    public ResultSet getmonthpayments(String dira) throws SQLException
    {
        ResultSet rs=null;
        String s="SELECT * FROM "+dbname+".payments where dira='mako'";
        s=s.replace("mako", dira);
        rs=stmt.executeQuery(s);
        return rs;
    }
    //------extra get---------
    public int getpriceof_req(String title) throws SQLException
    {
        ResultSet rs=null;
        String sql="SELECT price FROM "+dbname+".requests where title='fok'";

        sql=sql.replaceAll("fok", title);

        rs=stmt.executeQuery(sql);
        int a=0;
        while(rs.next())
        {
            a=Integer.valueOf(rs.getString(1));

        }


        return a;
    }




}