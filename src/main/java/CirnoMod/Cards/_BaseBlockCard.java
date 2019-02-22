package CirnoMod.Cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class _BaseBlockCard extends _BaseCard {
    private _BaseBlockHelper helper;
    _BaseBlockCard(_BaseBlockHelper h)
    {
        super(h);
        helper = h;
        this.baseBlock = h.getBaseBlock();
    }

    public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(helper.getUpgradeBlock());
        }
    }
}