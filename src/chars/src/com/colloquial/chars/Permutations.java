package com.colloquial.chars;

import java.util.ArrayList;
import java.util.List;

// Johnson-Trotter algorithm for generating all permutations of a list
// adapted from Sedgewick's implementation in Java
// see: http://en.wikipedia.org/wiki/Steinhaus%E2%80%93Johnson%E2%80%93Trotter_algorithm
// and: http://introcs.cs.princeton.edu/java/23recursion/Permutations.java.html

public class Permutations {

    // generate N! permutation of the elements of array a
    // collect permutations in a List
    public static void permute(String s, List<String> perms) {
       int strlen = s.length();
       char[] a = new char[strlen];
       for (int i = 0; i < strlen; i++) 
           a[i] = s.charAt(i);
       permN(a, strlen, perms);
    }

    // generate all permutations of first N chars in an array recursively
    private static void permN(char[] a, int n, List<String> perms) {
        if (n == 1) {
            String p = new String(a);
            perms.add(p);
            return;
        }
        for (int i = 0; i < n; i++) {
            swap(a, i, n-1);
            permN(a, n-1, perms);
            swap(a, i, n-1);
        }
    }  

    private static void swap(char[] a, int i, int j) {
        char tmp;
        tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        String letters = args[0];
        ArrayList<String> result = new ArrayList<String>();
        permute(letters,result);
        for (String s: result) {
            System.out.println(s);
        }
    }

}
