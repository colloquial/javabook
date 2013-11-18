package com.colloquial.applucene;

import java.util.Date;

import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.DateTools.Resolution;

public class DateDemo {
    
    public static void main(String[] args) {
        /*x DateDemo.1 */
        Date now = new Date();
        String strMilli
            = DateTools.dateToString(now,
                                     Resolution.MILLISECOND);
        String strDay
            = DateTools.dateToString(now,
                                     Resolution.DAY);
        long numDay = 
            DateTools.round(now.getTime(),
                            Resolution.DAY);
        /*x*/

        System.out.println("now: " + now.toString());
        System.out.println("strMilli: " + strMilli);
        System.out.println("strDay: " + strDay);
        System.out.println("time now: " + now.getTime());
        System.out.println("time rounded: " + numDay);
        
    }

}