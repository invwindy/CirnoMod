package CirnoMod.Cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public abstract class _BaseAttackHelper extends _BaseCardHelper {
    abstract int getBaseDamage();
    abstract int getUpgradeDamage();
    abstract AbstractGameAction.AttackEffect getAttackEffect();
    public AbstractCard.CardType getType() { return AbstractCard.CardType.ATTACK; }
}
