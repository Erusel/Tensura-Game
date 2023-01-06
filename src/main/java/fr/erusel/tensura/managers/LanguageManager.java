package fr.erusel.tensura.managers;

import fr.erusel.tensura.Main;

public class LanguageManager{

    Main main;
    public String currentLanguage = "en";

    public LanguageManager(Main main) {
        this.main = main;
        currentLanguage = main.getConfig().getString("language");
        main.getLogger().info("Current languange set to " + currentLanguage);
    }
}
