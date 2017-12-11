package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void testCTORname(){
        Room tester = new Room();
        assertEquals("Nothing", tester.getName());
    }

    @Test
    public void testCTORdesc(){
        Room tester = new Room();
        assertEquals("Nothingness", tester.getDesc());
    }

    @Test
    public void testCTORwithName(){
        Room tester = new Room("Test");
        assertEquals("Test", tester.getName());
    }

    @Test
    public void testCTORwithDesc(){
        Room tester = new Room("Test", "Blank");
        assertEquals("Blank", tester.getDesc());
    }
}
