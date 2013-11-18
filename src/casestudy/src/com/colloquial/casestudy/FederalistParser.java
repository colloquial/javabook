package com.colloquial.casestudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**  Ad hoc parser for Project Gutenburg Etext #1404 
 *   "The Federalist Papers"  
 */
public class FederalistParser {

    /*x FederalistParser.1 */
    public static final String INPUT_ENCODING = "UTF-8";
    public static final String OUTPUT_ENCODING = "UTF-8";
    /*x*/
    /*x FederalistParser.2 */
    public static final int TOTAL_PAPERS = 85;

    public static void main(String[] args) 
        throws IOException, FileNotFoundException {
        if (args.length < 2) {
            System.err.println("usage: FederalistParser "
                               + "<etext> <outdir>");
            System.exit(-1);
        }
        File etextFile = new File(args[0]);
        File outputDir = new File(args[1]);

        FederalistParser parser = new FederalistParser();
        List<Paper> papers = parser.parsePapers(etextFile);
        if (papers.size() != TOTAL_PAPERS) {
            System.err.println("\nerror(s) found, "
                               + " no files generated");
        } else {
            for (Paper paper : papers)
                parser.paperToXml(paper, outputDir);
            System.out.println("All papers processed.");
        }
    }
    /*x*/

    /*x FederalistParser.3 */
    public static final String START_PAPER = "FEDERALIST No.";
    public static final String END_OF_BOOK
        = "End of the Project Gutenberg";

    public List<Paper> parsePapers(File inFile)
        throws IOException, FileNotFoundException {
        ArrayList<Paper> papers = new ArrayList<Paper>();

        FileInputStream inStream = null;
        InputStreamReader inReader = null;
        BufferedReader bufReader = null;
        try {
            inStream = new FileInputStream(inFile);
            inReader = new InputStreamReader(inStream,
                                             INPUT_ENCODING);
            bufReader = new BufferedReader(inReader);

            // read from beginning of file to start of paper #1
            String line = null;
            while ((line = bufReader.readLine()) != null) {
                if (line.startsWith(START_PAPER)) break;
            }
            if (line == null) 
                throw new IOException("unexpected EOF");

            StringBuilder paragraph = new StringBuilder();
            ArrayList<String> parsList = new ArrayList<String>(); 

            int ct = 0;
            // process all papers
            while (!(line.startsWith(END_OF_BOOK))) {
                // process previous paper, if any
                if (parsList.size() > 0) { 
                    if (paragraph.length() > 0) {
                        parsList.add(paragraph.toString());
                    }                    
                    Paper paper = parsePaper(parsList);
                    if (paper != null) {
                        papers.add(paper);
                    } else {
                        System.err.println("\nparse error paper: " 
                                           + ct);
                    }
                    parsList.clear();
                    paragraph.setLength(0);
                }
                // get this paper
                ct++;
                parsList.add(line);  // header line 
                while ((line = bufReader.readLine()) != null) {
                    if (line.startsWith(START_PAPER)) break;
                    if (line.startsWith(END_OF_BOOK)) break;
                    if ("".equals(line)) {
                        if (paragraph.length() > 0) {
                            parsList.add(paragraph.toString());
                            paragraph.setLength(0);
                        }
                    } else {
                        paragraph.append(line + " ");
                    }
                }
            }
            // process final paper in book
            if (parsList.size() > 0) {
                if (paragraph.length() > 0)
                    parsList.add(paragraph.toString());
                Paper paper = parsePaper(parsList);
                if (paper != null) {
                    papers.add(paper);
                } else {
                    System.err.println("\nparse error paper: " 
                                       + ct);
                }
            }
        } finally {
            close(bufReader);
            close(inReader);
            close(inStream);
        }
        return papers;
    }
    /*x*/

    /*x FederalistParser.4 */
    // process list of paragraphs
    // first 5 contain header information, rest are body
    public Paper parsePaper(List<String> pars) {

        String header = pars.remove(0);
        String number = null;
        String regex = "FEDERALIST No. (\\d+).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(header);
        if (matcher.matches()) {
            number = matcher.group(1);
        }

        String title = pars.remove(0);

        String publication = pars.remove(0);
        String pubName = null;
        String pubDate = null;
        regex = "\\w+ ([^\\.]+)\\. \\w+, ([^\\.]+).*";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(publication);
        if (matcher.matches()) {
            pubName = matcher.group(1);
            pubDate = matcher.group(2);
        }

        String author = pars.remove(0);
        String author1 = null;
        String author2 = null;
        regex = "(\\w+)(?:, with (\\w+))?.*";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(author);
        if (matcher.matches()) {
            author1 = matcher.group(1);
            author2 = matcher.group(2);
        }

        pars.remove(0);         // skip salutation 

        return new Paper.Builder(number).title(title)
            .pubName(pubName).pubDate(pubDate)
            .author1(author1).author2(author2)
            .paragraphs(pars).build();
    }
    /*x*/
    /*x FederalistParser.5 */
    public void paperToXml(Paper paper, File outDir) 
        throws IOException {

        // pad paper number so that files sort nicely
        String numPaper 
            = String.format("%02d",paper.getNumber());
        String filename = "paper_" + numPaper + ".xml";
        File outFile = new File(outDir,filename);

        FileOutputStream outStream = null;
        OutputStreamWriter outWriter = null;
        BufferedWriter bufWriter = null;
        try {
            outStream = new FileOutputStream(outFile);
            outWriter = new OutputStreamWriter(outStream,
                                               OUTPUT_ENCODING);
            bufWriter = new BufferedWriter(outWriter);

            Format format = Format.getPrettyFormat();
            format.setEncoding(OUTPUT_ENCODING);
            XMLOutputter outputter
                = new XMLOutputter(format);
            Element root = new Element("add");
            Document document = new Document(root);
            root.addContent(paper.toXml());
            outputter.output(document,bufWriter);
        } finally {
            close(bufWriter);
            close(outWriter);
            close(outStream);
        }
    }
    /*x*/

    /*x FederalistParser.6 */
    void close(Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (IOException e) {
            // ignore
        }
        /*x*/
    }
}

