package ecobike.admin.bike;

import ecobike.abstracts.AnEditableBikePane;
import ecobike.database.SQLDatabase;

import javax.swing.*;

public class BikeInforAdd extends AnEditableBikePane {

    public BikeInforAdd() {
    }

    public void init() {
        super.init();

        tfID.setEditable(false);
        tfID.setText(Integer.toString(SQLDatabase.GetInstance().getNewBikeID()));

        JButton btnBack = new javax.swing.JButton();
        btnBack.setText("Back");
        btnBack.setBounds(100, 580, 140, 40);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bikeController.getBackToAdminScreen();
            }
        });

        JButton btnAdd = new javax.swing.JButton();
        btnAdd.setText("Add");
        btnAdd.setBounds(280, 580, 140, 40);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bikeController.addBike();
            }
        });

        bikeInfoPane.add(btnBack);
        bikeInfoPane.add(btnAdd);
    }

}
