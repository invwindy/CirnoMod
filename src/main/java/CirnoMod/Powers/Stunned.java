package CirnoMod.Powers;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.EndTurnAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.StunStarEffect;

class StunnedHelper extends _PowerParamHelper
{
    public static String ID = "Frozen";
    public String getID() { return StunnedHelper.ID; }
    public String getPath() { return ""; }
    public AbstractPower.PowerType getType() { return AbstractPower.PowerType.DEBUFF; }
}

public class Stunned extends _BasePower {
    public Stunned(AbstractCreature owner) {
        super(owner, owner, 1, new StunnedHelper());
    }
    public static String getID(){ return StunnedHelper.ID; }

    public Object[] descriptionObjects() {
        return new Object[]{ this.amount };
    }

    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new StunStarEffect(0, 0)));
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.unique.LoseEnergyAction(EnergyPanel.getCurrentEnergy()));
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
    }
}