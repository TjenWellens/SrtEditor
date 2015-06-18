package srtEditor.srtfiles;

import java.io.File;
import java.util.Arrays;
import srtEditor.srtfiles.srtEntry.SrtEntry;
import java.util.ArrayList;
import srtEditor.srtfiles.srtEntry.TimeObject;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tjen
 */
public class SrtFile implements SrtOperations
{

    private ArrayList<SrtEntry> srtEntrys = new ArrayList<SrtEntry>();

    public ArrayList<SrtEntry> getSrtEntrys()
    {
        return srtEntrys;
    }

    public void addSrtEntrys(SrtEntry srtEntry)
    {
        this.srtEntrys.add(srtEntry);
    }

    @Override
    public void setLater(TimeObject offset)
    {
        for (SrtEntry srtEntry : srtEntrys)
        {
            srtEntry.setLater(offset);
        }
    }

    @Override
    public void scale(double scale)
    {
        for (SrtEntry srtEntry : srtEntrys)
        {
            srtEntry.scale(scale);
        }
    }

    @Override
    public String toString()
    {
        String returnString = "";
        for (SrtEntry srtEntry : srtEntrys)
        {
            returnString += srtEntry;
        }
        return returnString;
    }

    public void readFromFile(File file)
    {
        readFromStrIt(FileReader.readFile(file));
    }
    
    protected void readFromStrIt(Iterable<String> strings)
    {
        boolean readNumber = false;
        boolean readTime = false;
        SrtEntry se = null;
        // 
        for (String currentStr : strings)
        {
            if (currentStr.isEmpty())
            {
                readNumber = false;
                readTime = false;
                if (se != null)
                {
                    addSrtEntrys(se);
                    se = null;
                }
            } else
            {
                if (readNumber)
                {
                    if (readTime)
                    {
                        // read text
                        se.addTextEntries(currentStr);
                    } else
                    {
                        //read time
                        se.setTimeObject(SrtEntry.createTimeObject(currentStr));
                        readTime = true;
                    }
                } else
                {
                    //read number
                    se = new SrtEntry();
                    se.setId(SrtEntry.createID(currentStr));
                    readNumber = true;
                }
            }
        }
    }

    public void readFromString(String input)
    {
        readFromStrIt(Arrays.asList(input.split("\n")));
    }

    public void writeToFile(File file)
    {
        FileWriter.saveToFile(file, toString());
    }
}
