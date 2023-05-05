package sk.uniza.fri.hlavnyProgram;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.karty.Karta;
import sk.uniza.fri.karty.KartaDivoka;
import sk.uniza.fri.karty.KartaNormalna;
import sk.uniza.fri.karty.KartaOtocit;
import sk.uniza.fri.karty.KartaPreskocit;
import sk.uniza.fri.karty.KartaTahajDve;
import sk.uniza.fri.karty.KartaDivokaTahajStyri;
import sk.uniza.fri.karty.Znak;

import javax.swing.JOptionPane;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Hra {
    private final ArrayList<Karta> balicekKariet;
    private final ArrayList<Karta> balicekPouzitychKariet;
    private final ArrayList<Hrac> hraci;
    private final int pocetHracov;
    private boolean padloUno;
    private boolean poSmereHodinovychRuciciek;
    private Hrac hracNaTahu;

    /**
     * Parametricky konstruktor kde zadavame limit hracov ktorých máme nastaviť
     * Spustí sa ArrayList pre: balicekKariet, balicekPouzitychKariet a hracov
     * Atribút padloUno sa nastaví na false
     *
     * @param pocetHracov - pocet hracov ktorý majú byť v hre
     */
    public Hra(int pocetHracov) {
        this.poSmereHodinovychRuciciek = true;
        this.balicekKariet = new ArrayList<>();
        this.balicekPouzitychKariet = new ArrayList<>();
        this.hraci = new ArrayList<>();
        //Minimálne musia hrať hru dvaja hráči :D
        if (pocetHracov >= 2 && pocetHracov <= 4) {
            this.pocetHracov = pocetHracov;
        } else {
            this.pocetHracov = 2;
        }

        this.padloUno = false;

    }

    /**
     * Pridá hráčovi karty
     *
     * @param kolkoKariet - kolko kariet má dostať hrac
     */
    public void dajHracoviKartyPodlaPoctu(int kolkoKariet, boolean jeToPreDalsiehoHraca) {
        //Ako vyriesim to ked si hrac chce zobrat iba kartu ale zaroven tuto metodu pouzivaju karty Tahaj 4 a tahaj 2
        if (jeToPreDalsiehoHraca) {
            this.dalsiHrac();
        }
        for (int i = 0; i < kolkoKariet; i++) {
            Karta novaKarta = this.getBalicekKariet().get(0);
            if (this.getHracNaTahu().zoberKartu(novaKarta)) {
                this.getBalicekKariet().remove(novaKarta);
                System.out.println(novaKarta.toString());
            }
        }
        if (!jeToPreDalsiehoHraca) {
            this.dalsiHrac();
        }
    }

    public void setPoSmereHodinovychRuciciek(boolean poSmereHodinovychRuciciek) {
        this.poSmereHodinovychRuciciek = poSmereHodinovychRuciciek;
    }

    public boolean getPoSmereHodinovychRuciciek() {
        return this.poSmereHodinovychRuciciek;
    }

    public Hrac getHracNaTahu() {
        return this.hracNaTahu;
    }

    /**
     * Vráti objekt typu ArrayList
     *
     * @return balicekKariet
     */
    public ArrayList<Karta> getBalicekKariet() {
        return this.balicekKariet;
    }

    /**
     * Vráti objekt typu ArrayList
     *
     * @return balicekPouzitychKariet
     */
    public ArrayList<Karta> getBalicekPouzitychKariet() {
        return this.balicekPouzitychKariet;
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

    /**
     * Metoda na vratenie ci padlo UNO od niektorého z hráčov
     *
     * @return padloUno
     */
    public boolean padloUno() {
        return this.padloUno;
    }

    /**
     * Metoda na pridanie hraca
     *
     * @param hrac - hrac ktorý sa má pridať
     * @return true alebo false - ak sa splnila podmienka
     */
    public boolean pridajHraca(Hrac hrac) {
        if (this.getHraci().size() >= this.getPocetHracov()) {
            System.out.println("Viac hracov nejde pridať");
            return false;
        }
        switch (this.getHraci().size()) {
            case 0 -> {
                this.hraci.add(hrac);
                this.hracNaTahu = this.hraci.get(0);
                return true;
            }
            case 1 -> {
                if (this.getHraci().get(0).getMenoHraca().getX() == hrac.getMenoHraca().getX() &&
                        this.getHraci().get(0).getMenoHraca().getY() == hrac.getMenoHraca().getY()) {
                    hrac.getMenoHraca().setY(800);
                }
                this.hraci.add(hrac);
                return true;
            }
            case 2 -> {
                hrac.setPoziciaMenoHraca(this.getHraci().get(this.getHraci().size() - 1).getMenoHraca().getX() + 850, hrac.getMenoHraca().getY());
                this.hraci.add(hrac);
                return true;
            }
            case 3 -> {
                hrac.setPoziciaMenoHraca(this.getHraci().get(this.getHraci().size() - 2).getMenoHraca().getX() + 850, this.getHraci().get(1).getMenoHraca().getY());
                this.hraci.add(hrac);
                return true;
            }
            default -> {
                System.out.println("Nie je možné pridať hráča");
                return false;
            }
        }
    }

    /**
     * Metoda na vytvorenie všetkých 108 kariet UNO
     */
    public void vytvorKarty() {
        int predvoleneX = 40;
        int predvoleneY = 400;
        Color[] poleFarieb = {Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN};
        //NormalneKarty
        for (Color farba : poleFarieb) {
            this.getBalicekKariet().add(new KartaNormalna(predvoleneX, predvoleneY, farba, Znak.KARTA_NULA));
            //System.out.println("Karta nula, farba: " + farba.toString() + " znak: " + Znak.KARTA_NULA);
            System.out.println();
            for (int i = 0; i < 2; i++) {
                for (int j = 1; j < 10; j++) {
                    this.getBalicekKariet().add(new KartaNormalna(predvoleneX, predvoleneY, farba, Znak.values()[j]));
                    //System.out.println("Farba: " + farba.toString() + " znak: " + Znak.values()[j]);
                }
                //Karty TahajDve
                this.getBalicekKariet().add(new KartaTahajDve(predvoleneX, predvoleneY, farba));
                //Karty Otocit
                this.getBalicekKariet().add(new KartaOtocit(predvoleneX, predvoleneY, farba));
                //Karty Preskocit
                this.getBalicekKariet().add(new KartaPreskocit(predvoleneX, predvoleneY, farba));

            }

        }
        //KartyTahajStyri a KartyDivoke
        for (int i = 0; i < 4; i++) {
            this.getBalicekKariet().add(new KartaDivokaTahajStyri(predvoleneX, predvoleneY));
            this.getBalicekKariet().add(new KartaDivoka(predvoleneX, predvoleneY));
        }
        System.out.println(this.balicekKariet.size());
    }

    /**
     * Metóda na pomiesanie kariet v ArrayListe
     */
    public void pomiesajKarty() {
        Collections.shuffle(this.getBalicekKariet());

    }

    /**
     * Metoda pomocou ktorej sa zacne hra
     */
    public void zacatHru() {
        this.vytvorKarty();
        this.pomiesajKarty();

        int j = 1;
        for (int i = 0; i < this.getPocetHracov(); i++) {
            String menoHraca = JOptionPane.showInputDialog(null, "Zadaj svoje meno:");
            if (menoHraca == null || menoHraca.trim().isEmpty()) {
                menoHraca = "Hráč " + j;
                j++;
            }
            this.pridajHraca(new Hrac(menoHraca, this));
        }

        this.rozdajKarty();
        this.skryKartyHracov(this.getHracNaTahu());
        //TODO Ked kliknem na kartu balicek tak tomu hracovi co je na rade sa pridá karta - !!!Zatiaľ nevyriešené!!!😒
        new KartaNormalna(40, 400, Color.ORANGE, Znak.KARTA_BALICEK);

        //Ked rozdá karty tak vlozi do pouzitých jednu kartu z balicka (ešte nepouzitych kariet)
        var kartaNaZaciatok = this.getBalicekKariet().get(this.getBalicekKariet().size() - 1);
        kartaNaZaciatok.zmenPoziciu((1920 / 2), 400);
        kartaNaZaciatok.vykresli();
        this.getBalicekPouzitychKariet().add(kartaNaZaciatok);
        this.getBalicekKariet().remove(kartaNaZaciatok);

    }

    /**
     * Metóda ktorá na začiatku hry rozdá hráčom karty (7(čo je vlastne maximum) kariet pre každeho)
     */
    public void rozdajKarty() {
        int index = 0;
        for (Hrac hrac : this.hraci) {
            for (int i = 0; i < 7; i++) {
                Karta vlozenaKarta = this.getBalicekKariet().get(index);
                hrac.zoberKartu(vlozenaKarta);
                this.getBalicekKariet().remove(vlozenaKarta);
                index++;
            }
        }
    }

    public void pridajPouzituKartu(Karta pouzitaKarta) {
        //TODO dat pouzitu kartu dakde do stredu platna
        // -Update: 29.4: Asi to je v poradku este musim znova neskôr pozrieť
        pouzitaKarta.zmenPoziciu((1920 / 2), 400);

        for (Karta karta : this.getBalicekPouzitychKariet()) {
            if (!this.getBalicekPouzitychKariet().isEmpty()) {
                karta.skrySa();
            }
        }


        this.getBalicekPouzitychKariet().add(pouzitaKarta);
        pouzitaKarta.vykresli();

        //Ked uz bude balicek z ktorých vyberám karty prázdny tak sa vyplní kartami z balicku pouzitych kariet
        if (this.getBalicekKariet().isEmpty()) {
            this.getBalicekKariet().addAll(this.getBalicekPouzitychKariet());
            this.pomiesajKarty();
        }
    }

    public void dalsiHrac() {
        int indexDalsiehoHraca;

        if (this.poSmereHodinovychRuciciek) {
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
        System.out.println("Teraz je na rade " + this.getHracNaTahu().getMeno());
    }

    public void skryKartyHracov(Hrac hracNaZobrazenie) {
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
     * Slučka hry ktorá sa vykonáva pokial this.padloUno nie je true
     */
    public void hlavnyLoop(String s, Scanner scanner) {
        switch (s) {
            case "w": //dalsi hrac
                this.dalsiHrac();
                break;
            case "p": //vyber karty
                boolean vyber = false;
                while (!vyber) {
                    System.out.println("Zadaj ktoru kartu chces pouzit: ");
                    int karta = scanner.nextInt();
                    this.getHracNaTahu().pouziKartu(this.getHracNaTahu().getMojeKarty().get((karta - 1)));
                    vyber = true;
                }
                break;
            case "k":
                this.hracNaTahu.potiahniSiKartu();
                break;
            case "e": //koniec
                System.out.println("Maj sa krásne!");
                System.exit(0);

        }

    }

    /**
     * Metoda na ukoncenie hry
     */
    public void ukonciHru() {
        this.padloUno = true;

    }

}
