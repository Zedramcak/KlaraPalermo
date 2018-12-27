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
}
