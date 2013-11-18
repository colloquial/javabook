package com.colloquial.io;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlProperties {

    /*x UrlProperties.1 */
    public static void main(String[] args) 
        throws MalformedURLException {

        String url = args[0];
        URL u = new URL(url);
        System.out.println("getAuthority()=" + u.getAuthority());
    /*x*/
        System.out.println("getDefaultPort()=" + u.getDefaultPort());
        System.out.println("getFile()=" + u.getFile());
        System.out.println("getHost()=" + u.getHost());
        System.out.println("getPath()=" + u.getPath());
        System.out.println("getPort()=" + u.getPort());
        System.out.println("getProtocol()=" + u.getProtocol());
        System.out.println("getQuery()=" + u.getQuery());
        System.out.println("getRef()=" + u.getRef());
        System.out.println("getUserInfo()=" + u.getUserInfo());
        System.out.println("toExternalForm()=" + u.toExternalForm());
        System.out.println("toString()=" + u.toString());
    }

}