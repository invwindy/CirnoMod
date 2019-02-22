package CirnoMod.Cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

class StrikeHelper extends _BaseAttackHelper
{
    public String getID()                          { return "Strike"; }
    public int getCost()                           { return 1; }
    public AbstractCard.CardRarity getRarity()     { return AbstractCard.CardRarity.BASIC; }
    public AbstractCard.CardTarget getTarget()     { return AbstractCard.CardTarget.ENEMY; }
    public int getBaseDamage()                     { return 6; }
    public int getUpgradeDamage()                  { return 3; }
    public AbstractGameAction.AttackEffect getAttackEffect(){
                                              return AbstractGameAction.AttackEffect.SLASH_DIAGONAL; }
    public AbstractCard.CardTags[] getCardTags()   { return new AbstractCard.CardTags[]{
            AbstractCard.CardTags.STRIKE,
            BaseModCardTags.BASIC_STRIKE};
    }
}
public class Strike extends _BaseAttackCard
{
    public Strike(){
        super(new StrikeHelper());
    }
}
