package CirnoMod.Cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import org.apache.logging.log4j.LogManager;

class QuickEvasionHelper extends _BaseBlockHelper
{
    public String getID()                          { return "QuickEvasion"; }
    public int getCost()                           { return 1; }
    public AbstractCard.CardRarity getRarity()     { return AbstractCard.CardRarity.COMMON; }
    public AbstractCard.CardTarget getTarget()     { return AbstractCard.CardTarget.SELF; }
    public int getBaseBlock()                      { return 2; }
    public int getUpgradeBlock()                   { return -1; }
    public int getMagicNumber()                    { return 3; }
    public int getUpgradeMagicNumber()             { return 2; }
    public AbstractCard.CardTags[] getCardTags()   { return new AbstractCard.CardTags[]{ };
    }
}

public class QuickEvasion extends _BaseBlockCard
{
    public QuickEvasion(){
        super(new QuickEvasionHelper());
    }

    public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < this.magicNumber; ++i)
        {
            LogManager.getLogger(this.getClass().getName()).info(magicNumber);
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        }
    }
}
