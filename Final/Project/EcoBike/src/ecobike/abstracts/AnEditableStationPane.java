package ecobike.abstracts;

import ecobike.bean.Station;

import javax.swing.*;

public class AnEditableStationPane {

    //private SQLDatabase sql = new SQLDatabase();
    protected JPanel stationInfoPane = new javax.swing.JPanel(null);
    protected JTextField tfID;
    protected JTextField tfName;
    protected JTextField tfAddress;
    protected JTextField tfTotal_dock;

    public void init(AListPaneController stationController) {
        JLabel jLabel3 = new javax.swing.JLabel();

        JLabel lbID = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();

        JLabel lbName = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();

        JLabel lbAddress = new javax.swing.JLabel();
        tfAddress = new javax.swing.JTextField();

        JLabel lbTotal_dock = new javax.swing.JLabel();
        tfTotal_dock = new javax.swing.JTextField();

        stationInfoPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Station Information");
        jLabel3.setBounds(0, 20, 540, 50);
        stationInfoPane.add(jLabel3);

        lbID.setText("ID:");
        lbID.setBounds(20, 100, 140, 40);
        lbID.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfoPane.add(lbID);

        tfID.setBounds(180, 100, 330, 40);
        stationInfoPane.add(tfID);

        lbName.setText("Name:");
        lbName.setBounds(20, 160, 140, 40);
        lbName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfoPane.add(lbName);

        tfName.setBounds(180, 160, 330, 40);
        stationInfoPane.add(tfName);

        lbAddress.setText("Address:");
        lbAddress.setBounds(20, 220, 140, 40);
        lbAddress.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfoPane.add(lbAddress);

        tfAddress.setBounds(180, 220, 330, 40);
        stationInfoPane.add(tfAddress);

        lbTotal_dock.setText("Total_dock:");
        lbTotal_dock.setBounds(20, 280, 140, 40);
        lbTotal_dock.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfoPane.add(lbTotal_dock);

        tfTotal_dock.setBounds(180, 280, 330, 40);
        stationInfoPane.add(tfTotal_dock);

    }

    public Station returnStation() {
        Station station = null;
        station = new Station(Integer.valueOf((tfID.getText())), tfName.getText(), tfAddress.getText(), Integer.valueOf(tfTotal_dock.getText()));

        return station;
    }

    public JPanel getStationInfoPane() {
        return stationInfoPane;
    }
}
