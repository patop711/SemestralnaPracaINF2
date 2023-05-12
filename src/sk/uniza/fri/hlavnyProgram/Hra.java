package sk.uniza.fri.hlavnyProgram;

import sk.uniza.fri.balikyKariet.BalikPouzitychUnoKariet;
import sk.uniza.fri.balikyKariet.BalikUnoKariet;
import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.karty.Karta;
import sk.uniza.fri.karty.KartaDivoka;
import sk.uniza.fri.karty.KartaDivokaTahajStyri;
import sk.uniza.fri.karty.KartaNormalna;
import sk.uniza.fri.karty.Znak;
import sk.uniza.fri.shapesge.Manager;
import sk.uniza.fri.shapesge.Text;

import javax.swing.JOptionPane;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Hra {
    private final ArrayList<Hrac> hraci;
    private final int pocetHracov;
    private boolean poSmereHodinovychRuciciek;
    private Hrac hracNaTahu;
    private final Manager manazer;
    private final Text textKtoJeNaRade;
    private final BalikUnoKariet balikUnoKariet;
    private final BalikPouzitychUnoKariet balikPouzitychUnoKariet;


    /**
     * Parametricky konstruktor kde zadavame limit hracov ktorých máme nastaviť
     * Spustí sa ArrayList pre: balicekKariet, balicekPouzitychKariet a hracov
     * Atribút getPadloUno sa nastaví na false
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
        this.balikUnoKariet = new BalikUnoKariet(this);
        this.balikPouzitychUnoKariet = new BalikPouzitychUnoKariet(this);
    }

    /**
     * Vráti objekt typu BalikPouzitychUnoKariet
     *
     * @return balikPouzitychUnoKariet
     */
    public BalikPouzitychUnoKariet getBalikPouzitychUnoKariet() {
        return this.balikPouzitychUnoKariet;
    }

    /**
     * Vráti objekt typu BalikUnoKariet
     *
     * @return balikUnoKariet
     */
    public BalikUnoKariet getBalikUnoKariet() {
        return this.balikUnoKariet;
    }

    /**
     * Pridá hráčovi karty
     *
     * @param kolkoKariet - kolko kariet má dostať hrac
     */
    public void dajHracoviKartyPodlaPoctu(int kolkoKariet, boolean jeToPreDalsiehoHraca) {
        if (this.getBalikUnoKariet().jeBalicekPrazdny()) {
            for (int i = this.getBalikPouzitychUnoKariet().pocetKarietVBaliku() - 2; i >= 0 ; i--) {
                var kartaNaPridanie = this.getBalikPouzitychUnoKariet().getBalicekKarietList().get(i);
                this.getBalikUnoKariet().pridajKartuDoBalika(kartaNaPridanie);
                this.getBalikPouzitychUnoKariet().getBalicekKarietList().remove(i);
            }
            this.getBalikUnoKariet().dajHracoviKartyPodlaPoctu(kolkoKariet, jeToPreDalsiehoHraca);
        } else {
            this.getBalikUnoKariet().dajHracoviKartyPodlaPoctu(kolkoKariet, jeToPreDalsiehoHraca);
        }
    }

    public void setPoSmereHodinovychRuciciek(boolean poSmereHodinovychRuciciek) {
        this.poSmereHodinovychRuciciek = poSmereHodinovychRuciciek;
    }

    public Hrac getHracNaTahu() {
        return this.hracNaTahu;
    }

    /**
     * Vráti objekt typu ArrayList
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

    public boolean jePoSmereHodinovychRuciciek() {
        return this.poSmereHodinovychRuciciek;
    }

    public Manager getManazer() {
        return this.manazer;
    }

    public Text getTextKtoJeNaRade() {
        return this.textKtoJeNaRade;
    }

    /**
     * Metoda na pridanie hraca
     *
     * @param hrac - hrac ktorý sa má pridať
     */

    public void pridajHraca(Hrac hrac) {
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
        new KartaNormalna(40, 400, Color.ORANGE, Znak.KARTA_BALICEK);

        //Ked rozdá karty tak vlozi do pouzitých jednu kartu z vrchu z balicka (ešte nepouzitych kariet)
        var indexPoslednejKartyVBalicku = this.getBalikUnoKariet().getBalicekKarietList().size() - 1;
        var kartaNaZaciatok = this.getBalikUnoKariet().getBalicekKarietList().get(indexPoslednejKartyVBalicku);
        if (kartaNaZaciatok instanceof KartaDivoka || kartaNaZaciatok instanceof KartaDivokaTahajStyri) {
            kartaNaZaciatok.vyberSiFarbu();
        }
        kartaNaZaciatok.zmenPoziciu((1920 / 2), 400);
        var ktoJeNaRadeX = kartaNaZaciatok.getVonkajsiaVrstva().getX() - 20;
        var ktoJeNaRadeY = kartaNaZaciatok.getVonkajsiaVrstva().getY() - 20;
        this.getTextKtoJeNaRade().setX(ktoJeNaRadeX);
        this.getTextKtoJeNaRade().setY(ktoJeNaRadeY);
        this.getTextKtoJeNaRade().makeVisible();

        kartaNaZaciatok.vykresli();
        this.getBalikPouzitychUnoKariet().pridajKartuDoBalika(kartaNaZaciatok);
        this.getBalikUnoKariet().vymazKartuZBalika(kartaNaZaciatok);

    }

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
//        System.out.println("Teraz je na rade " + this.getHracNaTahu().getMeno());
    }

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

    public void pouziKartu() {
        boolean vysledok = false;
        while (!vysledok) {
            String cisloKarty = JOptionPane.showInputDialog(null, "Zadaj cislo od 1 po " + this.hracNaTahu.getMojeKarty().size() + "\nZadaj ktoru kartu chces pouzit:");
            try {
                int karta = Integer.parseInt(cisloKarty);
                this.getHracNaTahu().pouziKartu(this.getHracNaTahu().getMojeKarty().get((karta - 1)));
                vysledok = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Nezadal si cislo, musis zadat cislo od 1 po " + this.hracNaTahu.getMojeKarty().size());
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Nemôžeš zadať prázdny vstup!");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Zadal si zly vstup, musis zadat cislo od 1 po " + this.hracNaTahu.getMojeKarty().size());
            }
        }
    }

    public void potiahniKartu() {
        this.hracNaTahu.potiahniSiKartu();
    }

    public void pomocka() {
        JOptionPane.showMessageDialog(null, "Ovládanie:\nKlávesa Q -> Použitie karty\nKlávesa W -> potiahnutie karty\nKlávesa E -> ukončenie hry");
    }
    public void exit() {
        System.exit(0);
    }
}

