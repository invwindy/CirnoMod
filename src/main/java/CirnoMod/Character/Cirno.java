package CirnoMod.Character;

import CirnoMod.Patches.AbstractCardEnum;
import CirnoMod.Patches.CirnoEnum;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;

public class Cirno extends CustomPlayer {

    private static String CharacterID = "Cirno";
    private static com.megacrit.cardcrawl.localization.CharacterStrings CharacterStrings = CardCrawlGame.languagePack.getCharacterString(CharacterID);

    private static final int ENERGY_PER_TURN = 3;
    private static final int STARTING_HP = 45;
    private static final int MAX_HP = 45;
    private static final int STARTING_GOLD = 99;
    private static final int CARD_DRAW = 5;
    private static final int ORB_SLOTS = 0;

    private static final String[] orbTextures = {
            "chars/Cirno/orbs_bright/Border1.png",
            "chars/Cirno/orbs_bright/6.png",
            "chars/Cirno/orbs_bright/5.png",
            "chars/Cirno/orbs_bright/4.png",
            "chars/Cirno/orbs_bright/1.png",

            "chars/Cirno/orbs_bright/Border.png",

            "chars/Cirno/orbs_dark/Border1.png",
            "chars/Cirno/orbs_dark/6.png",
            "chars/Cirno/orbs_dark/5.png",
            "chars/Cirno/orbs_dark/4.png",
            "chars/Cirno/orbs_dark/1.png",
    };
    private static final String orbVfxPath = "chars/Cirno/orbs_bright/vfx.png";

    private static final float[] layerSpeeds = {
            80.0F, 40.0F, -40.0F, 20.0F, 0.0F
    };
    private static final String standUrl = "chars/Cirno/Stand.png";
    private static final String shoulder2Url = "chars/Cirno/Shoulder2.png";
    private static final String shoulderUrl = "chars/Cirno/Shoulder1.png";
    private static final String corpseUrl = "chars/Cirno/Corpse.png";

    public Cirno(String playerName)
    {
        super(playerName, CirnoEnum.CIRNO, orbTextures, orbVfxPath, layerSpeeds, null, null);
        initializeClass(standUrl, shoulder2Url, shoulderUrl, corpseUrl, getLoadout(), 20, 10, 220, 290, new EnergyManager(ENERGY_PER_TURN));
    }

    public void update()
    {
        super.update();
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> deck = new ArrayList<>();
        deck.add("CirnoMod.Card.Snowfall");
        deck.add("CirnoMod.Card.FreezingAura");
        deck.add("CirnoMod.Card.Chillness");
        deck.add("CirnoMod.Card.UnnaturalColdAir");
        deck.add("CirnoMod.Card.AvoidDanger");
        deck.add("CirnoMod.Card.IcicleShoot");
        deck.add("CirnoMod.Card.FreezingWind");
        deck.add("CirnoMod.Card.FlappingWings");
        deck.add("CirnoMod.Card.AirStrike");
        deck.add("CirnoMod.Card.QuickAttack");
        deck.add("CirnoMod.Card.FairyTrick");
        return deck;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> relics = new ArrayList<>();
        relics.add("CirnoMod.Relic.FairyWisdom");
        UnlockTracker.markRelicAsSeen("CirnoMod.Relic.FairyWisdom");
        return relics;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(CharacterStrings.NAMES[0],
                CharacterStrings.TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this,
                getStartingRelics(), getStartingDeck(), false);

    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return CharacterStrings.NAMES[1];
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.CIRNO_BLUE;
    }

    @Override
    public Color getCardRenderColor() {
        return CardHelper.getColor(100.0f, 149.0f, 237.0f);
    }

    // A starting card in Match and Keep event except Strike and Defense
    @Override
    public AbstractCard getStartCardForEvent() {
        return null;
    }

    @Override
    public Color getCardTrailColor() {
        return CardHelper.getColor(160.0f, 190.0f, 240.0f);
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 15;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA(getCustomModeCharacterButtonSoundKey(), MathUtils.random(-0.2F, 0.2F));
        //CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "EVENT_FOUNTAIN";
    }

    @Override
    public String getLocalizedCharacterName() {
        return CharacterStrings.NAMES[0];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new Cirno(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return CharacterStrings.TEXT[1];
    }

    @Override
    public Color getSlashAttackColor() {
        return CardHelper.getColor(100.0f, 149.0f, 237.0f);
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[0];
    }

    @Override
    public String getVampireText() {
        return CharacterStrings.TEXT[2];
    }
}
