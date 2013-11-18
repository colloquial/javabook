package com.colloquial.applucene;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.ngram.NGramTokenizer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.FieldInfo.IndexOptions;

import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.BooleanClause.Occur;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;

import org.apache.lucene.util.Version;

/**  Use Lucene as a classifier to identify newsgroup given post.
     Parse, index, and evaluate the 20 newsgroups dataset.
 */
public class ClassifyNewsgroups {

    public static final String ENCODING = "UTF-8";

    public static final Version VERSION = Version.LUCENE_45;

    /*x ClassifyNewsgroups.5 */
    public static final String[] NEWSGROUPS
        = { "alt.atheism",
            "comp.graphics",
            "comp.os.ms-windows.misc",
    /*x*/
            "comp.sys.ibm.pc.hardware",
            "comp.sys.mac.hardware",
            "comp.windows.x",
            "misc.forsale",
            "rec.autos",
            "rec.motorcycles",
            "rec.sport.baseball",
            "rec.sport.hockey",
            "sci.crypt",
            "sci.electronics",
            "sci.med",
            "sci.space",
            "soc.religion.christian",
            "talk.politics.guns",
            "talk.politics.mideast",
            "talk.politics.misc",
            "talk.religion.misc" };

    private final String mType;
    private final Analyzer mAnalyzer;

    public static void main(String[] args) 
        throws IOException, FileNotFoundException {
        if (args.length < 4) {
            System.err.println("usage: ClassifyNewsgroups "
                               + "<trainingData> <indexDir> <testData> <aType>");
            System.exit(-1);
        }
        File trainDir = new File(args[0]);
        File indexDir = new File(args[1]);
        File testDir = new File(args[2]);
        ClassifyNewsgroups classifier 
            = new ClassifyNewsgroups(args[3]);
        if (!indexDir.exists()
            || indexDir.listFiles().length == 0) {
            classifier.buildIndex(indexDir, trainDir);
        }
        classifier.testIndex(indexDir, testDir);
    }

    /* Instantiate Lucene Analyzer used for search and indexing.
     * Command-line argument specifies Analyzer type */
    ClassifyNewsgroups(String aType) {
        mType = aType.toLowerCase();
        if (! ("std".equals(aType)
               || "lc".equals(aType)
               || "ngram".equals(aType)))
            throw new IllegalArgumentException("unknown analyzer: " 
                                               + aType);                   
        /*x ClassifyNewsgroups.3 */
        if ("std".equals(aType)) {
            mAnalyzer
                = new StandardAnalyzer(VERSION);
        } else if ("lc".equals(aType)) {
            mAnalyzer = new Analyzer() {
                    @Override protected TokenStreamComponents 
                        createComponents(String fieldName, 
                                         Reader reader) {
                        Tokenizer source = 
                            new StandardTokenizer(VERSION,reader);
                        TokenStream filter = 
                            new LowerCaseFilter(VERSION,source);
                        return new TokenStreamComponents(source, 
                                                         filter);
                    }
                };
        } else if ("ngram".equals(aType)) {
            mAnalyzer = new Analyzer() {
                    @Override protected TokenStreamComponents 
                        createComponents(String fieldName, 
                                         Reader reader) {
                        Tokenizer source = 
                            new NGramTokenizer(VERSION,reader,4,4);
                        return new TokenStreamComponents(source);
                    }
                };
        /*x*/
        } else  { mAnalyzer = null; }
    }

    /* Iterate over all posts in test set and tabulate results.
     * Directory name is newsgroup name.
     * Each dir contains posts to that newsgroup, 1 post per file.
     * For each post, do Lucene search over index and
     * use category of best-matching document to classify post */
    /*x ClassifyNewsgroups.2 */
    void testIndex(File indexDir, File testDir)
        throws IOException, FileNotFoundException {
        Directory fsDir = FSDirectory.open(indexDir);
        DirectoryReader reader = DirectoryReader.open(fsDir);
        IndexSearcher searcher = new IndexSearcher(reader);

        int[][] confusionMatrix
            = new int[NEWSGROUPS.length][NEWSGROUPS.length];

        File[] groupsDir = testDir.listFiles();
        for (File group : groupsDir) {
            int postCt = 0;
            String groupName = group.getName();
            int rowIdx = Arrays.binarySearch(NEWSGROUPS,groupName);

            File[] posts = group.listFiles();
            for (File postFile : posts) {
                postCt++;
                String number = postFile.getName();
                NewsPost post = parse(postFile, groupName, number);
                BooleanQuery termsQuery 
                    = buildQuery(post.subject() 
                                 + " " + post.body());

                // only get first-best result
                TopDocs hits = searcher.search(termsQuery,1);
                ScoreDoc[] scoreDocs = hits.scoreDocs;
                for (int n = 0; n < scoreDocs.length; ++n) {
                    ScoreDoc sd = scoreDocs[n];
                    int docId = sd.doc;
                    Document d = searcher.doc(docId);
                    String category = d.get("category");
                    // record result in confusion matrix
                    int colIdx 
                        = Arrays.binarySearch(NEWSGROUPS,category);
                    confusionMatrix[rowIdx][colIdx]++;
                }
            }
    /*x*/
            //            System.out.println("test items for " + groupName + ": " + postCt);
            //            System.out.printf("%s: %5.3f\n",
            //                              groupName,
            //                              ((confusionMatrix[rowIdx][rowIdx]*1.0d)/(postCt*1.0d)));
            System.out.print(groupName);
            for (int i=0; i<NEWSGROUPS.length; i++) 
                 System.out.printf("| %4d ", confusionMatrix[rowIdx][i]);
            System.out.println("|");
        }
    }

