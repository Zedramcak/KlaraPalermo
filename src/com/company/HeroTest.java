package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void exploreAnItem() {
        Hero hero = new Hero();
        Item item = new Item("smth","wow","OMG");
        hero.addItemToInventory(item);
        assertEquals("OMG",hero.exploreAnItem(0));
    }

    @Test
    public void isSomethingInInventory() {
        Hero hero = new Hero();
        Item item = new Item("smth","wow","OMG");
        assertEquals(false,hero.isSomethingInInventory());
        hero.addItemToInventory(item);
        assertEquals(true,hero.isSomethingInInventory());
        hero.removeItemFromInventory(0);
        assertEquals(false,hero.isSomethingInInventory());
    }

    @Test
    public void sizeOfInventory() {
        Hero hero = new Hero();
        Item item = new Item("smth","wow","OMG");
        assertEquals(0,hero.sizeOfInventory());
        hero.addItemToInventory(item);
        assertEquals(1,hero.sizeOfInventory());
        hero.addItemToInventory(item);
        assertEquals(2,hero.sizeOfInventory());
        hero.removeItemFromInventory(0);
        assertEquals(1,hero.sizeOfInventory());
    }


}