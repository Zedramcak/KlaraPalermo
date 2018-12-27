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
}
