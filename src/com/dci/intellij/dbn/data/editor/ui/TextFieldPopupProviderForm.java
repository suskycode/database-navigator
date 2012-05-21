package com.dci.intellij.dbn.data.editor.ui;

import com.dci.intellij.dbn.common.thread.ConditionalLaterInvocator;
import com.dci.intellij.dbn.common.ui.KeyUtil;
import com.dci.intellij.dbn.common.ui.UIForm;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.Shortcut;
import com.intellij.openapi.keymap.KeymapUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopup;
import org.jetbrains.annotations.Nullable;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public abstract class TextFieldPopupProviderForm extends KeyAdapter implements UIForm{
    public static final Border EMPTY_BORDER = new EmptyBorder(2, 2, 2, 2);

    private TextFieldWithPopup textField;
    private boolean isAutoPopup;
    private boolean isEnabled = true;
    private JBPopup popup;
    private Set<AnAction> actions = new HashSet<AnAction>();
    private boolean disposed;

    protected TextFieldPopupProviderForm(TextFieldWithPopup textField, boolean isAutoPopup) {
        this.textField = textField;
        this.isAutoPopup = isAutoPopup;
    }

    public TextFieldWithPopup getTextEditor() {
        return textField;
    }

    public JTextField getTextField() {
        return textField.getTextField();
    }

    public Project getProject() {
        return textField.getProject();
    }

    public JBPopup getPopup() {
        return popup;
    }

    /**
     * Create the popup and return it.
     * If the popup shouldn't show-up for some reason (e.g. empty completion list),
     * than this method should return null
     */
    @Nullable
    public abstract JBPopup createPopup();
    public abstract void handleKeyPressedEvent(KeyEvent e);
    public abstract void handleKeyReleasedEvent(KeyEvent e);
    public abstract void handleFocusLostEvent(FocusEvent e);
    public abstract String getKeyShortcutName();
    public abstract String getDescription();
    public abstract TextFieldPopupType getPopupType();
    public String getKeyShortcutDescription() {
        Shortcut[] shortcuts = KeyUtil.getShortcuts(getKeyShortcutName());
        return KeymapUtil.getShortcutsText(shortcuts);
    }

    public boolean isAutoPopup() {
        return isAutoPopup;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void registerAction(AnAction action) {
        actions.add(action);
    }

    public void keyPressed(KeyEvent e) {
        for (AnAction action : actions) {
            if (KeyUtil.match(action.getShortcutSet().getShortcuts(), e)) {
                DataContext dataContext = DataManager.getInstance().getDataContext(getComponent());
                ActionManager actionManager = ActionManager.getInstance();
                AnActionEvent actionEvent = new AnActionEvent(null, dataContext, "", action.getTemplatePresentation(), actionManager, 2);
                action.actionPerformed(actionEvent);
                e.consume();
                return;
            }
        }
    }

    public Set<AnAction> getActions() {
        return actions;
    }

    public boolean matchesKeyEvent(KeyEvent keyEvent) {
        Shortcut[] shortcuts = KeyUtil.getShortcuts(getKeyShortcutName());
        return KeyUtil.match(shortcuts, keyEvent);
    }

    public void showPopup() {
        new ConditionalLaterInvocator(){
            public void run() {
                if (!isShowingPopup()) {
                    popup = createPopup();
                    if (popup != null) {
                        JPanel panel = (JPanel) popup.getContent();
                        panel.setBorder(new LineBorder(Color.DARK_GRAY));

                        textField.clearSelection();

                        if (textField.isShowing()) {
                            Point location = textField.getLocationOnScreen();
                            location.setLocation(location.getX() + 4 , location.getY() + textField.getHeight() + 4);
                            popup.showInScreenCoordinates(textField, location);
                            //cellEditor.highlight(TextCellEditor.HIGHLIGHT_TYPE_POPUP);
                        }
                    }
                }
            }
        }.start();
    }

    protected void disposePopup() {
        if (isShowingPopup()) {
            popup.cancel();
            popup = null;
        }
    }

    protected boolean isShowingPopup() {
        return popup != null && popup.isVisible();
    }

    @Override
    public void dispose() {
        disposed = true;
    }

    @Override
    public boolean isDisposed() {
        return disposed;
    }
}
