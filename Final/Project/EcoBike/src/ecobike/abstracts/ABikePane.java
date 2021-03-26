package ecobike.abstracts;

import ecobike.bean.Bike;

import javax.swing.*;

public abstract class ABikePane {

    protected JPanel bikeInfor = new javax.swing.JPanel(null);
    protected String stationID;

    public ABikePane(String stationID) {
        this.stationID = stationID;
    }

    public ABikePane() {

    }

    public void init(Bike bike) {
        JLabel jLabel3 = new javax.swing.JLabel();

        JLabel lbID = new javax.swing.JLabel();
        JTextField tfID = new javax.swing.JTextField();
        JLabel lbName = new javax.swing.JLabel();
        JTextField tfName = new javax.swing.JTextField();
        JLabel lbAddress = new javax.swing.JLabel();
        JTextField tfAddress = new javax.swing.JTextField();
        JLabel lbNoBike = new javax.swing.JLabel();
        JTextField tfNoBike = new javax.swing.JTextField();
        JLabel lbNoEcoBike = new javax.swing.JLabel();
        JTextField tfNoEcoBike = new javax.swing.JTextField();
        JLabel lbNoTwinBike = new javax.swing.JLabel();
        JTextField tfNoTwinBike = new javax.swing.JTextField();
        JLabel lbNoEmptyDock = new javax.swing.JLabel();
        JTextField tfNoEmptyDock = new javax.swing.JTextField();

        bikeInfor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Bike Information");
        jLabel3.setBounds(0, 20, 540, 50);
        bikeInfor.add(jLabel3);

        lbID.setText("ID:");
        lbID.setBounds(20, 100, 140, 40);
        lbID.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        bikeInfor.add(lbID);

        tfID.setEditable(false);
        tfID.setBounds(180, 100, 330, 40);
        tfID.setText(Integer.toString(bike.getBikeID()));
        bikeInfor.add(tfID);

        lbName.setText("Name:");
        lbName.setBounds(20, 160, 140, 40);
        lbName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        bikeInfor.add(lbName);

        tfName.setEditable(false);
        tfName.setBounds(180, 160, 330, 40);
        tfName.setText(bike.getBikeName());
        bikeInfor.add(tfName);

        lbAddress.setText("Type:");
        lbAddress.setBounds(20, 220, 140, 40);
        lbAddress.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        bikeInfor.add(lbAddress);

        tfAddress.setEditable(false);
        tfAddress.setBounds(180, 220, 330, 40);
        tfAddress.setText(bike.getClass().getSimpleName());
        bikeInfor.add(tfAddress);

        lbNoBike.setText("License plate:");
        lbNoBike.setBounds(20, 280, 140, 40);
        lbNoBike.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        bikeInfor.add(lbNoBike);

        tfNoBike.setEditable(false);
        tfNoBike.setBounds(180, 280, 330, 40);
        tfNoBike.setText(bike.getLicensePlate());
        bikeInfor.add(tfNoBike);

        lbNoEcoBike.setText("Weight:");
        lbNoEcoBike.setBounds(20, 340, 140, 40);
        lbNoEcoBike.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        bikeInfor.add(lbNoEcoBike);

        tfNoEcoBike.setEditable(false);
        tfNoEcoBike.setBounds(180, 340, 330, 40);
        tfNoEcoBike.setText(Float.toString(bike.getWeight()));
        bikeInfor.add(tfNoEcoBike);

        lbNoTwinBike.setText("<html>Manufacturing Date:</html>");
        lbNoTwinBike.setBounds(20, 400, 140, 40);
        lbNoTwinBike.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        bikeInfor.add(lbNoTwinBike);

        tfNoTwinBike.setEditable(false);
        tfNoTwinBike.setBounds(180, 400, 330, 40);
        tfNoTwinBike.setText(bike.getManufacturingDate());
        bikeInfor.add(tfNoTwinBike);

        lbNoEmptyDock.setText("Producer:");
        lbNoEmptyDock.setBounds(20, 460, 140, 40);
        lbNoEmptyDock.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        bikeInfor.add(lbNoEmptyDock);

        tfNoEmptyDock.setEditable(false);
        tfNoEmptyDock.setBounds(180, 460, 330, 40);
        tfNoEmptyDock.setText(bike.getProducer());
        bikeInfor.add(tfNoEmptyDock);

    }

}
