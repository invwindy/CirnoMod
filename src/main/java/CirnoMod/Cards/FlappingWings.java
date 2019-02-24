package CirnoMod.Cards;

import CirnoMod.Powers.Flying;
import CirnoMod.Powers.Frozen;
import CirnoMod.Powers.Stunned;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
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
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;

class FlappingWingsHelper extends _BaseAttackHelper
{
    public String getID()                          { return "FlappingWings"; }
    public int getCost()                           { return 1; }
    public int getBaseDamage()                     { return 3; }
    public int getUpgradeDamage()                  { return 3; }
    public int getMagicNumber()                    { return 2; }
    public int getUpgradeMagicNumber()             { return 0; }
    public AbstractCard.CardRarity getRarity()     { return AbstractCard.CardRarity.COMMON; }
    public AbstractCard.CardTarget getTarget()     { return AbstractCard.CardTarget.ENEMY; }
    public AbstractCard.CardTags[] getCardTags()   { return new AbstractCard.CardTags[]{ }; }
    public AbstractGameAction.AttackEffect getAttackEffect(){
        return AbstractGameAction.AttackEffect.SMASH; }

}
public class FlappingWings extends _BaseAttackCard
{
    public FlappingWings(){
        super(new FlappingWingsHelper());
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SMASH));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Flying(m, p, magicNumber), magicNumber));

    }




}