package fr.erusel.tensura.scenarios;

import fr.erusel.tensura.objects.Eventable;
import fr.erusel.tensura.objects.Scenario;

public class AutoSmeltScenario extends Scenario implements Eventable {

    public AutoSmeltScenario() {
        super("Auto Smelt", "Auto smelt every mined ore.");
    }

}
