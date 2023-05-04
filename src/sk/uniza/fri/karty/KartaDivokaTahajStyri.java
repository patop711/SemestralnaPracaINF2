package sk.uniza.fri.karty;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.shapesge.Rectangle;

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

    @Override
    public boolean vyberSiFarbu() {
        super.vyberSiFarbu();
        return true;
    }

    @Override
    public boolean vykonajAkciu(Hrac hrac) {
        //Ked je tato karta pouzita, tak sa preskoci na dalsieho hraca, ktorý si musí tahať 4 karty a
        //tým pádom stratí možnosť byť na rade a ide na dalsieho hraca
        this.vyberSiFarbu();
        hrac.getHra().dajHracoviKartyPodlaPoctu(4, true);
        return true;
    }

    @Override
    public void zmenPoziciu(int x, int y) {
        super.zmenPoziciu(x, y);
    }
}
