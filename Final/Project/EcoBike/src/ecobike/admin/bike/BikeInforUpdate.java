package ecobike.admin.bike;

import ecobike.abstracts.AnEditableBikePane;
import ecobike.bean.Bike;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BikeInforUpdate extends AnEditableBikePane {

    public BikeInforUpdate() {
    }

    public void init(Bike bike) {
        super.init();

        tfID.setEditable(false);

        tfID.setText(Integer.toString(bike.getBikeID()));
        tfName.setText(bike.getBikeName());
        bikeType.setSelectedItem(bike.getClass().getSimpleName());
        tfLicense.setText(bike.getLicensePlate());
        tfWeight.setText(Float.toString(bike.getWeight()));

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(bike.getManufacturingDate());
            manuDate.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tfProducer.setText(bike.getProducer());

        stationID.setSelectedItem(Integer.toString(bike.getStationID()));

        JButton btnBack = new javax.swing.JButton();
        btnBack.setText("Back");
        btnBack.setBounds(100, 580, 140, 40);
        btnBack.addActionListener(e -> {
            bikeController.getBackToAdminScreen();
        });

        JButton btnUpdate = new javax.swing.JButton();
        btnUpdate.setText("Update");
        btnUpdate.setBounds(280, 580, 140, 40);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bikeController.updateBike(bike);
            }
        });

        bikeInfoPane.add(btnBack);
        bikeInfoPane.add(btnUpdate);
    }
}
