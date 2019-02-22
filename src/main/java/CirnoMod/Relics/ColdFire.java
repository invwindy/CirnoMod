package CirnoMod.Relics;

import basemod.BaseMod;
import basemod.interfaces.PostCampfireSubscriber;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Girya;
import com.megacrit.cardcrawl.relics.PeacePipe;
import com.megacrit.cardcrawl.relics.Shovel;
import com.megacrit.cardcrawl.rooms.RestRoom;

class ColdFireHelper extends _BaseRelicHelper{
    public String getID() { return "ColdFire"; }
    public AbstractRelic.RelicTier getTier() { return AbstractRelic.RelicTier.RARE; }
    public AbstractRelic.LandingSound getSFX() { return AbstractRelic.LandingSound.MAGICAL; }
}


public class ColdFire extends _BaseRelic {

    public ColdFire() {
        super(new ColdFireHelper());
    }

    public boolean canSpawn()
    {
        if ((AbstractDungeon.floorNum >= 48) && (!Settings.isEndless)) {
            return false;
        }
        int campfireRelicCount = 0;
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (((r instanceof PeacePipe)) || ((r instanceof Shovel)) || ((r instanceof Girya))) {
                campfireRelicCount++;
            }
        }
        return campfireRelicCount < 2;

    }

    public void onEnterRestRoom()
    {
        flash();
    }
}
