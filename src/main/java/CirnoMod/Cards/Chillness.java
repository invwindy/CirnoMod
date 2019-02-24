package CirnoMod.Cards;

import CirnoMod.Powers.Flying;
import CirnoMod.Powers.Frozen;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import org.apache.logging.log4j.LogManager;

class ChillnessHelper extends _BaseCardHelper
{
    public String getID()                          { return "Chillness"; }
    public int getCost()                           { return 1; }
    public AbstractCard.CardRarity getRarity()     { return AbstractCard.CardRarity.BASIC; }
    public AbstractCard.CardType getType()         { return AbstractCard.CardType.SKILL; }
    public AbstractCard.CardTarget getTarget()     { return AbstractCard.CardTarget.ENEMY; }
    public int getMagicNumber()                    { return 3; }
    public int getUpgradeMagicNumber()             { return 0; }
    public AbstractCard.CardTags[] getCardTags()   { return new AbstractCard.CardTags[]{ };
    }
}

public class Chillness extends _BaseCard
{
    public Chillness(){
        super(new ChillnessHelper());
    }

    public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new Frozen(m, p, magicNumber), magicNumber));
    }

    public void upgrade()
    {
        if(!this.upgraded)
        {
            super.upgrade();
            this.target = CardTarget.ALL;
        }
    }
}
