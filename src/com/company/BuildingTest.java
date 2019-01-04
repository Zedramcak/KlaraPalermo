package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BuildingTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void inspectTheBuilding() {

    }

    @Test
    public void exploreItem() {
    }

    @Test
    public void takeAnItem() {
    }

    @Test
    public void showConnectedBuilding() {
    }

    @Test
    public void getNameOfTheBuilding() {
        Building building = new Building("b1");
        assertEquals("b1",building.getNameOfTheBuilding());
    }

    @Test
    public void numberOfConnectedBuildingTest() {
        Building building = new Building("b1");
        assertEquals(0,building.numberOfConnectedBuilding());

        building.addConnectedBuilding(new Building("b2"));
        assertEquals(1,building.numberOfConnectedBuilding());


        building.addConnectedBuilding(new Building("b3"));
        assertEquals(2,building.numberOfConnectedBuilding());
    }

    @Test
    public void moveToConnectedBuilding() {
        Building building = new Building("b1");
        Building building2 = new Building("b2");
        building.addConnectedBuilding(building2);

        assertEquals(building2,building.moveToConnectedBuilding(0));
    }

    @Test
    public void areThereItems() {
        Building building = new Building("b1");
        Item item = new Item("sm","sm","sm");
        assertEquals(false, building.areThereItems());
        building.addItem(item);
        assertEquals(true, building.areThereItems());

    }

    @Test
    public void numberOfItems() {
        Building building = new Building("b1");
        Item item = new Item("sm","sm","sm");
        assertEquals(0, building.numberOfItems());
        building.addItem(item);
        assertEquals(1, building.numberOfItems());
        building.addItem(item);
        assertEquals(2, building.numberOfItems());
        building.removeIs;
        assertEquals(2, building.numberOfItems());

    }

    @Test
    public void isSomeoneInTheBuilding() {
        Building building = new Building("b1");
        Character smbd = new Character("smbd","smbd","smbd",true,building);
        assertEquals(false, building.isSomeoneInTheBuilding());
        building.addCharacter(smbd);
        assertEquals(true, building.isSomeoneInTheBuilding());
        building.removeCharacterFromBuilding();
        assertEquals(false,building.isSomeoneInTheBuilding());
    }


}