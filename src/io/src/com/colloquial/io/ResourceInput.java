package com.colloquial.io;

import java.io.InputStream;
import java.io.IOException;

public class ResourceInput {

    public static void main(String[] args)
        throws IOException {
        
        /*x ResourceInput.1 */
        String resourceName = args[0];
        InputStream in 
            = ResourceInput.class
            .getResourceAsStream(resourceName);
        /*x*/
        byte[] bs = new byte[8192];
        for (int n; (n = in.read(bs)) >= 0; )
            System.out.write(bs,0,n);

    }

}