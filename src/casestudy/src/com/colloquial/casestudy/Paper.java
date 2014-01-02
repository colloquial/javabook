package com.colloquial.casestudy;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jdom2.Element;
import org.jdom2.Text;

/*x Paper.1 */
/** Paper:  domain model of a single federalist paper */
public class Paper {
    private final int mNumber;
    private final String mTitle;
    private final String mPubName;
    private final Date mPubDate;
    private final String mAuthor1;
    private final String mAuthor2;
    private final List<String> mParagraphs;
    /*x*/
    /*x Paper.2 */
    public static class Builder {
        private int number;
        private String title;
        private String pubName;
        private Date pubDate;
        private String author1;
        private String author2;
        private List<String> paragraphs;

        public Builder(String str) {
            number = -1;
            try {
                number = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                // do nothing 
            }
            paragraphs = new ArrayList<String>();
        }

        public boolean isValid() {
            if (number < 1
                || title == null
                || pubName == null
                || pubDate == null
                || author1 == null
                || paragraphs == null)
                return false;
            return true;
        }
        public Builder title(String str) {
            title = str; return this;
        }
        public Builder pubName(String str) {
            pubName = str; return this;
        }
        public Builder pubDate(String str) {
            if (str == null) return this;
            /*bbf*/String pattern = "MMMMM dd, yyyy";/*ebf*/
            /*bbf*/SimpleDateFormat format/*ebf*/
                /*bbf*/= new SimpleDateFormat(pattern,Locale.ENGLISH);/*ebf*/
            /*bbf*/Date date = format.parse(str, new ParsePosition(0));/*ebf*/
            pubDate = date; return this;
        }
        public Builder author1(String str) {
            author1 = str; return this;
        }
        public Builder author2(String str) {
            author2 = str; return this;
        }
        public Builder paragraphs(List<String> list) {
            paragraphs.addAll(list); return this;
        }

        public Paper build() {
            if (isValid()) 
                return new Paper(this);
            return null;
        }
    }
    /*x*/
    /*x Paper.3 */
    private Paper(Builder builder) {
        mNumber = builder.number;
        mTitle = builder.title;
        mPubName = builder.pubName;
        mPubDate = builder.pubDate;
        mAuthor1 = builder.author1;
        mAuthor2 = builder.author2;
        mParagraphs = builder.paragraphs;
    }
    /*x*/
    /*x Paper.4 */
    public int getNumber() { return mNumber; }
    public String getTitle() { return mTitle; }
    public String getPubName() { return mPubName; }
    public Date getPubDate() { return mPubDate; }
    public String getAuthor1() { return mAuthor1; }
    public String getAuthor2() { return mAuthor2; }
    public List<String> getParagraphs() { 
        return Collections.unmodifiableList(mParagraphs); 
    }
    /*x*/

    /*x Paper.5 */
    public Element toXml() {
        Element root = new Element("doc");

        Element fNumber = new Element("field");
        fNumber.setAttribute("name","number");
        String number = Integer.toString(mNumber);
        fNumber.addContent(new Text(number));
        root.addContent(fNumber);

        Element fTitle = new Element("field");
        fTitle.setAttribute("name","title");
        fTitle.addContent(new Text(mTitle));
        root.addContent(fTitle);

        Element fPubName = new Element("field");
        fPubName.setAttribute("name","pubName");
        fPubName.addContent(new Text(mPubName));
        root.addContent(fPubName);
        Element fPubDate = new Element("field");
        fPubDate.setAttribute("name","pubDate");

        DateFormat df 
            = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String date = df.format(mPubDate);
        fPubDate.addContent(new Text(date));
        root.addContent(fPubDate);

        Element fAuthor1 = new Element("field");
        fAuthor1.setAttribute("name","author");
        fAuthor1.addContent(new Text(mAuthor1));
        root.addContent(fAuthor1);
        if (mAuthor2 != null) {
            Element fAuthor2 = new Element("field");
            fAuthor2.setAttribute("name","author");
            fAuthor2.addContent(new Text(mAuthor2));
            root.addContent(fAuthor2);
        }
        for (String par : mParagraphs) {
            Element fText = new Element("field");
            fText.setAttribute("name","text");
            fText.addContent(new Text(par));
            root.addContent(fText);
        }
        return root;
    }
    /*x*/

}

