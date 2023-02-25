package fr.erusel.tensura.enums;

public enum Teams {


    RED("§cRed"),
    YELLOW("§eYellow"),
    GREEN("§aGreen"),
    BLUE("§9Blue"),
    NONE("§7None");

    private final String displayText;

    Teams(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
