package ecobike.abstracts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AListPaneController {

    public AListPaneController() {
        
    }

    public void getDataTable(JTable table, String query) {
    }

    public void getSearchParam(String search, JTable table) {
        search = search.toLowerCase();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        table.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + search));
    }

    public void getDataTable(JTable table) {
        // TODO Auto-generated method stub
    }

}
