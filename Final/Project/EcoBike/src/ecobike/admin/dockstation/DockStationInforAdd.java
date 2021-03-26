package ecobike.admin.dockstation;

import ecobike.abstracts.AListPaneController;
import ecobike.abstracts.AnEditableStationPane;
import ecobike.bean.Station;
import ecobike.database.SQLDatabase;
import ecobike.login.AdminPane;
import ecobike.main.MainController;

import javax.swing.*;

public class DockStationInforAdd extends AnEditableStationPane {

    public static JPanel pane;

    public DockStationInforAdd() {
        this.pane = this.getStationInfoPane();
    }

    public void init(AListPaneController stationController) {
        super.init(stationController);
        tfID.setEditable(false);
        tfID.setText(Integer.toString(SQLDatabase.GetInstance().getNewStationID()));

        JButton btnBack = new javax.swing.JButton();
        btnBack.setText("Back");
        btnBack.setBounds(100, 580, 140, 40);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminPane adminPane = new AdminPane();
                adminPane.init();
                adminPane.setShowIndexTabPanel(1);
                MainController.GetInstance().navigate(adminPane.getPane());
            }
        });

        JButton btnAdd = new javax.swing.JButton();
        btnAdd.setText("Add");
        btnAdd.setBounds(280, 580, 140, 40);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                Station station = returnStation();
                boolean check = SQLDatabase.GetInstance().addStation(station);
                if (!check) {
                    JOptionPane.showMessageDialog(DockStationInforAdd.pane, "Fail to add!");
                } else {
                    JOptionPane.showMessageDialog(DockStationInforAdd.pane, "Success to add!");
                }
                AdminPane adminPane = new AdminPane();
                adminPane.init();
                adminPane.setShowIndexTabPanel(1);
                MainController.GetInstance().navigate(adminPane.getPane());
            }
        });

        stationInfoPane.add(btnBack);
        stationInfoPane.add(btnAdd);
    }

}
