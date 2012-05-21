package com.dci.intellij.dbn.options.ui;

import com.dci.intellij.dbn.common.options.Configuration;
import com.dci.intellij.dbn.common.ui.DBNDialog;
import com.dci.intellij.dbn.common.util.MessageUtil;
import com.dci.intellij.dbn.connection.ConnectionHandler;
import com.dci.intellij.dbn.options.GlobalProjectSettings;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.help.HelpManager;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.util.Alarm;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;

public class GlobalProjectSettingsDialog extends DBNDialog {
    private JButton bApply;
    private GlobalProjectSettings globalSettings;
    private Project project;

    public GlobalProjectSettingsDialog(Project project) {
        super(project, "Settings", true);
        this.project = project;
        setModal(true);
        setResizable(true);
        //setHorizontalStretch(1.5f);

        globalSettings = GlobalProjectSettings.getInstance(project);
        init();
    }

    protected String getDimensionServiceKey() {
        return "DBNavigator.GlobalSettings";
    }

    protected JComponent createCenterPanel() {
        return GlobalProjectSettings.getInstance(project).createCustomComponent();
    }

    public void dispose() {
        super.dispose();
    }

    protected final Action[] createActions() {
        return new Action[]{
                getOKAction(),
                getCancelAction(),
                new ApplyAction(),
                getHelpAction()
        };
    }

    protected JButton createJButtonForAction(Action action) {
        if (action instanceof ApplyAction) {
            bApply = new JButton();
            bApply.setAction(action);
            bApply.setEnabled(false);
            return bApply;
        }
        return super.createJButtonForAction(action);
    }

    public void doCancelAction() {
        globalSettings.reset();
        globalSettings.disposeUIResources();
        super.doCancelAction();
    }

    public void doOKAction() {
        try {
            globalSettings.apply();
            globalSettings.disposeUIResources();
            super.doOKAction();
        } catch (ConfigurationException e) {
            MessageUtil.showErrorDialog(e.getMessage());
        }

    }

    public void doApplyAction() {
        try {
            globalSettings.apply();
            bApply.setEnabled(false);
            setCancelButtonText("Close");
        } catch (ConfigurationException e) {
            MessageUtil.showErrorDialog(e.getMessage(), e.getTitle());
        }
    }

    protected void doHelpAction() {
        HelpManager.getInstance().invokeHelp(globalSettings.getHelpTopic());
    }

    private class ApplyAction extends AbstractAction {
        private Alarm alarm = new Alarm(Alarm.ThreadToUse.SWING_THREAD);
        private Runnable reloader = new Runnable() {
            public void run() {
                if (isShowing()) {
                    boolean isModified = globalSettings.isModified();
                    bApply.setEnabled(isModified);
                    //setCancelButtonText(isModified ? "Cancel" : "Close");
                    addReloadRequest();
                }
            }
        };

        private void addReloadRequest() {
            alarm.addRequest(reloader, 500, ModalityState.stateForComponent(getWindow()));
        }

        public ApplyAction() {
            putValue(Action.NAME, "Apply");
            putValue(DEFAULT_ACTION, Boolean.FALSE);
            addReloadRequest();
        }

        public void actionPerformed(ActionEvent e) {
            doApplyAction();
        }
    }

    public void focusConnectionSettings(ConnectionHandler connectionHandler) {
        GlobalProjectSettingsEditorForm globalSettingsEditor = globalSettings.getSettingsEditor();
        if (globalSettingsEditor != null) {
            globalSettingsEditor.focusConnectionSettings(connectionHandler);
        }
    }

    public void focusSettings(Configuration configuration) {
        GlobalProjectSettingsEditorForm globalSettingsEditor = globalSettings.getSettingsEditor();
        if (globalSettingsEditor != null) {
            globalSettingsEditor.focusSettingsEditor(configuration);
        }
    }
}
