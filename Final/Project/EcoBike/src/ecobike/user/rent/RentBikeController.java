package ecobike.user.rent;

import ecobike.bean.Bike;
import ecobike.bean.Rent;
import ecobike.database.SQLDatabase;
import ecobike.main.MainController;
import ecobike.user.payment.CalculationMethod;
import ecobike.user.payment.DepositType;
import ecobike.user.returns.ReturnBike;
import ecobike.user.returns.ReturnBikeController;
import ecobike.user.station.list.UserStation;
import ecobike.user.station.list.UserStationController;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RentBikeController {

    SQLDatabase sql = SQLDatabase.GetInstance();
    private RentBike rentBike;

    public RentBikeController() {
    }

    public RentBikeController(RentBike rentBike) {
        this.rentBike = rentBike;
    }

    public Bike getBike(JTextField tf) {
        Bike bike = null;
        if (!tf.getText().isEmpty()) {
            bike = sql.getABike(tf.getText());
        }
        return bike;
    }

    public int checkBalance(String type, int balance) {

        switch (type) {
            case Bike.ECO:
                if (balance > DepositType.ECOBIKE_DEPOSIT + 200000) {
                    return 1;
                } else {
                    return 0;
                }
            case Bike.NORMAL:
                if (balance > DepositType.NORMALBIKE_DEPOSIT + 200000) {
                    return 1;
                } else {
                    return 0;
                }
            case Bike.TWIN:
                if (balance > DepositType.TWINBIKE_DEPOSIT + 200000) {
                    return 1;
                } else {
                    return 0;
                }
            default:
                break;
        }
        return 0;
    }

    public void btnSetBikeID() {
        rentBike.getBtnSetBikeID().addActionListener(e -> {
            Bike bike = getBike(rentBike.getTfID());
            if (bike != null) {
                rentBike.getTfLicense().setText(bike.getLicensePlate());
                rentBike.getTfType().setText(bike.getClass().getSimpleName());

            } else {
                JOptionPane.showMessageDialog(null, "Invalid ID", "ERROR: ", JOptionPane.ERROR_MESSAGE);
            }

        });
    }

    public void btnSetCardNumber(int userID) {
        rentBike.getBtnSetCardNumber().addActionListener(e -> {

            String b = sql.getBalance(rentBike.getTfCardNumber().getText());
            if (b == "") {
                JOptionPane.showMessageDialog(null, "Invalid Card number", "ERROR: ", JOptionPane.ERROR_MESSAGE);
            } else {
                rentBike.getTfBalance().setText(b);
                sql.updataAccount(rentBike.getTfCardNumber().getText(), rentBike.getTfBalance().getText(), userID);
            }
        });
    }

    public void btnBack() {
        rentBike.getBtnBack().addActionListener(evt -> {
            UserStation userStation = new UserStation("Station");
            userStation.setListPaneController(new UserStationController(userStation));
            userStation.init();
            MainController.GetInstance().navigate(userStation.getListPane());
        });
    }

    public void checkRenting() {
        Rent renting = sql.getRecord(MainController.GetInstance().getUserID());
        if (renting != null) {
            rentBike.getBtnRentBike().setEnabled(false);
            rentBike.getTfID().setText("" + renting.getBikeId());
            rentBike.getTfID().setEditable(false);
            rentBike.getTfCardNumber().setEditable(false);

            Bike bike = sql.getBikeById(renting.getBikeId());
            rentBike.getTfLicense().setText(bike.getLicensePlate());
            String bikeType = bike.getClass().getSimpleName();
            rentBike.getTfType().setText(bikeType);

            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startime = renting.getStartTime();
            String endtime = dateFormat.format(date);
            rentBike.getTfEnd().setText(endtime);
            rentBike.getTfStart().setText(startime);

            Date start = CalculationMethod.stringToDate(startime);
            Date end = CalculationMethod.stringToDate(endtime);
            rentBike.getTfPrice().setText("" + CalculationMethod.calculate(bikeType, start, end));
            rentBike.getTfTime().setText(CalculationMethod.getTotalTime(start, end));
            rentBike.getBtnSetCardNumber().setEnabled(false);
            rentBike.getBtnSetBikeID().setEnabled(false);

        } else {
            rentBike.getBtnRentBike().setEnabled(true);
            rentBike.getBtnReturnBike().setEnabled(false);
        }

    }

    public void btnRentBike(int id) {
        rentBike.getBtnRentBike().addActionListener(e -> {
            if (rentBike.getTfID().getText().isEmpty() || rentBike.getTfCardNumber().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "BikeID or CardNumber can not be empty", "ERROR: ", JOptionPane.ERROR_MESSAGE);
            } else if (getBike(rentBike.getTfID()) == null) {
                JOptionPane.showMessageDialog(null, "Invalid ID", "ERROR: ", JOptionPane.ERROR_MESSAGE);
            } else if (sql.getBalance(rentBike.getTfCardNumber().getText()) == "") {
                JOptionPane.showMessageDialog(null, "Invalid Card number", "ERROR: ", JOptionPane.ERROR_MESSAGE);
            } else {
                Bike bike = sql.getBikeById(Integer.parseInt(rentBike.getTfID().getText()));
                String b = sql.getBalance(rentBike.getTfCardNumber().getText());
                int check = checkBalance(bike.getClass().getSimpleName(), Integer.parseInt(b));
                if (check == 0) {
                    JOptionPane.showMessageDialog(null, "Your Bank Account doesn't have enough money", "ERROR: ", JOptionPane.ERROR_MESSAGE);
                } else {

                    Date date = Calendar.getInstance().getTime();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Rent rent = new Rent(sql.countRecord() + 1, MainController.GetInstance().getUserID(), Integer.parseInt(rentBike.getTfID().getText()), dateFormat.format(date), null);
                    sql.addRecord(rent);
                    sql.refundDeposit(rent, 0);

                    JOptionPane.showMessageDialog(null, "Rent bike successful", "INFORMATION: ", JOptionPane.INFORMATION_MESSAGE);
                    MainController.GetInstance().setRecordID(sql.countRecord());
                    checkRenting();
                    rentBike.getBtnReturnBike().setEnabled(true);
                }
            }

        });
    }

    public void btnReturnBike() {
        rentBike.getBtnReturnBike().addActionListener(evt -> {
            ReturnBike returnBike = new ReturnBike("Select Station to return");
            returnBike.setListPaneController(new ReturnBikeController());
            returnBike.init();
            MainController.GetInstance().navigate(returnBike.getListPane());

        });
    }
}
