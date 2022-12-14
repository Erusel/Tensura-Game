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
        setItem(16, createAmountLootCratesItem().build(), this::amountCrates);
    }


    // Item events
    private void skillOnStart(InventoryClickEvent event) {

        if (event.getClick().equals(ClickType.LEFT)) {
            if (getGameSettingManager().getSkillOnStart() < Skills.getAllSkillByTier(SkillTier.UNIQUE).size()) {
                getGameSettingManager().setSkillOnStart(getGameSettingManager().getSkillOnStart() + 1);
                ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
                itemMeta.setDisplayName("§6Unique Skill on start : " + getGameSettingManager().getSkillOnStart());
                event.getCurrentItem().setItemMeta(itemMeta);
                ((Player) event.getWhoClicked()).updateInventory();
                return;
            }
        }
        if (event.getClick().equals(ClickType.RIGHT)) {
            if (getGameSettingManager().getSkillOnStart() > 1) {
                getGameSettingManager().setSkillOnStart(getGameSettingManager().getSkillOnStart() - 1);
                ItemMeta itemMetaa = event.getCurrentItem().getItemMeta();
                itemMetaa.setDisplayName("§6Unique Skill on start : " + getGameSettingManager().getSkillOnStart());
                event.getCurrentItem().setItemMeta(itemMetaa);
                ((Player) event.getWhoClicked()).updateInventory();
                return;
            }
        }


    }
    private void naturalRegen(InventoryClickEvent event){
        if (!getGameSettingManager().getNaturalRegen()){
            getGameSettingManager().setNaturalRegen(true);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Natural Regeneration : §aEnabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.GREEN_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }else {
            getGameSettingManager().setNaturalRegen(false);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Natural Regeneration : §cDisabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.RED_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }
    }
    private void monsterSpawn(InventoryClickEvent event){
        if (!getGameSettingManager().getMonsterSpawn()){
            getGameSettingManager().setMonsterSpawn(true);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Monster Spawn : §aEnabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.GREEN_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }else {
            getGameSettingManager().setMonsterSpawn(false);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Monster Spawn : §cDisabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.RED_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }
    }
    private void raceActivated(InventoryClickEvent event){
        if (!getGameSettingManager().isRaceActivated()){
            getGameSettingManager().setRaceActivated(true);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Races : §aEnabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.GREEN_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }else {
            getGameSettingManager().setRaceActivated(false);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Races : §cDisabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.RED_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }
    }
    private void skillDrop(InventoryClickEvent event){
        if (!getGameSettingManager().isSkillDrop()){
            getGameSettingManager().setSkillDrop(true);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Skill Drop : §aEnabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.GREEN_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }else {
            getGameSettingManager().setSkillDrop(false);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Skill Drop : §cDisabled");
            event.getCurrentItem().setItemMeta(itemMeta);
            event.getCurrentItem().setType(Material.RED_WOOL);
            ((Player) event.getWhoClicked()).updateInventory();
        }
    }
    private void borderRadius(InventoryClickEvent event) {

        if (event.getClick().equals(ClickType.LEFT)) {
            getGameSettingManager().setBorderRadius(getGameSettingManager().getBorderRadius() + 100);
            ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName("§6Border Radius : " + getGameSettingManager().getBorderRadius());
            event.getCurrentItem().setItemMeta(itemMeta);
            ((Player) event.getWhoClicked()).updateInventory();
            return;
        }
        if (event.getClick().equals(ClickType.RIGHT)) {
            if (getGameSettingManager().getBorderRadius() > 100) {
                getGameSettingManager().setBorderRadius(getGameSettingManager().getBorderRadius() - 100);
                ItemMeta itemMetaa = event.getCurrentItem().getItemMeta();
                itemMetaa.setDisplayName("§6Border Radius : " + getGameSettingManager().getBorderRadius());
                event.getCurrentItem().setItemMeta(itemMetaa);
                ((Player) event.getWhoClicked()).updateInventory();
            }
        }
    }
    private void amountCrates(InventoryClickEvent event) {

            if (event.getClick().equals(ClickType.LEFT)) {
                getGameSettingManager().setAmountCrates(getGameSettingManager().getAmountCrates() + 1);
                ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
                itemMeta.setDisplayName("§6Amount Crates : " + getGameSettingManager().getAmountCrates());
                event.getCurrentItem().setItemMeta(itemMeta);
                ((Player) event.getWhoClicked()).updateInventory();
                return;
            }
            if (event.getClick().equals(ClickType.RIGHT)) {
                if (getGameSettingManager().getAmountCrates() > 0) {
                    getGameSettingManager().setAmountCrates(getGameSettingManager().getAmountCrates() - 1);
                    ItemMeta itemMetaa = event.getCurrentItem().getItemMeta();
                    itemMetaa.setDisplayName("§6Amount Crates : " + getGameSettingManager().getAmountCrates());
                    event.getCurrentItem().setItemMeta(itemMetaa);
                    ((Player) event.getWhoClicked()).updateInventory();
                }
            }
    }


    // Create Items
    private ItemBuilder createSkillOnStartItem(){
        // Skill on start
        ItemBuilder skillOnStart = new ItemBuilder(Material.ACACIA_PLANKS);
        skillOnStart.name("§6Unique Skill on start : " + getGameSettingManager().getSkillOnStart());
        skillOnStart.addLore("§7---------------");
        skillOnStart.addLore("§7> §6Left Click +");
        skillOnStart.addLore("§7> §6Right Click -");
        return skillOnStart;
    }
    private ItemBuilder createNaturalRegenItem(){
        // Natural Regen
        ItemBuilder naturalRegen;
        if (getGameSettingManager().getNaturalRegen()) {
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
        if (getGameSettingManager().getNaturalRegen()) {
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
        if (getGameSettingManager().isRaceActivated()) {
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
        if (getGameSettingManager().isRaceActivated()) {
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
        borderRadius.name("§6Border Radius : " + getGameSettingManager().getBorderRadius());
        borderRadius.addLore("§7---------------");
        borderRadius.addLore("§7> §6Left Click +100");
        borderRadius.addLore("§7> §6Right Click -100");
        return borderRadius;
    }
    private ItemBuilder createAmountLootCratesItem() {
        // Skill on start
        ItemBuilder amountCrates = new ItemBuilder(Material.CHEST);
        amountCrates.name("§6Amount Crates : " + getGameSettingManager().getAmountCrates());
        amountCrates.addLore("§7---------------");
        amountCrates.addLore("§7> §6Left Click +1");
        amountCrates.addLore("§7> §6Right Click -1");
        return amountCrates;
    }
}
