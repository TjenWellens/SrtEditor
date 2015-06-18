/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package srtEditor.srtfiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author tjen
 */
public class FileWriter
{

    public static void saveToFile(File file, String text)
    {
        try
        {
            // Create file 
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            out.write(text);
            //Close the output stream
            out.close();
        } catch (Exception e)
        {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}
