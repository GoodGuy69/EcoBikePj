package ecobike.user.bike.detail;

import ecobike.abstracts.ATableData;

import javax.swing.*;

public class BikeTableData extends ATableData {

    public BikeTableData(String[] col, JTable table) {
        super(col, table);
        table.getColumnModel().getColumn(2).setPreferredWidth(220);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
    }

}
