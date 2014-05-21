package com.mestrelab.tools;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public void testApp2(){
    	assertTrue("Lucia", true);
    }
    
    /*
    public void testApp3(){
    	String var1="sa";
    	String var2="sax";
    	assertEquals("Lucia", "Lucia", var2);
    }
    //este claramente falla xa que espera a cadena lucia e recibe a cadena sax
    */
    
    public void testApp3(){
    	String var2="sax";
    	assertEquals("sax", "sax", var2);
    }
    
    public void testSuma(){
    	int suma1 = 5;
    	int suma2 = 4;
    	
    	assertTrue((suma1 + suma2) == 9);
    }
    
    public void testSuma2(){
    	int suma1 = 4;
    	int suma2 = 4;
    	
    	assertFalse((suma1 + suma2) == 9);
    	
    }
    
    
    
    public void testApp4(){
    	String var1 = "OOOhh";
    	assertFalse(var1, false);
    	assertTrue(var1, true);
    }
    
    public void testSuma3(){
    	int suma1 = 8;
    	int suma2 = 4;
    	
    	assertTrue((suma1 - suma2) == 4);
    	assertFalse((suma1 - suma2) == 3);
    	assertEquals((suma1 - suma2) == 4, (suma1 - suma2) == 4);
    	
    }
}
