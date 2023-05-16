package sk.uniza.fri.hlavnyProgram;

import javax.swing.JOptionPane;

/** Trieda Main, spustí hru
 *  @author Patrik Pavlík
 *  @version 1.23.15
 */
public class Main {
    public static void main(String[] args) {
        int pocetHracov = 0;
        Object[] moznosti = {"Áno, chem hrať 😄", "Nie, necítim sa na to 😒"};
        int n = JOptionPane.showOptionDialog(null, """
                Uno je kartová hra, ktorá sa hrá so štandardným balíčkom 108 kariet. Hra sa zvyčajne hrá s 2 až 10 hráčmi a cieľom hry je zbaviť sa všetkých svojich kariet.
                Hráči dostanú na začiatku hry 7 kariet, ktoré si vezmú do svojej ruky. Zvyšok balíčka sa umiestni na stôl, pričom sa otočí jedna karta a umiestni sa vedľa neho, aby sa vytvoril odhodený balíček.
                Hráč, ktorý je na rade, položí na odhodený balíček kartu, ktorá má buď rovnaké číslo alebo farbu ako karta na vrchu odhodeného balíčka. Ak hráč nemá vhodnú kartu, musí si ťahať jednu kartu zo zvyšku balíčka.
                Okrem toho existujú špeciálne karty, ktoré môžu hráči použiť na zmenu farby, ťahy alebo na prinútenie súpera ťahať karty. Tieto karty zahŕňajú Wild karty, ktoré umožňujú hráčovi zmeniť farbu karty na ktorúkoľvek farbu a Wild Draw Four karty, ktoré prinútia súpera ťahať štyri karty a zmeniť farbu karty.
                Prvý hráč, ktorý sa zbaví všetkých svojich kariet, vyhráva hru. Ak hráč nevie alebo nechce položiť žiadnu kartu, musí si ťahať karty, až kým nebude môcť položiť vhodnú kartu.
                Toto sú základné pravidlá hry Uno. Pevne dúfam, že ti pomôžu začať hrať a užiť si túto skvelú kartovú hru!
                
                Ovládanie:
                                Klávesa Q >> Použitie karty
                                Klávesa W >> potiahnutie karty
                                Klávesa Escape >> ukončenie hry
                                
                                Karty:
                                KartaDivoka >> výber farby, možno položiť na všetky karty
                                KartaDivokaTahajStyri >> výber farby, pridanie 4 kariet ďalšiemu hráčovi a následne preskočenie na ďalšieho hráča, možno položiť na všetky karty
                                KartaNormalna  >> možno položiť na kartu ktorá má buď rovnaký znak alebo farbu
                                KartaOtocit >> možno položiť na kartu ktorá má rovnakú farbu, otočí sa smer ktorým je ďalší hráč na rade
                                KartaPreskocit >> možno položiť na kartu ktorá má rovnakú farbu, hráč ktorý je na rade nebude mať možnosť ťahu
                                KartaTahajDve >> možno položiť na kartu ktorá má rovnakú farbu, ďalší hráč si ťahá 2 karty a stráca možnosť ťahu""", "Uvítacia správa UNO", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, moznosti, moznosti[0]);
        if (n == 0) {
            boolean spravne = false;
            while (!spravne) {
                String inputPocetHracov = JOptionPane.showInputDialog(null, "Zadaj počet hráčov:", "Kartová hra UNO", JOptionPane.QUESTION_MESSAGE);
                if (inputPocetHracov == null) {
                    System.exit(0); //ak bol stlačený Cancel ukonči sa program
                }
                try {
                    pocetHracov = Integer.parseInt(inputPocetHracov);
                    spravne = true;
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Musíš zadať číslo!");
                }
            }
            Hra hra = new Hra(pocetHracov);
            hra.zacatHru();
        } else {
            System.exit(0);
        }
    }
}