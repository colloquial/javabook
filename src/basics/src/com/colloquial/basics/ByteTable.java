package com.colloquial.basics;

public class ByteTable {

    public static void main(String[] args) {
        System.out.println("BASES");
        System.out.printf("%3s  %5s  %3s  %2s  %8s\n\n",
                          "10","-10","8","16","2");
        /*x ByteTable.1*/
        for (int i = 0; i < 256; ++i) {
            byte b = (byte) i; // overflows if i > 127
            int k = b < 0 ? (b + 256) : b;
            System.out.printf("%3d  %5d  %3s  %2s  %8s\n",
                      k, b, Integer.toOctalString(k),
                      Integer.toHexString(k), 
                      Integer.toBinaryString(k));
        }
        /*x*/
    }



}