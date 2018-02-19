/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unesc.hal.data;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mateus Generoso
 */
public class SourceTest {
    
    public SourceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCode method, of class Source.
     */
    @Test
    public void testGetCode() {
        Source instance = new Source("");
        String expResult = "";
        String result = instance.getCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCode method, of class Source.
     */
    @Test
    public void testSetCode() {
        String code = "";
        Source instance = new Source("");
        instance.setCode(code);
    }

    /**
     * Test of toString method, of class Source.
     */
    @Test
    public void testToString() {
        Source instance = new Source("");
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getChars method, of class Source.
     */
    @Test
    public void testGetChars() {
        Source instance = new Source("");
        ArrayList<Character> result = instance.getChars();
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of getLineCount method, of class Source.
     */
    @Test
    public void testGetLineCount() {
        Source instance = new Source("");
        Integer expResult = null;
        Integer result = instance.getLineCount();
        assertEquals(expResult, result);
    }
    
}
