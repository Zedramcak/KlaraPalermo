package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PalermoTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void addingItemsToBuildingTest(){
        Building b1 = new Building("b1");
        
        b1.addItem(new Item("smt", "smt", "smt"));
        
        assertEquals(1, b1.numberOfItems());
    }


}