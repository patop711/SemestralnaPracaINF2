package sk.uniza.fri.balikyKariet;

import sk.uniza.fri.hlavnyProgram.Hra;
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

/** Trieda BalikUnoKariet(potomok abstraktnej tredy Balik), slúži na vytvorenie balíčka kariet UNO
 *  @author Patrik Pavlík
 *  @version 1.23.15
 */
public class BalikUnoKariet extends Balik {

    public BalikUnoKariet(Hra hra) {
        super(hra);
        this.vytvorKarty();
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
    public int pocetKarietVBaliku() {
        return super.pocetKarietVBaliku();
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

    /**
     * Rozdá karty hráčom z ArrayListu
     * @param hraci - ArrayList hráčov
     */
    public void rozdajKarty(ArrayList<Hrac> hraci) {
        int index = 0;
        for (Hrac hrac : hraci) {
            for (int i = 1; i <= 7; i++) {
                Karta vlozenaKarta = this.getBalicekKarietList().get(index);
                hrac.zoberKartu(vlozenaKarta);
                this.getBalicekKarietList().remove(vlozenaKarta);
                index++;
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
            //System.out.println("Karta nula, farba: " + farba.toString() + " znak: " + Znak.KARTA_NULA);
            System.out.println();
            for (int i = 0; i < 2; i++) {
                for (int j = 1; j < 10; j++) {
                    this.getBalicekKarietList().add(new KartaNormalna(predvoleneX, predvoleneY, farba, Znak.values()[j]));
                    //System.out.println("Farba: " + farba.toString() + " znak: " + Znak.values()[j]);
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

        System.out.println(super.pocetKarietVBaliku());
        this.pomiesajKarty();
    }

    /**
     * Metóda na pomiesanie kariet v ArrayListe
     */
    private void pomiesajKarty() {
        Collections.shuffle(this.getBalicekKarietList());

    }

    /**
     * Pridá hráčovi karty
     *
     * @param kolkoKariet - kolko kariet má dostať hrac
     */
    public void dajHracoviKartyPodlaPoctu(int kolkoKariet, boolean jeToPreDalsiehoHraca) {
        if (jeToPreDalsiehoHraca) {
            this.getHra().dalsiHrac();
        }
        for (int i = 0; i < kolkoKariet; i++) {
            Karta novaKarta = this.getBalicekKarietList().get(0);
            if (this.getHra().getHracNaTahu().zoberKartu(novaKarta)) {
                this.getBalicekKarietList().remove(novaKarta);
                System.out.println(novaKarta.toString());
            }
        }
        if (!jeToPreDalsiehoHraca) {
            this.getHra().dalsiHrac();
        }
    }
}
