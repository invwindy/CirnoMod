package CirnoMod.Powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.EndTurnAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.StunStarEffect;

class StunnedHelper extends _PowerParamHelper
{
    public static String ID = "Stunned";
    public String getID() { return StunnedHelper.ID; }
    public AbstractPower.PowerType getType() { return AbstractPower.PowerType.DEBUFF; }
}

public class Stunned extends _BasePower {

    private Texture playerNormalImage;

    public Stunned(AbstractCreature owner) {
        super(owner, owner, 1, new StunnedHelper());
        updateDescription();
        if(owner instanceof AbstractPlayer)
        {
            AbstractPlayer player = (AbstractPlayer)owner;
            playerNormalImage = player.img;
            player.img = player.corpseImg;
        }
    }
    public static String getID(){ return StunnedHelper.ID; }

    public Object[] descriptionObjects() {
        return new Object[]{ this.amount };
    }

    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new StunStarEffect(0, 0)));
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.unique.LoseEnergyAction(EnergyPanel.getCurrentEnergy()));
    }

    public void atEndOfTurn(boolean isPlayer)
    {
        if(isPlayer)
        {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }
    }

    public void onRemove()
    {
        if(owner instanceof AbstractPlayer)
        {
            AbstractPlayer player = (AbstractPlayer)owner;
            player.img = playerNormalImage;
        }
    }



}