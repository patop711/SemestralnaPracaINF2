package sk.uniza.fri.karty;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.shapesge.Ellipse;
import sk.uniza.fri.shapesge.Rectangle;
import sk.uniza.fri.shapesge.Text;

import javax.swing.*;
import java.awt.Color;

public abstract class Karta {
    private Color farba;
    private final Znak znak;
    private final Rectangle vonkajsiaVrstva;
    private final Rectangle vnutornaVrstva;
    private final Ellipse elipsa;
    private final Ellipse elipsaRub;
    private final Text textKarty;
    private final Text textRub;
    private boolean jeOtocena;
    private final int x;
    private final int y;

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
        this.jeOtocena = false;
        this.x = x;
        this.y = y;
        this.farba = farba;
        this.znak = znak;
        this.vonkajsiaVrstva = new Rectangle(80, 120, this.x, this.y, Color.black);
        this.vnutornaVrstva = new Rectangle(this.getVonkajsiaVrstva().getWidth() - 10, this.getVonkajsiaVrstva().getHeight() - 10, this.getVonkajsiaVrstva().getX() + 5, this.getVonkajsiaVrstva().getY() + 5, this.getFarba());
        //this.vnutornaVrstva.setX(this.getVonkajsiaVrstva().getX() + 5);
        //this.getVnutornaVrstva().setY(this.getVonkajsiaVrstva().getY() + 5);
        this.elipsa = new Ellipse(60, 60, this.getVnutornaVrstva().getX() + 5, this.getVnutornaVrstva().getY() + 20);
        this.elipsaRub = new Ellipse(this.getElipsa().getDiameterX(), this.getElipsa().getDiameterY(), this.getElipsa().getPoziciaX(), this.getElipsa().getPoziciaY());
        this.textKarty = new Text(this.getZnak().getText(), this.getElipsa().getPoziciaX() + 24, this.getElipsa().getPoziciaY() + 35, 20);
        this.textRub = new Text("UNO", this.getTextKarty().getX() - 15, this.getTextKarty().getY(), this.getTextKarty().getSize());
        this.vykresli();
    }

    /**
     * Vráti navratový typ typu Rectangle
     *
     * @return vonkajsiaVrstva- Vráti navratový typ typu Rectangle
     */
    public Rectangle getVonkajsiaVrstva() {
        return this.vonkajsiaVrstva;
    }

    /**
     * Vráti navratový typ typu Rectangle
     *
     * @return vnutornaVrstva- Vráti navratový typ typu Rectangle
     */
    public Rectangle getVnutornaVrstva() {
        return this.vnutornaVrstva;
    }

    /**
     * Vráti navratový typ typu Ellipse
     *
     * @return elipsa - Vráti navratový typ typu Ellipse
     */
    public Ellipse getElipsa() {
        return this.elipsa;
    }

    /**
     * Vráti či je alebo nie je karta otocena
     *
     * @return jeOtocena - Vráti či je alebo nie je karta otocena
     */
    public boolean getJeOtocena() {
        return this.jeOtocena;
    }

    /**
     * Vráti navratový typ typu Color
     *
     * @return farba - Vráti navratový typ typu Text
     */
    public Color getFarba() {
        return this.farba;
    }

    /**
     * Vráti navratový typ typu Znak
     *
     * @return znak - Vráti navratový typ typu Znak
     */
    public Znak getZnak() {
        return this.znak;
    }

    /**
     * Vráti navratový typ typu Text
     *
     * @return textKarty - Vráti navratový typ typu Text
     */
    public Text getTextKarty() {
        return this.textKarty;
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
     * Vráti navratový typ typu Text
     *
     * @return textRub - Vráti navratový typ typu Text
     */
    public Text getTextRub() {
        return this.textRub;
    }

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

//    public void posunVodorovne(int kolkoX) {
//        this.getVonkajsiaVrstva().moveHorizontal(kolkoX);
//        this.getVnutornaVrstva().moveHorizontal(kolkoX);
//        this.getElipsa().moveHorizontal(kolkoX);
//        this.getTextKarty().moveHorizontal(kolkoX);
//        this.getElipsaRub().moveHorizontal(kolkoX);
//        this.getTextRub().moveHorizontal(kolkoX);
//
//    }

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

//    public void vykresli2(int x, int y) {
//        this.zmenPoziciu(x, y);
//        this.getTextRub().makeInvisible();
//        this.getElipsaRub().makeInvisible();
//
//        this.getVonkajsiaVrstva().makeVisible();
//        this.getVnutornaVrstva().makeVisible();
//
//        this.getElipsa().makeVisible();
//        this.getTextKarty().makeVisible();
//
//    }

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
     *
     * @param hrac - ktorý hráč ju ma použiť
     * @return
     */
    public abstract boolean vykonajAkciu(Hrac hrac);

    /**
     * Metóda ktorá otočí kartu naopak, ak sú splnené podmienky
     */
//    public void otocKartu() {
//        if (this.getJeOtocena()) {
//            this.vykresli();
//            this.jeOtocena = false;
//        } else if (!this.getJeOtocena()) {
//            this.rub();
//            this.jeOtocena = true;
//        }
//
//    }
    public void setFarba (Color novaFarba) {
        this.farba = novaFarba;
    }

    public boolean vyberSiFarbu() {
        //Polymorfizmus
        String[] moznosti = {"Cervena", "Zelena", "Modra", "Zlta"};
        int vysledok = JOptionPane.showOptionDialog(null, "Vyber farbu karty", "Farba karty", JOptionPane.DEFAULT_OPTION,
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
        return true;
    }

}
