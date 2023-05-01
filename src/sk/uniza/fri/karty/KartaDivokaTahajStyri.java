package sk.uniza.fri.karty;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.shapesge.Rectangle;

import javax.swing.JOptionPane;
import java.awt.Color;

public class KartaDivokaTahajStyri extends Karta {
    public KartaDivokaTahajStyri(int x, int y) {
        super(x, y, Color.gray, Znak.KARTA_TAHAJ_STYRI);
    }

    @Override
    public Rectangle getVonkajsiaVrstva() {
        return super.getVonkajsiaVrstva();
    }

    @Override
    public Color getFarba() {
        return super.getFarba();
    }

    @Override
    public Znak getZnak() {
        return super.getZnak();
    }

    @Override
    public void vykresli() {
        super.vykresli();
    }

    public boolean vyberSiFarbu() {
        //Polymorfizmus Interface
        String[] moznosti = {"Cervena", "Zelena", "Modra", "Zlta"};
        int vysledok = JOptionPane.showOptionDialog(null, "Vyber farbu karty", "Farba karty", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, moznosti, moznosti[0]);
        switch (vysledok) {
            case 0 -> {
                this.getVnutornaVrstva().changeColor("red");
                super.setFarba(Color.red);
            }
            case 1 -> {
                this.getVnutornaVrstva().changeColor("green");
                super.setFarba(Color.green);
            }
            case 2 -> {
                this.getVnutornaVrstva().changeColor("blue");
                super.setFarba(Color.blue);
            }
            case 3 -> {
                this.getVnutornaVrstva().changeColor("yellow");
                super.setFarba(Color.yellow);
            }
        }
        return true;
    }

    @Override
    public boolean vykonajAkciu(Hrac hrac) {
        //Ked je tato karta pouzita, tak sa preskoci na dalsieho hraca, ktorý si musí tahať 4 karty a
        //tým pádom stratí možnosť byť na rade a ide na dalsieho hraca
        this.vyberSiFarbu();
        hrac.getHra().dajHracoviKartyPodlaPoctu(4);
        return true;
    }

    @Override
    public void zmenPoziciu(int x, int y) {
        super.zmenPoziciu(x, y);
    }
}
