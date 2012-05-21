package com.dci.intellij.dbn.editor.data.structure;

import com.dci.intellij.dbn.browser.model.BrowserTreeElement;
import com.dci.intellij.dbn.common.Icons;
import com.dci.intellij.dbn.language.psql.structure.PSQLStructureViewElement;
import com.intellij.ide.util.treeView.smartTree.ActionPresentation;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;
import java.util.Comparator;

public class DatasetEditorStructureViewModelSorter implements Sorter {

    public Comparator getComparator() {
        return COMPARATOR;    
    }

    public boolean isVisible() {
        return true;
    }

    @NotNull
    public ActionPresentation getPresentation() {
        return ACTION_PRESENTATION;
    }

    @NotNull
    public String getName() {
        return "Sort by Name";
    }

    private static final ActionPresentation ACTION_PRESENTATION = new ActionPresentation() {
        public String getText() {
            return "Sort by name";
        }

        public String getDescription() {
            return "Sort elements alphabetically by name";
        }

        public Icon getIcon() {
            return Icons.COMMON_ALPHABETIC_SORTING;
        }
    };

    private static final Comparator COMPARATOR = new Comparator() {
        public int compare(Object object1, Object object2) {
            if (object1 instanceof DatasetEditorStructureViewElement && object2 instanceof DatasetEditorStructureViewElement) {
                DatasetEditorStructureViewElement structureViewElement1 = (DatasetEditorStructureViewElement) object1;
                DatasetEditorStructureViewElement structureViewElement2 = (DatasetEditorStructureViewElement) object2;
                BrowserTreeElement treeElement1 = structureViewElement1.getValue();
                BrowserTreeElement treeElement2 = structureViewElement2.getValue();
                return treeElement1.getName().compareTo(treeElement2.getName());
            } else {
                return object1 instanceof PSQLStructureViewElement ? 1 : -1;
            }
        }
    };
}
