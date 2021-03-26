package ecobike.user.station.list;

import ecobike.abstracts.AListPane;

import javax.swing.*;

public class UserStation extends AListPane {

    private JButton btnRent = new JButton();
    private JButton logout = new JButton();
    private String[] colTable = new String[]{"ID", "Name", "Address"};
    private UserStationController userStationController;

    public JButton getBtnRent() {
        return btnRent;
    }

    public void setBtnRent(JButton btnRent) {
        this.btnRent = btnRent;
    }

    public JButton getLogout() {
        return logout;
    }

    public void setLogout(JButton logout) {
        this.logout = logout;
    }

    public UserStation(String title) {
        super(title);
    }

    public void init() {
        super.init();
        userStationController = new UserStationController(this);
        StationTableData tableData = new StationTableData(colTable, table);
        listPane.add(tableData.getjPanel5());
        listPaneController.getDataTable(table, "select * from stations");

        userStationController.stationDetailScreen();

        btnRent.setText("Rent Bike");
        btnRent.setBounds(320, 620, 100, 40);
        listPane.add(btnRent);
        userStationController.btnRentBike();

        logout.setText("Logout");
        logout.setBounds(100, 620, 100, 40);
        listPane.add(logout);
        userStationController.btnLogout();
    }
}
