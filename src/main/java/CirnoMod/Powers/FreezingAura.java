package CirnoMod.Powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.sun.java.swing.action.ActionManager;

class FreezingAuraHelper extends _PowerParamHelper
{
    public static String ID = "FreezingAura";
    public String getID() { return FreezingAuraHelper.ID; }
    public AbstractPower.PowerType getType() { return AbstractPower.PowerType.DEBUFF; }
}

public class FreezingAura extends _BasePower {
    public FreezingAura(AbstractCreature owner, AbstractCreature source, int amount)
    {
        super(owner, source, amount, new FreezingAuraHelper());
    }
    public static String getID(){ return FreezingAuraHelper.ID; }


    public void onInitialApplication()
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new ThornsPower(owner, this.amount), this.amount));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new PlatedArmorPower(owner, this.amount), this.amount));
    }
    public void atStartOfTurn()
    {
        AbstractPower thornsPower = owner.getPower(ThornsPower.POWER_ID);
        if(thornsPower != null)
        {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, thornsPower, this.amount));
        }
        AbstractPower platedArmorPower = owner.getPower(PlatedArmorPower.POWER_ID);
        if(platedArmorPower != null)
        {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, platedArmorPower, this.amount));
        }
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this));
    }
}
