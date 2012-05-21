package com.dci.intellij.dbn.browser.options.ui;

import com.dci.intellij.dbn.browser.options.DatabaseBrowserFilterSettings;
import com.dci.intellij.dbn.common.options.ui.CompositeConfigurationEditorForm;

import javax.swing.*;
import java.awt.*;

public class DatabaseBrowserFilterSettingsForm extends CompositeConfigurationEditorForm<DatabaseBrowserFilterSettings> {
    private JPanel mainPanel;
    private JPanel visibleObjectTypesPanel;

    public DatabaseBrowserFilterSettingsForm(DatabaseBrowserFilterSettings settings) {
        super(settings);
        visibleObjectTypesPanel.add(settings.getObjectTypeFilterSettings().createComponent(), BorderLayout.CENTER);
    }

    public JComponent getComponent() {
        return mainPanel;
    }
}
