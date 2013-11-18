package com.colloquial.webchars;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GetJson {
    public static void main(String[] args) {
        try {
            /*x GetJson.1 */
            URL url 
                = new URL("http://earthquake.usgs.gov/earthquakes/"
                          + "feed/v0.1/summary/all_hour.geojson");
            HttpURLConnection conn
                = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept","application/json");
            conn.connect();
            /*x*/
            if (conn.getResponseCode() != 200) {
                throw new IOException("Failed : HTTP error code : " 
                                      + conn.getResponseCode());
            }

            /*x GetJson.2 */
            String charset = "UTF-8";
            String contentType = conn.getContentType();
            /*x*/
            if (contentType != null) {
                Pattern pattern = Pattern.compile(".*charset=(.*)");
                Matcher matcher = pattern.matcher(contentType);
                if (matcher.matches()) { 
                    charset = matcher.group(1);
                }
            }

            /*x GetJson.3 */
            String content
                = readFromStream(conn.getInputStream(),charset);
            JSONTokener tokener = new JSONTokener(content);
            JSONObject quakes = new JSONObject(tokener);
            /*x*/
            conn.disconnect();

            if (quakes != null) {
                /*x GetJson.4 */
                String geoType = quakes.getString("type");
                System.out.println("geoJSON object type: " + geoType);
                JSONArray results = quakes.getJSONArray("features");
                System.out.println("number of quakes in past hour: " 
                                   + results.length());
                /*x*/
            } else {
                System.out.println("null response from usgs");
            }
        } catch (Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } 
    }

    public static String readFromStream(InputStream in,String encoding) 
        throws IOException {
        InputStreamReader reader = null;
        BufferedReader bufReader = null;
        try {
            reader = new InputStreamReader(in,encoding);
            bufReader = new BufferedReader(reader);
            String line = null;
            StringBuilder builder = new StringBuilder();
            while ((line = bufReader.readLine()) != null) {
                builder.append(line); 
                builder.append('\n');
            }
            return builder.toString();
        } finally {
            close(reader);
            close(bufReader);
        }
    }

    public static void close(Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (IOException e) {
            /* ignore exception */
        }
    }

}
