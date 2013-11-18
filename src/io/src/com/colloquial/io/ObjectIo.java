package com.colloquial.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectIo {

    public static void main(String[] args)
        throws IOException, ClassNotFoundException {

        /*x ObjectIo.1 */
        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bytesOut); 

        out.writeObject(new File("foo"));
        out.writeUTF("bar");
        out.writeInt(42);

        out.close();
        /*x*/
        
        /*x ObjectIo.2 */
        byte[] bytes = bytesOut.toByteArray();
        InputStream bytesIn = new ByteArrayInputStream(bytes);
        ObjectInput in = new ObjectInputStream(bytesIn);

        @SuppressWarnings("unchecked")
        File file = (File) in.readObject();
        String s = in.readUTF();
        int n = in.readInt();

        in.close();
        /*x*/

        System.out.println("file.getCanonicalPath()=" + file.getCanonicalPath());
        System.out.println("s=" + s);
        System.out.println("n=" + n);
    }

}