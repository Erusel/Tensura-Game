package fr.erusel.tensura.objects;

public abstract class Scenario extends GameElement{

    private final String name;
    private final String lore;

    public Scenario(String name, String lore) {
        this.name = name;
        this.lore = lore;
    }

    public String getName() {
        return name;
    }
    public String getLore() {
        return lore;
    }
}
