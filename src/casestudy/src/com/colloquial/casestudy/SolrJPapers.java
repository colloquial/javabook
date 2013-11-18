package com.colloquial.casestudy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;

import org.apache.lucene.util.Version;

public class SolrJPapers {

    public static void main(String[] args)
        throws IOException, SolrServerException {
        if (args.length < 2) {
            System.err.println("usage: SolrJPapers "
                               + "<etext> <solrUrl>");
            System.exit(-1);
        }
        /*x SolrJPapers.1 */
        File etextFile = new File(args[0]);
        String solrUrl = args[1];
        ConcurrentUpdateSolrServer solrServer 
            = new ConcurrentUpdateSolrServer(solrUrl,10,3);
        /*x*/
        /*x SolrJPapers.2 */        
        try {
            FederalistParser parser = new FederalistParser();
            List<Paper> papers = parser.parsePapers(etextFile);
            for (Paper paper : papers) {
                SolrInputDocument doc = new SolrInputDocument();
                doc.addField("number", paper.getNumber());
                doc.addField("title", paper.getTitle());
                doc.addField("author", paper.getAuthor1());
                if (paper.getAuthor2() != null) {
                    doc.addField("author",paper.getAuthor2());
                }
                doc.addField("pubName", paper.getPubName());
                doc.addField("pubDate", paper.getPubDate());
                for (String paragraph : paper.getParagraphs()) {
                    doc.addField("text", paragraph);
                }
                solrServer.add(doc);
            }
            UpdateResponse response = solrServer.commit();
            System.out.println(response.toString());
        } finally {
            solrServer.shutdown();
        }
        /*x*/
        System.out.println("done");
    }
}
