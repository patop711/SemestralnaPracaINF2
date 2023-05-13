package sk.uniza.fri.balikyKariet;

import sk.uniza.fri.hlavnyProgram.Hra;
import sk.uniza.fri.karty.Karta;

import java.util.ArrayList;

/** Abstraktná trieda balík
 *  @author Patrik Pavlík
 *  @version 1.23.15
 */
public abstract class Balik {
    private final ArrayList<Karta> balicekKarietList;
    private final Hra hra;

    /**
     * Parametrický konštruktor abstraktnej triedy
     * @param hra - hra s ktorou bude spolupracovať
     */
    public Balik(Hra hra) {
        this.hra = hra;
        this.balicekKarietList = new ArrayList<>();
    }

    /**
     * Vráti počet kariet v balíčku
     * @return balicekKarietList.size
     */
    public int pocetKarietVBaliku() {
        return this.balicekKarietList.size();
    }

    /**
     * Vráti objekt typu Hra
     * @return hra
     */
    public Hra getHra() {
        return this.hra;
    }

    /**
     * Vráti objekt typu ArrayList
     * @return balicekKarietList
     */
    public ArrayList<Karta> getBalicekKarietList() {
        return this.balicekKarietList;
    }

    /**
     * Vráti boolean či je balicek prazdny
     * @return getBalicekKarietList.isEmpty
     */
    public boolean jeBalicekPrazdny() {
        return this.getBalicekKarietList().isEmpty();
    }

    /**
     * Pridá kartu do balíka
     * @param karta - karta ktorá sa pridá
     */
    public void pridajKartuDoBalika(Karta karta) {
        this.getBalicekKarietList().add(karta);
    }
    /**
     *Odstráni kartu z balíka
     * @param karta - karta ktorá sa odstani
     */
    public void vymazKartuZBalika(Karta karta) {
        this.getBalicekKarietList().remove(karta);
    }

    /**
     * Vráti objekt typu Karta z pola kariet
     * @param index - pozicia karty na indexe
     * @return getBalicekKarietList.get
     */
    public Karta dajKartuNaIndexe(int index) {
        if (index >= 0 && (index <= this.getBalicekKarietList().size() - 1)) {
            return this.getBalicekKarietList().get(index);
        }
        return null;
    }
}
