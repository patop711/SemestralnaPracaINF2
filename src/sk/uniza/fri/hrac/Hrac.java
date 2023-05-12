package sk.uniza.fri.hrac;

import sk.uniza.fri.hlavnyProgram.Hra;
import sk.uniza.fri.karty.Karta;
import sk.uniza.fri.shapesge.Text;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Hrac {
    private final String meno;
    private final ArrayList<Karta> mojeKarty;
    private final Text menoHraca;
    private final Hra hra;

    /**
     * Parametricky konstruktor, kde zadáme meno hráča, ktoré sa potom vytvorí v triede Text a vytvorí sa ArrayList
     * s hráčovími kartami
     *
     * @param meno - meno hráča
     */
    public Hrac(String meno, Hra hra) {
        this.hra = hra;
        this.meno = meno;
        this.menoHraca = new Text(this.getMeno());
        this.getMenoHraca().makeVisible();
        this.mojeKarty = new ArrayList<>();

    }

    /**
     * Vráti objekt typu Hra
     * @return hra
     */
    public Hra getHra() {
        return this.hra;
    }

    /**
     * Vráti objekt typu Text
     *
     * @return menoHraca - Vráti návratovú hodnotu typu Text
     */
    public Text getMenoHraca() {
        return this.menoHraca;
    }

    /**
     * Nastavi poziciu mena hráča
     *
     * @param x - pozícia X textu
     * @param y - pozícia Y textu
     */
    public void setPoziciaMenoHraca(int x, int y) {
        this.getMenoHraca().setX(x);
        this.getMenoHraca().setY(y);
        this.getMenoHraca().makeVisible();

        for (Karta karta : this.getMojeKarty()) {
            karta.posunZvisle(y);
        }
    }

    /**
     * Otočí karty smerom na rub
     */
    public void skryKarty() {
        for (Karta karta : this.getMojeKarty()) {
            karta.rub();
        }
    }

    /**
     * Otočí smerom karty na farbu a znak
     */
    public void zobrazKarty() {
        for (Karta karta : this.getMojeKarty()) {
            karta.vykresli();
        }
    }
    /**
     * Vráti String mena hráča
     *
     * @return meno - vráti meno hráča
     */
    public String getMeno() {
        return this.meno;
    }
    /**
     * Vráti objekt typu ArrayList
     *
     * @return mojeKarty
     */
    public ArrayList<Karta> getMojeKarty() {
        return this.mojeKarty;
    }

    /**
     * Aktualizuje pozície kariet po tom čo sa pridá alebo použije karta
     */
    private void aktualizujPozicieKariet() {
        List<Karta> karty = this.getMojeKarty();
        int startX = this.getMenoHraca().getX() + 110;
        int startY = this.getMenoHraca().getY();
        int i = 0;
        for (Karta kartaVRuke : karty) {
            int x = startX + (i * 88);
            int y = startY;
            if (i >= 7) {
                x -= 7 * 88;
                y += 125;
            }
            kartaVRuke.zmenPoziciu(x, y);
            i++;
        }
    }
    /**
     * Metóda pomocou ktorej si hráč potiahne kartu z balíčka kariet
     */
    public void potiahniSiKartu() {
        this.getHra().dajHracoviKartyPodlaPoctu(1, false);
    }

    /**
     * Metóda ktorá použije kartu z hráčovej ruky a následne ju vymaže z ArrayListu a zoradí karty
     *
     * @param karta - karta ktorú použijem z mojej ruky(ArrayListu)
     */
    public void pouziKartu(Karta karta) {
        List<Karta> karty = this.getMojeKarty();
        if (karty.contains(karta)) {
            if (karta.vykonajAkciu(this)) {
                int indexKartyNaOdstranenie = this.getMojeKarty().indexOf(karta);
                karty.remove(indexKartyNaOdstranenie);
                this.getHra().pridajPouzituKartu(karta);
                this.aktualizujPozicieKariet();
                this.getHra().dalsiHrac();
            } else {
                JOptionPane.showMessageDialog(null, "Túto kartu nie je možné položíť\nProsím vyber si inú kartu", "Výsledok použitia karty", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Metoda ktorá zoberie kartu z balicka kariet (ArrayList) z triedy hra a pridá ich hráčovi do ruky pričom sa usporiadajú aby neboli všetky na tej istej
     * pozícii. Pokial ideme dat prvu kartu tak sa da vedla mena hráča
     *
     * @param karta - karta ktorú ideme vložit do hráčovej ruky(ArrayList)
     */
    public boolean zoberKartu(Karta karta) {
        boolean vysledok = false;
        var pocetKariet = this.getMojeKarty().size();
        if (this.getMojeKarty().isEmpty()) {
            Karta prvaKarta = karta;
            prvaKarta.zmenPoziciu(this.getMenoHraca().getX() + 110, this.getMenoHraca().getY());
            this.getMojeKarty().add(prvaKarta);
            vysledok = true;
        } else {
            Karta novaKarta = karta;

            if (pocetKariet < 14) {
                this.getMojeKarty().add(novaKarta);
                vysledok = true;
            } else {
                JOptionPane.showMessageDialog(null, "Hráč ma maximum kariet!\nMusí už použiť niektoré z existujúcich.", "Nie je možné si zobrať kartu", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (vysledok) {
            this.aktualizujPozicieKariet();
        }

        return vysledok;
    }
}
