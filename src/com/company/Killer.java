package com.company;
//extended znamena ze dedi z class character

public class Killer extends Character{

    public Killer(Building whereCharacterLives) {
        super();
        name = "Pharmacist";
        nameAbout = "Pharmacist";
        answerToQuestion = "I ...emh...have not kill him.";
        isWoman = true;
        this.whereCharacterLives = whereCharacterLives;
    }
}
