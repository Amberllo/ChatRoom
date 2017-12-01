package com.chatroom.client;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/2 0002.
 */
public class DateUtils {
    public static String getTime(){
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    public static void main(String[] args){
        System.out.println(getTime());
    }
}
