package CirnoMod.Cards;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class _BaseAttackCard extends _BaseCard {
    private _BaseAttackHelper helper;
    _BaseAttackCard(_BaseAttackHelper h)
    {
        super(h);
        helper = h;
        this.baseDamage = h.getBaseDamage();
    }

    public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn),
                helper.getAttackEffect()));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(helper.getUpgradeDamage());
        }
    }
}