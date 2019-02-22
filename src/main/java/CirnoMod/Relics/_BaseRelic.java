package CirnoMod.Relics;

import CirnoMod.Character.Cirno;
import CirnoMod.Generic.Indexing;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.RelicStrings;

public class _BaseRelic extends CustomRelic {

    private RelicStrings relicStrings;

    _BaseRelic(_BaseRelicHelper helper) {
        super(helper.getFullID(),
                ImageMaster.loadImage(helper.getPath()),
                ImageMaster.loadImage(helper.getPathOutline()),
                helper.getTier(), helper.getSFX());
        relicStrings = CardCrawlGame.languagePack.getRelicStrings(helper.getFullID());
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
    public boolean canSpawn() {
        return AbstractDungeon.player instanceof Cirno;
    }
}