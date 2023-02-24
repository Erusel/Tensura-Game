package fr.erusel.tensura.enums;

import fr.erusel.tensura.objects.Race;
import fr.erusel.tensura.races.demonlordstage.*;
import fr.erusel.tensura.races.firststage.*;

import java.util.Random;
import java.util.function.Supplier;

public enum Races {


    // Human
    SAINT("Saint",RaceStages.DEMONLORDSTAGE, SaintRace::new, null),
    HUMAN("Human",RaceStages.FIRSTSTAGE, HumanRace::new, SAINT),


    // Demon
    DEMON_LORD("Demon Lord",RaceStages.DEMONLORDSTAGE, DemonLordRace::new, null),
    DEMON("Demon",RaceStages.FIRSTSTAGE, DemonRace::new, DEMON_LORD),


    // Slime
    DEMON_SLIME("Demon Slime",RaceStages.DEMONLORDSTAGE, DemonSlimeRace::new, null),
    SLIME("Slime",RaceStages.FIRSTSTAGE, SlimeRace::new, DEMON_SLIME),


    // Elf
    DEMON_ELF("Demon Elf",RaceStages.DEMONLORDSTAGE, DemonElfRace::new, null),
    ELF("Elf",RaceStages.FIRSTSTAGE, ElfRace::new, DEMON_ELF),


    // Dwarf
    DEMON_DWARF("Demon Dwarf",RaceStages.DEMONLORDSTAGE, DemonDwarfRace::new, null),
    DWARF("Dwarf",RaceStages.FIRSTSTAGE, DwarfRace::new, DEMON_DWARF),


    // Orc
    DEMON_ORC("Demon Orc",RaceStages.DEMONLORDSTAGE, DemonOrcRace::new, null),
    ORC("Orc",RaceStages.FIRSTSTAGE, OrcRace::new, DEMON_ORC),


    // Majin
    DEMON_MAJIN("Demon Majin",RaceStages.DEMONLORDSTAGE, DemonMajinRace::new, null),
    MAJIN("Majin",RaceStages.FIRSTSTAGE, MajinRace::new, DEMON_MAJIN),


    // Dragonewt
    DRAGONOID("Dragonoid",RaceStages.DEMONLORDSTAGE, DragonoidRace::new, null),
    DRAGONEWT("Dragonewt",RaceStages.FIRSTSTAGE, DragonewtRace::new, DRAGONOID);




    private final String raceName;
    private final RaceStages raceStages;
    private final Supplier<Race> raceSupplier;
    private final Races raceEvolution;

    Races(String name, RaceStages raceStages, Supplier<Race> raceClass, Races evolution) {
        this.raceName = name;
        this.raceStages = raceStages;
        this.raceSupplier = raceClass;
        this.raceEvolution = evolution;
    }

    public String getName(){
        return raceName;
    }
    public RaceStages getRaceStages(){
        return raceStages;
    }
    public Races getEvolution(){
        return raceEvolution;
    }

    public Race createInstance(){
        return raceSupplier.get();
    }

    public static Races getRandomRaceByStage(RaceStages stages){
        Races races = getRandomRace();
        while (!races.getRaceStages().equals(stages)){
            races = getRandomRace();
        }
        return races;
    }
    public static Races getRandomRace() {
        Random random = new Random();
        Races r = values()[random.nextInt(values().length)];
        return r;
    }


}
