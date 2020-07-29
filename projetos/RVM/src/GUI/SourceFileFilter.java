/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author ernestas
 */
public class SourceFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = f.getName().substring(f.getName().length()-4);

        if (extension != null) {
            if (extension.equalsIgnoreCase(".src")) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }


    @Override
    public String getDescription() {
        return "src";//throw new UnsupportedOperationException("Source code files only. *.src");
    }
    
}
