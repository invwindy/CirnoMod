package CirnoMod.Relics;

import CirnoMod.Generic.Indexing;
import com.megacrit.cardcrawl.relics.AbstractRelic;

abstract class _BaseRelicHelper {
    abstract String getID();
    abstract AbstractRelic.RelicTier getTier();
    abstract AbstractRelic.LandingSound getSFX();

    String getFullID(){ return Indexing.relicID(getID()); }
    String getPath(){ return Indexing.relicPath(getID()); }
    String getPathOutline() { return Indexing.relicOutlinePath(getID()); }
}
