package com.colloquial.webchars;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Text;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class JDomGenerateXml {
    /*x JDomGenerateXml.1*/
    public static String CHEERS = "cheers!";
    public static String SANTE = "sant\u00e9!";
    public static String NAZDROV = "\u041d\u0430 \u0437\u0434\u043e\u0440\u0432\u044c!";
    public static String GAMBAE = "\uac74\ubc30!";
    /*x*/

    public static void main(String[] args) 
        throws IOException{
        
        String encoding = args[0];
        String fileName = args[1];
        System.out.println("Encoding: " + encoding);
        System.out.println("Output file: "+ fileName);

        /*x JDomGenerateXml.2*/
        Element root = new Element("toast_set");
        Document document = new Document(root);
        /*x*/

        /*x JDomGenerateXml.3*/
        Element e1 = new Element("toast");
        e1.setAttribute("lang","English");
        e1.addContent(new Text(CHEERS));
        root.addContent(e1);

        Element e2 = new Element("toast");
        e2.setAttribute("lang","French");
        e2.addContent(new Text(SANTE));
        root.addContent(e2);

        Element e3 = new Element("toast");
        e3.setAttribute("lang","Russian");
        e3.addContent(new Text(NAZDROV));
        root.addContent(e3);

        Element e4 = new Element("toast");
        e4.setAttribute("lang","Korean");
        e4.addContent(new Text(GAMBAE));
        root.addContent(e4);
        /*x*/

        /*x JDomGenerateXml.4*/
        Format format = Format.getPrettyFormat();
        format.setEncoding(encoding);
        FileOutputStream fileOut
            = new FileOutputStream(fileName);
        OutputStreamWriter writer
            = new OutputStreamWriter(fileOut,encoding);
        BufferedWriter bufWriter 
            = new BufferedWriter(writer);
        XMLOutputter outputter
            = new XMLOutputter(format);
        outputter.output(document,bufWriter);
        /*x*/

        bufWriter.close();
        writer.close();
        fileOut.close();
    }
}

