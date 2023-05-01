package sk.uniza.fri.karty;

public enum Znak {
    KARTA_NULA("0"),
    KARTA_JEDEN("1"),
    KARTA_DVA("2"),
    KARTA_TRI("3"),
    KARTA_STYRI("4"),
    KARTA_PAT("5"),
    KARTA_SEST("6"),
    KARTA_SEDEM("7"),
    KARTA_OSEM("8"),
    KARTA_DEVAT("9"),
    KARTA_PRESKOCIT("P"),
    KARTA_OTOCIT("O"),
    KARTA_TAHAJ_DVE("T2"),
    KARTA_TAHAJ_STYRI("T4"),
    KARTA_DIVOKA("D"),
    KARTA_BALICEK("?");

    private final String znak;

    /**
     * Parametricky konstuktor ktorý ako parameter zadáva znak z enumu
     * @param znak - znak karty
     */
    Znak(String znak) {
        this.znak = znak;
    }

    /**
     * Vráti Stringovú hodnotu textu zo Znaku
     * @return znak
     */
    public String getText() {
        return this.znak;
    }
}
