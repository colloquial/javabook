package com.colloquial.webchars;

import java.io.IOException;

import java.net.URLConnection;
import java.net.URL;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EchoHttpHeader {

    public static void main(String[] args) {
        try {
            System.out.println("URL: "+args[0]);
            /*x EchoHttpHeader.1 */
            URL url = new URL(args[0]);
            URLConnection connection 
                = (URLConnection)url.openConnection();
            connection.connect();
            /*x*/
            System.out.println("successful connection");
            System.out.println("HTTP Response header fields:");
            /*x EchoHttpHeader.2 */
            Map<String,List<String>> headerFields 
                = connection.getHeaderFields();
            /*x*/
            for (Iterator it=headerFields.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String,List<String>> entry = (Map.Entry)it.next();
                System.out.print("field name: " + entry.getKey());
                List<String> values = entry.getValue();
                if (values.size() == 1) {
                    System.out.println("\tvalue: " + values.get(0));
                } else {
                    System.out.print("\tvalues : ");
                    for (int i = 0; i<values.size(); i++) {
                        System.out.print(values.get(i));
                        if (i < values.size()-1) System.out.print(", ");
                    }
                    System.out.println();
                }
            }
        } catch (Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}

