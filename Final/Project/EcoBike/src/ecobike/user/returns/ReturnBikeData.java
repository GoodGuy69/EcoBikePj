package ecobike.user.returns;

import ecobike.abstracts.ATableData;

import javax.swing.*;


public class ReturnBikeData extends ATableData {

    public ReturnBikeData(String[] col, JTable table) {
        super(col, table);
        table.getColumnModel().getColumn(3).setPreferredWidth(160);
    }
}
