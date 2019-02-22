package CirnoMod.Cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public abstract class _BaseBlockHelper extends _BaseCardHelper {
    abstract int getBaseBlock();
    abstract int getUpgradeBlock();
    public AbstractCard.CardType getType() { return AbstractCard.CardType.SKILL; }
}
