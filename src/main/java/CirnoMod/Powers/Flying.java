package CirnoMod.Powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

class FlyingHelper extends _PowerParamHelper
{
    public static String ID = "Flying";
    public String getID() { return FlyingHelper.ID; }
    public String getPath() { return ""; }
    public AbstractPower.PowerType getType() { return AbstractPower.PowerType.BUFF; }
}

public class Flying extends _BasePower {
    public Flying(AbstractCreature owner, AbstractCreature source, int amount)
    {
        super(owner, source, amount, new FlyingHelper());
        updateDescription();
        loadRegion("flight");
        this.priority = 50;
    }

    public static String getID(){ return FlyingHelper.ID; }

    private int reduceAmount() {
        return 1;
    }
    private double getDamageRatio() {
        return 0.75;
    }

    public Object[] descriptionObjects() {
        return new Object[]{ this.name, (int)((1 - getDamageRatio()) * 100), reduceAmount() };
    }


    private void loseFlying(AbstractCreature source)
    {
        flash();
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, source, this.ID, reduceAmount()));
        updateDescription();
    }

    public void playApplyPowerSfx()
    {
        CardCrawlGame.sound.play("POWER_FLIGHT", 0.05F);
    }

    public void onRemove()
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new Stunned(this.owner)));
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type)
    {
        if (type == DamageInfo.DamageType.NORMAL)
        {
            damage *= getDamageRatio();
        }
        return damage;
    }

    public int onAttacked(DamageInfo info, int damageAmount)
    {
        if ((info.owner != null) && (info.type != DamageInfo.DamageType.HP_LOSS) && (info.type != DamageInfo.DamageType.THORNS) && (damageAmount > 0))
        {
            loseFlying(this.owner);
        }
        return damageAmount;
    }
}
