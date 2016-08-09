package edu.psu.ist402.cleanride;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by george on 8/3/2016.
 */
public class SpecificLocationTest extends TestCase {
    SpecificLocation sL = new SpecificLocation();

    public void testGetCurrentYear() throws Exception {
        int year = sL.getCurrentYear();

        assertEquals(2016, year);
    }

    public void testGetCurrentMonth() throws Exception {
        int month = sL.getCurrentMonth();

        assertEquals(8, month);
    }

    public void testGetCurrentDayOfMonth() throws Exception {
        int day = sL.getCurrentDayOfMonth();

        assertEquals(6, day);
    }
}