package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Palermo {
//scenner aby to prijalo info zadane uzivatelem
    private Scanner input = new Scanner(System.in);
    private int dayCounter;


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

        boolean playTheGame = true;

        do {
            setUpBuildings();
            setUpTown();
            while (hero.getIsAlive() && !killer.getIsInPrison()) {

                System.out.println(dayCounter + ". den ráno přišel Šerif do své kanceláře na policejní stanici a rozhodoval se, co bude dělat.");
                releaseFromPrison();

                activeBuilding = policejniStanice;

                heroDecision();

                if (!killer.getIsInPrison()) {
                    chooseWhoWillBeKilled();
                    dayCounter++;
                }
            }
            gameOverSummary();

            System.out.println();
            System.out.println("Přejete si hrát znovu?");
            System.out.println("1 - ANO");
            System.out.println("2 - NE");
            int decision = userInput();
            while ( !(decision ==1 || decision == 2) ){
                System.err.println("Neplatná volba");
                //dává možnost napsat znovu proměnnou a vrací na while
                decision = userInput();
            }
            if (decision == 2)
                playTheGame = false;

        }while (playTheGame);

    }


    private void gameOverSummary(){
        if (killer.getIsInPrison()){
            if(dayCounter==1) {
                System.out.println("Ve vězení se " + killer.getName() + " přiznala k vraždě.");
            }
            else if (dayCounter==2){
                System.out.println("Ve vězení se " + killer.getName() + " přiznala k oboum vraždám.");
            }
            else{
                System.out.println("Ve vězení se " + killer.getName() + " přiznala ke všem " + dayCounter + " vraždám.");
            }
            System.out.println("Gratulujeme! Podařilo se Vám vypátrat vraha.");
        }
        else{
            System.out.println("Nepodařilo se Vám vypatrát pravého vyníka. Hra končí. Zkuste to znovu");
        }
    }


    private void heroDecision(){
        boolean heroSendSomeoneToPrison=false;

        do{

            System.out.println();
            System.out.println("Co se Šerif rozhodl udělat?");
            System.out.println("1 - přesunout so do jiné budovy");
            System.out.println("2 - porozhlédnout se po budově");
            System.out.println("3 - promluvit si s obyvateli domu");
            System.out.println("4 - prohlédnout si inventář");

            int decision = userInput();

            while(decision<1||decision>4){
                System.err.println("Neplatná volba. Zadejte znovu, co se Šerif rozhodl udělat");
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
                        System.out.println();
                        System.out.println("Nikdo z podezřelých tu není.");
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
        System.out.println();
        System.out.println(
                "Jaké byly jeho další kroky?\n" +
                        "1 - Prohlednout si veci v inventari\n" +
                        "2 - Odebrat vec z inventare");
        int decision = userInput();
        while (decision < 1 || decision > 2) {
            System.err.println("Neplatná volba. Zadejte znovu, co se Šerif rozhodl udělat");
            decision = userInput();
        }
        switch (decision) {
            case 1:
                if (hero.isSomethingInInventory()) {
                    System.out.println();
                    System.out.println("Který předmět si Šerif prohlédl?");
                    hero.listOFItemsInInventory();
                    System.out.println((hero.getInventory().size() + 1) + " - Žádný");
                    int what = userInput();
                    while (what < 1 || what > hero.getInventory().size() + 1) {
                        System.err.println("Neplatná volba. Zadejte znovu, který předmět si Šerif prohlédl.");
                        what = userInput();

                    }
//nahore definice dole co se dela
                    if (what == (hero.getInventory().size() + 1)) {
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println(hero.exploreAnItem(what - 1));
                    }
                } else {
                    System.out.println();
                    System.out.println("Inventář je prázdný");
                }
                break;
            case 2:
                if (hero.isSomethingInInventory()) {
                    System.out.println();
                    System.out.println("Který předmět chcete odebrat?");
                    hero.listOFItemsInInventory();
                    System.out.println((hero.getInventory().size() + 1) + " - Žádný");
                    int removedItem = userInput();
                    while (removedItem < 1 || removedItem > hero.getInventory().size() + 1) {
                        System.err.println("Neplatná volba. Zadejte znovu, který předmět chcete odbrat.");
                        removedItem = userInput();
                    }
                    System.out.println();
                    //nelibi se tomu to what, a už jsou zkoušela všechno ale myslím že tam je problém že chce string a volba je v int
                  // String item = Hero.exploreAnItem(removedItem);

                    hero.removeItemFromInventory(removedItem - 1);

                   //activeBuilding.addItem(item);
                } else {
                    System.out.println();
                    System.out.println("Inventář je prázdný");
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
            System.out.println("Bylo jisté, že vraždu v noci spáchal stejný pachatel jako vraždu starostky.");
            System.out.println("To znamenalo, že osoba, která momentálně seděla v cele nemohla být hledaný vrah.");
            if(prisoner.getGender()){
                System.out.println(prisoner.getName() + " byla propuštěna z vězení");
            }else {
                System.out.println(prisoner.getName() + " byl propuštěn z vězení");
            }

        }
    }


    private boolean interactWithCharacters(Character suspect){
        System.out.println();
        System.out.println("Šerif přistoupil k "+suspect.getNameAbout()+"\n");
        System.out.println(
                "Jaké byly jeho další kroky?\n" +
                "1 - Výslech\n" +
                "2 - Poslat do vězení\n" +
                "3 - Nechat být");
        int decision = userInput();
        while (decision<1||decision>3){
            System.err.println("Neplatná volba. Zadejte znovu, co se Šerif rozhodl udělat");
            decision = userInput();
        }
        switch (decision){
            case 1:
                interviewWithASuspect(suspect);
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


    private void interviewWithASuspect(Character suspect){
        System.out.println("Šerif: Co mi můžete říct o té vraždě?");
        System.out.println(suspect.getName() + ": " + suspect.getAnswerToQuestion() + "\n");
    }


    private void itemsInBuilding(){
        activeBuilding.inspectTheBuilding();
        //kdyz je to boolean neumusim data se rovna porotze mam nastaveny vychozi a s tim se pocita
        if (activeBuilding.areThereItems()) {
            System.out.println();
            System.out.println("Co se rozhodl Šerif udělat?");
            System.out.println("1 - vzít si některý z předmětů\n" +
                    "2 - nechat předměty být\n" +
                    "3 - prohlédnout si předměty");
            int decision = userInput();

            while(decision<1||decision>3){
                System.err.println("Neplatná volba. Zadejte znovu, co se Šerif rozhodl udělat");
                decision = userInput();
            }

            interactWithAnItem(decision);

        }
    }

    // interaction with an items in the building
    private void interactWithAnItem(int decision){
        switch (decision){
            case 1:
                System.out.println();
                System.out.println("Co si Šerif vzal?");
                for (int i = 0; i < activeBuilding.numberOfItems(); i++) {
                    System.out.printf("%d - %S\n",(i+1), activeBuilding.itemsInBuilding().get(i).getNameOfTheItem());
                }
                int chosenItemToTake = userInput();
                while (chosenItemToTake>=activeBuilding.numberOfItems()+1||chosenItemToTake<1){
                    System.err.println("Neplatná volba. Zadejte znovu, který předmět si Šerif vzal");
                    chosenItemToTake = userInput();
                }
                if( hero.sizeOfInventory() > 1)
                {System.err.println("Inventar je plny, nejdrive z inventare neco odeberte") ; }
                else{
                hero.addItemToInventory(activeBuilding.takeAnItem(chosenItemToTake));}
                break;

            case 2:
                System.out.println();
                System.out.println("Šerif nechal vše tak jak bylo");
                break;

            case 3:
                System.out.println();
                System.out.println("Který předmět si Šerif prohlédl?");
                for (int i = 0; i < activeBuilding.numberOfItems(); i++) {
                    System.out.printf("%d - %S\n",(i+1), activeBuilding.itemsInBuilding().get(i).getNameOfTheItem());
                }
                int chosenItemToInspect = userInput();
                while (chosenItemToInspect>=activeBuilding.numberOfItems()+1||chosenItemToInspect<1){
                    System.err.println("Neplatná volba. Zadejte znovu, který předmět si Šerif prohlédl");
                    chosenItemToInspect = userInput();
                }
                System.out.println(activeBuilding.itemsInBuilding().get(chosenItemToInspect-1).getDescriptionOfTheItemWhenFound()+"\n");
                break;
        }
    }


    // method to change the active building. Making sure, that the input is correct
    private void setNewActiveBuilding(){
        activeBuilding.showConnectedBuilding();
        int decision = userInput();
        while (decision>(activeBuilding.NumberOfConnectedBuilding()+1)||decision<1){
            System.err.println("Neplatná volba. Zadejte znovu, kam se má šerif vypravit");
            decision = userInput();
        }
        if (decision==activeBuilding.NumberOfConnectedBuilding()+1){
            System.out.println();
        }
        else{
            activeBuilding = activeBuilding.moveToConnectedBuilding(decision-1);
            System.out.println();
            System.out.println("Další šerifovou zastávkou byla " + activeBuilding.getNameOfTheBuilding() + "\n");
            whoIsInTheBuilding();
        }
    }

    // checks and inform if there is someone in the building
    private void whoIsInTheBuilding(){
        System.out.println();
        if (activeBuilding.isSomeoneInTheBuilding()&&!activeBuilding.getCharactersInBuilding().get(0).getIsInPrison()){
            System.out.println("Nachází se zde " + activeBuilding.getCharactersInBuilding().get(0).getName());
        }
        else{
            System.out.println("Nikdo tu není.");
        }
    }

    // changes the isInPrison boolean of a character and removes it from the suspect array
    private void sendCharacterToJail(Character potentialMurderer){
        potentialMurderer.switchIsInPrison();
        System.out.println();
        // change the sentence base on the gender
        if (potentialMurderer.getGender()){
            System.out.println("Na Šerifův rozkaz byla do vězení poslána " + potentialMurderer.getName());
        }
        else {
            System.out.println("Na Šerifův rozkaz byl do vězení poslán " + potentialMurderer.getName());
        }

        prison.add(potentialMurderer);
    }

    // method to change the isAlive boolean of the character and removes it from the games arrays
    private void characterHasBeenKilled(Character victim){

        victim.kill();
        System.out.println();
        if (victim instanceof Hero) {
            System.out.println("Další ráno našli na policejní stanici mrtvého šerifa. Vrah vyhrál!");
            return;
        }
        System.out.println("Ráno se Palermo probudilo k dalšímu hrůznému činnu.\n" +
        "Další obětí neznámého vraha je " + victim.getName());

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

            if(decision.toLowerCase().equals("exit")){
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