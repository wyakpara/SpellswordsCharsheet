//package com.spellswords.charactersheet.logic.aggregate;
//
//import com.spellswords.charactersheet.components.aggregate.Skill;
//import com.spellswords.charactersheet.components.aggregate.SkillTable;
//import com.spellswords.charactersheet.logic.tabs.JavaFxSerializable;
//import javafx.collections.ListChangeListener;
//import javafx.scene.Node;
//
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlType;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@XmlRootElement(name = "skills")
//@XmlType(propOrder = {"skillList"})
//public class SkillTableModel implements JavaFxSerializable<SkillTableModel, SkillTable> {
//
//    List<SkillModel> skillList = new ArrayList<>();
//
//    private SkillTable skillTable;
//
//    @XmlElement(name = "skill")
//    public List<SkillModel> getSkillList() {
//        this.updateSkillList();
//        return skillList;
//    }
//
//    private void updateSkillList() {
//        if (skillList.size() != skillTable.skills.size()) {
//            skillList.stream().forEachOrdered(x -> x.clearBindings());
//            skillList.addAll(skillTable.skills.stream().map(SkillModel::new).collect(Collectors.toList()));
//        }
//    }
//
//    public void setSkillList(List<SkillModel> skills) {
//        this.clearBindings();
//        matchAndBind(skills);
//    }
//
//    private void matchAndBind(List<SkillModel> skills) {
//        for (SkillModel mod : skills) {
//            skillTable.skills.stream().filter((node) -> {
//                boolean isSkill = node instanceof Skill;
//                Skill skill = (Skill) node;
//                String skillName = skill.skillProperty.getValue();
//                String modName = mod.skillName.getValue();
//                if (isSkill && skillName.equals(modName)) return true;
//                else return false;
//            }).findFirst().ifPresent(skill -> mod.bind((Skill) skill));
//            skillList.add(mod);
//
//        }
//    }
//
//    @Override
//    public void copy(SkillTableModel toCopy) {
//        this.clearBindings();
//        matchAndBind(toCopy.skillList);
//
//    }
//
//    @Override
//    public void bind(SkillTable toBind) {
//        toBind.skills.addListener((ListChangeListener<Skill>) obs -> {
//            while (obs.next()) {
//                List<? extends Skill> removed = obs.getRemoved();
//                skillList.forEach(skillModel -> {
//                    removed.contains(skillModel.skill);
//                    skillList.remove(skillModel);
//                });
//                skillList.addAll(obs.getAddedSubList().stream().map(SkillModel::new).collect(Collectors.toList()));
//            }
//        });
//        this.skillTable = toBind;
//        this.updateSkillList();
//
//    }
//
//    @Override
//    public void clearBindings() {
//        for (SkillModel skill : skillList) {
//            skill.clearBindings();
//        }
//        skillList.clear();
//    }
//}
