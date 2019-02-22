package CirnoMod.Relics;

import CirnoMod.Cards.SoundAsleep;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.ui.buttons.ProceedButton;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import java.util.ArrayList;

public class ColdFireEffect
        extends AbstractGameEffect
{
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CampfireSleepEffect");
    public static final String[] TEXT = uiStrings.TEXT;
    private static final float DUR = 3.0F;
    private static final float FAST_MODE_DUR = 1.5F;
    private Color screenColor = AbstractDungeon.fadeColor.cpy();
    private boolean cardGained;

     ColdFireEffect()
    {
        cardGained = false;
        if (Settings.FAST_MODE) {
            this.startingDuration = 1.5F;
        } else {
            this.startingDuration = 3.0F;
        }
        this.duration = this.startingDuration;

        this.screenColor.a = 0.0F;
        ((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
        AbstractDungeon.overlayMenu.proceedButton.hide();
    }

    public void update()
    {
        this.duration -= Gdx.graphics.getDeltaTime();
        updateBlackScreenColor();
        if ((this.duration < this.startingDuration - 0.5F) && (!this.cardGained))
        {
            playSleepJingle();
            this.cardGained = true;
            AbstractDungeon.actionManager.addToTop(new AddCardToDeckAction(new SoundAsleep()));
        }
        if (this.duration < this.startingDuration / 2.0F)
        {
            this.isDone = true;
            ((RestRoom)AbstractDungeon.getCurrRoom()).fadeIn();
            com.megacrit.cardcrawl.rooms.AbstractRoom.waitTimer = 0.0F;
            AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
        }
    }

    private void playSleepJingle()
    {
        int roll = MathUtils.random(0, 2);
        switch (AbstractDungeon.id)
        {
            case "Exordium":
                if (roll == 0) {
                    CardCrawlGame.sound.play("SLEEP_1-1");
                } else if (roll == 1) {
                    CardCrawlGame.sound.play("SLEEP_1-2");
                } else {
                    CardCrawlGame.sound.play("SLEEP_1-3");
                }
                break;
            case "TheCity":
                if (roll == 0) {
                    CardCrawlGame.sound.play("SLEEP_2-1");
                } else if (roll == 1) {
                    CardCrawlGame.sound.play("SLEEP_2-2");
                } else {
                    CardCrawlGame.sound.play("SLEEP_2-3");
                }
                break;
            case "TheBeyond":
                if (roll == 0) {
                    CardCrawlGame.sound.play("SLEEP_3-1");
                } else if (roll == 1) {
                    CardCrawlGame.sound.play("SLEEP_3-2");
                } else {
                    CardCrawlGame.sound.play("SLEEP_3-3");
                }
                break;
        }
    }

    private void updateBlackScreenColor()
    {
        if (this.duration > this.startingDuration - 0.5F) {
            this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - (this.startingDuration - 0.5F)) * 2.0F);
        } else if (this.duration < 1.0F) {
            this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration);
        } else {
            this.screenColor.a = 1.0F;
        }
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.screenColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
    }

    public void dispose() {}
}
