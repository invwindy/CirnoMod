package CirnoMod.Cards;

import CirnoMod.Generic.Indexing;
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
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;

class CarelessChargeHelper extends _BaseAttackHelper
{
    public String getID()                          { return "CarelessCharge"; }
    public int getCost()                           { return 0; }
    public int getBaseDamage()                     { return 0; }
    public int getUpgradeDamage()                  { return 0; }
    public int getMagicNumber()                    { return 2; }
    public int getUpgradeMagicNumber()             { return 1; }
    public AbstractCard.CardRarity getRarity()     { return AbstractCard.CardRarity.COMMON; }
    public AbstractCard.CardTarget getTarget()     { return AbstractCard.CardTarget.ENEMY; }
    public AbstractCard.CardTags[] getCardTags()   { return new AbstractCard.CardTags[]{ }; }
    public AbstractGameAction.AttackEffect getAttackEffect(){
        return AbstractGameAction.AttackEffect.SMASH; }

}
public class CarelessCharge extends _BaseAttackCard
{
    public CarelessCharge(){
        super(new CarelessChargeHelper());
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction(p, p));
        this.baseDamage = p.currentBlock * this.baseMagicNumber;
        CardStrings c = CardCrawlGame.languagePack.getCardStrings(Indexing.cardID("CarelessCharge"));
        calculateCardDamage(m);
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m, new com.megacrit.cardcrawl.cards.DamageInfo(p, this.damage, com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL), com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.BLUNT_HEAVY));

        this.rawDescription = c.DESCRIPTION;
        initializeDescription();

    }
    public void onMoveToDiscard()
    {
        CardStrings c = CardCrawlGame.languagePack.getCardStrings(Indexing.cardID("CarelessCharge"));
        this.rawDescription = c.DESCRIPTION;
        initializeDescription();
    }
    public void applyPowers()
    {
        CardStrings c = CardCrawlGame.languagePack.getCardStrings(Indexing.cardID("CarelessCharge"));
        this.baseDamage = AbstractDungeon.player.currentBlock * this.baseMagicNumber;
        super.applyPowers();

        this.rawDescription = c.DESCRIPTION;
        this.rawDescription += c.UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster m)
    {
        CardStrings c = CardCrawlGame.languagePack.getCardStrings(Indexing.cardID("CarelessCharge"));
        super.calculateCardDamage(m);
        this.rawDescription = c.DESCRIPTION;
        this.rawDescription += c.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            upgradeAction();
        }
    }



}