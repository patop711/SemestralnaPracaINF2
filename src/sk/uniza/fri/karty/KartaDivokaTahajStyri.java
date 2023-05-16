package sk.uniza.fri.karty;

import sk.uniza.fri.hrac.Hrac;
import java.awt.Color;

/** Trieda KartaDivokaTahajStyri(potomok triedy Karta)
 *  @author Patrik Pavlík
 *  @version 1.23.15
 */
public class KartaDivokaTahajStyri extends Karta {
    public KartaDivokaTahajStyri(int x, int y) {
        super(x, y, Color.gray, Znak.KARTA_TAHAJ_STYRI);
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
    public void vyberSiFarbu() {
        super.vyberSiFarbu();
    }

    @Override
    public void zmenPoziciu(int x, int y) {
        super.zmenPoziciu(x, y);
    }

    /**
     * KartaDivokaTahajStyri – výber farby, pridanie 4 kariet ďalšiemu hráčovi a následne preskočenie na ďalšieho hráča, možno položiť na všetky karty
     * @param hrac - ktorý hráč ju ma použiť
     * @return true
     */
    @Override
    public boolean vykonajAkciu(Hrac hrac) {
        //Ked je tato karta pouzita, tak sa preskoci na dalsieho hraca, ktorý si musí tahať 4 karty a
        //tým pádom stratí možnosť byť na rade a ide na dalsieho hraca
        this.vyberSiFarbu();
        hrac.getHra().dajHracoviKartyPodlaPoctu(4, true);
        return true;
    }
}
