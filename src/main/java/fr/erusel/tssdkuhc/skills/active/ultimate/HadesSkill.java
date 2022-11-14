package fr.erusel.tssdkuhc.skills.active.ultimate;

import fr.erusel.tssdkuhc.Main;
import fr.erusel.tssdkuhc.enums.SkillTier;
import fr.erusel.tssdkuhc.inventorys.InvestigatorGUI;
import fr.erusel.tssdkuhc.objects.ActiveSkill;
import fr.erusel.tssdkuhc.objects.Skill;
import fr.erusel.tssdkuhc.threads.ImperceptibleRunnable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HadesSkill extends Skill implements ActiveSkill {


    public HadesSkill() {
        super("Hades, Lord of Invisibility", "Grant you invisibility for 30sec and knockback near players", SkillTier.ULTIMATE, 1000, null);
    }

    @Override
    public void onUse(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 600, 0, false, true));
        for(Entity entity: player.getNearbyEntities(20,20,20)) {
            entity.setVelocity(entity.getLocation().getDirection().setY(0).normalize().multiply(3));
        }
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).
                setImperceptible(true);
        Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()).
                setImperceptibleTime(30);
        new ImperceptibleRunnable(Main.getInstance().getPlayerManager().getGPlayerByUUID(player.getUniqueId()))
                .runTaskTimer(Main.getInstance(), 0, 20);
        activateCooldown();
    }
}
