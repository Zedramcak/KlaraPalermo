package com.company;

public class Character {

    protected String name;
    protected String answerToQuestion;
    protected String nameAbout;
    private boolean isInPrison;
    boolean isAlive;
    protected boolean isWoman;
    protected Building whereCharacterLives;

    public Character(){
        isInPrison = false;
        isAlive = true;
    }

    public Character(String name, String answerToQuestion, String nameAbout, boolean isWoman, Building whereCharacterLives){
        this.name = name;
        this.answerToQuestion = answerToQuestion;
        this.nameAbout = nameAbout;
        this.isWoman = isWoman;
        this.whereCharacterLives = whereCharacterLives;
        isInPrison = false;
        isAlive = true;
}


    public String getAnswerToQuestion(){
        return answerToQuestion;
    }

    public String getName(){
        return name;
    }

    public boolean getIsInPrison() {
        return isInPrison;
    }

    // Put Character to prison or release it
    public void switchIsInPrison() {
        isInPrison = !isInPrison;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void kill() {
        isAlive = false;
    }

    public String getNameAbout() {
        return nameAbout;
    }

    public boolean getGender(){
        return isWoman;
    }

}
