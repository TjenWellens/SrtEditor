package srtEditor.srtfiles.srtEntry;

import srtEditor.srtfiles.SrtOperations;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tjen
 */
public class TimeBeginAndEnd implements SrtOperations
{

    private TimeObject begin;
    private TimeObject end;

    protected TimeBeginAndEnd()
    {
    }

    public void setBegin(TimeObject begin)
    {
        this.begin = begin;
    }

    public void setEnd(TimeObject end)
    {
        this.end = end;
    }

    @Override
    public String toString()
    {
        // 00:00:44,920 --> 00:00:46,320
        return begin + " --> " + end;
    }

    protected static TimeBeginAndEnd getInstance(String s)
    {
        // 00:00:48,120 --> 00:00:49,640
        TimeBeginAndEnd to = new TimeBeginAndEnd();

        String[] ss = s.split(" --> ");
        to.setBegin(TimeObject.getInstance(ss[0]));
        to.setEnd(TimeObject.getInstance(ss[1]));

        return to;
    }

    @Override
    public void setLater(TimeObject offset)
    {
        begin.setLater(offset);
        end.setLater(offset);
    }

    @Override
    public void scale(double scale)
    {
        begin.scale(scale);
        end.scale(scale);
    }
}
