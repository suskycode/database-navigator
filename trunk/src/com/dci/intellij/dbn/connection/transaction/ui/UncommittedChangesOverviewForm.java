package com.dci.intellij.dbn.connection.transaction.ui;

import com.dci.intellij.dbn.browser.DatabaseBrowserManager;
import com.dci.intellij.dbn.common.event.EventManager;
import com.dci.intellij.dbn.common.thread.SimpleLaterInvocator;
import com.dci.intellij.dbn.common.ui.UIForm;
import com.dci.intellij.dbn.common.ui.UIFormImpl;
import com.dci.intellij.dbn.connection.ConnectionHandler;
import com.dci.intellij.dbn.connection.ConnectionManager;
import com.dci.intellij.dbn.connection.transaction.TransactionAction;
import com.dci.intellij.dbn.connection.transaction.TransactionListener;
import com.dci.intellij.dbn.connection.transaction.UncommittedChangeBundle;
import com.intellij.openapi.project.Project;
import com.intellij.ui.ColoredListCellRenderer;
import com.intellij.ui.GuiUtils;
import com.intellij.ui.SimpleTextAttributes;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UncommittedChangesOverviewForm extends UIFormImpl implements UIForm, TransactionListener {
    private JPanel mainPanel;
    private JPanel actionsPanel;
    private JPanel detailsPanel;
    private JList connectionsList;
    private List<ConnectionHandler> connectionHandlers = new ArrayList<ConnectionHandler>();

    private Map<ConnectionHandler, UncommittedChangesForm> uncommittedChangeForms = new HashMap<ConnectionHandler, UncommittedChangesForm>();
    private Project project;

    public UncommittedChangesOverviewForm(Project project) {
        this.project = project;
        DefaultListModel model = new DefaultListModel();

        DatabaseBrowserManager browserManager = DatabaseBrowserManager.getInstance(project);
        for (ConnectionManager connectionManager : browserManager.getConnectionManagers()) {
            for (ConnectionHandler connectionHandler : connectionManager.getConnectionHandlers()) {
                if (connectionHandler.hasUncommittedChanges()) {
                    connectionHandlers.add(connectionHandler);
                    model.addElement(connectionHandler);
                }
            }
        }

        GuiUtils.replaceJSplitPaneWithIDEASplitter(mainPanel);
        connectionsList.setModel(model);
        connectionsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ConnectionHandler connectionHandler = (ConnectionHandler) connectionsList.getSelectedValue();
                showChangesForm(connectionHandler);
            }
        });
        connectionsList.setCellRenderer(new ListCellRenderer());
        connectionsList.setSelectedIndex(0);

        EventManager.subscribe(project, TransactionListener.TOPIC, this);
    }

    public boolean hasUncommittedChanges() {
        for (ConnectionHandler connectionHandler : connectionHandlers) {
            if (connectionHandler.hasUncommittedChanges()) {
                return true;
            }
        }
        return false;
    }

    public JPanel getComponent() {
        return mainPanel;
    }

    public void dispose() {
        super.dispose();
        EventManager.unsubscribe(project, this);
        connectionHandlers = null;
    }

    public List<ConnectionHandler> getConnectionHandlers (){
        return connectionHandlers;
    }

    public void showChangesForm(ConnectionHandler connectionHandler) {
        detailsPanel.removeAll();
        if (connectionHandler != null) {
            UncommittedChangesForm uncommittedChangesForm = uncommittedChangeForms.get(connectionHandler);
            if (uncommittedChangesForm == null) {
                uncommittedChangesForm = new UncommittedChangesForm(connectionHandler, null, true);
                uncommittedChangeForms.put(connectionHandler, uncommittedChangesForm);
            }
            detailsPanel.add(uncommittedChangesForm.getComponent(), BorderLayout.CENTER);
        }
        detailsPanel.updateUI();
    }

    private class ListCellRenderer extends ColoredListCellRenderer {

        @Override
        protected void customizeCellRenderer(JList list, Object value, int index, boolean selected, boolean hasFocus) {
            ConnectionHandler connectionHandler = (ConnectionHandler) value;
            setIcon(connectionHandler.getIcon());
            append(connectionHandler.getName(), SimpleTextAttributes.REGULAR_ATTRIBUTES);
            UncommittedChangeBundle uncommittedChanges = connectionHandler.getUncommittedChanges();
            int changes = uncommittedChanges == null ? 0 : uncommittedChanges.size();
            append(" (" + changes + ")", SimpleTextAttributes.GRAY_ATTRIBUTES);

        }
    }

    /********************************************************
     *                Transaction Listener                  *
     ********************************************************/
    @Override
    public void beforeAction(ConnectionHandler connectionHandler, TransactionAction action) throws SQLException {
    }

    @Override
    public void afterAction(ConnectionHandler connectionHandler, TransactionAction action, boolean succeeded) throws SQLException {
        refreshForm();
    }

    private void refreshForm() {
        new SimpleLaterInvocator() {
            @Override
            public void run() {
                if (!isDisposed()) {
                    connectionsList.repaint();
                }
            }
        }.start();

    }
}
