package fr.erusel.tssdkuhc.enums;

public enum Prefixs {

    VOICE_OF_THE_WORLD("§6§b[§r§6Voice of the World§6§b]§r§o§3 "),
    GREAT_SAGE("[§bGreat Sage§f] "),
    RAPAHEL("[§bRapahel§f] ");


    private final String text;

    Prefixs(String text) {
        this.text = text;
    }

    public String getText(){
        return text;
    }


}
