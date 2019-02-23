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
    public String getPath() { return ""; }
    public AbstractPower.PowerType getType() { return AbstractPower.PowerType.DEBUFF; }
}

public class Frozen extends _BasePower {
    public Frozen(AbstractCreature owner, AbstractCreature source, int amount)
    {
        super(owner, source, amount, new FrozenHelper());
    }
    public static String getID(){ return FrozenHelper.ID; }

    int reduceAmount()
    {
        return 1;
    }

    public Object[] descriptionObjects() {
        return new Object[]{ this.name, this.amount, reduceAmount() };
    }

    public void stackPower(int amount)
    {
        super.stackPower(amount);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, amount)));
    }

    public void reducePower(int amount)
    {
        super.reducePower(amount);
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, new StrengthPower(this.owner, amount), 1));
    }

    public void atEndOfTurn(boolean isPlayer)
    {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, this.amount));
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this.ID, reduceAmount()));
    }
}
