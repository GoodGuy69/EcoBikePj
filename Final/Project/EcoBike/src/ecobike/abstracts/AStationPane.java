package ecobike.abstracts;

import ecobike.bean.Station;
import ecobike.database.SQLDatabase;

import javax.swing.*;

/**
 *
 * @author duclt
 */
public abstract class AStationPane {

    protected JPanel stationInfor = new javax.swing.JPanel(null);
    //private SQLDatabase sql = new SQLDatabase();

    public AStationPane() {
    }

    public void init(Station station) {
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

        stationInfor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Station Information");
        jLabel3.setBounds(0, 20, 540, 50);
        stationInfor.add(jLabel3);

        lbID.setText("ID:");
        lbID.setBounds(20, 100, 140, 40);
        lbID.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfor.add(lbID);

        tfID.setEditable(false);
        tfID.setBounds(180, 100, 330, 40);
        tfID.setText(Integer.toString(station.getStationID()));
        stationInfor.add(tfID);

        lbName.setText("Name:");
        lbName.setBounds(20, 160, 140, 40);
        lbName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfor.add(lbName);

        tfName.setEditable(false);
        tfName.setBounds(180, 160, 330, 40);
        tfName.setText(station.getStationName());
        stationInfor.add(tfName);

        lbAddress.setText("Address:");
        lbAddress.setBounds(20, 220, 140, 40);
        lbAddress.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfor.add(lbAddress);

        tfAddress.setEditable(false);
        tfAddress.setBounds(180, 220, 330, 40);
        tfAddress.setText(station.getStationAddress());
        stationInfor.add(tfAddress);

        lbNoBike.setText("No Bike:");
        lbNoBike.setBounds(20, 280, 140, 40);
        lbNoBike.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfor.add(lbNoBike);

        tfNoBike.setEditable(false);
        tfNoBike.setBounds(180, 280, 330, 40);
        int countNoBike = SQLDatabase.GetInstance().countBikeInStation("NormalBike", Integer.toString(station.getStationID()));
        tfNoBike.setText(Integer.toString(countNoBike));
        stationInfor.add(tfNoBike);

        lbNoEcoBike.setText("No EcoBike:");
        lbNoEcoBike.setBounds(20, 340, 140, 40);
        lbNoEcoBike.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfor.add(lbNoEcoBike);

        tfNoEcoBike.setEditable(false);
        tfNoEcoBike.setBounds(180, 340, 330, 40);
        int countNoEcoBike = SQLDatabase.GetInstance().countBikeInStation("EcoBike", Integer.toString(station.getStationID()));
        tfNoEcoBike.setText(Integer.toString(countNoEcoBike));
        stationInfor.add(tfNoEcoBike);

        lbNoTwinBike.setText("No TwinBike:");
        lbNoTwinBike.setBounds(20, 400, 140, 40);
        lbNoTwinBike.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfor.add(lbNoTwinBike);

        tfNoTwinBike.setEditable(false);
        tfNoTwinBike.setBounds(180, 400, 330, 40);
        int countNoTwinBike = SQLDatabase.GetInstance().countBikeInStation("TwinBike", Integer.toString(station.getStationID()));
        tfNoTwinBike.setText(Integer.toString(countNoTwinBike));
        stationInfor.add(tfNoTwinBike);

        lbNoEmptyDock.setText("No Empty Dock:");
        lbNoEmptyDock.setBounds(20, 460, 140, 40);
        lbNoEmptyDock.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfor.add(lbNoEmptyDock);

        tfNoEmptyDock.setEditable(false);
        tfNoEmptyDock.setBounds(180, 460, 330, 40);
        int countNoEmptyBike = station.getNoDock() - countNoBike - countNoEcoBike - countNoTwinBike;
        tfNoEmptyDock.setText(Integer.toString(countNoEmptyBike));
        stationInfor.add(tfNoEmptyDock);

    }
}
