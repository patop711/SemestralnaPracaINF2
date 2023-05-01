package sk.uniza.fri.hlavnyProgram;

import javax.swing.*;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.karty.Karta;
import sk.uniza.fri.karty.KartaDivoka;
import sk.uniza.fri.karty.KartaNormalna;
import sk.uniza.fri.karty.Znak;
import sk.uniza.fri.shapesge.Circle;

import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Zatial to je len takto pretoze chcem aby to bolo ovladane s mysou
        Hra hra = new Hra(4);
        hra.zacatHru();
        Hrac hrac = new Hrac("Patrik", hra);
        Hrac hrac1 = new Hrac("Dominik", hra);
        Hrac hrac2 = new Hrac("Katarína", hra);
        Hrac hrac3 = new Hrac("Dáša", hra);
        hra.pridajHraca(hrac);
        hra.pridajHraca(hrac1);
        hra.pridajHraca(hrac2);
        hra.pridajHraca(hrac3);
        hra.zacatHru();
        //hra.dalsiHrac();
        //Karta karta = new KartaNormalna(100, 100, Color.red, Znak.KARTA_JEDEN);
        //karta.vykresli();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String s = scanner.next();
            hra.hlavnyLoop(s, scanner);
        }
    }
}