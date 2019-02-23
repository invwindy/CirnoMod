package CirnoMod.Cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiwordKeywords;
import basemod.patches.com.megacrit.cardcrawl.localization.LocalizedStrings.GetCardStringsModID;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

import CirnoMod.Patches.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public abstract class _BaseCard extends CustomCard {
    private _BaseCardHelper helper;
    _BaseCard(_BaseCardHelper c) {
        super(c.getFullID(),
                CardCrawlGame.languagePack.getCardStrings(c.getFullID()).NAME,
                c.getPath(),
                c.getCost(),
                CardCrawlGame.languagePack.getCardStrings(c.getFullID()).DESCRIPTION,
                c.getType(),
                AbstractCardEnum.CIRNO_BLUE, c.getRarity(),
                c.getTarget());
        helper = c;
        baseMagicNumber = c.getMagicNumber();
        this.magicNumber = baseMagicNumber;

        //for(CardTags tag : c.getCardTags())
        //{
        //    this.tags.add(tag);
        //}
        this.tags = new ArrayList<>(Arrays.asList(c.getCardTags()));
        setDescription();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(helper.getUpgradeMagicNumber());
            upgradeAction();
            setDescription();
        }
    }

    public void upgradeAction() {}

    private void setDescription() {
        String desc;
        CardStrings cs = CardCrawlGame.languagePack.getCardStrings(helper.getFullID());
        if(this.upgraded && cs.UPGRADE_DESCRIPTION != null)
        {
            desc = cs.UPGRADE_DESCRIPTION;
        }else{
            desc = cs.DESCRIPTION;
        }
        this.rawDescription = desc;
        initializeDescription();
    }
}