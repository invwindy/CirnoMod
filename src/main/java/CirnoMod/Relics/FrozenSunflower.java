package CirnoMod.Relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

class FrozenSunflowerHelper extends _BaseRelicHelper{
    public String getID() { return "FrozenSunflower"; }
    public AbstractRelic.RelicTier getTier() { return AbstractRelic.RelicTier.UNCOMMON; }
    public AbstractRelic.LandingSound getSFX() { return AbstractRelic.LandingSound.SOLID; }
}


public class FrozenSunflower extends _BaseRelic {

    public FrozenSunflower() {
        super(new FrozenSunflowerHelper());
    }

    public void atTurnStartPostDraw()
    {
        int drawSize = AbstractDungeon.player.drawPile.size();
        int discardSize = AbstractDungeon.player.discardPile.size();

        if(discardSize > 2 * drawSize)
        {
            flash();
            AbstractDungeon.player.gainEnergy(1);
        }
    }
}
