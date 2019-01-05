package com.company;

import java.util.Scanner;

public class Output {

    Scanner sc = new Scanner(System.in);

    private String intro1 = "Mayors wife was found dead. " +
            "Sherif needs to find out who is killer. But if he sends someone to jail and it is not killer, someone else will die." +
            "Will he find the killer before the killer will finds him?\n";
    private String intro2 = "Welcome in the game\n";

    public Output(){

    }

    public void writeIntro(){
        for (char c: intro1.toCharArray()) {
            System.out.print(c);
            //Thread.sleep(50);
        }
        for (char c: intro2.toCharArray()) {
            System.out.print(c);
            //Thread.sleep(50);
        }


        System.out.println("  _____        _      ______ _____  __  __  ____  _ \n" +
                " |  __ \\ /\\   | |    |  ____|  __ \\|  \\/  |/ __ \\| |\n" +
                " | |__) /  \\  | |    | |__  | |__) | \\  / | |  | | |\n" +
                " |  ___/ /\\ \\ | |    |  __| |  _  /| |\\/| | |  | | |\n" +
                " | |  / ____ \\| |____| |____| | \\ \\| |  | | |__| |_|\n" +
                " |_| /_/    \\_\\______|______|_|  \\_\\_|  |_|\\____/(_)\n" +
                "                                                    \n" +
                "                                                    ");
    }

    public void gameOverSummary(Killer killer, int dayCounter){
        if (killer.getIsInPrison()){
            if(dayCounter==1) {
                System.out.println("In the jail " + killer.getName() + " she admitted to the murder.");
            }
            else if (dayCounter==2){
                System.out.println("In the jail " + killer.getName() + " she admitted to both murders");
            }
            else{
                System.out.println("In the jail " + killer.getName() + " she admitted to all " + dayCounter + " murders.");
            }
            System.out.println("Congratulation you win!");
        }
        else{
            System.out.println("You did not find the killer. Game over");
        }
    }

    public boolean continuePlay() {
        System.out.println();
        System.out.println("Play again?");
        System.out.println("yes/no");
        String decision = sc.nextLine();
        while ( !decision.toLowerCase().equals("yes")&&!decision.toLowerCase().equals("no") ){
            System.err.println("invalid");

            decision = sc.nextLine();
        }
        if (decision.toLowerCase().equals("no"))
            return false;
        else
            return true;
    }

    public void newDay(int dayCounter) {
        System.out.println(dayCounter + ". day sherif came to his office and were deciding what to do.");
    }


    public void releaseFromPrisonOnTheNewDay(Character prisoner) {
        System.out.println("The murder at night commited the same person like the murder of mayors wife");
        System.out.println("It means that person in jail cannot be the killer.");
        if(prisoner.getGender()){
            System.out.println(prisoner.getName() + " she was released");
        }else {
            System.out.println(prisoner.getName() + " he was released");
        }
    }

    public void heroIsDead() {
        System.out.println("Another day they found dead sherif at the station. Game over!");
    }

    public void newDeadBody(Character victim) {
        System.out.println("Morning is here and something terrible happended again.\n" +
                "Another victim is " + victim.getName());
    }

    public void sendSomeoneToPrison(Character potentialMurderer) {
        if (potentialMurderer.getGender()){
            System.out.println("Sherif sent to jail " + potentialMurderer.getName());
        }
        else {
            System.out.println("Sherif sent to jail " + potentialMurderer.getName());
        }
    }

    public void wrongDecisionWhileMoving(){
        System.err.println("Invalid. Where shloud sherif go?");
    }

    public void writeWhoIsInTheBuilding(Building activeBuilding) {
        if (activeBuilding.isSomeoneInTheBuilding()&&!activeBuilding.getCharactersInBuilding().get(0).getIsInPrison()){
            System.out.println("Here is " + activeBuilding.getCharactersInBuilding().get(0).getName());
        }
        else{
            System.out.println("Noone is here.");
        }
    }

    public void whereHeGoesNext(Building activeBuilding) {
        System.out.println("\nAnother sherifs stopover is " + activeBuilding.getNameOfTheBuilding() + "\n");
    }


