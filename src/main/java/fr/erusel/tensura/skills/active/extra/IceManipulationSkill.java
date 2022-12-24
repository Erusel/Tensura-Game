package fr.erusel.tensura.skills.active.extra;

import fr.erusel.tensura.enums.SkillScope;
import fr.erusel.tensura.enums.SkillTier;
import fr.erusel.tensura.enums.Skills;
import fr.erusel.tensura.objects.ExtraSkill;
import fr.erusel.tensura.objects.Skill;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class IceManipulationSkill extends Skill implements ExtraSkill {


    public IceManipulationSkill() {
        super("Ice Manipulation", Skills.ICEMANIPULATION, SkillScope.UNOBTAINABLE, SkillTier.EXTRA, 800, null);
        super.addLore("Lore TODO");
    }

    @Override
    public String getRightClickSkillLore() {
        return "Bring the Ice Age";
    }

    @Override
    public void onRightClick(Player player) {
        // transform blocks in a radius of 10 blocks into packed ice
        for (int x = -10; x < 10; x++) {
            for (int y = -5; y < 10; y++) {
                for (int z = -10; z < 10; z++) {
                    Location location = player.getLocation().add(x, y, z);
                    if (location.getBlock().getType().isSolid()) {
                        if (!(location.getBlock().getType() == Material.BEDROCK)) {
                            location.getBlock().setType(Material.PACKED_ICE);
                        }
                    }
                }
            }
        }
        // apply slowness effect to players in a radius of 10 blocks
        for (Player p : player.getWorld().getPlayers()) {
            if (p.getLocation().distance(player.getLocation()) < 10) {
                if (!(p == player)) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 0));
                }
            }
        }
        activateCooldown();
    }

    @Override
    public void onLeftClick(Player player) {

    }
}
