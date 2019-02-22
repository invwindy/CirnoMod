package CirnoMod.Cards;

import CirnoMod.Generic.Indexing;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

abstract class _BaseCardHelper {
    abstract String getID();
    abstract int getCost();
    abstract AbstractCard.CardType getType();
    abstract AbstractCard.CardRarity getRarity();
    abstract AbstractCard.CardTarget getTarget();
    abstract AbstractCard.CardTags[] getCardTags();

    String getFullID(){ return Indexing.cardID(getID()); }
    String getPath() { return Indexing.cardPath(getID()); }
}
