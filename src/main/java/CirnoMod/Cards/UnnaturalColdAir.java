package CirnoMod.Cards;

import CirnoMod.Powers.Frozen;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

class UnnaturalColdAirHelper extends _BaseBlockHelper
{
    public String getID()                          { return "UnnaturalColdAir"; }
    public int getCost()                           { return 1; }
    public AbstractCard.CardRarity getRarity()     { return AbstractCard.CardRarity.COMMON; }
    public AbstractCard.CardTarget getTarget()     { return AbstractCard.CardTarget.SELF_AND_ENEMY; }
    public int getBaseBlock()                      { return 7; }
    public int getUpgradeBlock()                   { return 3; }
    public int getMagicNumber()                    { return 2; }
    public int getUpgradeMagicNumber()             { return 1; }
    public AbstractCard.CardTags[] getCardTags()   { return new AbstractCard.CardTags[]{ };
    }
}

public class UnnaturalColdAir extends _BaseBlockCard
{
    public UnnaturalColdAir(){
        super(new UnnaturalColdAirHelper());
    }

    public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new Frozen(m, p, magicNumber), magicNumber));
    }
}
