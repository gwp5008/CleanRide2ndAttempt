package edu.psu.ist402.cleanride;

import junit.framework.TestCase;

/**
 * Created by george on 8/3/2016.
 */
public class MainActivityTest extends TestCase {

    public void testPerformLogout() throws Exception {
        assertFalse(MainActivity.isLoggedIn);
    }
}