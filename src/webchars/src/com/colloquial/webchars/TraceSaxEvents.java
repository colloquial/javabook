package com.colloquial.webchars;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class TraceSaxEvents {
    public static void main(String[] args) 
        throws IOException, SAXException {

        System.out.println("Reading doc with URL=" + args[0]);
        System.out.println("events:");

        /*x TraceSaxEvents.2 */
        InputSource in = new InputSource(args[0]);
        XMLReader reader = XMLReaderFactory.createXMLReader();
        reader.setContentHandler(new TraceHandler());
        reader.parse(in);
        /*x*/
        
        System.out.println("Done");
    }

    /*x TraceSaxEvents.1 */
    static class TraceHandler extends DefaultHandler {
        public void characters(char[] cs, int start, int len) {
            System.out.println("characters: |" 
                            + new String(cs,start,len)
                            + "|");
        }
        public void startDocument() {
            System.out.println("startDocument");
        }
        public void startElement(String namespaceURI, 
                                 String localName, 
                                 String qName, 
                                 Attributes atts) {
            System.out.println("startElement: " + qName);
        }
        public void endDocument() {
            System.out.println("endDocument");
        }
        public void endElement(String namespaceURI, 
                               String localName, 
                               String qName) {
            System.out.println("endElement: " + qName);
        }
    }
    /*x*/

}