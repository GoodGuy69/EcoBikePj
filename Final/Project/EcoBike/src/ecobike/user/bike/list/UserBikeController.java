package ecobike.user.bike.list;

import ecobike.abstracts.AListPaneController;
import ecobike.bean.Bike;
import ecobike.bean.Station;
import ecobike.database.SQLDatabase;
import ecobike.main.MainController;
import ecobike.user.bike.detail.BikeInfor;
import ecobike.user.rent.RentBike;
import ecobike.user.station.detail.StationInfor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class UserBikeController extends AListPaneController {

    private UserBike userBike;

    public UserBikeController(UserBike userBike) {
        this.userBike = userBike;
    }

    @Override
    public void getDataTable(JTable table, String query) {
        ArrayList<Bike> station = SQLDatabase.GetInstance().getAllBike(query);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Object[] row = new Object[4];
        for (int i = 0; i < station.size(); i++) {

            row[0] = station.get(i).getBikeID();
            row[1] = station.get(i).getClass().getSimpleName();
            row[2] = station.get(i).getLicensePlate();
            row[3] = station.get(i).getProducer();
            model.addRow(row);
        }
    }

    public void bikeDetailScreen(String id) {
        JTable table = userBike.getTable();
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int selectRow = table.getSelectedRow();
                Bike bike = SQLDatabase.GetInstance().getABike(model.getValueAt(selectRow, 0).toString());
                BikeInfor bikeInfor = new BikeInfor(id);
                bikeInfor.init(bike);
                MainController.GetInstance().navigate(bikeInfor.getStationInfor());
            }
        });
    }

    public void rentBikeScreen() {
        userBike.getBtnRent().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RentBike rentBike = new RentBike(0);
                rentBike.init();
                MainController.GetInstance().navigate(rentBike.getStationInfor());
            }
        });
    }

    public void btnBack(String id) {
        userBike.getBtnBack().addActionListener(e -> {
            Station station = SQLDatabase.GetInstance().getAStation(id);
            StationInfor stationInfor = new StationInfor();
            stationInfor.init(station);
            MainController.GetInstance().navigate(stationInfor.getStationInfor());

        });
    }
}
