package sk.uniza.fri.karty;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.shapesge.Rectangle;
import javax.swing.JOptionPane;
import java.awt.Color;

public class KartaDivoka extends Karta {

    public KartaDivoka(int x, int y) {
        super(x, y, Color.gray, Znak.KARTA_DIVOKA);
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
        //Tu je polymorfizmus
        this.vyberSiFarbu();
        return true;
    }

    @Override
    public void zmenPoziciu(int x, int y) {
        super.zmenPoziciu(x, y);
    }
}
