package com.example.quizjepang.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.quizjepang.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class Common {

    public static List<Quiz> questionList = new ArrayList<>();
    public static ArrayList imageList = new ArrayList();
    public static String level;
    public static final String User_Name = "User_Name";


    //Koneksi Internet
    public static boolean isConnectedToInternet(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager !=null){
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                if(info != null){
                    for (int i=0;i<info.length;i++){
                        if(info[i].getState() == NetworkInfo.State.CONNECTED)
                            return  true;
                    }
                }
        }
        return false;
    }

}
