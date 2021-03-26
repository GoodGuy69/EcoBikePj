package ecobike.login;

import ecobike.admin.bike.AdminBike;
import ecobike.admin.bike.AdminBikeController;
import ecobike.admin.dockstation.AdminDockStation;
import ecobike.admin.dockstation.AdminStationController;

import javax.swing.*;
import java.awt.*;

public class AdminPane {

    protected JPanel rootPane = new JPanel();
    protected JTabbedPane tabbedPane = new JTabbedPane();
    protected AdminBikeController bikeController;

    AdminBike bikePane;

    private int stationIndex;
    private int bikeIndex;

    public AdminPane() {
        bikeController = new AdminBikeController("Bikes");
        bikePane = bikeController.getBikePane();
    }

    public void init() {
        bikePane.init();

        BorderLayout layout = new BorderLayout();

        rootPane.setLayout(layout);
        rootPane.add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.addTab("Bikes", null, bikePane.getListPane(), "Bikes");
        bikeIndex = 0;

        AdminDockStation adminStation = new AdminDockStation("Dock station");
        adminStation.setListPaneController(new AdminStationController());
        adminStation.init();
        tabbedPane.addTab("Stations", null, adminStation.getListPane(), "Stations");
        stationIndex = 1;
    }

    public JPanel getPane() {
        return rootPane;
    }

    public void switchToBikeTab() {
        tabbedPane.setSelectedIndex(bikeIndex);
    }

    public void setShowIndexTabPanel(int x) {
        tabbedPane.setSelectedIndex(x);
    }
}
