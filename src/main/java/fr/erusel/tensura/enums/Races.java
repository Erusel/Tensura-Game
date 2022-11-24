package fr.erusel.tensura.enums;

import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.races.demonlordstage.*;
import fr.erusel.tensura.races.firststage.*;

import java.util.Random;

public enum Races {


    // First Stage
    HUMAN("Human",RaceStages.FIRSTSTAGE, HumanRace.class, SaintRace.class),
    DEMON("Demon",RaceStages.FIRSTSTAGE, DemonRace.class, DemonLordRace.class),
    SLIME("Slime",RaceStages.FIRSTSTAGE, SlimeRace.class, DemonSlimeRace.class),
    ELF("Elf",RaceStages.FIRSTSTAGE, ElfRace.class, DemonElfRace.class),
    DWARF("Dwarf",RaceStages.FIRSTSTAGE, DwarfRace.class, DemonDwarfRace.class),
    ORC("Orc",RaceStages.FIRSTSTAGE, OrcRace.class, DemonOrcRace.class),
    MAJIN("Majin",RaceStages.FIRSTSTAGE, MajinRace.class, DemonMajinRace.class),
    DRAGONEWT("Dragonewt",RaceStages.FIRSTSTAGE, DragonewtRace.class, DragonoidRace.class),



    // Demon Lord Stage
    SAINT("Saint",RaceStages.DEMONLORDSTAGE, SaintRace.class, null),
    DEMON_LORD("Demon Lord",RaceStages.DEMONLORDSTAGE, DemonLordRace.class, null),
    DEMON_SLIME("Demon Slime",RaceStages.DEMONLORDSTAGE, DemonSlimeRace.class, null),
    DEMON_ELF("Demon Elf",RaceStages.DEMONLORDSTAGE, DemonElfRace.class, null),
    DEMON_DWARF("Demon Dwarf",RaceStages.DEMONLORDSTAGE, DemonDwarfRace.class, null),
    DEMON_ORC("Demon Orc",RaceStages.DEMONLORDSTAGE, DemonOrcRace.class, null),
    DEMON_MAJIN("Demon Majin",RaceStages.DEMONLORDSTAGE, DemonMajinRace.class, null),
    DRAGONOID("Dragonoid",RaceStages.DEMONLORDSTAGE, DragonoidRace.class, null);


    private final String raceName;
    private final RaceStages raceStages;
    private final Class<? extends Race> raceClass;
    private final Class<? extends Race> raceEvolution;

    Races(String name, RaceStages raceStages, Class<? extends Race> raceClass, Class<? extends Race> evolution) {
        this.raceName = name;
        this.raceStages = raceStages;
        this.raceClass = raceClass;
        this.raceEvolution = evolution;
    }

    public String getName(){
        return raceName;
    }
    public RaceStages getRaceStages(){
        return raceStages;
    }
    public Class<? extends Race> getRaceClass(){
        return raceClass;
    }
    public Class<? extends Race> getRaceEvolutionClass(){
        return raceEvolution;
    }


    public static Races getRandomRaceByStage(RaceStages stages){
        Races races = getRandomRace();
        while (!races.getRaceStages().equals(stages)){
            races = getRandomRace();
            System.out.println(races.getName());
        }
        return races;
    }
    public static Races getRandomRace() {
        Random random = new Random();
        Races r = values()[random.nextInt(values().length)];
        System.out.println(r.getName());
        return r;
    }


}
