package com.colloquial.webchars;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.json.JSONArray;
import org.json.JSONObject;

public class GenJson {
    public static void main(String[] args) 
        throws IOException, UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out,true,"UTF-8"));
        /*x GenJson.1 */
        JSONObject fiddy = new JSONObject();
        fiddy.put("name","fifty-cent");
        JSONArray nicknames = new JSONArray();
        nicknames.put("fiddy");
        nicknames.put("50\u00a2");
        fiddy.put("nicknames",nicknames);
        /*x*/
        fiddy.put("lucky_number",2.99792458E8);
        JSONArray phones = new JSONArray();
        JSONObject phoneHome = new JSONObject();
        phoneHome.put("h","212-555-1234");
        phones.put(phoneHome);
        JSONObject phoneMobile = new JSONObject();
        phoneMobile.put("m","646-555-5678");
        phones.put(phoneMobile);
        /*x GenJson.2 */
        JSONObject phoneFax = new JSONObject();
        phoneFax.put("f",JSONObject.NULL);
        /*x*/
        phones.put(phoneFax);
        fiddy.put("phone",phones);

        System.out.println(fiddy.toString(2));
    }

}
