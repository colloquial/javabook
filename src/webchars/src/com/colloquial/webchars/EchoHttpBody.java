package com.colloquial.webchars;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;

import java.net.URL;
import java.net.URLConnection;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoHttpBody {

    public static void main(String[] args) {
        try {
            System.out.println("URL: "+args[0]);
            /*x EchoHttpBody.1 */
            URL url = new URL(args[0]);
            URLConnection connection 
                = (URLConnection)url.openConnection();
            connection.connect();
            /*x*/

            /*x EchoHttpBody.2 */
            String charset = "ISO-8859-1";
            String contentType = 
                connection.getContentType();
            if (contentType != null) {
                Pattern pattern = Pattern.compile(".*charset=(.*)");
                Matcher matcher = pattern.matcher(contentType);
                if (matcher.matches()) { 
                    charset = matcher.group(1);
                }
            }
            /*x*/
            System.out.println("Content-Type: " + contentType);
            System.out.println("charset: " + charset);

            /*x EchoHttpBody.3 */
            InputStream in = connection.getInputStream();
            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            int b;
            while ((b = in.read()) != -1) {
                bytesOut.write((byte)b);
            }
            in.close();
            byte[] respBytes = bytesOut.toByteArray();
            bytesOut.close();
            String respString = new String(respBytes,charset);
            /*x*/

            System.out.println("HTTP Response body:");
            /*x EchoHttpBody.4 */
            OutputStreamWriter charsetWriter
                = new OutputStreamWriter(System.out, charset);
            PrintWriter out
                = new PrintWriter(charsetWriter, true);
            out.println(respString);
            /*x*/

        } catch (Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }

    }


}
