package sk.uniza.fri.hlavnyProgram;

import sk.uniza.fri.balikKariet.Balik;
import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.karty.Karta;
import sk.uniza.fri.karty.KartaDivoka;
import sk.uniza.fri.karty.KartaDivokaTahajStyri;
import sk.uniza.fri.karty.KartaNormalna;
import sk.uniza.fri.karty.KartaOtocit;
import sk.uniza.fri.karty.KartaPreskocit;
import sk.uniza.fri.karty.KartaTahajDve;
import sk.uniza.fri.karty.Znak;
import sk.uniza.fri.shapesge.Manager;
import sk.uniza.fri.shapesge.Text;

import javax.swing.JOptionPane;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Trieda Hra, vytovrí hru UNO.
 *
 * @author Patrik Pavlík
 * @version 1.23.15
 */
public class Hra {
    private final ArrayList<Hrac> hraci;
    private final int pocetHracov;
    private boolean poSmereHodinovychRuciciek;
    private Hrac hracNaTahu;
    private final Manager manazer;
    private final Text textKtoJeNaRade;
    private final Balik balikUnoKariet;
    private final Balik balikPouzitychUnoKariet;


    /**
     * Parametricky konstruktor kde zadavame limit hracov ktorých máme nastaviť
     * Spustí sa ArrayList pre hracov
     *
     * @param pocetHracov - pocet hracov ktorý majú byť v hre
     */
    public Hra(int pocetHracov) {
        this.textKtoJeNaRade = new Text("Teraz na rade nie je nikto");
        this.poSmereHodinovychRuciciek = true;
        this.hraci = new ArrayList<>();
        //Minimálne musia hrať hru dvaja hráči :D
        if (pocetHracov >= 2 && pocetHracov <= 4) {
            this.pocetHracov = pocetHracov;
        } else {
            this.pocetHracov = 2;
        }
        //Cez Manazera ovladam hru
        this.manazer = new Manager();
        this.getManazer().manageObject(this);
        this.balikUnoKariet = new Balik(true);
        this.balikPouzitychUnoKariet = new Balik(false);
    }

    /**
     * Vráti objekt typu BalikPouzitychUnoKariet
     *
     * @return balikPouzitychUnoKariet
     */
    public Balik getBalikPouzitychUnoKariet() {
        return this.balikPouzitychUnoKariet;
    }

    /**
     * Vráti objekt typu BalikUnoKariet
     *
     * @return balikUnoKariet
     */
    public Balik getBalikUnoKariet() {
        return this.balikUnoKariet;
    }

    /**
     * Pridá hráčovi karty
     *
     * @param kolkoKariet - kolko kariet má dostať hrac
     */
    public void dajHracoviKartyPodlaPoctu(int kolkoKariet, boolean jeToPreDalsiehoHraca) {

        if (this.getBalikUnoKariet().jeBalicekPrazdny()) {
            for (int i = this.getBalikPouzitychUnoKariet().pocetKarietVBaliku() - 2; i >= 0; i--) {
                var kartaNaPridanie = this.getBalikPouzitychUnoKariet().dajKartuNaIndexe(i);
                this.getBalikUnoKariet().pridajKartuDoBalika(kartaNaPridanie);
                this.getBalikPouzitychUnoKariet().vymazKartuZBalika(kartaNaPridanie);
            }
        }

        if (jeToPreDalsiehoHraca) {
            this.dalsiHrac();
        }
        for (int i = 0; i < kolkoKariet; i++) {
            Karta novaKarta = this.getBalikUnoKariet().dajKartuNaIndexe(0);
            if (this.getHracNaTahu().zoberKartu(novaKarta)) {
                this.getBalikUnoKariet().vymazKartuZBalika(novaKarta);
            }
        }
        if (!jeToPreDalsiehoHraca) {
            this.dalsiHrac();
        }
    }

