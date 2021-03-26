package ecobike.user.station.list;

import ecobike.abstracts.AListPaneController;
import ecobike.bean.Station;
import ecobike.database.SQLDatabase;
import ecobike.login.HomePanel;
import ecobike.main.MainController;
import ecobike.user.rent.RentBike;
import ecobike.user.station.detail.StationInfor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class UserStationController extends AListPaneController {

    private UserStation userStation;

    public UserStationController(UserStation userStation) {
        this.userStation = userStation;
    }

    @Override
    public void getDataTable(JTable table, String query) {
        ArrayList<Station> station = SQLDatabase.GetInstance().getStation(query);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Object[] row = new Object[3];
        for (int i = 0; i < station.size(); i++) {

            row[0] = station.get(i).getStationID();
            row[1] = station.get(i).getStationName();
            row[2] = station.get(i).getStationAddress();
            model.addRow(row);
        }
    }

    public void stationDetailScreen() {
        JTable table = userStation.getTable();
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int selectRow = table.getSelectedRow();
                Station station = new SQLDatabase().getAStation(model.getValueAt(selectRow, 0).toString());
                StationInfor stationInfor1 = new StationInfor();
                stationInfor1.init(station);
                MainController.GetInstance().navigate(stationInfor1.getStationInfor());
            }
        });
    }

    public void btnRentBike() {
        userStation.getBtnRent().addActionListener(e -> {
            RentBike rentBike = new RentBike(0);
            rentBike.init();
            MainController.GetInstance().navigate(rentBike.getStationInfor());

        });
    }

    public void btnLogout() {
        userStation.getLogout().addActionListener(e -> {
            HomePanel loginScreen = new HomePanel();
            loginScreen.init();
            MainController.GetInstance().navigate(loginScreen.homescreen);

        });
    }
}
