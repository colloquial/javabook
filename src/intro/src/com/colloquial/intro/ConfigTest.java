package com.colloquial.intro;
public class ConfigTest {
    public static void main(String[] args) throws Exception {
        /*x ConfigTest.1*/
        byte[] bs = "small a: a\n".getBytes("UTF-8");
        System.out.write(bs,0,bs.length);
        bs = "small a with grave accent: \u00E0\n".getBytes("UTF-8");
        System.out.write(bs,0,bs.length);
        /*x*/
        bs = "Cyrillic capital eN: \u041d\n".getBytes("UTF-8");
        System.out.write(bs,0,bs.length);
        bs = "Hangul syllable geon: \uac74\n".getBytes("UTF-8");
        System.out.write(bs,0,bs.length);
    }
}


