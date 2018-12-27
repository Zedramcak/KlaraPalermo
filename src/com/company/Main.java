package com.company;


import java.util.Scanner;

public class Main {
//system.in = vstup od uživatele
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {



        String intro1 = "Starostova manželka byla ráno nalezena mrtvá. Šerif si vytipoval několik možných pachatelů." +
                " Podaří se mu najít vraha před tím, než si vrah najde jeho?\n";
        String intro2 = "Vítejte ve hře\n";
//ve stringu jednotlivá písmena jsou charaktery. aby se to spouštělo postupně to je metoda por string která převede string do pole charakterů
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


            new Palermo();


//tady už se jenom vypíše text po ukončení
        System.out.println();
        System.out.println("Hra bude nyní ukončena. Děkujeme, že jste si ji zahráli.");

    }

}
