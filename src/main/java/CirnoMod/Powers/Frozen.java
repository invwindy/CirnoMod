package CirnoMod.Powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

class FrozenHelper extends _PowerParamHelper
{
    public static String ID = "Frozen";
    public String getID() { return FrozenHelper.ID; }
    public AbstractPower.PowerType getType() { return AbstractPower.PowerType.DEBUFF; }
}

public class Frozen extends _BasePower {
    public Frozen(AbstractCreature owner, AbstractCreature source, int amount)
    {
        super(owner, source, amount, new FrozenHelper());
        updateDescription();
    }
    public static String getID(){ return FrozenHelper.ID; }

    private int reduceAmount()
    {
        return 1;
    }

    public Object[] descriptionObjects() {
        return new Object[]{ this.name, this.amount, reduceAmount() };
    }

    private void applyStrengthModifier(int n)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, n), n));
    }

    public void onInitialApplication()
    {
        applyStrengthModifier(-this.amount);
    }

    public void stackPower(int n)
    {
        int oldAmount = this.amount;
        super.stackPower(n);
        applyStrengthModifier(oldAmount - this.amount);
    }

    public void reducePower(int n)
    {
        int oldAmount = this.amount;
        super.reducePower(n);
        applyStrengthModifier(oldAmount - this.amount);
    }

    //public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source)
    //{
    //    super.onApplyPower(power, target, source);
    //    applyStrengthModifier(-power.amount);
    //}

    public void atEndOfTurn(boolean isPlayer)
    {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, this.amount));
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this.ID, reduceAmount()));
    }
}