    /**
     * Setter na nastavenie akým smerom majú ísť hráči
     *
     * @param poSmereHodinovychRuciciek - po smere alebo proti smeru
     */
    public void setPoSmereHodinovychRuciciek(boolean poSmereHodinovychRuciciek) {
        this.poSmereHodinovychRuciciek = poSmereHodinovychRuciciek;
    }

    /**
     * Vráti objekt typu hráč ktorý je na ťahu
     *
     * @return hracNaTahu
     */
    public Hrac getHracNaTahu() {
        return this.hracNaTahu;
    }

    /**
     * Vráti objekt typu ArrayList kde sú hráči
     *
     * @return hraci
     */
    public ArrayList<Hrac> getHraci() {
        return this.hraci;
    }

    /**
     * Vráti počet hráčov
     *
     * @return pocetHracov
     */
    public int getPocetHracov() {
        return this.pocetHracov;
    }

    /**
     * Vráti boolean či sa ide po smere(true) alebo proti smeru(false)
     *
     * @return poSmereHodinovychRuciciek
     */
    public boolean jePoSmereHodinovychRuciciek() {
        return this.poSmereHodinovychRuciciek;
    }

    /**
     * Vráti objekt typu Manager
     *
     * @return manazer
     */
    public Manager getManazer() {
        return this.manazer;
    }

    /**
     * Vráti objekt typu Text
     *
     * @return textKtoJeNaRade
     */
    public Text getTextKtoJeNaRade() {
        return this.textKtoJeNaRade;
    }

    /**
     * Metoda na pridanie hraca do hry
     *
     * @param hrac - hrac ktorý sa má pridať
     */

    private void pridajHraca(Hrac hrac) {
        var pocetHracovSize = this.getHraci().size();

        switch (pocetHracovSize) {
            case 0 -> {
                if (this.getPocetHracov() == 2) {
                    hrac.getMenoHraca().setX(600);
                }
                this.hraci.add(hrac);
            }
            case 1 -> {
                hrac.setPoziciaMenoHraca(this.getHraci().get(0).getMenoHraca().getX(), 750);
                this.hraci.add(hrac);
            }
            case 2 -> {
                hrac.setPoziciaMenoHraca(this.getHraci().get(pocetHracovSize - 1).getMenoHraca().getX() + 850, hrac.getMenoHraca().getY());
                this.hraci.add(hrac);
            }
            case 3 -> {
                hrac.setPoziciaMenoHraca(this.getHraci().get(pocetHracovSize - 2).getMenoHraca().getX() + 850, this.getHraci().get(1).getMenoHraca().getY());
                this.hraci.add(hrac);
            }
        }
    }


