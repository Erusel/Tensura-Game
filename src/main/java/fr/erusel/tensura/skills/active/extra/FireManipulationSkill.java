package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ExtraSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class FireManipulationSkill extends Skill implements ExtraSkill {

    Random rdm = new Random();

    public FireManipulationSkill() {
        super("Fire Manipulation", Skills.FIREMANIPULATION, SkillScope.UNOBTAINABLE, SkillTier.EXTRA, 800, null);
        super.addLore("Use the power of the Fire");
    }

    @Override
    public String getRightClickSkillLore() {
        return "Bring the nether";
    }

    @Override
    public String getLeftClickSkillLore() {
        return "Shoot Fireball";
    }

    @Override
    public void onRightClick(Player player) {
        // transform blocks in a radius of 10 blocks into netherack and place fire on top
        for (int x = -10; x < 10; x++) {
            for (int y = -5; y < 10; y++) {
                for (int z = -10; z < 10; z++) {
                    Location location = player.getLocation().add(x, y, z);
                    if (location.getBlock().getType().isSolid()) {
                        if (!(location.getBlock().getType() == Material.BEDROCK)) {
                            int random = rdm.nextInt(8);
                            location.getBlock().setType(Material.NETHERRACK);
                            if (random == 0) {
                                location.add(0, 1, 0).getBlock().setType(Material.FIRE);
                            }
                        }
                    }
                }
            }
        }
        activateCooldown();
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, 0));
    }

    @Override
    public void onLeftClick(Player player) {
        Location location = player.getLocation().add(0,1,0);
        Fireball fb = (Fireball) player.getWorld().spawnEntity(location, EntityType.FIREBALL);
        fb.setYield(2f);
        activateCooldown();
    }
}
