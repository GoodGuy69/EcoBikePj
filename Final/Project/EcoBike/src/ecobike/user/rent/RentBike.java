package ecobike.user.rent;

import ecobike.bean.Account;
import ecobike.database.SQLDatabase;
import ecobike.main.MainController;

import javax.swing.*;

public class RentBike {

    public JPanel stationInfor = new JPanel(null);
    private int bike;
//    private SQLDatabase sql = new SQLDatabase();
    private RentBikeController rentBikeController;

    private JTextField tfID = new JTextField();
    private JTextField tfType = new JTextField();
    private JTextField tfCardNumber = new JTextField();
    private JTextField tfLicense = new JTextField();
    private JTextField tfStart = new JTextField();
    private JTextField tfEnd = new JTextField();
    private JTextField tfTime = new JTextField();
    private JTextField tfPrice = new JTextField();
    private JTextField tfBalance = new JTextField();
    private JButton btnBack = new JButton();
    private JButton btnRentBike = new JButton();
    private JButton btnReturnBike = new JButton();
    private JButton btnSetBikeID = new JButton();
    private JButton btnSetCardNumber = new JButton();

    public JTextField getTfID() {
        return tfID;
    }

    public void setTfID(JTextField tfID) {
        this.tfID = tfID;
    }

    public JTextField getTfType() {
        return tfType;
    }

    public void setTfType(JTextField tfType) {
        this.tfType = tfType;
    }

    public JTextField getTfCardNumber() {
        return tfCardNumber;
    }

    public void setTfCardNumber(JTextField tfCardNumber) {
        this.tfCardNumber = tfCardNumber;
    }

    public JTextField getTfLicense() {
        return tfLicense;
    }

    public void setTfLicense(JTextField tfLicense) {
        this.tfLicense = tfLicense;
    }

    public JTextField getTfStart() {
        return tfStart;
    }

    public void setTfStart(JTextField tfStart) {
        this.tfStart = tfStart;
    }

    public JTextField getTfEnd() {
        return tfEnd;
    }

    public void setTfEnd(JTextField tfEnd) {
        this.tfEnd = tfEnd;
    }

    public JTextField getTfTime() {
        return tfTime;
    }

    public void setTfTime(JTextField tfTime) {
        this.tfTime = tfTime;
    }

    public JTextField getTfPrice() {
        return tfPrice;
    }

    public void setTfPrice(JTextField tfPrice) {
        this.tfPrice = tfPrice;
    }

    public JTextField getTfBalance() {
        return tfBalance;
    }

