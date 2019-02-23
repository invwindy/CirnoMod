package CirnoMod.Cards;

import CirnoMod.Powers.Flying;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

class AvoidDangerHelper extends _BaseBlockHelper{
    public String getID()                          { return "AvoidDanger"; }
    public int getCost()                           { return 1; }
    public AbstractCard.CardRarity getRarity()     { return AbstractCard.CardRarity.COMMON; }
    public AbstractCard.CardTarget getTarget()     { return AbstractCard.CardTarget.SELF_AND_ENEMY; }
    public int getBaseBlock()                      { return 7; }
    public int getUpgradeBlock()                   { return 3; }
    public int getMagicNumber()                    { return 2; }
    public int getUpgradeMagicNumber()             { return 1; }
    public AbstractCard.CardTags[] getCardTags()   { return new AbstractCard.CardTags[]{}; }
}

public class AvoidDanger extends _BaseBlockCard
{
    public AvoidDanger()
    {
        super(new AvoidDangerHelper());
        baseBlock = 7;

    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if(m.intent == AbstractMonster.Intent.ATTACK) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        }else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Flying(p, p, magicNumber), magicNumber));
        }
    }
}
