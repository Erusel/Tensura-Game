package fr.erusel.tensura.inventories.config;

import fr.erusel.tensura.Main;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.managers.GameManager;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class ConfigSettingsGUI extends FastInv {

    public ConfigSettingsGUI() {
        super(36, "Tensura Config | Settings");


        // Skill on start
        ItemBuilder skillOnStart = new ItemBuilder(Material.ACACIA_PLANKS);
        skillOnStart.name("§6Unique Skill on start : " + GameManager.getInstance().getSkillOnStart());
        skillOnStart.addLore("§7---------------");
        skillOnStart.addLore("§7> §6Left Click +");
        skillOnStart.addLore("§7> §6Right Click -");

        // Natural Regen
        ItemBuilder naturalRegen;
        if (GameManager.getInstance().getNaturalRegen()) {
            naturalRegen = new ItemBuilder(Material.GREEN_WOOL);
            naturalRegen.name("§6Natural Regeneration : §aEnabled");
        }
        else {
            naturalRegen = new ItemBuilder(Material.RED_WOOL);
            naturalRegen.name("§6Natural Regeneration : §cDisabled");
        }

        // Monster Spawn
        ItemBuilder monsterSpawn;
        if (GameManager.getInstance().getNaturalRegen()) {
            monsterSpawn = new ItemBuilder(Material.GREEN_WOOL);
            monsterSpawn.name("§6Monster Spawn : §aEnabled");
        }
        else {
            monsterSpawn = new ItemBuilder(Material.RED_WOOL);
            monsterSpawn.name("§6Monster Spawn : §cDisabled");
        }

        // Races Activated
        ItemBuilder raceActivated;
        if (GameManager.getInstance().isRaceActivated()) {
            raceActivated = new ItemBuilder(Material.GREEN_WOOL);
            raceActivated.name("§6Races : §aEnabled");
        }
        else {
            raceActivated = new ItemBuilder(Material.RED_WOOL);
            raceActivated.name("§6Races : §cDisabled");
        }

        setItem(10, skillOnStart.build(), this::skillOnStart);
        setItem(11, naturalRegen.build(), this::naturalRegen);
        setItem(12, monsterSpawn.build(), this::monsterSpawn);
        setItem(13, raceActivated.build(), this::raceActivated);


    }

    private void skillOnStart(InventoryClickEvent event) {

        if (event.getClick().equals(ClickType.LEFT)) {
            if (GameManager.getInstance().getSkillOnStart() < Skills.getAllSkillByTier(SkillTier.UNIQUE).size()) {
                GameManager.getInstance().setSkillOnStart(GameManager.getInstance().getSkillOnStart() + 1);
                ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
                itemMeta.setDisplayName("§6Unique Skill on start : " + GameManager.getInstance().getSkillOnStart());
                event.getCurrentItem().setItemMeta(itemMeta);
                ((Player) event.getWhoClicked()).updateInventory();
                return;
            }
        }
        if (event.getClick().equals(ClickType.RIGHT)) {
            if (GameManager.getInstance().getSkillOnStart() > 1) {
                GameManager.getInstance().setSkillOnStart(GameManager.getInstance().getSkillOnStart() - 1);
                ItemMeta itemMetaa = event.getCurrentItem().getItemMeta();
                itemMetaa.setDisplayName("§6Unique Skill on start : " + GameManager.getInstance().getSkillOnStart());
                event.getCurrentItem().setItemMeta(itemMetaa);
                ((Player) event.getWhoClicked()).updateInventory();
                return;
            }
        }


    }

    private void naturalRegen(InventoryClickEvent event){
        if (!GameManager.getInstance().getNaturalRegen()){
            GameManager.getInstance().setNaturalRegen(true);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Natural Regeneration : §aEnabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.GREEN_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }else {
            GameManager.getInstance().setNaturalRegen(false);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Natural Regeneration : §cDisabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.RED_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }
    }

    private void monsterSpawn(InventoryClickEvent event){
        if (!GameManager.getInstance().getMonsterSpawn()){
            GameManager.getInstance().setMonsterSpawn(true);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Monster Spawn : §aEnabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.GREEN_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }else {
            GameManager.getInstance().setMonsterSpawn(false);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Monster Spawn : §cDisabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.RED_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }
    }
    private void raceActivated(InventoryClickEvent event){
        if (!GameManager.getInstance().isRaceActivated()){
            GameManager.getInstance().setRaceActivated(true);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Races : §aEnabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.GREEN_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }else {
            GameManager.getInstance().setRaceActivated(false);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Races : §cDisabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.RED_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }
    }


}
