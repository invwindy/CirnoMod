package CirnoMod.Powers;

import CirnoMod.Generic.Indexing;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class _BasePower extends AbstractPower {
    private AbstractCreature source;
    private String[] allDescriptions;
    public _PowerParamHelper helper;

    private String makeID(String text) { return Indexing.powerID(text); }

    public _BasePower(AbstractCreature owner, AbstractCreature source, int amount, _PowerParamHelper info) {
        helper = info;

        this.ID = makeID(info.getID());
        PowerStrings ps = CardCrawlGame.languagePack.getPowerStrings(ID);

        this.name = ps.NAME;
        allDescriptions = ps.DESCRIPTIONS;

        this.amount = amount;
        this.owner = owner;
        this.source = source;
        this.img = ImageMaster.loadImage(info.getPath());
        this.type = info.getType();
        updateDescription();
    }

    public Object[] descriptionObjects()
    {
        return new Object[]{};
    }

    public void updateDescription()
    {
        this.description = Indexing.format(allDescriptions[0], descriptionObjects());
    }



}
