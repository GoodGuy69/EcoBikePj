package ecobike.user.bike.list;

import ecobike.abstracts.AListPane;
import ecobike.user.bike.detail.BikeTableData;

import javax.swing.*;

public class UserBike extends AListPane {

    private JButton btnRent = new JButton();
    private JButton btnBack = new JButton();
    private String id;
    private String[] colTable = new String[]{"ID", "Type", "License Plate", "Producer"};
    private UserBikeController userBikeController;

    public UserBike(String title, String id) {
        super(title);
        this.id = id;
    }

    public JButton getBtnRent() {
        return btnRent;
    }

    public void setBtnRent(JButton btnRent) {
        this.btnRent = btnRent;
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    public void setBtnBack(JButton btnBack) {
        this.btnBack = btnBack;
    }

    public void init() {
        super.init();
        userBikeController = new UserBikeController(this);
        BikeTableData tableData = new BikeTableData(colTable, table);
        listPane.add(tableData.getjPanel5());
        listPaneController.getDataTable(table, "select * from bikes where SID =" + id);
        userBikeController.bikeDetailScreen(id);

        btnRent.setText("Rent Bike");
        btnRent.setBounds(320, 620, 100, 40);
        listPane.add(btnRent);
        userBikeController.rentBikeScreen();

        btnBack.setText("Back");
        btnBack.setBounds(100, 620, 100, 40);
        listPane.add(btnBack);
        userBikeController.btnBack(id);

    }

    public JPanel getUserStation() {
        return listPane;
    }

    public void setUserStation(JPanel userStation) {
        this.listPane = userStation;
    }
}
