package ecobike.admin.dockstation;

import ecobike.abstracts.ATableData;

import javax.swing.*;

public class StationTablePane extends ATableData {

    public StationTablePane(String[] col, JTable table) {
        super(col, table);
        table.getColumnModel().getColumn(2).setPreferredWidth(220);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
    }

}