    /**
     * Metoda pomocou ktorej sa zacne hra
     */
    public void zacatHru() {
        int j = 1;
        for (int i = 0; i < this.getPocetHracov(); i++) {
            String menoHraca = JOptionPane.showInputDialog(null, "Zadaj svoje meno:");
            if (menoHraca == null || menoHraca.trim().isEmpty()) {
                menoHraca = "Hráč " + j;
                j++;
            }
            this.pridajHraca(new Hrac(menoHraca, this));
        }


        var pocetHracovSize = this.getHraci().size() - 1;
        Random random = new Random();
        this.hracNaTahu = this.getHraci().get(random.nextInt(pocetHracovSize));
        this.textKtoJeNaRade.changeText("Teraz je na rade " + this.getHracNaTahu().getMeno());

        this.getBalikUnoKariet().rozdajKarty(this.getHraci());
        this.skryKartyHracov(this.getHracNaTahu());

        //Ked rozdá karty tak vlozi do pouzitých jednu kartu z vrchu z balicka (ešte nepouzitych kariet)
        var indexPoslednejKartyVBalicku = this.getBalikUnoKariet().getBalicekKarietList().size() - 1;
        var kartaNaZaciatok = this.getBalikUnoKariet().getBalicekKarietList().get(indexPoslednejKartyVBalicku);

        if (kartaNaZaciatok instanceof KartaDivoka || kartaNaZaciatok instanceof KartaDivokaTahajStyri) {
            kartaNaZaciatok.vyberSiFarbu();
        } else if (kartaNaZaciatok instanceof KartaOtocit) {
            this.setPoSmereHodinovychRuciciek(false);
            this.dalsiHrac();
        } else if (kartaNaZaciatok instanceof KartaPreskocit) {
            this.dalsiHrac();
        } else if (kartaNaZaciatok instanceof KartaTahajDve) {
            this.dajHracoviKartyPodlaPoctu(2, false);
        }

        kartaNaZaciatok.zmenPoziciu((1920 / 2), 400);
        var kartaNaZaciatokX = kartaNaZaciatok.getVonkajsiaVrstva().getX();
        var kartaNaZaciatokY = kartaNaZaciatok.getVonkajsiaVrstva().getY();
        new KartaNormalna(kartaNaZaciatokX - 90, kartaNaZaciatokY, Color.ORANGE, Znak.KARTA_BALICEK);
        this.getTextKtoJeNaRade().setX(kartaNaZaciatokX - 120);
        this.getTextKtoJeNaRade().setY(kartaNaZaciatokY - 20);
        this.getTextKtoJeNaRade().makeVisible();

        Text textPomoc = new Text("Stlač klávesu\nF1 pre pomoc");
        textPomoc.setX(1700);
        textPomoc.setY(900);
        textPomoc.makeVisible();

        kartaNaZaciatok.vykresli();
        this.getBalikPouzitychUnoKariet().pridajKartuDoBalika(kartaNaZaciatok);
        this.getBalikUnoKariet().vymazKartuZBalika(kartaNaZaciatok);

    }

    /**
     * Pridá použitú kartu do ArrayListu triedy BalikPouzitychUnoKariet a na plátno
     *
     * @param pouzitaKarta - pouzita karta ktorá sa má vložiť
     */
    public void pridajPouzituKartu(Karta pouzitaKarta) {
        pouzitaKarta.zmenPoziciu((1920 / 2), 400);

        for (Karta karta : this.getBalikPouzitychUnoKariet().getBalicekKarietList()) {
            if (!this.getBalikPouzitychUnoKariet().jeBalicekPrazdny()) {
                karta.skrySa();
            }
        }

        this.getBalikPouzitychUnoKariet().pridajKartuDoBalika(pouzitaKarta);
        pouzitaKarta.vykresli();
    }

    /**
     * Metoda pomocou ktorej sa prejde na dalsieho hráča
     */
    public void dalsiHrac() {
        ArrayList<Hrac> predchadzajuciHraci = new ArrayList<>();
        predchadzajuciHraci.add(this.getHracNaTahu());

        for (Hrac hrac : predchadzajuciHraci) {
            if (hrac.getMojeKarty().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vyhral: " + hrac.getMeno() + " !", "Koniec hry", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
                predchadzajuciHraci.clear();
                return;
            }
        }

        int indexDalsiehoHraca;

        if (this.jePoSmereHodinovychRuciciek()) {
            // ak sa hraje po smere hodinových ručičiek, získa sa index ďalšieho hráča tak,
            // že sa ku indexu aktuálneho hráča pripočíta +1
            indexDalsiehoHraca = this.getHraci().indexOf(this.getHracNaTahu()) + 1;

            if (indexDalsiehoHraca >= this.getHraci().size()) {
                // ak je index väčší alebo rovný počtu hráčov, nastaví sa na prvého hráč
                indexDalsiehoHraca = 0;
            }
        } else {
            // ak sa hraje proti smeru hodinových ručičiek, získa sa index ďalšieho hráča tak,
            // že sa od indexu aktuálneho hráča odráta -1
            indexDalsiehoHraca = this.getHraci().indexOf(this.getHracNaTahu()) - 1;

            if (indexDalsiehoHraca < 0) {
                // ak je index menší ako 0, nastaví sa na posledného hráča
                indexDalsiehoHraca = this.getHraci().size() - 1;
            }
        }
        // nastaví sa hráč na ťahu na ďalšieho hráča
        this.hracNaTahu = this.getHraci().get(indexDalsiehoHraca);
        // skryjú sa karty všetkých hráčov okrem hráča na ťahu
        this.skryKartyHracov(this.hracNaTahu);
        this.getTextKtoJeNaRade().changeText("Teraz je na rade " + this.getHracNaTahu().getMeno());
    }

