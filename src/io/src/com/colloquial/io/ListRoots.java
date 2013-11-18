package com.colloquial.io;

import java.io.File;
import java.io.IOException;

public class ListRoots {

    public static void main(String[] args) throws IOException {
        System.out.println("Root Directories");
        /*x ListRoots.1 */
        File[] roots = File.listRoots();
        for (File file : roots)
        /*x*/
            System.out.println("     " + file);
    }

}