    public void setTfBalance(JTextField tfBalance) {
        this.tfBalance = tfBalance;
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    public void setBtnBack(JButton btnBack) {
        this.btnBack = btnBack;
    }

    public JButton getBtnRentBike() {
        return btnRentBike;
    }

    public void setBtnRentBike(JButton btnRentBike) {
        this.btnRentBike = btnRentBike;
    }

    public JButton getBtnReturnBike() {
        return btnReturnBike;
    }

    public void setBtnReturnBike(JButton btnReturnBike) {
        this.btnReturnBike = btnReturnBike;
    }

    public JButton getBtnSetBikeID() {
        return btnSetBikeID;
    }

    public void setBtnSetBikeID(JButton btnSetBikeID) {
        this.btnSetBikeID = btnSetBikeID;
    }

    public JButton getBtnSetCardNumber() {
        return btnSetCardNumber;
    }

    public void setBtnSetCardNumber(JButton btnSetCardNumber) {
        this.btnSetCardNumber = btnSetCardNumber;
    }

    public RentBike(int bike) {
        this.bike = bike;
    }

    public void init() {
        rentBikeController = new RentBikeController(this);

        JLabel jLabel3 = new JLabel();
        JLabel jLabel4 = new JLabel();
        JLabel lbID = new JLabel();
        JLabel lbType = new JLabel();
        JLabel lbCardNumber = new JLabel();
        JLabel lbLicense = new JLabel();
        JLabel lbStart = new JLabel();
        JLabel lbEnd = new JLabel();
        JLabel lbTime = new JLabel();
        JLabel lbPrice = new JLabel();
        JLabel lbBalance = new JLabel();

        stationInfor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Rent bike");
        jLabel3.setBounds(0, 0, 540, 50);
        stationInfor.add(jLabel3);

        lbID.setText("Bike ID:");
        lbID.setBounds(20, 50, 140, 40);
        lbID.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfor.add(lbID);

        tfID.setEditable(false);
        tfID.setBounds(180, 50, 250, 40);

        tfID.setEditable(true);
        stationInfor.add(tfID);

        Account userAcc = SQLDatabase.GetInstance().getUserAcc(MainController.GetInstance().getUserID());
        lbCardNumber.setText("Credit Card:");
        lbCardNumber.setBounds(20, 100, 140, 40);
        lbCardNumber.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfor.add(lbCardNumber);

        tfCardNumber.setEditable(false);
        tfCardNumber.setBounds(180, 100, 250, 40);
        tfCardNumber.setText(userAcc.getCardNumber());
        tfCardNumber.setEditable(true);
        stationInfor.add(tfCardNumber);

        btnSetBikeID.setText("Set");
        btnSetBikeID.setBounds(450, 50, 70, 40);
        stationInfor.add(btnSetBikeID);
        rentBikeController.btnSetBikeID();

        btnSetCardNumber.setText("Set");
        btnSetCardNumber.setBounds(450, 100, 70, 40);
        stationInfor.add(btnSetCardNumber);
        rentBikeController.btnSetCardNumber(userAcc.getAID());

        lbBalance.setText("Balance:");
        lbBalance.setBounds(20, 150, 140, 40);
        lbBalance.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfor.add(lbBalance);

        tfBalance.setEditable(false);
        tfBalance.setBounds(180, 150, 250, 40);
        tfBalance.setText("" + userAcc.getBalance());
        stationInfor.add(tfBalance);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Bike Information");
        jLabel4.setBounds(0, 195, 540, 50);
        stationInfor.add(jLabel4);

        lbType.setText("Bike Type:");
        lbType.setBounds(20, 250, 140, 40);
        lbType.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfor.add(lbType);

        tfType.setEditable(false);
        tfType.setBounds(180, 250, 330, 40);

        stationInfor.add(tfType);

        lbLicense.setText("License Plate:");
        lbLicense.setBounds(20, 310, 140, 40);
        lbLicense.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfor.add(lbLicense);

        tfLicense.setEditable(false);
        tfLicense.setBounds(180, 310, 330, 40);

        stationInfor.add(tfLicense);

        lbStart.setText("Start time:");
        lbStart.setBounds(20, 370, 140, 40);
        lbStart.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfor.add(lbStart);

        tfStart.setEditable(false);
        tfStart.setBounds(180, 370, 330, 40);
        stationInfor.add(tfStart);

        lbEnd.setText("End time:");
        lbEnd.setBounds(20, 430, 140, 40);
        lbEnd.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfor.add(lbEnd);

        tfEnd.setEditable(false);
        tfEnd.setBounds(180, 430, 330, 40);
        stationInfor.add(tfEnd);

        lbTime.setText("Total time:");
        lbTime.setBounds(20, 490, 140, 40);
        lbTime.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfor.add(lbTime);

        tfTime.setEditable(false);
        tfTime.setBounds(180, 490, 330, 40);
        stationInfor.add(tfTime);

        lbPrice.setText("Total payment:");
        lbPrice.setBounds(20, 550, 140, 40);
        lbPrice.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        stationInfor.add(lbPrice);

        tfPrice.setEditable(false);
        tfPrice.setBounds(180, 550, 330, 40);
        stationInfor.add(tfPrice);

        btnBack.setText("Back");
        btnBack.setBounds(45, 620, 120, 40);
        stationInfor.add(btnBack);
        rentBikeController.btnBack();
        if (this.bike != 0) {
            tfID.setText("" + this.bike);
        }

        rentBikeController.checkRenting();
        btnRentBike.setText("Rent");

        rentBikeController.btnRentBike(userAcc.getAID());
        btnRentBike.setBounds(210, 620, 120, 40);
        stationInfor.add(btnRentBike);

        btnReturnBike.setText("Return");

        rentBikeController.btnReturnBike();
        btnReturnBike.setBounds(375, 620, 120, 40);
        stationInfor.add(btnReturnBike);
    }

    public JPanel getStationInfor() {
        return stationInfor;
    }

    public void setStationInfor(JPanel stationInfor) {
        this.stationInfor = stationInfor;
    }
}
