package srtEditor.srtfiles.srtEntry;

import java.util.ArrayList;
import srtEditor.srtfiles.SrtOperations;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tjen
 */
public class SrtEntry implements SrtOperations
{

    private int id;
    private TimeBeginAndEnd timeObject;  // tostring
    private ArrayList<String> textEntries = new ArrayList<String>();
    private String newLine = System.getProperty("line.separator");

    public void setNewLine(String newLine)
    {
        // TODO: make editable
        this.newLine = newLine;
    }

    public static int createID(String s)
    {
        return Integer.parseInt(s);
    }

    public static TimeBeginAndEnd createTimeObject(String s)
    {
        return TimeBeginAndEnd.getInstance(s);
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void addTextEntries(String textEntry)
    {
        this.textEntries.add(textEntry);
    }

    public void setTimeObject(TimeBeginAndEnd timeObject)
    {
        this.timeObject = timeObject;
    }

    @Override
    public String toString()
    {
        /*
        1
        00:00:44,920 --> 00:00:46,320
        De wereld is veranderd...
        
        2
        00:00:48,120 --> 00:00:49,640
        Ik voel het aan het water...
         */

        String returnString = "";
        returnString += "" + id;
        returnString += newLine;
        returnString += "" + timeObject;
        returnString += newLine;
        for (String textLine : textEntries)
        {
            returnString += "" + textLine;
            returnString += newLine;
        }
        returnString += newLine;

        return returnString;
    }

    @Override
    public void setLater(TimeObject offset)
    {
        timeObject.setLater(offset);
    }

    @Override
    public void scale(double scale)
    {
        timeObject.scale(scale);
    }
}
