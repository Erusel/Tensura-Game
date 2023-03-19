package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ExtraSkill;
import fr.erusel.tensura.objects.Skill;
import fr.erusel.tensura.threads.skills.MineralFinderRunnable;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;

public class MineralFinderSkill extends Skill implements ExtraSkill {

    private boolean mineralFound = false;

    public MineralFinderSkill() {
        super("Mineral Finder", Skills.MINERALFINDER, SkillScope.UNOBTAINABLE, SkillTier.EXTRA, 800, null);
        super.addLore("Find minerals in your chunk");
    }

    @Override
    public String getRightClickSkillLore() {
        return "Find near gold ores";
    }

    @Override
    public void onRightClick(Player player) {
        Chunk playerChunk = player.getLocation().getChunk();
        for(int x = 0; x < 16; x++) {
            for(int y = 0; y <= 100; y++) {
                for(int z = 0; z < 16; z++) {
                    Block block = playerChunk.getBlock(x, y, z);
                    if(block.getType().equals(Material.GOLD_ORE)) {
                        mineralFound = true;
                        placeFallingBlock(block);
                    }
                }
            }
        }
        if (!mineralFound) {
            player.sendMessage("§cNo gold ore found in your chunk");
        }
        mineralFound = false;
        activateCooldown();
    }

    @Override
    public String getLeftClickSkillLore() {
        return "Find near diamond ores";
    }

    @Override
    public void onLeftClick(Player player) {
        Chunk playerChunk = player.getLocation().getChunk();
        for(int x = 0; x < 16; x++) {
            for(int y = 0; y <= 24; y++) {
                for(int z = 0; z < 16; z++) {
                    Block block = playerChunk.getBlock(x, y, z);
                    if(block.getType().equals(Material.DIAMOND_ORE)) {
                        mineralFound = true;
                        placeFallingBlock(block);
                    }
                }
            }
        }
        if (!mineralFound) {
            player.sendMessage("§cNo diamond ore found in your chunk");
        }
        mineralFound = false;
        activateCooldown();
    }
    public void placeFallingBlock(Block block) {
        Location blockLocation = block.getLocation().add(0.5, 0, 0.5);
        FallingBlock fallingBlock = block.getWorld().spawnFallingBlock(blockLocation, Material.STONE.createBlockData());
        fallingBlock.teleport(block.getLocation().add(0.5, 0, 0.5));
        fallingBlock.setGlowing(true);
        fallingBlock.setDropItem(false);
        fallingBlock.setHurtEntities(false);
        fallingBlock.setInvulnerable(true);
        fallingBlock.setSilent(true);
        fallingBlock.setGravity(false);
        fallingBlock.setVelocity(fallingBlock.getVelocity().setY(0));
        fallingBlock.setVelocity(fallingBlock.getVelocity().setX(0));
        fallingBlock.setVelocity(fallingBlock.getVelocity().setZ(0));
        new MineralFinderRunnable(fallingBlock, 5)
                .runTaskTimer(getMain(), 0, 20);
    }
}
