package CirnoMod.Cards;

import CirnoMod.Powers.Frozen;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;

class SnowfallHelper extends _BaseCardHelper
{
    public String getID()                          { return "Snowfall"; }
    public int getCost()                           { return 1; }
    public AbstractCard.CardRarity getRarity()     { return AbstractCard.CardRarity.COMMON; }
    public AbstractCard.CardType getType()         { return AbstractCard.CardType.SKILL; }
    public AbstractCard.CardTarget getTarget()     { return AbstractCard.CardTarget.ALL; }
    public int getMagicNumber()                    { return 3; }
    public int getUpgradeMagicNumber()             { return 0; }
    public AbstractCard.CardTags[] getCardTags()   { return new AbstractCard.CardTags[]{ };
    }
}

public class Snowfall extends _BaseCard
{
    public Snowfall(){
        super(new SnowfallHelper());
        this.exhaustOnUseOnce = true;
    }

    public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new BlizzardEffect(60, false)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Frozen(p, p, magicNumber), magicNumber));
        for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters)
        {
            if (!monster.isDeadOrEscaped())
            {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new Frozen(monster, p, magicNumber), magicNumber));
            }
        }
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new Frozen(m, p, magicNumber), magicNumber));
    }
}
