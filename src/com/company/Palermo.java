package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Palermo {
//scenner aby to prijalo info zadane uzivatelem
    private Scanner input = new Scanner(System.in);
    private int dayCounter;
    private Output op = new Output();

    private Building activeBuilding;

    private ArrayList<Character> prison = new ArrayList<>();
    private ArrayList<Character> killableCharacters = new ArrayList<>();
    //Initialize assets
    private Building policejniStanice = new Building("Policejní stanice");
    private Building radnice = new Building("Radnice");
    private Building rybarna = new Building("Rybárna");
    private Building lekarna = new Building("Lékárna");
    private Building masna = new Building("Masna");

    private Killer killer = new Killer(rybarna);
    private Hero hero = new Hero();
    private Character reznik = new Character("Řezník", "Já jsem to nebyl", "Řezníkovi", false, masna);
    private Character starosta = new Character("Starosta", "Já jsem to nebyl", "Starostovi", false, radnice);
    private Character rybarka = new Character("Rybářka", "Já jsem to nebyla", "Rybářce", true, rybarna);

    private Item mapaVztahu = new Item(
            "podivný papír",
            "Je na něm cosi, co vypadá jako mapa vztahů",
            "Starosta + Starostka = manželé\n" +
                    "Lékárnice + Starosta = Milenci\n" +
                    "Řezník + Starostka = Milenci\n" +
                    "Rybářka + Starostka = Sestry ");

    private Item nuz = new Item(
            "nuz",
            "zkrvaveny nuz, mozna vrazedna zbran",
            "vrazedna zbran, nuz od starosticiny krve, bez otisku prstu");
    private Item baterka = new Item(
            "baterka",
            "zajimava baterka",
            "na baterce neni nic co by pomohlo s hledanim vraha");




    // construct and start the game
    public Palermo(){

//ve stringu jednotlivá písmena jsou charaktery. aby se to spouštělo postupně to je metoda por string která převede string do pole charakterů

        op.writeIntro();

        boolean playTheGame = true;

        do {
            setUpBuildings();
            setUpTown();
            while (hero.getIsAlive() && !killer.getIsInPrison()) {

                op.newDay(dayCounter);
                releaseFromPrison();

                activeBuilding = policejniStanice;

                heroDecision();

                if (!killer.getIsInPrison()) {
                    chooseWhoWillBeKilled();
                    dayCounter++;
                }
            }

            op.gameOverSummary(killer, dayCounter);

            playTheGame = op.continuePlay();

        }while (playTheGame);

    }





    private void heroDecision(){
        boolean heroSendSomeoneToPrison=false;

        do{


            op.mainDecision();

            int decision = userInput();

            while(decision<1||decision>4){
                op.wrongInput();
                decision = userInput();
            }
//switch bere promenou konkretne decision a rozhoduje se co udela podle case a ten vzdy konci break!
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

                        op.emptyBuilidng();
                    }
                    break;
                case 4:
                    exploreInventory();
                    break;

            }
//while patrici k do jakmile nekoh posle do vezeni cyklus konci
        }while(!heroSendSomeoneToPrison);
    }


    private void exploreInventory() {

        op.inventoryDecision();
        int decision = userInput();
        while (decision < 1 || decision > 2) {
            op.wrongInput();
            decision = userInput();
        }
        switch (decision) {
            case 1:
                if (hero.isSomethingInInventory()) {

                    op.listOfItemsInInventory(hero, 1);

                    int thisItem = userInput();
                    while (thisItem < 1 || thisItem > hero.getInventory().size() + 1) {
                        op.wrongInput();
                        thisItem = userInput();

                    }
//nahore definice dole co se dela
                    if (thisItem == (hero.getInventory().size() + 1)) {
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println(hero.exploreAnItem(thisItem - 1));
                    }
                } else {
                    System.out.println();
                    op.emptyInventory();
                }
                break;
            case 2:
                if (hero.isSomethingInInventory()) {
                    System.out.println();

                    op.listOfItemsInInventory(hero, 0);
                    int removedItem = userInput();
                    while (removedItem < 1 || removedItem > hero.getInventory().size() + 1) {
                        op.wrongInput();
                        removedItem = userInput();
                    }
                    System.out.println();

                    Item NameOfRemovedItem = hero.getInventory().get(removedItem);
                    activeBuilding.addItem(NameOfRemovedItem);
                    hero.removeItemFromInventory(removedItem - 1);

                   //activeBuilding.addItem(item);
                } else {
                    System.out.println();
                    op.emptyInventory();
                }
                break;

        }
    }


    private void releaseFromPrison(){
        if (!prison.isEmpty()){
            Character prisoner = prison.get(0);
            prisoner.switchIsInPrison();
            activeBuilding.addCharacter(prisoner);
            prison.clear();

            op.releaseFromPrisonOnTheNewDay(prisoner);

        }
    }


    private boolean interactWithCharacters(Character suspect){
        op.interactDecision(suspect);

        int decision = userInput();
        while (decision<1||decision>3){
            op.wrongInput();
            decision = userInput();
        }
        switch (decision){
            case 1:
                op.interviewWithASuspect(suspect);
                break;
                //tady je to boolean aby fungoval hero decision
            case 2:
                sendCharacterToJail(suspect);
                return true;
            case 3:
                break;
        }

        return false;

    }

    private void itemsInBuilding(){
        op.listItemsInTheBuilding(activeBuilding);
        //kdyz je to boolean neumusim data se rovna porotze mam nastaveny vychozi a s tim se pocita
        if (activeBuilding.areThereItems()) {
            op.buildingItemsDecision();
            int decision = userInput();

            while(decision<1||decision>3){
                op.wrongInput();
                decision = userInput();
            }

            interactWithAnItem(decision);

        }
    }

    // interaction with an items in the building
    private void interactWithAnItem(int decision){
        switch (decision){
            case 1:
                op.itemToBeTaken(activeBuilding, 1);

                int chosenItemToTake = userInput();
                while (chosenItemToTake>=activeBuilding.numberOfItems()+1||chosenItemToTake<1){
                    op.wrongInput();
                    chosenItemToTake = userInput();
                }
                if( hero.sizeOfInventory() > 1) {

                    op.fullInventory();
                }
                else{
                    op.itemToken(activeBuilding, chosenItemToTake);
                    hero.addItemToInventory(activeBuilding.takeAnItem(chosenItemToTake));}
                break;

            case 2:
                op.leaveItem();
                break;

            case 3:
                op.itemToBeTaken(activeBuilding, 0);

                int chosenItemToInspect = userInput();
                while (chosenItemToInspect>=activeBuilding.numberOfItems()+1||chosenItemToInspect<1){
                    op.wrongInput();
                    chosenItemToInspect = userInput();
                }
                System.out.println(activeBuilding.itemsInBuilding().get(chosenItemToInspect-1).getDescriptionOfTheItemWhenFound()+"\n");
                break;
        }
    }


    // method to change the active building. Making sure, that the input is correct
    private void setNewActiveBuilding(){
        op.showConnectedBuildings(activeBuilding);
        int decision = userInput();
<<<<<<< HEAD
        while (decision>(activeBuilding.NumberOfConnectedBuilding()+1)||decision<1){
            op.wrongInput();
=======
        while (decision>(activeBuilding.numberOfConnectedBuilding()+1)||decision<1){
            System.err.println("Neplatná volba. Zadejte znovu, kam se má šerif vypravit");
>>>>>>> 9384d3297ed9c5bada6a8963825e65436cd1f47b
            decision = userInput();
        }
        if (decision==activeBuilding.numberOfConnectedBuilding()+1){
            System.out.println();
        }
        else{
            activeBuilding = activeBuilding.moveToConnectedBuilding(decision-1);
            op.whereHeGoesNext(activeBuilding);
            whoIsInTheBuilding();
        }
    }

    // checks and inform if there is someone in the building
    private void whoIsInTheBuilding(){
        System.out.println();
        op.writeWhoIsInTheBuilding(activeBuilding);
    }

    // changes the isInPrison boolean of a character and removes it from the suspect array
    private void sendCharacterToJail(Character potentialMurderer){
        potentialMurderer.switchIsInPrison();
        System.out.println();
        // change the sentence base on the gender
        op.sendSomeoneToPrison(potentialMurderer);

        prison.add(potentialMurderer);
    }

    // method to change the isAlive boolean of the character and removes it from the games arrays
    private void characterHasBeenKilled(Character victim){

        victim.kill();
        System.out.println();
        if (victim instanceof Hero) {
            op.heroIsDead();
            return;
        }
        op.newDeadBody(victim);

        victim.whereCharacterLives.removeCharacterFromBuilding();
        killableCharacters.remove(victim);
    }


    private void chooseWhoWillBeKilled(){
        //math je class javi floor zaokrouhluje dolu random vytvari nahodne cislo mezi 0 a 1 ato nasobi poctem characters
        int random = (int)Math.floor(Math.random()*killableCharacters.size());
        while (killableCharacters.get(random).getIsInPrison()){
            random = (int)Math.floor(Math.random()*killableCharacters.size());
        }
        characterHasBeenKilled(killableCharacters.get(random));
    }

    //methods to set up the game
    private void setUpTown(){
        dayCounter = 1;
        activeBuilding = policejniStanice;
        killableCharacters.addAll(Arrays.asList(reznik, rybarka, hero, starosta));
    }


    private void addCharactersToBuildings(){
        radnice.addCharacter(starosta);
        rybarna.addCharacter(rybarka);
        lekarna.addCharacter(killer);
        masna.addCharacter(reznik);
    }


    private void addItemsToBuilding(){
        lekarna.addItem(mapaVztahu);
        rybarna.addItem(nuz);
        lekarna.addItem(baterka);
    }


    private void setRouteBetweenBuildings(Building a, Building b){
        a.addConnectedBuilding(b);
        b.addConnectedBuilding(a);
    }


    private void setUpBuildings(){
        setRouteBetweenBuildings(policejniStanice, radnice);
        setRouteBetweenBuildings(policejniStanice, rybarna);
        setRouteBetweenBuildings(policejniStanice, lekarna);
        setRouteBetweenBuildings(policejniStanice, masna);
        setRouteBetweenBuildings(radnice, lekarna);
        setRouteBetweenBuildings(lekarna, masna);
        setRouteBetweenBuildings(masna, rybarna);
        addCharactersToBuildings();
        addItemsToBuilding();
    }


    //user input, set the return value to -1 if the input is not an integer
    private int userInput(){
        try{
            String decision = input.nextLine();

            if(decision.toLowerCase().equals("konec")){
                System.exit(0);
            }
            if (decision.toLowerCase().equals("napoveda")){
                System.out.println("Vaším cílem je najít vraha starosty.\n" +
                        "Pro spuštění nápovědy zadejte \"napoveda\"\n" +
                        "Příkazy zadávejte pomocí čísel\n" +
                        "Pro ukončení hry napište \"exit\"\n");
                return userInput();
            }

            return Integer.parseInt(decision);
        }catch (NumberFormatException e){
            return -1;
        }
    }

}
