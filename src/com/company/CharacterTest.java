package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAnswerToQuestion() {
        Building building = new Building("b1");
        Character smbd = new Character("smbd", "smbd","smbd",true,building);
        assertEquals("smbd",smbd.getAnswerToQuestion());
    }

    @Test
    public void getName() {


    }

    @Test
    public void getIsInPrison() {
        Building building = new Building("b1");
        Character smbd = new Character("smbd", "smbd","smbd",true,building);
        assertEquals(false,smbd.getIsInPrison());

    }

    @Test
    public void switchIsInPrison() {

    }

    @Test
    public void getIsAlive() {
        Building building = new Building("b1");
        Character smbd = new Character("smbd", "smbd","smbd",true,building);
        assertEquals(true,smbd.getIsAlive());
    }

    @Test
    public void kill() {
    }

    @Test
    public void getNameAbout() {
    }

    @Test
    public void getGender() {
        Building building = new Building("b1");
        Character smbd = new Character("smbd", "smbd","smbd",true,building);
        assertEquals(true,smbd.getGender());
    }
}