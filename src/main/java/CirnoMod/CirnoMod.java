package CirnoMod;

import CirnoMod.Cards.*;
import CirnoMod.Character.Cirno;
import CirnoMod.Patches.AbstractCardEnum;
import CirnoMod.Patches.CirnoEnum;
import CirnoMod.Relics.ColdFire;
import CirnoMod.Relics.FairyWisdom;
import CirnoMod.Relics.FrozenFrog;
import CirnoMod.Relics.FrozenSunflower;
import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import CirnoMod.Generic.Keyword;

@SpireInitializer
public class CirnoMod implements PostInitializeSubscriber, EditCharactersSubscriber, EditStringsSubscriber, EditRelicsSubscriber, EditCardsSubscriber, EditKeywordsSubscriber {
    private static final Logger logger = LogManager.getLogger(CirnoMod.class.getName());
    private static final Color CIRNO_COLOR = new Color(0x99CCFF);

    public CirnoMod()
    {
        BaseMod.subscribe(this);
        BaseMod.addColor(AbstractCardEnum.CIRNO_BLUE, CIRNO_COLOR, CIRNO_COLOR,CIRNO_COLOR,CIRNO_COLOR,CIRNO_COLOR,CIRNO_COLOR,CIRNO_COLOR,
                "Theme/AttackBg.png",
                "Theme/SkillBg.png",
                "Theme/PowerBg.png",
                "Theme/EnergyOrb.png",

                "Theme/AttackBgPortrait.png",
                "Theme/SkillBgPortrait.png",
                "Theme/PowerBgPortrait.png",
                "Theme/EnergyOrbPortrait.png",

                "Theme/CardEnergyOrb.png");
    }

    public static void initialize()
    {

        logger.info("========================= CIRNO INIT =========================");

        @SuppressWarnings("unused")
        CirnoMod cirno = new CirnoMod();

        logger.info("================================================================");
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("begin editing characters.");

        logger.info("add " + CirnoEnum.CIRNO.toString());

        int ButtonCount = 4;
        Random rd = new Random();
        int buttonIndex = rd.nextInt(ButtonCount) + 1;

        BaseMod.addCharacter(new Cirno(CardCrawlGame.playerName),
                "chars/Cirno/Select/9_" + buttonIndex + ".png",
                "chars/Cirno/Portrait.png",
                CirnoEnum.CIRNO);

        logger.info("done editing characters");
    }

    String getLanguageFlag()
    {
        String languageFlag;
        switch(Settings.language)
        {
            case ENG:
                languageFlag = "eng";
                break;
            case ZHS:
                languageFlag = "zhs";
                break;
            default:
                languageFlag = "eng";
                break;
        }
        return languageFlag;
    }

    @Override
    public void receiveEditStrings() {
        String languageFlag = getLanguageFlag();

        Hashtable<Class, String> hd = new Hashtable<Class, String>() {{
            put(CharacterStrings.class, "Cirno-Character");
            put(CardStrings.class, "Cirno-Card");
            put(PowerStrings.class, "Cirno-Power");
            put(RelicStrings.class, "Cirno-Relic");
        }};
        for(Map.Entry entry : hd.entrySet())
        {
            logger.info("begin editing " + entry.getValue() + " strings.");
            Class cls = (Class)entry.getKey();
            String filename = (String)entry.getValue();
            BaseMod.loadCustomStringsFile(cls, "Localization/" + languageFlag + "/" + filename + ".json");
            logger.info("done editing " + entry.getValue() + " strings.");
        }
    }

    @Override
    public void receiveEditCards() {
        logger.info("begin editing cards");
        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Defend());
        BaseMod.addCard(new Chillness());
        BaseMod.addCard(new QuickEvasion());
        BaseMod.addCard(new AvoidDanger());
        BaseMod.addCard(new UnnaturalColdAir());
        BaseMod.addCard(new FreezingAura());
        BaseMod.addCard(new Snowfall());
        BaseMod.addCard(new IcicleShoot());
        BaseMod.addCard(new FreezingWind());
        BaseMod.addCard(new FlappingWings());
        BaseMod.addCard(new AirStrike());
        BaseMod.addCard(new QuickAttack());
        BaseMod.addCard(new FairyTrick());
        logger.info("done editing cards");
    }

    @Override
    public void receiveEditRelics() {
        logger.info("begin editing relics");
        BaseMod.addRelicToCustomPool(new FairyWisdom(), AbstractCardEnum.CIRNO_BLUE);
        BaseMod.addRelicToCustomPool(new FrozenFrog(), AbstractCardEnum.CIRNO_BLUE);
        BaseMod.addRelicToCustomPool(new FrozenSunflower(), AbstractCardEnum.CIRNO_BLUE);
        BaseMod.addRelicToCustomPool(new ColdFire(), AbstractCardEnum.CIRNO_BLUE);
        logger.info("done editing relics");
    }

    private static final String MODNAME = "Cirno the Ice Fairy";
    private static final String AUTHOR = "invwindy";
    private static final String DESCRIPTION = "Adds the Cirno character to the game";

    @Override
    public void receivePostInitialize() {
        Texture badgeTexture = new Texture("badge.png");
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addUIElement(new ModLabel("*No setting is available for Cirno mod now.*", 400.0f, 700.0f, settingsPanel, (me)->{}));
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
    }

    @Override
    public void receiveEditKeywords() {
        String languageFlag = getLanguageFlag();

        logger.info("begin editing Cirno-Keyword strings");
        final String json = Gdx.files.internal("Localization/" + languageFlag + "/Cirno-Keyword.json").readString(String.valueOf(StandardCharsets.UTF_8));


        Gson gson = new Gson();
        final Keyword[] keywords = (Keyword[])gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(keyword.PROPERNAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
        logger.info("done editing Cirno-Keyword strings");
    }
}
