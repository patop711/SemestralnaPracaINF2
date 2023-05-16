package sk.uniza.fri.karty;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.shapesge.Ellipse;
import sk.uniza.fri.shapesge.Rectangle;
import sk.uniza.fri.shapesge.Text;

import javax.swing.JOptionPane;
import java.awt.Color;

/** Abstraktná trieda Karta
 *  @author Patrik Pavlík
 *  @version 1.23.15
 */
public abstract class Karta {
    private Color farba;
    private final Znak znak;
    private final Rectangle vonkajsiaVrstva;
    private final Rectangle vnutornaVrstva;
    private final Ellipse elipsa;
    private final Ellipse elipsaRub;
    private final Text textKarty;
    private final Text textRub;

    /**
     * Abstraktna trieda Karta, z ktorej si vytvoríme dalsie karty
     * Parametricky konstruktor ktorý vytvorí kartu s danou pozíciou, farbou a znakom
     *
     * @param x     - pozicia X karty
     * @param y     - pozicia Y karty
     * @param farba - farba karty
     * @param znak  - znak karty
     */
    public Karta(int x, int y, Color farba, Znak znak) {
        this.farba = farba;
        this.znak = znak;
        this.vonkajsiaVrstva = new Rectangle(80, 120, x, y, Color.black);
        this.vnutornaVrstva = new Rectangle(this.getVonkajsiaVrstva().getWidth() - 10, this.getVonkajsiaVrstva().getHeight() - 10, this.getVonkajsiaVrstva().getX() + 5, this.getVonkajsiaVrstva().getY() + 5, this.getFarba());
        this.elipsa = new Ellipse(60, 60, this.getVnutornaVrstva().getX() + 5, this.getVnutornaVrstva().getY() + 20);
        this.elipsaRub = new Ellipse(this.getElipsa().getDiameterX(), this.getElipsa().getDiameterY(), this.getElipsa().getPoziciaX(), this.getElipsa().getPoziciaY());
        this.textKarty = new Text(this.getZnak().getText(), this.getElipsa().getPoziciaX() + 24, this.getElipsa().getPoziciaY() + 35, 20);
        this.textRub = new Text("UNO", this.getTextKarty().getX() - 15, this.getTextKarty().getY(), this.getTextKarty().getSize());
        this.vykresli();
    }
    //Gettery

    /**
     * Vráti objekt typu Rectangle
     *
     * @return vonkajsiaVrstva
     */
    public Rectangle getVonkajsiaVrstva() {
        return this.vonkajsiaVrstva;
    }

    /**
     * Vráti objekt typu Rectangle
     *
     * @return vnutornaVrstva
     */
    public Rectangle getVnutornaVrstva() {
        return this.vnutornaVrstva;
    }

    /**
     * Vráti objekt typu Ellipse
     *
     * @return elipsa
     */
    public Ellipse getElipsa() {
        return this.elipsa;
    }

    /**
     * Vráti navratový typ typu Ellipse
     *
     * @return elipsaRub- Vráti navratový typ typu Ellipse
     */
    public Ellipse getElipsaRub() {
        return this.elipsaRub;
    }
    /**
     * Vráti objekt typu Color
     *
     * @return farba - Vráti navratový typ typu Text
     */
    public Color getFarba() {
        return this.farba;
    }

    /**
     * Vráti objekt typu Znak
     *
     * @return znak - Vráti navratový typ typu Znak
     */
    public Znak getZnak() {
        return this.znak;
    }

    /**
     * Vráti objekt typu Text
     *
     * @return textKarty - Vráti navratový typ typu Text
     */
    public Text getTextKarty() {
        return this.textKarty;
    }

    /**
     * Vráti objekt typu Text
     *
     * @return textRub - Vráti navratový typ typu Text
     */
    public Text getTextRub() {
        return this.textRub;
    }

    //Settery

