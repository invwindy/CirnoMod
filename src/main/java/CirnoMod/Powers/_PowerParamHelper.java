package CirnoMod.Powers;

import CirnoMod.Generic.Indexing;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class _PowerParamHelper {
    abstract String getID();
    abstract AbstractPower.PowerType getType();
    public String getPathLarge() { return Indexing.powerPathLarge(getID()); }
    public String getPathSmall() { return Indexing.powerPathSmall(getID()); }
}
