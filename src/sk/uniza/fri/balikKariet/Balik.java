package sk.uniza.fri.balikKariet;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.karty.Karta;
import sk.uniza.fri.karty.KartaDivoka;
import sk.uniza.fri.karty.KartaDivokaTahajStyri;
import sk.uniza.fri.karty.KartaNormalna;
import sk.uniza.fri.karty.KartaOtocit;
import sk.uniza.fri.karty.KartaPreskocit;
import sk.uniza.fri.karty.KartaTahajDve;
import sk.uniza.fri.karty.Znak;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Abstraktná trieda Balík
 *
 * @author Patrik Pavlík
 * @version 1.23.15
 */
public class Balik {
    private final ArrayList<Karta> balicekKarietList;
    private final boolean jeToBalikNovychKariet;

    /**
     * Parametrický konštruktor abstraktnej triedy
     *
     * @param jeToBalikNovychKariet - ak je to balík nových kariet tak sa vytvoria karty a do ArrayListu sa vložia Uno karty
     */
    public Balik(boolean jeToBalikNovychKariet) {
        this.balicekKarietList = new ArrayList<>();
        this.jeToBalikNovychKariet = jeToBalikNovychKariet;
        if (this.jeToBalikNovychKariet) {
            this.vytvorKarty();
        }
    }

    /**
     * Vráti počet kariet v balíčku
     *
     * @return balicekKarietList.size
     */
    public int pocetKarietVBaliku() {
        return this.balicekKarietList.size();
    }

    /**
     * Vráti objekt typu ArrayList
     *
     * @return balicekKarietList
     */
    public ArrayList<Karta> getBalicekKarietList() {
        return this.balicekKarietList;
    }

    /**
     * Vráti boolean či je balicek prazdny
     *
     * @return getBalicekKarietList.isEmpty
     */
    public boolean jeBalicekPrazdny() {
        return this.getBalicekKarietList().isEmpty();
    }

    /**
     * Pridá kartu do balíka
     *
     * @param karta - karta ktorá sa pridá
     */
    public void pridajKartuDoBalika(Karta karta) {
        this.getBalicekKarietList().add(karta);
    }

    /**
     * Odstráni kartu z balíka
     *
     * @param karta - karta ktorá sa odstani
     */
    public void vymazKartuZBalika(Karta karta) {
        this.getBalicekKarietList().remove(karta);
    }

    /**
     * Vráti objekt typu Karta z pola kariet
     *
     * @param index - pozicia karty na indexe
     * @return getBalicekKarietList.get
     */
    public Karta dajKartuNaIndexe(int index) {
        if (index >= 0 && (index <= this.getBalicekKarietList().size() - 1)) {
            return this.getBalicekKarietList().get(index);
        }
        return null;
    }

    /**
     * Rozdá karty hráčom z ArrayListu
     *
     * @param hraci - ArrayList hráčov
     */
    public void rozdajKarty(ArrayList<Hrac> hraci) {
        if (this.jeToBalikNovychKariet) {
            int index = 0;
            for (Hrac hrac : hraci) {
                for (int i = 1; i <= 7; i++) {
                    Karta vlozenaKarta = this.dajKartuNaIndexe(index);
                    hrac.zoberKartu(vlozenaKarta);
                    this.vymazKartuZBalika(vlozenaKarta);
                    index++;
                }
            }
        }
    }

    /**
     * Metoda na vytvorenie všetkých 108 kariet UNO
     */
    private void vytvorKarty() {
        int predvoleneX = -80;
        int predvoleneY = 0;
        Color[] poleFarieb = {Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN};
        //NormalneKarty
        for (Color farba : poleFarieb) {
            this.getBalicekKarietList().add(new KartaNormalna(predvoleneX, predvoleneY, farba, Znak.KARTA_NULA));
            System.out.println();
            for (int i = 0; i < 2; i++) {
                for (int j = 1; j < 10; j++) {
                    this.getBalicekKarietList().add(new KartaNormalna(predvoleneX, predvoleneY, farba, Znak.values()[j]));
                }
                //Karty TahajDve
                this.getBalicekKarietList().add(new KartaTahajDve(predvoleneX, predvoleneY, farba));
                //Karty Otocit
                this.getBalicekKarietList().add(new KartaOtocit(predvoleneX, predvoleneY, farba));
                //Karty Preskocit
                this.getBalicekKarietList().add(new KartaPreskocit(predvoleneX, predvoleneY, farba));

            }

        }
        //KartyTahajStyri a KartyDivoke
        for (int i = 0; i < 4; i++) {
            this.getBalicekKarietList().add(new KartaDivokaTahajStyri(predvoleneX, predvoleneY));
            this.getBalicekKarietList().add(new KartaDivoka(predvoleneX, predvoleneY));
        }

        for (Karta karta : this.getBalicekKarietList()) {
            karta.skrySa();
        }

        this.pomiesajKarty();
    }

    /**
     * Metóda na pomiesanie kariet v ArrayListe
     */
    private void pomiesajKarty() {
        Collections.shuffle(this.getBalicekKarietList());

    }
}
