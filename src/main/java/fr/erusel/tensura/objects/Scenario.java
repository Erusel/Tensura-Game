package fr.erusel.tensura.objects;

public abstract class Scenario extends GameElement{

    private final String name;

    public Scenario(String name, String lore) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
