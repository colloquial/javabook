package com.colloquial.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class FilterFiles {

    public static void main(String[] args) throws IOException {
        /*x FilterFiles.1 */
        File dir = new File(args[0]);
        File[] allFiles = dir.listFiles();
        /*x*/

        System.out.println("directory name=|" 
                           + args[0] + "|\n"
                           + allFiles.length 
                           + " files total");

        /*x FilterFiles.3 */
        String sfx = args[1];
        FileFilter sfxFilter = new SfxFileFilter(sfx);
        File[] sfxFiles = dir.listFiles(sfxFilter);
        /*x*/

        System.out.println(sfxFiles.length 
                           + " files end with |" + sfx + "|");
        for (File file : sfxFiles) {
            System.out.println(file.getName());
        }

    }
}

/*x FilterFiles.2 */
class SfxFileFilter implements FileFilter {
    private final String mSfx;
    public SfxFileFilter(String sfx) {
        mSfx = sfx;
    }
    public boolean accept(File pathname) {
        return pathname.getName().endsWith(mSfx);
    }
}
/*x*/
