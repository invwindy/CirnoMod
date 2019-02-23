package CirnoMod.Relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.metrics.MetricData;
import com.megacrit.cardcrawl.ui.campfire.RestOption;
import com.megacrit.cardcrawl.vfx.campfire.CampfireSleepEffect;
import com.megacrit.cardcrawl.vfx.campfire.CampfireSleepScreenCoverEffect;

public class ColdFireOption extends RestOption {

    public ColdFireOption() {
        super(true);
        this.img = new Texture("");
        this.usable = true;
        this.label = "Cirno.RestOption.ColdFire";
    }

    public void useOption() {
        if(!this.usable) return;

        CardCrawlGame.sound.play("SLEEP_BLANKET");
        AbstractDungeon.effectList.add(new ColdFireEffect());
        for (int i = 0; i < 30; i++) {
            AbstractDungeon.topLevelEffects.add(new CampfireSleepScreenCoverEffect());
        }
    }

}