    public void listItemsInTheBuilding(Building activeBuilding) {
        if (activeBuilding.itemsInBuilding().isEmpty()){
            System.out.println("In the building is nothing interesting.");
        }
        else if(activeBuilding.itemsInBuilding().size()==1){
            System.out.println("Sherif noticed some interesting " + activeBuilding.itemsInBuilding().get(0).getNameOfTheItem());
        }
        else{
            System.out.println("Sherif is interested in these things");
            for (int i = 0; i < activeBuilding.itemsInBuilding().size(); i++) {
                System.out.print(activeBuilding.itemsInBuilding().get(i).getNameOfTheItem());
                if (i!=activeBuilding.itemsInBuilding().size()-1){
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }


    public void itemToken(Building activeBuilding, int chosenItemToTake) {
        System.out.println("sherif took " + activeBuilding.itemsInBuilding().get(chosenItemToTake).getNameOfTheItem());
    }


    public void showConnectedBuildings(Building activeBuilding) {
        System.out.println();
        System.out.println("Sherif can go to these buildings:");
        for (int i = 0; i < activeBuilding.getAccessibleBuildings().size(); i++) {

            System.out.printf("%d - %S\n",(i+1), activeBuilding.getAccessibleBuildings().get(i).getNameOfTheBuilding());

        }
        System.out.println( activeBuilding.getAccessibleBuildings().size()+1 + " - stay here");
    }

    public void wrongInput(){
        System.err.println("invalid");
    }

    public void mainDecision() {
        System.out.println();
        System.out.println("What is sherifs decision?");
        System.out.println("1 - move to another building");
        System.out.println("2 - search the building");
        System.out.println("3 - speak with resident");
        System.out.println("4 - check inventory");
    }

    public void emptyBuilidng() {
        System.out.println();
        System.out.println("Noone is here.");
    }

    public void inventoryDecision() {
        System.out.println();
        System.out.println(
                "What is his next step?\n" +
                        "1 - To look at items in inventory\n" +
                        "2 - remove item form inventory");
    }

    public void listOfItemsInInventory(Hero hero, int io){
        System.out.println();
        if (io == 1) {
            System.out.println("which item does he look at?");
        }
        else {
            System.out.println("Which item do you want to remove?");
        }
        for (int i = 0; i < hero.getInventory().size(); i++) {
            System.out.println((i+1)+" - "+hero.getInventory().get(i).getNameOfTheItem());
        }
        System.out.println((hero.getInventory().size() + 1) + " - Nothing");
    }

    public void emptyInventory() {
        System.out.println("inventory is empty");
    }

    public void interactDecision(Character suspect) {
        System.out.println();
        System.out.println("Sherif came to the resident "+suspect.getNameAbout()+"\n");
        System.out.println(
                "What is his next step?\n" +
                        "1 - Interviewed\n" +
                        "2 - Send to jail\n" +
                        "3 - Nothing");
    }

    public void interviewWithASuspect(Character suspect) {
        System.out.println("Sherif: What can you say about the murder of mayors wife?");
        System.out.println(suspect.getName() + ": " + suspect.getAnswerToQuestion() + "\n");
    }

    public void buildingItemsDecision() {
        System.out.println();
        System.out.println("What is sherifs next step?");
        System.out.println("1 - took some items\n" +
                "2 - let the items be\n" +
                "3 - look at items");
    }

    public void fullInventory() {
        System.err.println("Inventory is full, first you need to remove something") ;
    }

    public void itemToBeTaken(Building activeBuilding, int deci) {
        System.out.println();
        if (deci == 1) {
            System.out.println("Which item does sherif look at?");
        }
        else {
            System.out.println("What sherif took?");
        }
        for (int i = 0; i < activeBuilding.numberOfItems(); i++) {
            System.out.printf("%d - %S\n",(i+1), activeBuilding.itemsInBuilding().get(i).getNameOfTheItem());
        }
    }

    public void leaveItem() {
        System.out.println();
        System.out.println("sherif let everyting be.");
    }

    public void writeOutro() {
        System.out.println();
        System.out.println("Game will be ended now. Thank you for playing.");
    }
}
