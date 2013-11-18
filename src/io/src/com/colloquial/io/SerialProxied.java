package com.colloquial.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

/*x SerialProxied.1 */
public class SerialProxied implements Serializable {

    private final String mS;
    private final int mCount;
    private final Object mNotSerializable = new Object();

    public SerialProxied(String s, int count) {
        mS = s;  
        mCount = count;
    }
/*x*/

    public String toString() {
        return mS + ":" + mCount;
    }

    /*x SerialProxied.2 */
    private Object writeReplace() {
        return new Serializer(this);
    }
    /*x*/

    static final long serialVersionUID = -688378786294424932L;

    /*x SerialProxied.3 */
    private static class Serializer implements Externalizable {

        SerialProxied mObj;

        public Serializer() { }

        Serializer(SerialProxied obj) {
            mObj = obj;
        }
    /*x*/

        /*x SerialProxied.4 */
        public void writeExternal(ObjectOutput out) 
            throws IOException {

            out.writeUTF(mObj.mS);
            out.writeInt(mObj.mCount);
        }

        public void readExternal(ObjectInput in) throws IOException {
            String s = in.readUTF();
            int count = in.readInt();
            mObj = new SerialProxied(s,count);
        }

        Object readResolve() {
            return mObj;
        }
        /*x*/

        static final long serialVersionUID = -9070600003619854277L;
    }

    public static void main(String[] args)
        throws IOException, ClassNotFoundException {
        
        SerialProxied p = new SerialProxied("foo",7);
        @SuppressWarnings("unchecked")
        SerialProxied p2 = (SerialProxied)
            serializeDeserialize(p);
        System.out.println("p2=" + p2);
    }


    public static Object serializeDeserialize(Serializable s)
        throws IOException {

        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(bytesOut);
        objOut.writeObject(s);
        ByteArrayInputStream bytesIn
            = new ByteArrayInputStream(bytesOut.toByteArray());
        ObjectInputStream objIn = new ObjectInputStream(bytesIn);
        try {
            return objIn.readObject();
        } catch (ClassNotFoundException e) {
            String msg = "Compile i/o class not found exception=" + e;
            throw new IOException(msg);
        }
    }

}


