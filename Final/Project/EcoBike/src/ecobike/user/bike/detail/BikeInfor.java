package ecobike.user.bike.detail;

import ecobike.abstracts.ABikePane;
import ecobike.bean.Bike;

import javax.swing.*;

public class BikeInfor extends ABikePane {

    private BikeInforController bikeInforController;
    private JButton btnBack = new JButton();
    private JButton btnViewBike = new JButton();

    public BikeInfor(String stationID) {
        super(stationID);
    }

    public BikeInfor() {
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    public void setBtnBack(JButton btnBack) {
        this.btnBack = btnBack;
    }

    public JButton getBtnViewBike() {
        return btnViewBike;
    }

    public void setBtnViewBike(JButton btnViewBike) {
        this.btnViewBike = btnViewBike;
    }

    public void init(Bike bike) {
        super.init(bike);
        bikeInforController = new BikeInforController(this);

        btnBack.setText("Back");
        btnBack.setBounds(100, 580, 140, 40);
        bikeInfor.add(btnBack);
        bikeInforController.btnBack(stationID);

        btnViewBike.setText("Rent");
        btnViewBike.setBounds(320, 580, 140, 40);
        bikeInfor.add(btnViewBike);
        bikeInforController.rentBikeScreen(bike.getBikeID());
    }

    public JPanel getStationInfor() {
        return bikeInfor;
    }

    public void setStationInfor(JPanel stationInfor) {
        this.bikeInfor = stationInfor;
    }
}
