package CirnoMod.Cards;

import CirnoMod.Powers.Frozen;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

class FreezingAuraHelper extends _BaseCardHelper
{
    public String getID()                          { return "FreezingAura"; }
    public int getCost()                           { return 1; }
    public AbstractCard.CardRarity getRarity()     { return AbstractCard.CardRarity.COMMON; }
    public AbstractCard.CardType getType()         { return AbstractCard.CardType.SKILL; }
    public AbstractCard.CardTarget getTarget()     { return AbstractCard.CardTarget.SELF; }
    public int getMagicNumber()                    { return 3; }
    public int getUpgradeMagicNumber()             { return 2; }
    public AbstractCard.CardTags[] getCardTags()   { return new AbstractCard.CardTags[]{ };
    }
}

public class FreezingAura extends _BaseCard
{
    public FreezingAura(){
        super(new FreezingAuraHelper());
    }

    public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CirnoMod.Powers.FreezingAura(p, p, magicNumber), magicNumber));
    }
}
