package srtEditor.srtfiles;

import srtEditor.srtfiles.srtEntry.TimeObject;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tjen
 */
public interface SrtOperations
{
    void setLater(TimeObject offset);
    void scale(double scale);
}
