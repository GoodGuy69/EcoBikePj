package ecobike.admin.dockstation;

import ecobike.abstracts.AListPaneController;
import ecobike.abstracts.AnEditableStationPane;
import ecobike.bean.Station;
import ecobike.database.SQLDatabase;
import ecobike.login.AdminPane;
import ecobike.main.MainController;

import javax.swing.*;

public class DockStationInforUpdate extends AnEditableStationPane {

    public static JPanel pane;

    public DockStationInforUpdate() {
        this.pane = this.getStationInfoPane();
    }

    public void init(AListPaneController stationController, Station station) {
        super.init(stationController);

        tfID.setEditable(false);
        tfID.setText(Integer.toString(station.getStationID()));

        tfName.setText(station.getStationName());
        tfAddress.setText(station.getStationAddress());
        tfTotal_dock.setText(Integer.toString(station.getNoDock()));

        JButton btnBack = new javax.swing.JButton();
        btnBack.setText("Back");
        btnBack.setBounds(100, 580, 140, 40);
        btnBack.addActionListener(e -> {
            AdminPane adminPane = new AdminPane();
            adminPane.init();
            adminPane.setShowIndexTabPanel(1);
            MainController.GetInstance().navigate(adminPane.getPane());

        });

        JButton btnUpdate = new javax.swing.JButton();
        btnUpdate.setText("Update");
        btnUpdate.setBounds(280, 580, 140, 40);
        btnUpdate.addActionListener(e -> {
            Station station1 = returnStation();

            boolean check = SQLDatabase.GetInstance().updateStation(station1);
            if (!check) {
                JOptionPane.showMessageDialog(DockStationInforAdd.pane, "Fail to update!");
            } else {
                JOptionPane.showMessageDialog(DockStationInforAdd.pane, "Success to update!");
            }
            AdminPane adminPane = new AdminPane();
            adminPane.init();
            adminPane.setShowIndexTabPanel(1);
            MainController.GetInstance().navigate(adminPane.getPane());

        });

        stationInfoPane.add(btnBack);
        stationInfoPane.add(btnUpdate);
    }
}
