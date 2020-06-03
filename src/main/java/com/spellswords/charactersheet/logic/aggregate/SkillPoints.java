package com.spellswords.charactersheet.logic.aggregate;

import java.io.Serializable;
import java.util.Collection;

import static com.spellswords.charactersheet.logic.aggregate.SkillType.*;

public class SkillPoints implements Serializable {
    int level;

    int flex, flexSpent;
    int body, bodySpent;
    int mind, mindSpent;

    public SkillPoints(int flexFromChar, AbilityCollection abilityCollection) {
        flexSpent = bodySpent = mindSpent = 0;
        updatePoints(abilityCollection, flexFromChar);
    }

    public SkillPoints(int flexFromChar, AbilityCollection abilityCollection, int level) {
        this.level = level;
        flexSpent = bodySpent = mindSpent = 0;
        updatePoints(abilityCollection, flexFromChar);
    }

    private void updatePoints(AbilityCollection abilityCollection, int flexFromChar) {
        body = abilityCollection.getAbility("STR").getBaseScore() / 2;
        body += abilityCollection.getAbility("DEX").getBaseScore() / 2;
        body += abilityCollection.getAbility("CON").getBaseScore() / 2;
        mind = abilityCollection.getAbility("WIS").getBaseScore() / 2;
        mind += abilityCollection.getAbility("CHA").getBaseScore() / 2;
        flex = Math.min(flexFromChar + abilityCollection.getAbility("INT").getAbilityBonus() * level, 0);
    }

    public void setSkillProf(Skill skill, int profLevel) {
        int before, after;
        int oldProf;

        before = skill.getNumSkillPoints();
        oldProf = skill.getProfLevel();

        if(oldProf == profLevel) return;

        skill.setProfLevel(profLevel);

        after = skill.getNumSkillPoints();

        spendSkillPoints(skill.getType(), after - before);

    }

    public void spendSkillPoints(SkillType type, int pointsSpent) {
        int oldFSpent = flexSpent;
        int oldBSpent = bodySpent;
        int oldMSpent = mindSpent;
        int increment = 1;

        if(pointsSpent < 0) {
            increment = -1;
        }

        // Spend points from the skill type first
        if(type == BODY) {
            bodySpent += pointsSpent;
            if(bodySpent > body) {
                int rectify = bodySpent - body;
                flexSpent += rectify;
                bodySpent = body;
            }
        }
        else if(type == MIND) {
            mindSpent += pointsSpent;
            if(mindSpent > mind) {
                int rectify = mindSpent - mind;
                flexSpent += rectify;
                mindSpent = mind;
            }
        }
        else if(type == FLEX) {
            int pointsLeft = pointsSpent;
            // Make mind and body spent equal
            while(pointsLeft != 0) {
                // more mind than body
                if(mind - mindSpent > 0 && mind - mindSpent > body - bodySpent) {
                    if(increment > 0)
                        mindSpent += increment;
                    else if(flexSpent == 0 && body - bodySpent > 0)
                        bodySpent += increment;
                    else
                        flexSpent += increment;
                    pointsLeft -= increment;
                }
                // More body than mind
                else if(body - bodySpent > 0 && body - bodySpent > mind - mindSpent) {
                    if(increment > 0)
                        bodySpent += increment;
                    else if(flexSpent == 0 && mind - mindSpent > 0)
                        mindSpent += increment;
                    else
                        flexSpent += increment;
                    pointsLeft -= increment;
                }
                else if(mind == mindSpent && body == bodySpent) {
                    flexSpent += increment;
                    pointsLeft -=increment;
                }
            }
        }
    }
}
