package sk.uniza.fri.hlavnyProgram;

import javax.swing.JOptionPane;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int pocetHracov = 0;
        //Zatial to je len takto pretoze chcem aby to bolo ovladane s mysou
        Object[] moznosti = {"Áno, chem hrať 😄", "Nie, necítim sa na to 😒"};
        int n = JOptionPane.showOptionDialog(null, "Uno je kartová hra, ktorá sa hrá so štandardným balíčkom 108 kariet. Hra sa zvyčajne hrá s 2 až 10 hráčmi a cieľom hry je zbaviť sa všetkých svojich kariet.\n" +
                "\n" +
                "Hráči dostanú na začiatku hry 7 kariet, ktoré si vezmú do svojej ruky. Zvyšok balíčka sa umiestni na stôl, pričom sa otočí jedna karta a umiestni sa vedľa neho, aby sa vytvoril odhodený balíček.\n" +
                "\n" +
                "Hráč, ktorý je na rade, položí na odhodený balíček kartu, ktorá má buď rovnaké číslo alebo farbu ako karta na vrchu odhodeného balíčka. Ak hráč nemá vhodnú kartu, musí si ťahať jednu kartu zo zvyšku balíčka.\n" +
                "\n" +
                "Okrem toho existujú špeciálne karty, ktoré môžu hráči použiť na zmenu farby, ťahy alebo na prinútenie súpera ťahať karty. Tieto karty zahŕňajú Wild karty, ktoré umožňujú hráčovi zmeniť farbu karty na ktorúkoľvek farbu a Wild Draw Four karty, ktoré prinútia súpera ťahať štyri karty a zmeniť farbu karty.\n" +
                "\n" +
                "Prvý hráč, ktorý sa zbaví všetkých svojich kariet, vyhráva hru. Ak hráč nevie alebo nechce položiť žiadnu kartu, musí si ťahať karty, až kým nebudú môcť položiť vhodnú kartu.\n" +
                "\n" +
                "Toto sú základné pravidlá hry Uno. Pevne dúfam, že ti pomôžu začať hrať a užiť si túto skvelú kartovú hru!", "Uvítacia správa UNO", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, moznosti, moznosti[0]);
        if (n == 0) {
            boolean spravne = false;
            while (!spravne) {
                String inputPocetHracov = JOptionPane.showInputDialog(null, "Zadaj počet hráčov:");
                if (inputPocetHracov == null) { // ak bol stlačený Cancel
                    System.exit(0); // ukonči program
                }
                try {
                    pocetHracov = Integer.parseInt(inputPocetHracov);
                    spravne = true;
                    if (pocetHracov == 1) {
                        System.exit(0);
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Musíš zadať číslo!");
                }
            }
            Hra hra = new Hra(pocetHracov);
            hra.zacatHru();
        } else {
            System.exit(0);
        }
//        Hrac hrac1 = new Hrac("Patrik", hra);
//        Hrac hrac2 = new Hrac("Lukáš", hra);
//        Hrac hrac3 = new Hrac("Katarina", hra);
//        Hrac hrac4 = new Hrac("Dáša", hra);
//        hra.pridajHraca(hrac1);
//        hra.pridajHraca(hrac2);
//        hra.pridajHraca(hrac3);
//        hra.pridajHraca(hrac4);
    }
}