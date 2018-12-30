package com.myappartment.my_appartment;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.myappartment.my_appartment.payments.Createpayment;

public class tests {
    public boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }

    public static void main(String[]arg){




    //tests t = new tests();
   // System.out.println(t.isInteger("xxxx1"));



    }
}
