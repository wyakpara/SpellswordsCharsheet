package com.spellswords.charactersheet;

import com.spellswords.charactersheet.components.tabs.*;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TabInfo<V, T> {

    String name;
    FxmlControl<V, T> control;
    static List<TabInfo> tabs = new ArrayList<>();

    private TabInfo() {
    }

    static {
        try {
            tabs.add(new TabInfo<VitalsTab, FlowPane>()
                    .name("Vitals")
                    .control("tabs/Vitals.fxml"));
            tabs.add(new TabInfo<FeatsAndAbilitiesTab, FlowPane>()
                    .name("Feats & Abilities")
                    .control("tabs/FeatsAndAbilities.fxml"));
            tabs.add(new TabInfo<ArmoryTab, FlowPane>()
                    .name("Armory")
                    .control("tabs/Armory.fxml"));
            tabs.add(new TabInfo<InventoryTab, FlowPane>()
                    .name("Inventory")
                    .control("tabs/Inventory.fxml"));
            tabs.add(new TabInfo<StorageTab, FlowPane>()
                    .name("Storage")
                    .control("tabs/Storage.fxml"));
            tabs.add(new TabInfo<PowerListTab, FlowPane>()
                    .name("PowerList")
                    .control("tabs/PowerList.fxml"));
            tabs.add(new TabInfo<PowersTab, FlowPane>()
                    .name("Powers")
                    .control("tabs/Powers.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public TabInfo name(String name) {
        this.name = name;
        return this;
    }

    public TabInfo control(String fxml) throws IOException {
        this.control = new FxmlControl<>(fxml);
        return this;
    }
}
