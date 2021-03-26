package ecobike.user.payment;

import ecobike.bean.Account;
import ecobike.bean.Bike;
import ecobike.bean.Rent;
import ecobike.bean.User;
import ecobike.database.SQLDatabase;
import ecobike.main.MainController;
import ecobike.user.returns.ReturnBike;
import ecobike.user.returns.ReturnBikeController;
import ecobike.user.station.list.UserStation;
import ecobike.user.station.list.UserStationController;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PaymentController {

    public JPanel paymentPane = new javax.swing.JPanel(null);
//    private SQLDatabase sql = new SQLDatabase();
    private int stationID;

    public PaymentController(int stationID) {
        this.stationID = stationID;
    }

    // Get data from current Rent pane and process
    public void initialize() {

        SQLDatabase sql = SQLDatabase.GetInstance();
        // Here needs data from BikeInformation Pane
        Rent currentRent = sql.getRent(MainController.GetInstance().getRecordID());
        Bike currentBike = sql.getBikeById(currentRent.getBikeId());
        Account currentAccount1 = sql.getUserAcc(currentRent.getAccountId());
        User currentUser = sql.getUserById(currentAccount1.getUID());

        JLabel jLabel3 = new javax.swing.JLabel();
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Payment");
        jLabel3.setBounds(0, 20, 540, 50);
        paymentPane.add(jLabel3);

        JLabel lbUserName = new javax.swing.JLabel();
        lbUserName.setText("User Name:");
        lbUserName.setBounds(20, 100, 140, 40);
        lbUserName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        paymentPane.add(lbUserName);
        JTextField userName = new JTextField();
        userName.setText(currentUser.getName());
        userName.setEditable(false);
        userName.setBounds(180, 100, 280, 40);
        userName.setFont(new Font("Dialog", Font.BOLD, 15));
        userName.setHorizontalAlignment(SwingConstants.CENTER);
        userName.setColumns(10);
        paymentPane.add(userName);

        JLabel lbBikeName = new javax.swing.JLabel();
        lbBikeName.setText("Bike Name:");
        lbBikeName.setBounds(20, 160, 140, 40);
        lbBikeName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        paymentPane.add(lbBikeName);
        JTextField bikeName = new JTextField();
        bikeName.setText(currentBike.getBikeName());
        bikeName.setEditable(false); // 40
        bikeName.setBounds(180, 160, 280, 40);
        bikeName.setFont(new Font("Dialog", Font.BOLD, 15));
        bikeName.setHorizontalAlignment(SwingConstants.CENTER);
        bikeName.setColumns(10);
        paymentPane.add(bikeName);

        JLabel lbType = new javax.swing.JLabel();
        lbType.setText("Type:");
        lbType.setBounds(20, 220, 140, 40);
        lbType.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        paymentPane.add(lbType);

        JTextField tfType = new JTextField();
        tfType.setEditable(false);
        tfType.setBounds(180, 220, 280, 40);
        tfType.setFont(new Font("Dialog", Font.BOLD, 15));
        tfType.setHorizontalAlignment(SwingConstants.CENTER);
        tfType.setText(currentBike.getClass().getSimpleName());
        paymentPane.add(tfType);

        JLabel lbStation = new javax.swing.JLabel();
        lbStation.setText("<html>Return to station:</html>");
        lbStation.setBounds(20, 280, 140, 40);
        lbStation.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        paymentPane.add(lbStation);

        JTextField tfStaiton = new JTextField();
        tfStaiton.setEditable(false);
        tfStaiton.setBounds(180, 280, 280, 40);
        tfStaiton.setFont(new Font("Dialog", Font.BOLD, 15));
        tfStaiton.setHorizontalAlignment(SwingConstants.CENTER);
        tfStaiton.setText("" + this.stationID);
        paymentPane.add(tfStaiton);

        JLabel lbStartTime = new JLabel();
        lbStartTime.setText("Start Time:");
        lbStartTime.setBounds(20, 340, 140, 40);
        lbStartTime.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        paymentPane.add(lbStartTime);

        JLabel lbEndTime = new JLabel();
        lbEndTime.setText("End Time:");
        lbEndTime.setBounds(20, 400, 140, 40);
        lbEndTime.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        paymentPane.add(lbEndTime);

        JTextField startTimeValue = new JTextField();
        startTimeValue.setEditable(false);
        startTimeValue.setBounds(180, 340, 280, 40);
        startTimeValue.setFont(new Font("Dialog", Font.BOLD, 15));
        startTimeValue.setHorizontalAlignment(SwingConstants.CENTER);
        paymentPane.add(startTimeValue);

        JTextField endTimeValue = new JTextField();
        endTimeValue.setEditable(false);
        endTimeValue.setBounds(180, 400, 280, 40);
        endTimeValue.setFont(new Font("Dialog", Font.BOLD, 15));
        endTimeValue.setHorizontalAlignment(SwingConstants.CENTER);
        endTimeValue.setColumns(10);
        paymentPane.add(endTimeValue);

        JLabel lbTotalTime = new JLabel();
        lbTotalTime.setText("Total Time");
        lbTotalTime.setBounds(20, 460, 140, 40);
        lbTotalTime.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        paymentPane.add(lbTotalTime);

        JTextField totalTimeValue = new JTextField();
        totalTimeValue.setEditable(false);
        totalTimeValue.setBounds(180, 460, 280, 40);
        totalTimeValue.setFont(new Font("Dialog", Font.BOLD, 15));
        totalTimeValue.setHorizontalAlignment(SwingConstants.CENTER);
        totalTimeValue.setColumns(10);
        paymentPane.add(totalTimeValue);

        JLabel lbTotalFee = new JLabel();
        lbTotalFee.setText("Total Fee");
        lbTotalFee.setBounds(20, 520, 140, 40);
        lbTotalFee.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        paymentPane.add(lbTotalFee);
        JTextField totalFeeValue = new JTextField();
        totalFeeValue.setEditable(false);
        totalFeeValue.setBounds(180, 520, 280, 40);
        totalFeeValue.setFont(new Font("Dialog", Font.BOLD, 15));
        totalFeeValue.setHorizontalAlignment(SwingConstants.CENTER);
        totalFeeValue.setColumns(10);
        paymentPane.add(totalFeeValue);

        Date date = Calendar.getInstance().getTime();
        java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startime = currentRent.getStartTime();
        String endtime = dateFormat.format(date);
        endTimeValue.setText(endtime);
        startTimeValue.setText(startime);

        Date start = CalculationMethod.stringToDate(startime);
        Date end = CalculationMethod.stringToDate(endtime);
        int fee = CalculationMethod.calculate(currentBike.getClass().getSimpleName(), start, end);
        totalFeeValue.setText("" + fee);
        totalTimeValue.setText(CalculationMethod.getTotalTime(start, end));
        // Pay button
        JButton payButton = new JButton("Pay");
        payButton.setBounds(320, 600, 140, 40);
        payButton.setFont(new Font("Dialog", Font.BOLD, 15));
        paymentPane.add(payButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 600, 140, 40);
        backButton.setFont(new Font("Dialog", Font.BOLD, 15));
        paymentPane.add(backButton);

        backButton.addActionListener(e -> {
            ReturnBike returnBike = new ReturnBike("Select Station to return");
            returnBike.setListPaneController(new ReturnBikeController());
            returnBike.init();
            MainController.GetInstance().navigate(returnBike.getListPane());
        });
        // According to current Rent
        payButton.addActionListener(e -> {
            Account currentAccount = sql.refundDeposit(currentRent, 1);
            sql.updateBalance(currentRent, fee);
            sql.updateBikeInStation(currentBike.getBikeID(), this.stationID);

            JFrame successfulFrame = new JFrame("Successful");
            successfulFrame.setBounds(830, 300, 480, 400);
            successfulFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            successfulFrame.getContentPane().setLayout(null);

            JLabel userId = new JLabel("User ID");
            userId.setBounds(20, 40, 140, 40);
            userId.setFont(new Font("Dialog", Font.BOLD, 15));
            successfulFrame.getContentPane().add(userId);

            JLabel balance = new JLabel("Balance"); // 40
            balance.setBounds(20, 220, 140, 40);
            balance.setFont(new Font("Dialog", Font.BOLD, 15));
            successfulFrame.getContentPane().add(balance);
            JLabel userName1 = new JLabel("Name"); // 40
            userName1.setBounds(20, 100, 140, 40);
            userName1.setFont(new Font("Dialog", Font.BOLD, 15));
            successfulFrame.getContentPane().add(userName1);

            JLabel cardNumber = new JLabel("Card Number"); // 40
            cardNumber.setBounds(20, 160, 140, 40);
            cardNumber.setFont(new Font("Dialog", Font.BOLD, 15));
            successfulFrame.getContentPane().add(cardNumber);

            JTextField userIdValue = new JTextField();

            userIdValue.setText(Integer.toString(currentAccount.getUID()));
            userIdValue.setEditable(false);
            userIdValue.setBounds(180, 40, 280, 40);
            userIdValue.setFont(new Font("", Font.BOLD, 15));
            userIdValue.setHorizontalAlignment(SwingConstants.CENTER);
            userIdValue.setColumns(10);
            successfulFrame.getContentPane().add(userIdValue);

            JTextField userNameValue = new JTextField();
            userNameValue.setText(Integer.toString(currentAccount.getAID()));
            userNameValue.setEditable(false);
            userNameValue.setBounds(180, 100, 280, 40);
            userNameValue.setFont(new Font("", Font.BOLD, 15));
            userNameValue.setHorizontalAlignment(SwingConstants.CENTER);
            userNameValue.setColumns(10);
            successfulFrame.getContentPane().add(userNameValue);

            JTextField cardValue = new JTextField();
            cardValue.setText(currentAccount.getCardNumber());
            cardValue.setEditable(false);
            cardValue.setBounds(180, 160, 280, 40);
            cardValue.setFont(new Font("", Font.BOLD, 15));
            cardValue.setHorizontalAlignment(SwingConstants.CENTER);
            cardValue.setColumns(10);
            successfulFrame.getContentPane().add(cardValue);

            JTextField balanceValue = new JTextField();
            balanceValue.setText("" + (currentAccount.getBalance() - fee));
            balanceValue.setEditable(false); // 40
            balanceValue.setBounds(180, 220, 280, 40);
            balanceValue.setFont(new Font("", Font.BOLD, 15));
            balanceValue.setHorizontalAlignment(SwingConstants.CENTER);
            balanceValue.setColumns(10);
            successfulFrame.getContentPane().add(balanceValue);

            sql.updateRecord(MainController.GetInstance().getRecordID(), endtime);

            successfulFrame.setVisible(true);
            payButton.setEnabled(false);
            successfulFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    UserStation userStation = new UserStation("Station");
                    payButton.setEnabled(true);
                    userStation.setListPaneController(new UserStationController(userStation));
                    userStation.init();
                    MainController.GetInstance().navigate(userStation.getListPane());
                }
            });

        }
        );

    }

    public JPanel getPaymentPane() {
        return paymentPane;
    }

    public void setPaymentPane(JPanel paymentPane) {
        this.paymentPane = paymentPane;
    }
}
