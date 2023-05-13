package sk.uniza.fri.balikyKariet;

import sk.uniza.fri.hlavnyProgram.Hra;
import sk.uniza.fri.karty.Karta;

import java.util.ArrayList;

/** Trieda BalikPouzitychUnoKariet(potomok abstraktnej tredy Balik), slúži na uloženie použitých kariet UNO
 *  @author Patrik Pavlík
 *  @version 1.23.15
 */
public class BalikPouzitychUnoKariet extends Balik {
    public BalikPouzitychUnoKariet(Hra hra) {
        super(hra);
    }


    @Override
    public int pocetKarietVBaliku() {
        return super.pocetKarietVBaliku();
    }

    @Override
    public Hra getHra() {
        return super.getHra();
    }

    @Override
    public ArrayList<Karta> getBalicekKarietList() {
        return super.getBalicekKarietList();
    }

    @Override
    public boolean jeBalicekPrazdny() {
        return super.jeBalicekPrazdny();
    }

    @Override
    public void pridajKartuDoBalika(Karta karta) {
        super.pridajKartuDoBalika(karta);
    }

    @Override
    public void vymazKartuZBalika(Karta karta) {
        super.vymazKartuZBalika(karta);
    }

    @Override
    public Karta dajKartuNaIndexe(int index) {
        return super.dajKartuNaIndexe(index);
    }
}