    /* Build Lucene query from tokens in text. */
    /*x ClassifyNewsgroups.4 */
    BooleanQuery buildQuery(String text) throws IOException {
        BooleanQuery termsQuery = new BooleanQuery();
        Reader textReader = new StringReader(text);
        TokenStream tokStream 
            = mAnalyzer.tokenStream("text",textReader);
        try {
            tokStream.reset();
            CharTermAttribute terms = 
                tokStream.addAttribute(CharTermAttribute.class);
            int ct = 0;
            while (tokStream.incrementToken() 
                   && ct++ < 1024) {
                termsQuery.
                    add(new TermQuery(new Term("text",
                                               terms.toString())), 
                        Occur.SHOULD);
            }
            tokStream.end();
        } finally {
            tokStream.close();
            textReader.close();
        }
        return termsQuery;
    /*x*/
    }

    /* Create Lucene index and iterate over 
     * all posts in training set and add to index.
     * Directory name is newsgroup name.
     * Each dir contains posts to that newsgroup, 1 post per file.
     * Lastly, merge all segments into a single segment. */
    void buildIndex(File indexDir, File trainDir) 
        throws IOException, FileNotFoundException {
        Directory fsDir = FSDirectory.open(indexDir);
        IndexWriterConfig iwConf 
            = new IndexWriterConfig(VERSION,mAnalyzer);

        iwConf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter indexWriter
            = new IndexWriter(fsDir,iwConf);

        File[] groupsDir = trainDir.listFiles();
        for (File group : groupsDir) {
            int postCt = 0;
            String groupName = group.getName();
            File[] posts = group.listFiles();
            for (File postFile : posts) {
                String number = postFile.getName();
                /*x ClassifyNewsgroups.1 */
                NewsPost post = parse(postFile, groupName, number);
                Document d = new Document();
                d.add(new StringField("category",
                                      post.group(),Store.YES));
                d.add(new TextField("text",
                                    post.subject(),Store.NO));
                d.add(new TextField("text",
                                    post.body(),Store.NO));
                indexWriter.addDocument(d);
                /*x*/
                postCt++;
            }
            System.out.println("training items for " + groupName + ": " + postCt);

        }
        int numDocs = indexWriter.numDocs();
        indexWriter.forceMerge(1);
        indexWriter.commit();
        indexWriter.close();
        System.out.println("index=" + indexDir.getName());
        System.out.println("num docs=" + numDocs);
    }

    /* Construct NewsPost object from newsgroup post data file. */
    NewsPost parse(File inFile, String newsgroup, String number)
        throws IOException, FileNotFoundException {
        FileInputStream inStream = null;
        InputStreamReader inReader = null;
        BufferedReader bufReader = null;
        try {
            inStream = new FileInputStream(inFile);
            inReader = new InputStreamReader(inStream,ENCODING);
            bufReader = new BufferedReader(inReader);

            String subject = null;

            // read from beginning of file to end of header - 1st blank line
            String line = null;
            while ((line = bufReader.readLine()) != null) {
                // parse out subject from Subject line
                if (line.startsWith("Subject:")) {
                    subject = line.substring("Subject:".length());
                } else {
                    if (line.length() == 0) break;
                }
            }
            if (line == null) 
                throw new IOException("unexpected EOF");

            // remaining lines are message body
            StringBuilder body = new StringBuilder();
            int lines = 1;
            while ((line = bufReader.readLine()) != null) {
                body.append(line + " ");
                lines++;
            }
            return new NewsPost(newsgroup,number,subject,body.toString());
        } finally {
            close(bufReader);
            close(inReader);
            close(inStream);
        }
    }

    void close(Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (IOException e) {
            // ignore
        }
    }

    /* Domain model for one news post from 20news dataset. */
    static class NewsPost {
        private final String mGroup;
        private final String mNumber;
        private final String mSubject;
        private final String mBody;

        public NewsPost (String group, 
                         String number,
                         String subject, 
                         String body) {
            mGroup = group;
            mNumber = number;
            mSubject = subject;
            mBody = body;
        }
        public String group() { return mGroup; }
        public String number() { return mNumber; }
        public String subject() { return mSubject; }
        public String body() { return mBody; }
    }

}
