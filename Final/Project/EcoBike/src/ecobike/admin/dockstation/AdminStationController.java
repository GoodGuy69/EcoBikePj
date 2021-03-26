package ecobike.admin.dockstation;

import ecobike.abstracts.AListPaneController;
import ecobike.bean.Station;
import ecobike.database.SQLDatabase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class AdminStationController extends AListPaneController {

    public AdminStationController() {
        super();
    }

    @Override
    public void getDataTable(JTable table, String query) {
        ArrayList<Station> station = SQLDatabase.GetInstance().getStation(query);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Object[] row = new Object[4];
        for (int i = 0; i < station.size(); i++) {

            row[0] = station.get(i).getStationID();
            row[1] = station.get(i).getStationName();
            row[2] = station.get(i).getStationAddress();
            row[3] = station.get(i).getNoDock();
            model.addRow(row);
        }
    }
}
