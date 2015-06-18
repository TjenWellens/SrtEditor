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
public class TimeObject implements SrtOperations
{
    /*
    1
    00:00:44,920 --> 00:00:46,320
    De wereld is veranderd...
    
    2
    00:00:48,120 --> 00:00:49,640
    Ik voel het aan het water...
     */

    // hours:minutes:seconds,milliseconds
    private int hours;
    private int minutes;
    private int seconds;
    private int millis;

    protected TimeObject(int hours, int minutes, int seconds, int millis)
    {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.millis = millis;
    }

    protected TimeObject()
    {
    }

    public void setHours(int hours)
    {
        this.hours = hours;
    }

    public void setMillis(int milliseconds)
    {
        this.millis = milliseconds;
    }

    public void setMinutes(int minutes)
    {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds)
    {
        this.seconds = seconds;
    }

    @Override
    public String toString()
    {
        // hours:minutes:seconds,milliseconds
        // 00:00:44,920 --> 00:00:46,320
        return to2DigitString(hours) + ":" + to2DigitString(minutes) + ":" + to2DigitString(seconds) + "," + to3DigitString(millis);
    }

    private String to2DigitString(int i)
    {
        String is = "" + i;
        if (is.length() >= 2)
        {
            return ("" + is).substring(0, 2);
        } else if (is.length() == 1)
        {
            return ("0" + is).substring(0, 2);
        } else
        {
            return ("00" + is).substring(0, 2);
        }
    }

    private String to3DigitString(int i)
    {
        String is = "" + i;
        if (is.length() >= 3)
        {
            return ("" + is).substring(0, 3);
        } else if (is.length() == 2)
        {
            return ("0" + is).substring(0, 3);
        } else if (is.length() == 1)
        {
            return ("00" + is).substring(0, 3);
        } else
        {
            return ("000" + is).substring(0, 3);
        }
    }

    public static TimeObject getInstance(Long millis)
    {
        TimeObject returnTimeObject = new TimeObject();
        returnTimeObject.fromMillis(millis);
        return returnTimeObject;
    }

    protected static TimeObject getInstance(String s)
    {
        // 00:00:48,120
        TimeObject ts = new TimeObject();
        // split to <00:00:48> and <120>
        String[] ss = s.split(",");
        // use 2nd for millis
        ts.setMillis(Integer.parseInt(ss[1]));
        // use 1st for new split: <00> <00> <48>
        String[] sss = ss[0].split(":");
        // 1st hours, 2nd minutes, 3d seconds
        ts.setHours(Integer.parseInt(sss[0]));
        ts.setMinutes(Integer.parseInt(sss[1]));
        ts.setSeconds(Integer.parseInt(sss[2]));

        return ts;
    }

    @Override
    public void setLater(TimeObject offset)
    {
        this.increaseMillis(offset.millis);
        this.increaseSeconds(offset.seconds);
        this.increaseMinutes(offset.minutes);
        this.increaseHours(offset.hours);
    }

    private void increaseMillis(int increment)
    {
        this.millis += increment;
        if (this.millis > 1000 || this.millis < 0)
        {
            increaseSeconds((int) Math.floor(millis / 1000));
            this.millis %= 1000;
        }
    }

    private void increaseSeconds(int increment)
    {
        this.seconds += increment;
        if (this.seconds > 60 || this.seconds < 0)
        {
            increaseMinutes((int) Math.floor(seconds / 60));
            this.seconds %= 60;
        }
    }

    private void increaseMinutes(int increment)
    {
        this.minutes += increment;
        if (this.minutes > 60 || this.minutes < 0)
        {
            increaseHours((int) Math.floor(minutes / 60));
            this.minutes %= 60;
        }
    }

    private void increaseHours(int increment)
    {
        this.hours += increment;
    }

    private long timeToMillis()
    {
        long tempMillis = this.millis;
        long tempSeconds = this.seconds;
        long tempMinutes = this.minutes;
        long tempHours = this.hours;

        tempMinutes += tempHours * 60;
        tempSeconds += tempMinutes * 60;
        tempMillis += tempSeconds * 1000;

        return tempMillis;
    }

    private void fromMillis(long from)
    {
        long tempMillis = from;
        long tempSeconds = (long) Math.floor(tempMillis / 1000);
        long tempMinutes = (long) Math.floor(tempSeconds / 60);
        long tempHours = (long) Math.floor(tempMinutes / 60);

        tempMillis %= 1000;
        tempSeconds %= 60;
        tempMinutes %= 60;

        this.millis = (int) tempMillis;
        this.seconds = (int) tempSeconds;
        this.minutes = (int) tempMinutes;
        this.hours = (int) tempHours;
    }

    @Override
    public void scale(double scale)
    {
        long timeInMillis = timeToMillis();
        timeInMillis *= scale;
        fromMillis(timeInMillis);
    }
}
