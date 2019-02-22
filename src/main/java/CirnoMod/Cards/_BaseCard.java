package CirnoMod.Cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

import CirnoMod.Patches.*;

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

        for(CardTags tag : c.getCardTags())
        {
            this.tags.add(tag);
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }


}