    /**
     * Otočí všetky karty hráčov okrem toho ktorý je na taho
     *
     * @param hracNaZobrazenie - hrac ktorý je na tahu
     */
    private void skryKartyHracov(Hrac hracNaZobrazenie) {
        for (Hrac hrac : this.getHraci()) {
            if (hrac != hracNaZobrazenie) {
                // skryjú sa karty hráčov, ktorí nie sú na rade
                hrac.skryKarty();
            } else {
                // zobrazia sa karty hráča na ťahu
                hrac.zobrazKarty();
            }
        }
    }

    /**
     * Metoda pomocou ktorej sa ovláda akutalny hráč na ťahu cez Manažéra, ak chce použiť niektorú zo svojich kariet
     */
    public void pouziKartu() {
        boolean vysledok = false;
        while (!vysledok) {
            String cisloKarty = JOptionPane.showInputDialog(null, "Zadaj cislo od 1 po " + this.hracNaTahu.getMojeKarty().size() + "\nZadaj ktoru kartu chces pouzit:");
            try {
                int karta = Integer.parseInt(cisloKarty);
                this.getHracNaTahu().pouziKartu(this.getHracNaTahu().getMojeKarty().get((karta - 1)));
                vysledok = true;
            } catch (IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "Zadal si číslo mänšie ako 1 alebo väčšie ako " + this.hracNaTahu.getMojeKarty().size());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Nezadal si cislo, musis zadat cislo od 1 po " + this.hracNaTahu.getMojeKarty().size());
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Nemôžeš zadať prázdny vstup!");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Zadal si zly vstup, musis zadat cislo od 1 po " + this.hracNaTahu.getMojeKarty().size());
            }
        }
    }

    /**
     * Metoda pomocou ktorej sa ovláda akutalny hráč na ťahu cez Manažéra, ak si chce potiahnuť kartu
     */
    public void potiahniKartu() {
        this.getHracNaTahu().getHra().dajHracoviKartyPodlaPoctu(1, false);
    }

    /**
     * Metoda pomocou ktorej hráč môže zistit ako sa má hra ovládať a ako fungujú jednotlivé karty
     */
    public void pomocka() {
        JOptionPane.showMessageDialog(null, """
                Ovládanie:
                Klávesa Q >> Použitie karty
                Klávesa W >> potiahnutie karty
                Klávesa Escape >> ukončenie hry

                Karty:
                KartaDivoka >> výber farby, možno položiť na všetky karty
                KartaDivokaTahajStyri >> výber farby, pridanie 4 kariet ďalšiemu hráčovi a následne preskočenie na ďalšieho hráča, možno položiť na všetky karty
                KartaNormalna  >> možno položiť na kartu ktorá má buď rovnaký znak alebo farbu
                KartaOtocit >> možno položiť na kartu ktorá má rovnakú farbu, otočí sa smer ktorým je ďalší hráč na rade
                KartaPreskocit >> možno položiť na kartu ktorá má rovnakú farbu, hráč ktorý je na rade nebude mať možnosť ťahu
                KartaTahajDve >> možno položiť na kartu ktorá má rovnakú farbu, ďalší hráč si ťahá 2 karty a stráca možnosť ťahu
                """);
    }

    /**
     * Metoda pomocou ktorej môže hráč ukončiť program stlačením tlačidla Esc
     */
    public void exit() {
        System.exit(0);
    }
}