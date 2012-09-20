package com.dci.intellij.dbn.common.ui.table;

import com.dci.intellij.dbn.common.util.CommonUtil;
import com.intellij.openapi.project.Project;

import javax.swing.JTable;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;

public class DBNTable extends JTable {
    private static final int MAX_COLUMN_WIDTH = 300;
    private static final int MIN_COLUMN_WIDTH = 10;
    private Project project;


    public DBNTable(Project project, TableModel tableModel, boolean showHeader) {
        super(tableModel);
        this.project = project;
        setGridColor(Color.LIGHT_GRAY);
        if (!showHeader) {
            getTableHeader().setVisible(false);
            getTableHeader().setPreferredSize(new Dimension(-1, 0));
        }
    }

    public Project getProject() {
        return project;
    }

    public Object getValueAtMouseLocation() {
        Point location = MouseInfo.getPointerInfo().getLocation();
        location.setLocation(location.getX() - getLocationOnScreen().getX(), location.getY() - getLocationOnScreen().getY());
        return getValueAtLocation(location);
    }

    public Object getValueAtLocation(Point point) {
        int columnIndex = columnAtPoint(point);
        int rowIndex = rowAtPoint(point);
        return columnIndex > -1 && rowIndex > -1 ? getModel().getValueAt(rowIndex, columnIndex) : null;
    }

    /*********************************************************
     *                    Cell metrics                       *
     *********************************************************/
    public void accommodateColumnsSize() {
        for (int columnIndex = 0; columnIndex < getColumnCount(); columnIndex++){
            accommodateColumnSize(columnIndex, 22);
        }
    }

    @Override
    public int convertColumnIndexToView(int modelColumnIndex) {
        return super.convertColumnIndexToView(modelColumnIndex);
    }

    @Override
    public int convertColumnIndexToModel(int viewColumnIndex) {
        // table is not scrolling correctly when columns are moved
        if (getTableHeader().getDraggedColumn() != null && CommonUtil.isCalledThrough(BasicTableHeaderUI.MouseInputHandler.class)) {
            return getTableHeader().getDraggedColumn().getModelIndex();
        }
        return super.convertColumnIndexToModel(viewColumnIndex);
    }

    public void accommodateColumnSize(int colIndex, int span) {
        TableColumn column = getColumnModel().getColumn(colIndex);
        int columnIndex = column.getModelIndex();
        int preferredWidth = 0;

        // header
        JTableHeader tableHeader = getTableHeader();
        if (tableHeader != null) {
            Object headerValue = column.getHeaderValue();
            TableCellRenderer headerCellRenderer = column.getHeaderRenderer();
            if (headerCellRenderer == null) headerCellRenderer = tableHeader.getDefaultRenderer();
            Component headerComponent = headerCellRenderer.getTableCellRendererComponent(this, headerValue, false, false, 0, columnIndex);
            if (headerComponent.getPreferredSize().width > preferredWidth)
                preferredWidth = headerComponent.getPreferredSize().width;
        }

        // rows
        for (int rowIndex =0; rowIndex < getModel().getRowCount(); rowIndex++) {
            if (preferredWidth > MAX_COLUMN_WIDTH) {
                break;
            }
            Object value = getModel().getValueAt(rowIndex, columnIndex);
            TableCellRenderer renderer = getCellRenderer(rowIndex, columnIndex);

            if (renderer != null) {
                Component component = renderer.getTableCellRendererComponent(this, value, false, false, rowIndex, columnIndex);
                if (component.getPreferredSize().width > preferredWidth) {
                    preferredWidth = component.getPreferredSize().width;
                }
            }
        }

        if (preferredWidth > MAX_COLUMN_WIDTH) {
            preferredWidth = MAX_COLUMN_WIDTH;
        }

        if (preferredWidth < MIN_COLUMN_WIDTH) {
            preferredWidth = MIN_COLUMN_WIDTH;
        }

        preferredWidth = preferredWidth + span;

        if (column.getPreferredWidth() != preferredWidth)  {
            column.setPreferredWidth(preferredWidth);
        }

    }

    public void selectCell(int rowIndex, int columnIndex) {
        scrollRectToVisible(getCellRect(rowIndex, columnIndex, true));
        setRowSelectionInterval(rowIndex, rowIndex);
        setColumnSelectionInterval(columnIndex, columnIndex);
    }
}