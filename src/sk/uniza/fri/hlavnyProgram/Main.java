package sk.uniza.fri.hlavnyProgram;

import sk.uniza.fri.hrac.Hrac;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Zatial to je len takto pretoze chcem aby to bolo ovladane s mysou
        Hra hra = new Hra(4);
        hra.zacatHru();
        Hrac hrac1 = new Hrac("Patrik", hra);
        Hrac hrac2 = new Hrac("Dominik", hra);
        Hrac hrac3 = new Hrac("Katarína", hra);
        Hrac hrac4 = new Hrac("Dáša", hra);
        hra.pridajHraca(hrac1);
        hra.pridajHraca(hrac2);
        hra.pridajHraca(hrac3);
        hra.pridajHraca(hrac4);
        hra.zacatHru();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String s = scanner.next();
            hra.hlavnyLoop(s, scanner);
        }
    }
}