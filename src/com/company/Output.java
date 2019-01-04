package com.company;

import java.util.Scanner;

public class Output {

    Scanner sc = new Scanner(System.in);

    private String intro1 = "Starostova manželka byla ráno nalezena mrtvá. Šerif si vytipoval několik možných pachatelů." +
            " Podaří se mu najít vraha před tím, než si vrah najde jeho?\n";
    private String intro2 = "Vítejte ve hře\n";

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

    public boolean continuePlay() {
        System.out.println();
        System.out.println("Přejete si hrát znovu?");
        System.out.println("ano/ne");
        String decision = sc.nextLine();
        while ( !decision.toLowerCase().equals("ano")&&!decision.toLowerCase().equals("ne") ){
            System.err.println("Neplatná volba");

            decision = sc.nextLine();
        }
        if (decision.toLowerCase().equals("ne"))
            return false;
        else
            return true;
    }

    public void newDay(int dayCounter) {
        System.out.println(dayCounter + ". den ráno přišel Šerif do své kanceláře na policejní stanici a rozhodoval se, co bude dělat.");
    }


    public void releaseFromPrisonOnTheNewDay(Character prisoner) {
        System.out.println("Bylo jisté, že vraždu v noci spáchal stejný pachatel jako vraždu starostky.");
        System.out.println("To znamenalo, že osoba, která momentálně seděla v cele nemohla být hledaný vrah.");
        if(prisoner.getGender()){
            System.out.println(prisoner.getName() + " byla propuštěna z vězení");
        }else {
            System.out.println(prisoner.getName() + " byl propuštěn z vězení");
        }
    }

    public void heroIsDead() {
        System.out.println("Další ráno našli na policejní stanici mrtvého šerifa. Vrah vyhrál!");
    }

    public void newDeadBody(Character victim) {
        System.out.println("Ráno se Palermo probudilo k dalšímu hrůznému činnu.\n" +
                "Další obětí neznámého vraha je " + victim.getName());
    }

    public void sendSomeoneToPrison(Character potentialMurderer) {
        if (potentialMurderer.getGender()){
            System.out.println("Na Šerifův rozkaz byla do vězení poslána " + potentialMurderer.getName());
        }
        else {
            System.out.println("Na Šerifův rozkaz byl do vězení poslán " + potentialMurderer.getName());
        }
    }

    public void writeWhoIsInTheBuilding(Building activeBuilding) {
        if (activeBuilding.isSomeoneInTheBuilding()&&!activeBuilding.getCharactersInBuilding().get(0).getIsInPrison()){
            System.out.println("Nachází se zde " + activeBuilding.getCharactersInBuilding().get(0).getName());
        }
        else{
            System.out.println("Nikdo tu není.");
        }
    }

    public void whereHeGoesNext(Building activeBuilding) {
        System.out.println("\nDalší šerifovou zastávkou byla " + activeBuilding.getNameOfTheBuilding() + "\n");
    }


    public void listItemsInTheBuilding(Building activeBuilding) {
        if (activeBuilding.itemsInBuilding().isEmpty()){
            System.out.println("V budově není nic zajímavého");
        }
        else if(activeBuilding.itemsInBuilding().size()==1){
            System.out.println("Šerif si všiml že v místnosti je podezřele vypadající " + activeBuilding.itemsInBuilding().get(0).getNameOfTheItem());
        }
        else{
            System.out.println("Šerifa zaujali tyto předměty");
            for (Item item: activeBuilding.itemsInBuilding() ) {
                System.out.print(item.getNameOfTheItem() + " " );
            }
            System.out.println();
        }
    }


    public void itemToken(Building activeBuilding, int chosenItemToTake) {
        System.out.println("Šerif si vzal " + activeBuilding.itemsInBuilding().get(chosenItemToTake).getNameOfTheItem());
    }


    public void showConnectedBuildings(Building activeBuilding) {
        System.out.println();
        System.out.println("Šerif se odsud mohl vydat do těchto budov:");
        for (int i = 0; i < activeBuilding.getAccessibleBuildings().size(); i++) {

            System.out.printf("%d - %S\n",(i+1), activeBuilding.getAccessibleBuildings().get(i).getNameOfTheBuilding());

        }
        System.out.println( activeBuilding.getAccessibleBuildings().size()+1 + " - zůstat na místě");
    }

    public void wrongInput(){
        System.err.println("Neplatná volba");
    }

    public void mainDecision() {
        System.out.println();
        System.out.println("Co se Šerif rozhodl udělat?");
        System.out.println("1 - přesunout so do jiné budovy");
        System.out.println("2 - porozhlédnout se po budově");
        System.out.println("3 - promluvit si s obyvateli domu");
        System.out.println("4 - prohlédnout si inventář");
    }

    public void emptyBuilidng() {
        System.out.println();
        System.out.println("Nikdo z podezřelých tu není.");
    }

    public void inventoryDecision() {
        System.out.println();
        System.out.println(
                "Jaké byly jeho další kroky?\n" +
                        "1 - Prohlednout si veci v inventari\n" +
                        "2 - Odebrat vec z inventare");
    }

    public void listOfItemsInInventory(Hero hero, int io){
        System.out.println();
        if (io == 1) {
            System.out.println("Který předmět si Šerif prohlédl?");
        }
        else {
            System.out.println("Který předmět chcete odebrat?");
        }
        for (int i = 0; i < hero.getInventory().size(); i++) {
            System.out.println((i+1)+" - "+hero.getInventory().get(i).getNameOfTheItem());
        }
        System.out.println((hero.getInventory().size() + 1) + " - Žádný");
    }

    public void emptyInventory() {
        System.out.println("Inventář je prázdný");
    }

    public void interactDecision(Character suspect) {
        System.out.println();
        System.out.println("Šerif přistoupil k "+suspect.getNameAbout()+"\n");
        System.out.println(
                "Jaké byly jeho další kroky?\n" +
                        "1 - Výslech\n" +
                        "2 - Poslat do vězení\n" +
                        "3 - Nechat být");
    }

    public void interviewWithASuspect(Character suspect) {
        System.out.println("Šerif: Co mi můžete říct o té vraždě?");
        System.out.println(suspect.getName() + ": " + suspect.getAnswerToQuestion() + "\n");
    }

    public void buildingItemsDecision() {
        System.out.println();
        System.out.println("Co se rozhodl Šerif udělat?");
        System.out.println("1 - vzít si některý z předmětů\n" +
                "2 - nechat předměty být\n" +
                "3 - prohlédnout si předměty");
    }

    public void fullInventory() {
        System.err.println("Inventar je plny, nejdrive z inventare neco odeberte") ;
    }

    public void itemToBeTaken(Building activeBuilding, int io) {
        System.out.println();
        if (io == 1) {
            System.out.println("Který předmět si Šerif prohlédl?");
        }
        else {
            System.out.println("Co si Šerif vzal?");
        }
        for (int i = 0; i < activeBuilding.numberOfItems(); i++) {
            System.out.printf("%d - %S\n",(i+1), activeBuilding.itemsInBuilding().get(i).getNameOfTheItem());
        }
    }

    public void leaveItem() {
        System.out.println();
        System.out.println("Šerif nechal vše tak jak bylo");
    }
}
