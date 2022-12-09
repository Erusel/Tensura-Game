package fr.erusel.tensura.inventories.config;

import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
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

        setItem(10, createSkillOnStartItem().build(), this::skillOnStart);
        setItem(11, createNaturalRegenItem().build(), this::naturalRegen);
        setItem(12, createMonsterSpawnItem().build(), this::monsterSpawn);
        setItem(13, createRaceActivatedItem().build(), this::raceActivated);
        setItem(14, createSkillDropItem().build(), this::skillDrop);
        setItem(15, createBorderRadiusItem().build(), this::borderRadius);
    }


    // Item events
    private void skillOnStart(InventoryClickEvent event) {

        if (event.getClick().equals(ClickType.LEFT)) {
            if (getGameManager().getSkillOnStart() < Skills.getAllSkillByTier(SkillTier.UNIQUE).size()) {
                getGameManager().setSkillOnStart(getGameManager().getSkillOnStart() + 1);
                ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
                itemMeta.setDisplayName("§6Unique Skill on start : " + getGameManager().getSkillOnStart());
                event.getCurrentItem().setItemMeta(itemMeta);
                ((Player) event.getWhoClicked()).updateInventory();
                return;
            }
        }
        if (event.getClick().equals(ClickType.RIGHT)) {
            if (getGameManager().getSkillOnStart() > 1) {
                getGameManager().setSkillOnStart(getGameManager().getSkillOnStart() - 1);
                ItemMeta itemMetaa = event.getCurrentItem().getItemMeta();
                itemMetaa.setDisplayName("§6Unique Skill on start : " + getGameManager().getSkillOnStart());
                event.getCurrentItem().setItemMeta(itemMetaa);
                ((Player) event.getWhoClicked()).updateInventory();
                return;
            }
        }


    }
    private void naturalRegen(InventoryClickEvent event){
        if (!getGameManager().getNaturalRegen()){
            getGameManager().setNaturalRegen(true);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Natural Regeneration : §aEnabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.GREEN_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }else {
            getGameManager().setNaturalRegen(false);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Natural Regeneration : §cDisabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.RED_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }
    }
    private void monsterSpawn(InventoryClickEvent event){
        if (!getGameManager().getMonsterSpawn()){
            getGameManager().setMonsterSpawn(true);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Monster Spawn : §aEnabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.GREEN_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }else {
            getGameManager().setMonsterSpawn(false);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Monster Spawn : §cDisabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.RED_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }
    }
    private void raceActivated(InventoryClickEvent event){
        if (!getGameManager().isRaceActivated()){
            getGameManager().setRaceActivated(true);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Races : §aEnabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.GREEN_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }else {
            getGameManager().setRaceActivated(false);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Races : §cDisabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.RED_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }
    }
    private void skillDrop(InventoryClickEvent event){
        if (!getGameManager().isSkillDrop()){
            getGameManager().setSkillDrop(true);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Skill Drop : §aEnabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.GREEN_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }else {
            getGameManager().setSkillDrop(false);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Skill Drop : §cDisabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.RED_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }
    }
    private void borderRadius(InventoryClickEvent event) {

        if (event.getClick().equals(ClickType.LEFT)) {
            getGameManager().setBorderRadius(getGameManager().getBorderRadius() + 100);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Border Radius : " + getGameManager().getBorderRadius());
            event.getCurrentItem().setItemMeta(itemMeta);
            ((Player) event.getWhoClicked()).updateInventory();
            return;
        }
        if (event.getClick().equals(ClickType.RIGHT)) {
            if (getGameManager().getBorderRadius() > 100) {
                getGameManager().setBorderRadius(getGameManager().getBorderRadius() - 100);
                ItemMeta itemMetaa = event.getCurrentItem().getItemMeta();
                itemMetaa.setDisplayName("§6Border Radius : " + getGameManager().getBorderRadius());
                event.getCurrentItem().setItemMeta(itemMetaa);
                ((Player) event.getWhoClicked()).updateInventory();
                return;
            }
        }


    }


    // Create Items
    private ItemBuilder createSkillOnStartItem(){
        // Skill on start
        ItemBuilder skillOnStart = new ItemBuilder(Material.ACACIA_PLANKS);
        skillOnStart.name("§6Unique Skill on start : " + getGameManager().getSkillOnStart());
        skillOnStart.addLore("§7---------------");
        skillOnStart.addLore("§7> §6Left Click +");
        skillOnStart.addLore("§7> §6Right Click -");
        return skillOnStart;
    }
    private ItemBuilder createNaturalRegenItem(){
        // Natural Regen
        ItemBuilder naturalRegen;
        if (getGameManager().getNaturalRegen()) {
            naturalRegen = new ItemBuilder(Material.GREEN_WOOL);
            naturalRegen.name("§6Natural Regeneration : §aEnabled");
        }
        else {
            naturalRegen = new ItemBuilder(Material.RED_WOOL);
            naturalRegen.name("§6Natural Regeneration : §cDisabled");
        }
        return naturalRegen;
    }
    private ItemBuilder createMonsterSpawnItem(){
        // Monster Spawn
        ItemBuilder monsterSpawn;
        if (getGameManager().getNaturalRegen()) {
            monsterSpawn = new ItemBuilder(Material.GREEN_WOOL);
            monsterSpawn.name("§6Monster Spawn : §aEnabled");
        }
        else {
            monsterSpawn = new ItemBuilder(Material.RED_WOOL);
            monsterSpawn.name("§6Monster Spawn : §cDisabled");
        }
        return monsterSpawn;
    }
    private ItemBuilder createRaceActivatedItem(){
        // Races Activated
        ItemBuilder raceActivated;
        if (getGameManager().isRaceActivated()) {
            raceActivated = new ItemBuilder(Material.GREEN_WOOL);
            raceActivated.name("§6Races : §aEnabled");
        }
        else {
            raceActivated = new ItemBuilder(Material.RED_WOOL);
            raceActivated.name("§6Races : §cDisabled");
        }
        return raceActivated;
    }
    private ItemBuilder createSkillDropItem(){
        // Races Activated
        ItemBuilder skillDrop;
        if (getGameManager().isRaceActivated()) {
            skillDrop = new ItemBuilder(Material.GREEN_WOOL);
            skillDrop.name("§6Skill Drop : §aEnabled");
        }
        else {
            skillDrop = new ItemBuilder(Material.RED_WOOL);
            skillDrop.name("§6Skill Drop : §cDisabled");
        }
        return skillDrop;
    }
    private ItemBuilder createBorderRadiusItem() {
        // Skill on start
        ItemBuilder borderRadius = new ItemBuilder(Material.GLASS);
        borderRadius.name("§6Border Radius : " + getGameManager().getBorderRadius());
        borderRadius.addLore("§7---------------");
        borderRadius.addLore("§7> §6Left Click +100");
        borderRadius.addLore("§7> §6Right Click -100");
        return borderRadius;
    }
}
