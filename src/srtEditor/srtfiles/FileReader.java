package srtEditor.srtfiles;

import java.io.*;
import java.util.ArrayList;

public class FileReader
{

    public static ArrayList<String> readFile(File file)
    {
        ArrayList<String> strings = new ArrayList<String>();
        try
        {
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream(file);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null)
            {
                // Print the content on the console
                strings.add(strLine);
            }
            //Close the input stream
            in.close();
        } catch (Exception e)
        {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return strings;
    }
}