    /**
     * Metóda na zmenu pozície karty
     *
     * @param x - zmeni poziciu X
     * @param y - zmení pozíciu Y
     */
    public void zmenPoziciu(int x, int y) {
        this.getVonkajsiaVrstva().setX(x);
        this.getVonkajsiaVrstva().setY(y);

        this.getVnutornaVrstva().setX(this.getVonkajsiaVrstva().getX() + 5);
        this.getVnutornaVrstva().setY(this.getVonkajsiaVrstva().getY() + 5);

        this.getElipsa().setX(this.getVnutornaVrstva().getX() + 5);
        this.getElipsa().setY(this.getVnutornaVrstva().getY() + 20);

        this.getTextKarty().setX(this.getElipsa().getPoziciaX() + 24);
        this.getTextKarty().setY(this.getElipsa().getPoziciaY() + 35);

        this.getElipsaRub().setX(this.getElipsa().getPoziciaX());
        this.getElipsaRub().setY(this.getElipsa().getPoziciaY());

        this.getTextRub().setX(this.getTextKarty().getX() - 15);
        this.getTextRub().setY(this.getTextKarty().getY());
    }

    /**
     * Metóda na posunutie karty zvisle
     *
     * @param kolkoY - o kolko chceme kartu posunut z jej pôvodnej pozície
     */
    public void posunZvisle(int kolkoY) {
        this.getVonkajsiaVrstva().moveVertical(kolkoY);
        this.getVnutornaVrstva().moveVertical(kolkoY);
        this.getElipsa().moveVertical(kolkoY);
        this.getTextKarty().moveVertical(kolkoY);
        this.getElipsaRub().moveVertical(kolkoY);
        this.getTextRub().moveVertical(kolkoY);

    }

    /**
     * Metóda na vykreslenie karty
     */
    public void vykresli() {
        this.getTextRub().makeInvisible();
        this.getElipsaRub().makeInvisible();

        this.getVonkajsiaVrstva().makeVisible();
        this.getVnutornaVrstva().makeVisible();

        this.getElipsa().makeVisible();
        this.getTextKarty().makeVisible();
    }

    /**
     * Metoda ktorá otočí kartu na rub
     */
    public void rub() {
        this.getVnutornaVrstva().makeInvisible();
        this.getElipsa().makeInvisible();
        this.getTextKarty().makeInvisible();

        this.getElipsaRub().makeVisible();
        this.getTextRub().makeVisible();
    }

    /**
     * Metóda ktorá skryje kartu
     */
    public void skrySa() {
        this.getTextRub().makeInvisible();
        this.getElipsaRub().makeInvisible();

        this.getVonkajsiaVrstva().makeInvisible();
        this.getVnutornaVrstva().makeInvisible();

        this.getElipsa().makeInvisible();
        this.getTextKarty().makeInvisible();
    }

    /**
     * Abstraktná metoda ktorá vykoná akciu karty
     * Polymorfizmus
     *
     * @param hrac - ktorý hráč ju ma použiť
     * @return vrati ci sa akcia podarila
     */
    public abstract boolean vykonajAkciu(Hrac hrac);

    /**
     * Parametrická metóda ktorá nastaví novú farbu
     *
     * @param novaFarba - nová farba karty
     */
    public void setFarba(Color novaFarba) {
        this.farba = novaFarba;
    }

    /**
     * Metóda na výber farby karty cez JOptionPane
     */
    public void vyberSiFarbu() {
        //Polymorfizmus
        String[] moznosti = {"Červená", "Zelená", "Modrá", "Žltá"};
        int vysledok = JOptionPane.showOptionDialog(null, "Vyber farbu karty:", "Farba karty", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, moznosti, moznosti[0]);
        switch (vysledok) {
            case 0 -> {
                this.getVnutornaVrstva().changeColor("red");
                this.setFarba(Color.red);
            }
            case 1 -> {
                this.getVnutornaVrstva().changeColor("green");
                this.setFarba(Color.green);
            }
            case 2 -> {
                this.getVnutornaVrstva().changeColor("blue");
                this.setFarba(Color.blue);
            }
            case 3 -> {
                this.getVnutornaVrstva().changeColor("yellow");
                this.setFarba(Color.yellow);
            }
        }
    }

}
