package CirnoMod.Cards;

import CirnoMod.Powers.Stunned;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

class SoundAsleepHelper extends _BaseCardHelper
{
    public String getID()                          { return "SoundAsleep"; }
    public int getCost()                           { return 3; }
    public int getMagicNumber()                    { return 2; }
    public int getUpgradeMagicNumber()             { return -1; }
    public AbstractCard.CardRarity getRarity()     { return AbstractCard.CardRarity.RARE; }
    public AbstractCard.CardTarget getTarget()     { return AbstractCard.CardTarget.SELF; }
    public AbstractCard.CardType getType()         { return AbstractCard.CardType.POWER; }
    public AbstractCard.CardTags[] getCardTags()   { return new AbstractCard.CardTags[]{ }; }
}

public class SoundAsleep extends _BaseCard {
    public SoundAsleep()
    {
        super(new SoundAsleepHelper());
    }

    public void use(AbstractPlayer player, AbstractMonster monster)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new Stunned(player), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new HealAction(player, player, 999));

        AbstractCard masterCard = null;
        for(AbstractCard card : player.masterDeck.group)
        {
            if (card.uuid == this.uuid)
            {
                masterCard = card;
                break;
            }
        }
        if(masterCard != null)
        {
            CardCrawlGame.metricData.addPurgedItem(masterCard.getMetricID());
            AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(masterCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
            player.masterDeck.removeCard(masterCard);
        }
    }
}
