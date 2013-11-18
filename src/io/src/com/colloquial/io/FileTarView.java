package com.colloquial.io;

import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import java.util.Date;

import java.util.zip.GZIPInputStream;

public class FileTarView {

    public static void main(String[] args) throws IOException {
        File tarFile = new File(args[0]);
        boolean gzipped = Boolean.parseBoolean(args[1]);

        /*x FileTarView.1 */
        InputStream fileIn = new FileInputStream(tarFile);
        InputStream in = gzipped
            ? new GZIPInputStream(fileIn)
            : fileIn;
        TarInputStream tarIn = new TarInputStream(in);
        /*x*/

        /*x FileTarView.2 */
        while (true) {
            TarEntry entry = tarIn.getNextEntry();
            if (entry == null) break;

            boolean isDirectory = entry.isDirectory();
            String name = entry.getName();
            String userName = entry.getUserName();
            String groupName = entry.getGroupName();
            int mode = entry.getMode();
            Date date = entry.getModTime();
            long size = entry.getSize();

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] copyBuf = new byte[1024*8];
            int numBytes;
            while ((numBytes = tarIn.read(copyBuf)) > 0)
                out.write(copyBuf,0,numBytes);
            byte[] bs = out.toByteArray();


        /*x*/
            System.out.println("------------------------------------");
            System.out.println("name=" + name);
            System.out.println("isDirectory=" + isDirectory);
            System.out.println("userName=" + userName);
            System.out.println("groupName=" + groupName);
            System.out.println("mode=" + Integer.toOctalString(mode));
            System.out.println("date=" + date);
            System.out.println("size=" + size);
            System.out.println("#bytes read=" + bs.length);
        }
        /*x FileTarView.3 */        
        tarIn.close();
        /*x*/
    }

} 