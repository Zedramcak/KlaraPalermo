package com.company;
//extended znamena ze dedi z class character

public class Killer extends Character{

    public Killer(Building whereCharacterLives) {
        super();
        name = "Lékarnice";
        nameAbout = "Lékárnici";
        answerToQuestion = "Já jsem to... eh... nebyla. JE TO KAMPAŇ!";
        isWoman = true;
        this.whereCharacterLives = whereCharacterLives;
    }
}
