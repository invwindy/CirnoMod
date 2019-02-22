package CirnoMod.Relics;

import CirnoMod.Generic.Indexing;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

class FrozenFrogHelper extends _BaseRelicHelper{
    public String getID() { return "FrozenFrog"; }
    public AbstractRelic.RelicTier getTier() { return AbstractRelic.RelicTier.COMMON; }
    public AbstractRelic.LandingSound getSFX() { return AbstractRelic.LandingSound.SOLID; }
}


public class FrozenFrog extends _BaseRelic {

    public FrozenFrog() {
        super(new FrozenFrogHelper());
        this.counter = -1;
    }

    private int getTurnLimit(){ return 4; }
    private int getDamageMul() { return 2; }

    public void atBattleStart()
    {

        this.counter = 0;
    }

    public void onVictory()
    {
        this.counter = -1;
    }

    public void atTurnStart()
    {
        this.counter += 1;
        if(this.counter == getTurnLimit())
        {
            flash();
            this.pulse = true;
        }
    }

    public void onPlayerEndTurn()
    {

        if(this.counter == getTurnLimit())
        {
            this.pulse = false;
            this.counter = 0;

            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));

            for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters)
            {
                if(!monster.isDeadOrEscaped())
                {
                    AbstractPower power = monster.getPower("Frozen");
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,
                            new DamageInfo(AbstractDungeon.player, power.amount * getDamageMul(), DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SMASH));
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(monster, AbstractDungeon.player, power));
                }
            }
        }
    }

}
