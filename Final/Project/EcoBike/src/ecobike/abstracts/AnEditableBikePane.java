package ecobike.abstracts;

import com.toedter.calendar.JDateChooser;
import ecobike.admin.bike.AdminBikeController;
import ecobike.bean.Bike;
import ecobike.bean.EcoBike;
import ecobike.bean.NormalBike;
import ecobike.bean.TwinBike;
import ecobike.database.SQLDatabase;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AnEditableBikePane {

    protected AdminBikeController bikeController;

    protected JPanel bikeInfoPane = new javax.swing.JPanel(null);
    protected JTextField tfID;
    protected JTextField tfName;
    protected JComboBox bikeType;
    protected JTextField tfLicense;
    protected JTextField tfWeight;
    protected JDateChooser manuDate;
    protected JTextField tfProducer;
    protected JComboBox stationID;

    public void setBikeController(AdminBikeController bikeController) {
        this.bikeController = bikeController;
    }

    @SuppressWarnings("unchecked")
    public void init() {
        JLabel jLabel3 = new javax.swing.JLabel();

        JLabel lbID = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();

        JLabel lbName = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();

        JLabel lbType = new javax.swing.JLabel();
        String[] typeString = {"NormalBike", "EcoBike", "TwinBike"};
        bikeType = new JComboBox(typeString);

        JLabel lbLicense = new javax.swing.JLabel();
        tfLicense = new javax.swing.JTextField();

        JLabel lbWeight = new javax.swing.JLabel();
        tfWeight = new javax.swing.JTextField();

        JLabel lbManuDate = new javax.swing.JLabel();
        Date date;
        manuDate = new JDateChooser();
        manuDate.setDateFormatString("yyyy-MM-dd");

        JLabel lbProducer = new javax.swing.JLabel();
        tfProducer = new javax.swing.JTextField();

        JLabel lbStationID = new javax.swing.JLabel();

        ArrayList<String> allStationID = SQLDatabase.GetInstance().getEmptyStationID();
        String[] allStationIDString = new String[allStationID.size()];

        allStationIDString = allStationID.toArray(allStationIDString);

        stationID = new JComboBox(allStationIDString);

        bikeInfoPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Bike Information");
        jLabel3.setBounds(0, 20, 540, 50);
        bikeInfoPane.add(jLabel3);

        lbID.setText("ID:");
        lbID.setBounds(20, 100, 140, 40);
        lbID.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        bikeInfoPane.add(lbID);

        tfID.setBounds(180, 100, 330, 40);
        bikeInfoPane.add(tfID);

        lbName.setText("Name:");
        lbName.setBounds(20, 160, 140, 40);
        lbName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        bikeInfoPane.add(lbName);

        tfName.setBounds(180, 160, 330, 40);
        bikeInfoPane.add(tfName);

        lbType.setText("Type:");
        lbType.setBounds(20, 220, 140, 40);
        lbType.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        bikeInfoPane.add(lbType);

        bikeType.setBounds(180, 220, 330, 40);
        bikeInfoPane.add(bikeType);

        lbLicense.setText("License Plate:");
        lbLicense.setBounds(20, 280, 140, 40);
        lbLicense.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        bikeInfoPane.add(lbLicense);

        tfLicense.setBounds(180, 280, 330, 40);
        bikeInfoPane.add(tfLicense);

        lbWeight.setText("Weight:");
        lbWeight.setBounds(20, 340, 140, 40);
        lbWeight.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        bikeInfoPane.add(lbWeight);

        tfWeight.setBounds(180, 340, 330, 40);
        bikeInfoPane.add(tfWeight);

        lbManuDate.setText("<html> Manufacturing Date: </html>");
        lbManuDate.setBounds(20, 400, 140, 40);
        lbManuDate.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        bikeInfoPane.add(lbManuDate);

        manuDate.setBounds(180, 400, 330, 40);
        bikeInfoPane.add(manuDate);

        lbProducer.setText("Producer:");
        lbProducer.setBounds(20, 460, 140, 40);
        lbProducer.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        bikeInfoPane.add(lbProducer);

        tfProducer.setBounds(180, 460, 330, 40);
        bikeInfoPane.add(tfProducer);

        lbStationID.setText("StationID:");
        lbStationID.setBounds(20, 520, 140, 40);
        lbStationID.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        bikeInfoPane.add(lbStationID);

        stationID.setBounds(180, 520, 330, 40);
        bikeInfoPane.add(stationID);

    }

    public Bike returnBike() {
        Bike bike = null;

        switch (bikeType.getSelectedItem().toString()) {
            case "NormalBike":
                bike = new NormalBike(Integer.parseInt(tfID.getText()), tfName.getText(), Float.parseFloat(tfWeight.getText()), tfLicense.getText(), new SimpleDateFormat("yyyy-MM-dd").format(manuDate.getDate()), tfProducer.getText(), Integer.parseInt(stationID.getSelectedItem().toString()));
                break;
            case "EcoBike":
                bike = new EcoBike(Integer.parseInt(tfID.getText()), tfName.getText(), Float.parseFloat(tfWeight.getText()), tfLicense.getText(), new SimpleDateFormat("yyyy-MM-dd").format(manuDate.getDate()), tfProducer.getText(), Integer.parseInt(stationID.getSelectedItem().toString()));
                break;
            case "TwinBike":
                bike = new TwinBike(Integer.parseInt(tfID.getText()), tfName.getText(), Float.parseFloat(tfWeight.getText()), tfLicense.getText(), new SimpleDateFormat("yyyy-MM-dd").format(manuDate.getDate()), tfProducer.getText(), Integer.parseInt(stationID.getSelectedItem().toString()));
                break;
        }
        return bike;
    }

    public JPanel getBikeInfoPane() {
        return bikeInfoPane;
    }

    public JTextField getTfID() {
        return tfID;
    }

    public JTextField getTfName() {
        return tfName;
    }

    public JComboBox getBikeType() {
        return bikeType;
    }

    public JTextField getTfLicense() {
        return tfLicense;
    }

    public JTextField getTfWeight() {
        return tfWeight;
    }

    public JDateChooser getManuDate() {
        return manuDate;
    }

    public JTextField getTfProducer() {
        return tfProducer;
    }

    public JComboBox getStationID() {
        return stationID;
    }

}
