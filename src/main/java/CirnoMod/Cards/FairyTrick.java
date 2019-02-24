package CirnoMod.Cards;

import CirnoMod.Powers.Frozen;
import CirnoMod.Powers.Stunned;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

class FairyTrickHelper extends _BaseAttackHelper
{
    public String getID()                          { return "FairyTrick"; }
    public int getCost()                           { return 1; }
    public int getBaseDamage()                     { return 3; }
    public int getUpgradeDamage()                  { return 2; }
    public int getMagicNumber()                    { return 1; }
    public int getUpgradeMagicNumber()             { return 1; }
    public AbstractCard.CardRarity getRarity()     { return AbstractCard.CardRarity.COMMON; }
    public AbstractCard.CardTarget getTarget()     { return AbstractCard.CardTarget.ENEMY; }
    public AbstractCard.CardTags[] getCardTags()   { return new AbstractCard.CardTags[]{ }; }
    public AbstractGameAction.AttackEffect getAttackEffect(){
        return AbstractGameAction.AttackEffect.BLUNT_LIGHT; }

}
public class FairyTrick extends _BaseAttackCard
{
    public FairyTrick(){
        super(new FairyTrickHelper());
        this.exhaustOnUseOnce = true;

    }

    public void use(AbstractPlayer p, AbstractMonster m){
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(m, p, new com.megacrit.cardcrawl.powers.VulnerablePower(m, 3, false), 3));
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DrawCardAction(p, this.magicNumber));

    }




}