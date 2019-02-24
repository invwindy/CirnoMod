package CirnoMod.Cards;

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

class FreezingWindHelper extends _BaseAttackHelper
{
    public String getID()                          { return "FreezingWind"; }
    public int getCost()                           { return 1; }
    public int getBaseDamage()                     { return 4; }
    public int getUpgradeDamage()                  { return 7; }
    public int getMagicNumber()                    { return 1; }
    public int getUpgradeMagicNumber()             { return 2; }
    public AbstractCard.CardRarity getRarity()     { return AbstractCard.CardRarity.COMMON; }
    public AbstractCard.CardTarget getTarget()     { return AbstractCard.CardTarget.ALL_ENEMY; }
    public AbstractCard.CardTags[] getCardTags()   { return new AbstractCard.CardTags[]{ }; }
    public AbstractGameAction.AttackEffect getAttackEffect(){
        return AbstractGameAction.AttackEffect.BLUNT_LIGHT; }

}
public class FreezingWind extends _BaseAttackCard
{
    public FreezingWind(){
        super(new FreezingWindHelper());
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        if (com.megacrit.cardcrawl.core.Settings.FAST_MODE) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new BlizzardEffect(5,
                    AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
        } else {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new BlizzardEffect(5,
                    AbstractDungeon.getMonsters().shouldFlipVfx()), 1.0F));
        }
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.BLUNT_HEAVY, false));
        for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters)
        {
            if (!monster.isDeadOrEscaped())
            {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new Frozen(monster, p, magicNumber), magicNumber));
            }
        }

    }




}