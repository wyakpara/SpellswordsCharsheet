package com.spellswords.charactersheet.logic.aggregate;

import com.spellswords.charactersheet.utilities.Columns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class FeatRecord {
    ArrayList<FeatTree> trees;
    int MFeats, mspent;
    int SFeats, sspent;
    int PFeats, pspent;
    int AFeats, aspent;
    int DFeats, dspent;
    int UFeats, uspent;

    public FeatRecord() {
        trees = new ArrayList<>();
    }

    public void addNewTree(Feat root) {
        FeatTree tree = new FeatTree();
        tree.addPrimary(root, 1);
        trees.add(tree);
        Collections.sort(trees);
    }

    public int getNumTrees() {
        return trees.size();
    }

    public void calculateNumFeats(LevelRecord levels) {
        Collection<CharClass> c = levels.getCollection();
        MFeats = SFeats = PFeats = AFeats = DFeats = UFeats = 0;

        for(CharClass cc:c) {
            // Primary class
            int ccLevel = cc.getLevel();
            switch (cc.getType()) {
                case MARTIAL:
                    MFeats += ccLevel * 3;
                    break;
                case SPECIALIST:
                    SFeats += ccLevel * 3;
                    break;
                case PSIONIC:
                    PFeats += ccLevel * 3;
                    break;
                case ARCANE:
                    AFeats += ccLevel * 3;
                    break;
                case DIVINE:
                    DFeats += ccLevel * 3;
                    break;
            }

            // Primary class
            switch (cc.getSecondary()) {
                case MARTIAL:
                    MFeats += ccLevel * 2;
                    break;
                case SPECIALIST:
                    SFeats += ccLevel * 2;
                    break;
                case PSIONIC:
                    PFeats += ccLevel * 2;
                    break;
                case ARCANE:
                    AFeats += ccLevel * 2;
                    break;
                case DIVINE:
                    DFeats += ccLevel * 2;
                    break;
            }

            // Universal feats
            UFeats += ccLevel;
        }
    }

    private void spendFeats() {
        mspent = sspent = pspent = aspent = dspent = uspent = 0;
        // First go through all trees and add up the costs of the regular skills
        for(FeatTree tree:trees) {
            switch (tree.getRoot().getArchetype()) {
                case MARTIAL:
                    mspent += tree.getCost();
                    mspent -= tree.getUCost();
                    break;
                case SPECIALIST:
                    sspent += tree.getCost();
                    sspent -= tree.getUCost();
                    break;
                case PSIONIC:
                    pspent += tree.getCost();
                    pspent -= tree.getUCost();
                    break;
                case ARCANE:
                    aspent += tree.getCost();
                    aspent -= tree.getUCost();
                    break;
                case DIVINE:
                    dspent += tree.getCost();
                    dspent -= tree.getUCost();
                    break;
            }
            uspent += tree.getUCost();
        }
    }

    public boolean addPrimaryFeat(Feat feat, int index, int tier) {
        boolean retval = trees.get(index).addPrimary(feat, tier);
        spendFeats();
        return retval;
    }

    public void replacePrimaryFeat(Feat feat, int index, int tier) {
        trees.get(index).replacePrimary(feat, tier);
        spendFeats();
    }

    public boolean addSecondaryFeat(Feat feat, int index, int tier) {
        boolean retval = trees.get(index).addSecondary(feat, tier);
        spendFeats();
        return retval;
    }

    public FeatTree getTreeAt(int index) {
        return trees.get(index);
    }

    /** Textedit Functions **/
    public String featTreesToString() {
        Columns featTreeStr = new Columns().addLine("Index", "Tree Name", "Archetype");
        Collections.sort(trees);
        Feat root;
        for(int i = 0; i < trees.size(); i++) {
            root = trees.get(i).getRoot();
            featTreeStr = featTreeStr.addLine((i + 1) + "", root.getName(), root.getType().toString());
        }
        return featTreeStr.toString();
    }

    public String featResourcesToString() {
        Columns featRecord = new Columns().addLine("Archetype", "Remaining", "|", "Total", "Spent");
        if(MFeats > 0) {
            featRecord = featRecord.addLine("Martial", "" + (MFeats - mspent), "|", MFeats + "", mspent + "");
        }

        if(SFeats > 0) {
            featRecord = featRecord.addLine("Specialist", "" + (SFeats - sspent), "|", SFeats + "", sspent + "");
        }

        if(PFeats > 0) {
            featRecord = featRecord.addLine("Psionic", "" + (PFeats - pspent), "|", PFeats + "", pspent + "");
        }

        if(AFeats > 0) {
            featRecord = featRecord.addLine("Arcane", "" + (AFeats - aspent), "|", AFeats + "", aspent + "");
        }

        if(DFeats > 0) {
            featRecord = featRecord.addLine("Divine", "" + (DFeats - dspent), "|", DFeats + "", dspent + "");
        }

        return featRecord.toString();
    }

}
