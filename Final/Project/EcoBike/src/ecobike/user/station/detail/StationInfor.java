package ecobike.user.station.detail;

import ecobike.abstracts.AStationPane;
import ecobike.bean.Station;

import javax.swing.*;

public class StationInfor extends AStationPane {

    private JButton btnBack = new JButton();
    private JButton btnViewBike = new JButton();
    private StationInforController stationInforController;

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

    public StationInfor() {
    }

    public void init(Station station) {
        super.init(station);
        stationInforController = new StationInforController(this);
        btnViewBike.setBounds(320, 580, 140, 40);
        btnViewBike.setText("View Bike List");
        stationInfor.add(btnViewBike);
        stationInforController.btnViewBike(station.getStationID());

        btnBack.setBounds(100, 580, 140, 40);
        btnBack.setText("Back");
        stationInfor.add(btnBack);
        stationInforController.btnBack();
    }

    public JPanel getStationInfor() {
        return stationInfor;
    }

    public void setStationInfor(JPanel stationInfor) {
        this.stationInfor = stationInfor;
    }
}
