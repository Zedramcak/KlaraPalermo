package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Palermo {
    private Scanner input = new Scanner(System.in);
    private int dayCounter;
    private Output opt = new Output();

    private Building activeBuilding;

    private ArrayList<Character> prison = new ArrayList<>();
    private ArrayList<Character> killableCharacters = new ArrayList<>();

    private Building policeStation = new Building("Police Station");
    private Building townHall = new Building("Town hall");
    private Building fishStore = new Building("Fish store");
    private Building pharmacy = new Building("Pharmacy");
    private Building butchery = new Building("Butchery");

    private Killer killer = new Killer(pharmacy);
    private Hero hero = new Hero();
    private Character butcher = new Character("Butcher", "I have not kill anyone", "Buthcer", false, butchery);
    private Character mayor = new Character("Mayor", "I have not kill anyone", "Mayor", false, townHall);
    private Character fisherman = new Character("Fisherman", "I have not kill anyone", "Fisherman", true, fishStore);

    private Item mapOfRelationships = new Item(
            "strange paper",
            "something like a map of relationships",
            "mayor + mayors wife = married\n" +
                    "pharmacist + mayor = lovers\n" +
                    "butcher + mayors wife = lovers\n" +
                    "fisherman + mayors wife = sisters ");

    private Item knife = new Item(
            "knife",
            "knife coverd with blood",
            "nothing useful on this knife");
    private Item battery = new Item(
            "battery",
            "interesting battery",
            "nothing useful on this battery ");


    /** construct and start the game
     *
     */
    public Palermo(){
        opt.writeIntro();
        boolean playTheGame = true;
        do {
            setUpBuildings();
            setUpTown();
            while (hero.getIsAlive() && !killer.getIsInPrison()) {
                opt.newDay(dayCounter);
                releaseFromPrison();
                activeBuilding = policeStation;
                heroDecision();
                if (!killer.getIsInPrison()) {
                    chooseWhoWillBeKilled();
                    dayCounter++;
                }
            }
            opt.gameOverSummary(killer, dayCounter);
            playTheGame = opt.continuePlay();
        }while (playTheGame);
        opt.writeOutro();
    }

    /**first metode in the game let the player choose what to do next
     *
     */
    private void heroDecision(){
        boolean heroSendSomeoneToPrison=false;
        do{
            opt.mainDecision();
            int decision = userInput();
            while(decision<1||decision>4){
                opt.wrongInput();
                decision = userInput();
            }
            switch(decision){
                case 1:
                    setNewActiveBuilding();
                    break;
                case 2:
                    itemsInBuilding();
                    break;
                case 3:
                    if (activeBuilding.isSomeoneInTheBuilding()) {
                        heroSendSomeoneToPrison = interactWithCharacters(activeBuilding.getCharactersInBuilding().get(0));
                    }else{
                        opt.emptyBuilidng();
                    }
                    break;
                case 4:
                    exploreInventory();
                    break;
            }
        }while(!heroSendSomeoneToPrison);
    }

    /**this metode take care of inventory add, look at and remove items
     *
     */
    private void exploreInventory() {
        opt.inventoryDecision();
        int decision = userInput();
        while (decision < 1 || decision > 2) {
            opt.wrongInput();
            decision = userInput();
        }
        switch (decision) {
            case 1:
                if (hero.isSomethingInInventory()) {
                    opt.listOfItemsInInventory(hero, 1);
                    int thisItem = userInput();
                    while (thisItem < 1 || thisItem > hero.getInventory().size() + 1) {
                        opt.wrongInput();
                        thisItem = userInput();
                    }
                    if (thisItem == (hero.getInventory().size() + 1)) {
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println(hero.exploreAnItem(thisItem - 1));
                    }
                } else {
                    System.out.println();
                    opt.emptyInventory();
                }
                break;
            case 2:
                if (hero.isSomethingInInventory()) {
                    System.out.println();
                    opt.itemToBeTaken(activeBuilding,0);
                    int removedItem = userInput();
                    System.out.println();
                    opt.itemToBeTaken(activeBuilding,1);
                    Item NameOfRemovedItem = hero.getInventory().get(removedItem);
                    activeBuilding.addItem(NameOfRemovedItem);
                    hero.removeItemFromInventory(removedItem - 1);
                } else {
                    System.out.println();
                    opt.emptyInventory();
                }
                break;

        }
    }

    /**this metode remove prisoner from prison, only if the prison is full, and add him back to his building.
     *
     */
    private void releaseFromPrison(){
        if (!prison.isEmpty()){
            Character prisoner = prison.get(0);
            prisoner.switchIsInPrison();
            activeBuilding.addCharacter(prisoner);
            prison.clear();
            opt.releaseFromPrisonOnTheNewDay(prisoner);
        }
    }

    /**this metode let player interact with characters and send to jail or interviewed.
     *
     * @param suspect
     * @return
     */
    private boolean interactWithCharacters(Character suspect){
        opt.interactDecision(suspect);

        int decision = userInput();
        while (decision<1||decision>3){
            opt.wrongInput();
            decision = userInput();
        }
        switch (decision){
            case 1:
                opt.interviewWithASuspect(suspect);
                break;
            case 2:
                sendCharacterToJail(suspect);
                return true;
            case 3:
                break;
        }
        return false;
    }

    /**if in the building are some items player can interact with them.
     *
     */
    private void itemsInBuilding(){
        opt.listItemsInTheBuilding(activeBuilding);
        //kdyz je to boolean neumusim data se rovna porotze mam nastaveny vychozi a s tim se pocita
        if (activeBuilding.areThereItems()) {
            opt.buildingItemsDecision();
            int decision = userInput();
            while(decision<1||decision>3){
                opt.wrongInput();
                decision = userInput();
            }
            interactWithAnItem(decision);
        }
    }

    /**here the player can look at or take the thing. Only if the inventory is not full.
     *
     * @param decision
     */
    private void interactWithAnItem(int decision){
        switch (decision){
            case 1:
                opt.itemToBeTaken(activeBuilding, 1);
                int chosenItemToTake = userInput();
                while (chosenItemToTake>=activeBuilding.numberOfItems()+1||chosenItemToTake<1){
                    opt.wrongInput();
                    chosenItemToTake = userInput();
                }
                if( hero.sizeOfInventory() > 1) {
                    opt.fullInventory();
                }
                else{
                    opt.itemToken(activeBuilding, chosenItemToTake);
                    hero.addItemToInventory(activeBuilding.takeAnItem(chosenItemToTake));}
                break;
            case 2:
                opt.leaveItem();
                break;
            case 3:
                opt.itemToBeTaken(activeBuilding, 0);
                int chosenItemToInspect = userInput();
                while (chosenItemToInspect>=activeBuilding.numberOfItems()+1||chosenItemToInspect<1){
                    opt.wrongInput();
                    chosenItemToInspect = userInput();
                }
                System.out.println(activeBuilding.itemsInBuilding().get(chosenItemToInspect-1).getDescriptionOfTheItemWhenFound()+"\n");
                break;
        }
    }

    /**change the active building. To make sure, that the input is correct.
     *
     */
    private void setNewActiveBuilding(){
        opt.showConnectedBuildings(activeBuilding);
        int decision = userInput();
        while (decision>(activeBuilding.numberOfConnectedBuilding()+1)||decision<1) {
            opt.wrongInput();
        }
        while (decision>(activeBuilding.numberOfConnectedBuilding()+1)||decision<1){
            opt.wrongDecisionWhileMoving();
            decision = userInput();
        }
        if (decision==activeBuilding.numberOfConnectedBuilding()+1){
            System.out.println();
        }
        else{
            activeBuilding = activeBuilding.moveToConnectedBuilding(decision-1);
            opt.whereHeGoesNext(activeBuilding);
            whoIsInTheBuilding();
        }
    }


    /**checks and inform if there is someone in the building.
     *
     */
    private void whoIsInTheBuilding(){
        System.out.println();
        opt.writeWhoIsInTheBuilding(activeBuilding);
    }

    /**changes the isInPrison boolean of a character and removes it from the suspect array.
     *
     * @param potentialMurderer
     */
    private void sendCharacterToJail(Character potentialMurderer){
        potentialMurderer.switchIsInPrison();
        System.out.println();
        opt.sendSomeoneToPrison(potentialMurderer);
        prison.add(potentialMurderer);
    }

    /** method to change the isAlive boolean of the character and removes it from the games arrays.
     *
     * @param victim
     */
    private void characterHasBeenKilled(Character victim){
        victim.kill();
        System.out.println();
        if (victim instanceof Hero) {
            opt.heroIsDead();
            return;
        }
        opt.newDeadBody(victim);
        victim.whereCharacterLives.removeCharacterFromBuilding();
        killableCharacters.remove(victim);
    }

    /**random choose who will be killed.
     *
     */
    private void chooseWhoWillBeKilled(){
        int random = (int)Math.floor(Math.random()*killableCharacters.size());
        while (killableCharacters.get(random).getIsInPrison()){
            random = (int)Math.floor(Math.random()*killableCharacters.size());
        }
        characterHasBeenKilled(killableCharacters.get(random));
    }

    /**methods to set up the game
     *
     */
    private void setUpTown(){
        dayCounter = 1;
        activeBuilding = policeStation;
        killableCharacters.addAll(Arrays.asList(butcher, fisherman, hero, mayor));
    }


    private void addCharactersToBuildings(){
        townHall.addCharacter(mayor);
        fishStore.addCharacter(fisherman);
        pharmacy.addCharacter(killer);
        butchery.addCharacter(butcher);
    }


    private void addItemsToBuilding(){
        pharmacy.addItem(mapOfRelationships);
        fishStore.addItem(knife);
        pharmacy.addItem(battery);
    }


    private void setRouteBetweenBuildings(Building a, Building b){
        a.addConnectedBuilding(b);
        b.addConnectedBuilding(a);
    }


    private void setUpBuildings(){
        setRouteBetweenBuildings(policeStation, townHall);
        setRouteBetweenBuildings(policeStation, fishStore);
        setRouteBetweenBuildings(policeStation, pharmacy);
        setRouteBetweenBuildings(policeStation, butchery);
        setRouteBetweenBuildings(townHall, pharmacy);
        setRouteBetweenBuildings(pharmacy, butchery);
        setRouteBetweenBuildings(butchery, fishStore);
        addCharactersToBuildings();
        addItemsToBuilding();
    }


    /**user input, set the return value to -1 if the input is not an integer.
     *
     * @return
     */
    private int userInput(){
        try{
            String decision = input.nextLine();

            if(decision.toLowerCase().equals("exit")){
                System.exit(0);
            }
            if (decision.toLowerCase().equals("hint")){
                System.out.println("Your goal is to find a killer and send him/her to jail.\n" +
                        "For playing the game use numbers.\n" +
                        "Exit game with word \"exit\"\n");
                return userInput();
            }
            return Integer.parseInt(decision);
        }catch (NumberFormatException e){
            return -1;
        }
    }

}
