package sk.uniza.fri.balikyKariet;

import sk.uniza.fri.hlavnyProgram.Hra;
import sk.uniza.fri.karty.Karta;

import java.util.ArrayList;

public abstract class Balik {
    private final ArrayList<Karta> balicekKarietList;
    private final Hra hra;

    public Balik(Hra hra) {
        this.hra = hra;
        this.balicekKarietList = new ArrayList<>();
    }

    public void pridajKartuDoBalika(Karta karta) {
        this.getBalicekKarietList().add(karta);
    }
    public void vymazKartuZBalika(Karta karta) {
        this.getBalicekKarietList().remove(karta);
    }
    public Karta dajKartuNaIndexe(int index) {
        if (index >= 0 && (index <= this.getBalicekKarietList().size() - 1)) {
            return this.getBalicekKarietList().get(index);
        }
        return null;
    }

    public int pocetKarietVBaliku() {
        return this.balicekKarietList.size();
    }

    public Hra getHra() {
        return this.hra;
    }

    public ArrayList<Karta> getBalicekKarietList() {
        return this.balicekKarietList;
    }
    public boolean jeBalicekPrazdny() {
        return this.getBalicekKarietList().isEmpty();
    }
}
