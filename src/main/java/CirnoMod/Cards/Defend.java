package CirnoMod.Cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

class DefendHelper extends _BaseBlockHelper
{
    public String getID()                          { return "Defend"; }
    public int getCost()                           { return 1; }
    public AbstractCard.CardRarity getRarity()     { return AbstractCard.CardRarity.BASIC; }
    public AbstractCard.CardTarget getTarget()     { return AbstractCard.CardTarget.SELF; }
    public int getBaseBlock()                      { return 5; }
    public int getUpgradeBlock()                   { return 3; }
    public int getMagicNumber()                    { return 0; }
    public int getUpgradeMagicNumber()             { return 0; }
    public AbstractCard.CardTags[] getCardTags()   { return new AbstractCard.CardTags[]{
            BaseModCardTags.BASIC_DEFEND };
    }
}

public class Defend extends _BaseBlockCard
{
    public Defend(){
        super(new DefendHelper());
    }
}
