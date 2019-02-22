package CirnoMod.Patches;

import CirnoMod.Relics.ColdFireOption;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.RecallOption;
import com.megacrit.cardcrawl.ui.campfire.RestOption;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;

public class CampfirePatch {

    @SpirePatch(cls="com.megacrit.cardcrawl.rooms.CampfireUI", method="initializeButtons")
    public static class InitializeButtonsHook {

        @SuppressWarnings("unchecked")
        @SpireInsertPatch(
                locator=Locator.class,
                localvars={"buttons"}
        )
        public static void Insert(CampfireUI __instance, ArrayList<AbstractCampfireOption> buttons) {
            if(AbstractDungeon.player.hasRelic("ColdFire"))
            {
                buttons.add(new ColdFireOption());
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {

                Matcher finalMatcher = new Matcher.MethodCallMatcher(
                        ArrayList.class, "size");
                ArrayList<Matcher> preMatcher = new ArrayList<Matcher>();
                preMatcher.add(new Matcher.NewExprMatcher(RecallOption.class));
                return LineFinder.findInOrder(ctMethodToPatch, preMatcher, finalMatcher);
            }
        }
    }
}