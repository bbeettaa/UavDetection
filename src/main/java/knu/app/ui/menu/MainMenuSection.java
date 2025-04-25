package knu.app.ui.menu;

import java.util.List;

public class MainMenuSection {
    private final MenuSection[] sections;
    private final String sectionName;

    public MainMenuSection(String sectionName, MenuSection... sections) {
        this.sections = sections;
        this.sectionName = sectionName;
    }

    public MenuSection[] getSections() {
        return sections;
    }

    public String getSectionName() {
        return sectionName;
    }
}