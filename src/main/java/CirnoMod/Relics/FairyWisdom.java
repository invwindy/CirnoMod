package CirnoMod.Relics;

import CirnoMod.Generic.Indexing;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

class FairyWisdomHelper extends _BaseRelicHelper{
    public String getID() { return "FairyWisdom"; }
    public AbstractRelic.RelicTier getTier() { return AbstractRelic.RelicTier.STARTER; }
    public AbstractRelic.LandingSound getSFX() { return AbstractRelic.LandingSound.FLAT; }
}


public class FairyWisdom extends _BaseRelic {

    private boolean cardSelected;
    public FairyWisdom() {
        super(new FairyWisdomHelper());
        this.counter = 0;
        this.cardSelected = true;
    }

    public int getRemoveCardLimit()
    {
        return 6;
    }

    public void onVictory()
    {
        if(this.counter < getRemoveCardLimit()) this.counter++;
        if(this.counter == getRemoveCardLimit())
        {
            if (AbstractDungeon.player.masterDeck.size() > 1)
            {
                triggerRemove();
            }
        }
    }

    private void triggerRemove()
    {
        cardSelected = false;
        if (AbstractDungeon.isScreenUp)
        {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }
        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;

        AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck
                .getPurgeableCards(), 1, this.DESCRIPTIONS[1], false, false, true, true);
    }

    public void update()
    {
        super.update();
        if ((!this.cardSelected) &&
                (AbstractDungeon.gridSelectScreen.selectedCards.size() == 1))
        {
            this.cardSelected = true;

            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(AbstractDungeon.gridSelectScreen.selectedCards.get(0), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));


            for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards)
            {
                AbstractDungeon.player.masterDeck.removeCard(card);
            }
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.counter = 0;
        }
        else if(!this.cardSelected && AbstractDungeon.gridSelectScreen.cancelWasOn)
        {
            this.cardSelected = true;
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            //this.counter = 0;
        }
    }
}
